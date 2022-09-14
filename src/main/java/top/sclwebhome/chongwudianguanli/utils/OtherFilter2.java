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

@WebFilter(urlPatterns = {"/","/loginone"})
public class OtherFilter2 implements Filter {
	
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session=request.getSession();
      //注入xss过滤器实例
        XssHttpServletRequestWrapper reqW = new XssHttpServletRequestWrapper(request);
       
                System.out.println("注入检测");
                chain.doFilter(reqW, resp);
            }
      
        
    }

