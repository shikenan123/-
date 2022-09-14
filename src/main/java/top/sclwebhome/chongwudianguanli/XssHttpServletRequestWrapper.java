package top.sclwebhome.chongwudianguanli;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
 
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;
 


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private  final Logger log = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);
    /**
     * post请求体
     */
    private byte[] body;
 
    /**
     * 是否是文件上传
     */
    private boolean fileUpload = false;
 
    /**
     * sql注入正则
     */
    private static String badStrReg =
            "\\b(and|or)\\b.{1,6}?(=|>|<|\\bin\\b|\\blike\\b)|\\/\\*.+?\\*\\/|<\\s*script\\b|\\bEXEC\\b|UNION.+?SELECT|UPDATE.+?SET|INSERT\\s+INTO.+?VALUES|(SELECT|DELETE).+?FROM|(CREATE|ALTER|DROP|TRUNCATE)\\s+(TABLE|DATABASE)";
 
    /**
     * xss脚本正则
     */
    private final static Pattern[] scriptPatterns = {
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };
 
    public XssHttpServletRequestWrapper() {
        super(null);
    }
 
    /**
     * 构造函数 - 获取post请求体
     * @param httpservletrequest
     * @throws IOException
     */
    public XssHttpServletRequestWrapper(HttpServletRequest httpservletrequest) throws IOException {
        super(httpservletrequest);
        String sessionStream = getBodyString(httpservletrequest);
        body = sessionStream.getBytes(StandardCharsets.UTF_8);
    }
 
    /**
     * 读取post请求体
     * @param httpservletrequest
     * @return
     * @throws IOException
     */
    private String getBodyString(HttpServletRequest httpservletrequest) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream ins = httpservletrequest.getInputStream();
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(httpservletrequest);
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(httpservletrequest.getSession().getServletContext());
        boolean isMultipart = commonsMultipartResolver.isMultipart(httpservletrequest);
        if (isMultipartContent || isMultipart) {
            fileUpload = true;
        }
        try (BufferedReader isr = new BufferedReader(new InputStreamReader(ins, StandardCharsets.UTF_8));) {
            String line = "";
            while ((line = isr.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw e;
        }
        return sb.toString();
    }
 
    /**
     * 过滤springmvc中的 @RequestParam 注解中的参数
     * @param s
     * @return
     */
    @Override
    public String[] getParameterValues(String s) {
        String[] str = super.getParameterValues(s);
        if (str == null) {
            return null;
        }
        int i = str.length;
        String[] as1 = new String[i];
        for (int j = 0; j < i; j++) {
            as1[j] = cleanXSS(cleanSQLInject(str[j]));
        }
        log.info("XssHttpServletRequestWrapper净化后的请求为：========== {}", Arrays.toString(as1));
        return as1;
    }
 
    /**
     * 过滤request.getParameter的参数
     * @param s
     * @return
     */
    @Override
    public String getParameter(String s) {
        String s1 = super.getParameter(s);
        if (s1 == null) {
            return null;
        } else {
            String s2 = cleanXSS(cleanSQLInject(s1));
            log.info("XssHttpServletRequestWrapper净化后的请求为：========== {}",  s2);
            return s2;
        }
    }
 
 
    /**
     * 过滤请求体 json 格式的
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 非文件上传进行过滤
        if (!fileUpload) {
            // 获取body中的请求参数
            JSONObject json = JSONObject.parseObject(new String(body));
            // 校验并过滤xss攻击和sql注入
            for (String k : json.keySet()) {
                cleanSQLInject(cleanXSS(json.getString(k)));
            }
        }
        // 将请求体参数流转 -- 流读取一次就会消失,所以我们事先读取之后就存在byte数组里边方便流转
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
 
            @Override
            public int read() throws IOException {
                return bais.read();
            }
 
            @Override
            public boolean isFinished() {
                return false;
            }
 
            @Override
            public boolean isReady() {
                return false;
            }
 
            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }
 
    /**
     * 清除xss
     * @param src 单个参数
     * @return
     */
    public String cleanXSS(String src) {
        String temp = src;
        // 校验xss脚本
        for (Pattern pattern : scriptPatterns) {
            temp = pattern.matcher(temp).replaceAll("");
        }
        // 校验xss特殊字符
        temp = temp.replaceAll("\0|\n|\r", "");
        temp = temp.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
 
        if (!temp.equals(src)) {
 
            log.error("xss攻击检查：参数含有非法攻击字符，已禁止继续访问！！");
            log.error("原始输入信息-->" + temp);
 
            throw new RuntimeException("xss攻击检查：参数含有非法攻击字符，已禁止继续访问！！");
        }
 
        return src;
    }
 
    /**
     * 过滤sql注入 -- 需要增加通配，过滤大小写组合
     * @param src 单个参数值
     * @return
     */
    public String cleanSQLInject(String src) {
        // 非法sql注入正则
        Pattern sqlPattern = Pattern.compile(badStrReg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(src.toLowerCase()).find()) {
            log.error("sql注入检查：输入信息存在SQL攻击！");
            throw new RuntimeException("sql注入检查：参数含有非法攻击字符，已禁止继续访问！！");
        }
        return src;
    }
}