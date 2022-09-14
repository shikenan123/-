package top.sclwebhome.chongwudianguanli.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import top.sclwebhome.chongwudianguanli.XssHttpServletRequestWrapper;
import top.sclwebhome.chongwudianguanli.pojo.User;

@WebFilter(urlPatterns = {"/adminselectuser","/adminhome","/adminselectcaozuo","/admindeleteuser","/adminupdateusermiddle","/adminupdateuser","/adminaddusermiddle","/adminadduser","/adminaddpet1","/adminaddpetupdate","/adminselectpet","/adminshowupdatepet","/adminupdatepet","/adminupdateproblemmiddle","/adminupdateproblem","/adminselectproblem"})
public class AdminFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {
   
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //注入xss过滤器实例
        XssHttpServletRequestWrapper reqW = new XssHttpServletRequestWrapper(request);
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            if (user.getClassify() != 7) {
                System.out.println("非管理员，拦截");
                response.sendRedirect("error");
            } else {
                System.out.println("登陆过，放行");
                
                chain.doFilter(reqW, resp);
            }
        } catch (Exception e) {
            try {
                response.sendRedirect("error1");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}