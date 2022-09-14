package top.sclwebhome.chongwudianguanli.controller;

import java.io.UnsupportedEncodingException;
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

import top.sclwebhome.chongwudianguanli.pojo.Problem;
import top.sclwebhome.chongwudianguanli.pojo.User;
import top.sclwebhome.chongwudianguanli.services.ProblemsServices;

@Controller
public class ProblemsController {
    @Resource
    private ProblemsServices problemsServices;
@RequestMapping("/adminupdateproblemmiddle")
public String showupdateproblem(HttpServletRequest req, HttpServletResponse resp,Model model){
    String ID=req.getParameter("ID");
    Problem problem=problemsServices.selectbyid(Integer.valueOf(ID));
    model.addAttribute("problem",problem);
    return "adminupdateproblemmiddle";
}
@RequestMapping("/adminupdateproblem")
public String updateproblem(HttpServletRequest req, HttpServletResponse resp,Model model){
    String ID=req.getParameter("ID");
    String title=req.getParameter("title");
    String body=req.getParameter("body");
    String timess=req.getParameter("timess");
    String name=req.getParameter("name");
    String isi=req.getParameter("isi");
    String c=req.getParameter("c");
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String times = df.format(date);
    Problem problem=new Problem(Integer.parseInt(ID),title,body,name,c,isi+times,timess);
    problemsServices.updateByID(problem);
    return "ok1";
}
    @RequestMapping("/adminselectproblem")
    public String selectallproblem(HttpServletRequest req, HttpServletResponse resp, Model model) {
        String kwd = req.getParameter("keywordproblem");
        if (kwd != "" & kwd != null) {
            int ID = Integer.parseInt(kwd);
            List<Problem> selectlistpr =problemsServices.selectbykwdall(Integer.valueOf(kwd));
            model.addAttribute("selectlistpr",selectlistpr);
        }else {
            List<Problem> selectlistpr =problemsServices.findAll();
            model.addAttribute("selectlistpr",selectlistpr);
        }
        return "adminlistproblem";
    }
    @RequestMapping(value = "/deleteproblem",method = RequestMethod.GET)
    public String deleteproblem(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        String ID=req.getParameter("ID");
        problemsServices.deleteByid(Integer.valueOf(ID));
        return "ok1";
    }
    @RequestMapping("/useraddproblemmiddle")
    public String addallproblem(){
    return "useraddproblemmiddle";
    }
    @RequestMapping("/useraddproblem")
    public String addproblem(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        String name=user.getUsername();
        String title=req.getParameter("title");
        String body=req.getParameter("body");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timess = df.format(date);
        Problem problem=new Problem(0,title,body,name,"","",timess);
        problemsServices.addproblem(problem);
        return "ok1";
    }
    @RequestMapping("/myproblem")
    public String myproblem(HttpServletRequest req, HttpServletResponse resp,Model model){
        HttpSession session=req.getSession();
        User user= (User) session.getAttribute("user");
        List<Problem> selectlistpoo=problemsServices.findAllbyname(user.getUsername());
        model.addAttribute("selectlistpoo",selectlistpoo);
        return "usermyProblem";
    }
    @RequestMapping("/userseeproblem")
    public String seproblem(HttpServletRequest req, HttpServletResponse resp,Model model){
        String ID=req.getParameter("ID");
        int id=Integer.parseInt(ID);
       Problem problem=problemsServices.selectbyid(id);
        model.addAttribute("problem",problem);
        return "userseeproblem";
    }
}