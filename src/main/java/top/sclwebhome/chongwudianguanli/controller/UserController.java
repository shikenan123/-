package top.sclwebhome.chongwudianguanli.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import top.sclwebhome.chongwudianguanli.pojo.Log;
import top.sclwebhome.chongwudianguanli.pojo.User;
import top.sclwebhome.chongwudianguanli.services.LogServices;
import top.sclwebhome.chongwudianguanli.services.UserServices;

@Controller
public class UserController {
    @Resource
    private UserServices userServices;
    @Resource
    private LogServices logServices;
    
    
    /**
     * 已完成的功能
     * @return
     */
    @RequestMapping("/")
    public String hello(){
        return "login1";
    }
   
    @RequestMapping("/ok1")
    public void ok(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.sendRedirect("ok1.html");
    }
    @RequestMapping(value = "/error1")
    public String error1() throws IOException {
        return "error1";
    }
    @RequestMapping("/userupdatepasswordmiddle")
    public String userupdatepasswordmiddle(){
        return "userupdatepasswordmiddle";
    }
    @RequestMapping(value = "/userupdatepassword")
    public void userupdatepassword(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
        String yuan=request.getParameter("yuan");
        String xin1=request.getParameter("xin1");
        String xin2=request.getParameter("xin2");
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if (user!=null){
            if (user.getPassword().equals(yuan)){
                if (xin1.equals(xin2)){
                    user.setPassword(xin1);
                    userServices.passwordUpdate(user);
                    response.sendRedirect("ok.html");
                }else {
                    response.sendRedirect("error1");
                }
            }else {
                response.sendRedirect("error1");
            }
        }else {
            response.sendRedirect("error1");
        }
    }
    @RequestMapping(value = "/usergetuser",method = RequestMethod.GET)
    public String getuser(HttpServletRequest request, HttpServletResponse response,Model model){
        HttpSession session= request.getSession();
        if (session.getAttribute("user")!=null){
            model.addAttribute("user",session.getAttribute("user"));
            return "usergetuser1";
        }else {
            return "error1";
        }
    }
    @RequestMapping(value = "/usercallus",method = RequestMethod.GET)
    public String callus() throws IOException {
        return "usercallus";
    }
    @RequestMapping(value = "/useraboutus",method = RequestMethod.GET)
    public String aboutus() throws IOException {
        return "useraboutus";
    }
    @RequestMapping(value = "/breederhome",method = RequestMethod.GET)
    public String breederhome(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "breederhome";
    }
    @RequestMapping(value = "/userlogout",method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("ok1.html");
    }
    @RequestMapping(value = "/loginone")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String UID=request.getParameter("UID");
            String password=request.getParameter("password");
            User user= userServices.login(Integer.parseInt(UID));
            if (user!=null){
                if (user.getPassword().equals(password)){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println(user.getClassify() + user.getUsername() + "在" + df.format(new Date()) + "成功登陆了系统"+ "\r\n");
                    if (user.getClassify() == 7) {
                        System.out.println("登录成功");
                        response.sendRedirect("adminhome");
                    } else if (user.getClassify() == 8) {
                        System.out.println("登录成功");
                        response.sendRedirect("clerkhome");
                    } else if (user.getClassify() == 9) {
                        System.out.println("登录成功");
                        response.sendRedirect("breederhome");
                    }
                }else {
                    response.sendRedirect("error1");
                }
            }else {
                response.sendRedirect("error1");
            }
        }catch (Exception e){
            response.sendRedirect("error1");
        }
    }
    @RequestMapping("/adminaddusermiddle")
    public String adminaddusermiddle(HttpServletRequest request, HttpServletResponse response,Model model){
        return "adminaddusermiddle";
    }
   @RequestMapping("/adminadduser")
   public void adminadduser(HttpServletRequest request, HttpServletResponse response) throws IOException {
       try {
           HttpSession session=request.getSession();
           User user1= (User) session.getAttribute("user");
           Integer userID= user1.getUID();
           String UID=request.getParameter("UID");
           String username=request.getParameter("username");
           String age=request.getParameter("age");
           String sex=request.getParameter("sex");
           String classify=request.getParameter("classify");
           String phone=request.getParameter("phone");
           Date date = new Date();
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String created = df.format(date);
           String password =request.getParameter("password");
           User user=new User();
           user.setUID(Integer.parseInt(UID));
           user.setAge(Integer.parseInt(age));
           user.setUsername(username);
           user.setSex(sex);
           user.setClassify(Integer.parseInt(classify));
           user.setPhone(phone);
           user.setPassword(password);
           user.setCreated(created);
           userServices.adduser(user,userID);
           response.sendRedirect("ok1.html");
       }catch (Exception e){
           response.sendRedirect("error1.html");
       }
   }
   @RequestMapping(value = "/adminupdateusermiddle",method = RequestMethod.GET)
   public String showupdate(HttpServletRequest request, HttpServletResponse response,Model model){
   String UID=request.getParameter("UID");
   User user=userServices.selectbyuid(Integer.parseInt(UID));
   model.addAttribute("user",user);
   return "adminupdateusermiddle";
}
@RequestMapping(value = "/adminupdateuser")
public String update(HttpServletRequest request, HttpServletResponse response){
  try {
	  HttpSession session=request.getSession();
      User user1= (User) session.getAttribute("user");
      Integer userID= user1.getUID();
      String UID=request.getParameter("UID");
      String username=request.getParameter("username");
      String age=request.getParameter("age");
      String sex=request.getParameter("sex");
      String classify=request.getParameter("classify");
      String phone=request.getParameter("phone");
      Date date = new Date();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String created = df.format(date);
      String password =request.getParameter("password");
      User user=new User();
      user.setUID(Integer.parseInt(UID));
      user.setAge(Integer.parseInt(age));
      user.setUsername(username);
      user.setSex(sex);
      user.setClassify(Integer.parseInt(classify));
      user.setPhone(phone);
      user.setPassword(password);
      user.setCreated(created);
      userServices.updateByUid(user,userID);
      return "ok1";
  }catch (Exception e){
	  e.printStackTrace();
      return "error1";
  }
}
@RequestMapping(value = "/adminselectcaozuo",method = RequestMethod.GET)
public String selectbyuserid(HttpServletRequest request, HttpServletResponse response,Model model){
    String kwd=request.getParameter("keyword");
    if (kwd!=""&kwd!=null){
        int UID=Integer.parseInt(kwd);
        List<Log> selectbyuserid=logServices.selectbyuserid(UID);
        model.addAttribute("selectlist",selectbyuserid);
    }else {
        List<Log> selectbyuserid=logServices.selectall();
        model.addAttribute("selectlist",selectbyuserid);
    }
    return "adminlistlog";
}
    
    
    
    
    
    
    
    @RequestMapping(value = "/clerkhome",method = RequestMethod.GET)
    public String clerkhome(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
        HttpSession session= request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "clerkhome";
    }
    @RequestMapping(value = "/adminhome",method = RequestMethod.GET)
    public String adminhome(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
        HttpSession session= request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "adminhome";
    }
    
    
    @RequestMapping(value = "/adminselectuser",method = RequestMethod.GET)
    public String adminselectuser(HttpServletRequest request, HttpServletResponse response,Model model){
        String kwd=request.getParameter("keyword");
        if (kwd!=""&kwd!=null){
            int UID=Integer.parseInt(kwd);
            List<User> selectlist=userServices.selectbykwdall(UID);
            model.addAttribute("selectlist",selectlist);
        }else {
            List<User> selectlist=userServices.selectall();
            model.addAttribute("selectlist",selectlist);
        }
        return "adminlistuser";
    }
    
    @RequestMapping(value = "/admindeleteuser",method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
            HttpSession session=request.getSession();
           User user= (User) session.getAttribute("user");
            Integer userID= user.getUID();
            String UID=request.getParameter("UID");
            int IUID= Integer.parseInt(UID);
            try {
                userServices.deleteByUid(IUID,userID);
                return "ok1";
            }catch (Exception e){
                return "error1";
            }
    }
    
    
}