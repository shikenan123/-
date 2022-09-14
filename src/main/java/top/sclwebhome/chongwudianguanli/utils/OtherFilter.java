package top.sclwebhome.chongwudianguanli.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import top.sclwebhome.chongwudianguanli.XssHttpServletRequestWrapper;
import top.sclwebhome.chongwudianguanli.pojo.User;

@WebFilter(urlPatterns = {"/ok1","/error1","/userupdatepasswordmiddle","/userupdatepassword","/usergetuser","/usercallus","/useraboutus","/breederhome","/clerkhome","/userlogout","/deletepet","/breederselectpet","/breederupdatepetmiddel","/breederupdatepet","/clerkselectpet","/clerkupdatepetmiddle","/clerkupdatepet","/deleteproblem","/useraddproblemmiddle","/useraddproblem","/myproblem","/userseeproblem"})
public class OtherFilter implements Filter {
	
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session=request.getSession();
      //注入xss过滤器实例
        XssHttpServletRequestWrapper reqW = new XssHttpServletRequestWrapper(request);
        User user=(User) session.getAttribute("user");
        try{
            if (user==null){
                System.out.println("拦截");
                response.sendRedirect("error1.html");
            }else {
                System.out.println("登陆过，放行");
                chain.doFilter(reqW, resp);
            }
        }catch (Exception e){
            try {
                response.sendRedirect("error1.html");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
