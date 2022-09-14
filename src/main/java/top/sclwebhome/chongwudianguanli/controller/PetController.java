package top.sclwebhome.chongwudianguanli.controller;

import java.io.IOException;
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

import top.sclwebhome.chongwudianguanli.pojo.Pet;
import top.sclwebhome.chongwudianguanli.pojo.User;
import top.sclwebhome.chongwudianguanli.services.LogServices;
import top.sclwebhome.chongwudianguanli.services.PetServices;

@Controller
public class PetController {
    @Resource
    private LogServices logServices;
    @Resource
    private PetServices petServices;
@RequestMapping(value = "/deletepet",method = RequestMethod.GET)
public String deletepet(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
    HttpSession session=req.getSession();
    User user1= (User) session.getAttribute("user");
    Integer userID= user1.getUID();
    req.setCharacterEncoding("utf-8");
    String UID=req.getParameter("UID");
    petServices.deleteByUid(Integer.valueOf(UID),userID);
    return "ok1";
}
    @RequestMapping("/adminaddpetl")
    public String adminaddpet(HttpServletResponse response) throws IOException {
        return "adminaddpet";
    }
    @RequestMapping("/adminaddpetupdate")
    public String adminaddpetupdate(HttpServletRequest req,HttpServletResponse resp)  {
    	try {
    		  HttpSession session=req.getSession();
    	        User user1= (User) session.getAttribute("user");
    	        Integer userID= user1.getUID();
    	        String UID=req.getParameter("UID");
    	        String nickname=req.getParameter("nickname");
    	        String age=req.getParameter("age");
    	        String sex=req.getParameter("sex");
    	        String classify=req.getParameter("classify");
    	        String vaccine=req.getParameter("vaccine");
    	        String aggressivity=req.getParameter("aggressivity");
    	        String likeq=req.getParameter("likeq");
    	        String purchasenotes=req.getParameter("purchasenotes");
    	        String mattersneedattention=req.getParameter("mattersneedattention");
    	        String offeringprice=req.getParameter("offeringprice");
    	        String anamnesis=req.getParameter("anamnesis");
    	        String drill=req.getParameter("drill");
    	        String physicalcondition=req.getParameter("physicalcondition");
    	        String commentone=req.getParameter("commentone");
    	        String record=req.getParameter("record");
    	        String dietarystructure=req.getParameter("dietarystructure");
    	        String ifsell=req.getParameter("ifsell");
    	        String commentthree=req.getParameter("commentthree");
    	        Date date = new Date();
    	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	        String times = df.format(date);
    	        Pet pet=new Pet(null,Integer.parseInt(UID),nickname,Integer.parseInt(age),sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,ifsell,times,commentthree);
    	        petServices.addPet(pet,userID);
    	        return "ok1";
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
		return "error1";
      
    }
    @RequestMapping(value = "/adminselectpet",method = RequestMethod.GET)
    public String adminselectpet(HttpServletRequest req, HttpServletResponse resp,Model model){
        String kwd=req.getParameter("keywordpet");
        if (kwd!=""&kwd!=null) {
            List<Pet> selectlistp= petServices.selectbykwdall(Integer.parseInt(kwd));
            model.addAttribute("selectlistp",selectlistp);
        }else {
            List<Pet> selectlistp= petServices.findAll();
            model.addAttribute("selectlistp",selectlistp);
        }
        return "adminlistpet";
    }
    @RequestMapping(value = "/adminshowupdatepet",method = RequestMethod.GET)
    public String showupdatepet(HttpServletRequest req, HttpServletResponse resp, Model model){
        String UID=req.getParameter("UID");
        Pet pet=petServices.selectbyuid(Integer.valueOf(UID));
        model.addAttribute("petadmin",pet);
        return "adminupdatepetmiddle";
    }
    @RequestMapping("/adminupdatepet")
    public String updatepet(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session=req.getSession();
        User user1= (User) session.getAttribute("user");
        Integer userID= user1.getUID();
        String UID=req.getParameter("UID");
        String nickname=req.getParameter("nickname");
        String age=req.getParameter("age");
        String sex=req.getParameter("sex");
        String classify=req.getParameter("classify");
        String vaccine=req.getParameter("vaccine");
        String aggressivity=req.getParameter("aggressivity");
        String likeq=req.getParameter("likeq");
        String purchasenotes=req.getParameter("purchasenotes");
        String mattersneedattention=req.getParameter("mattersneedattention");
        String offeringprice=req.getParameter("offeringprice");
        String anamnesis=req.getParameter("anamnesis");
        String drill=req.getParameter("drill");
        String physicalcondition=req.getParameter("physicalcondition");
        String commentone=req.getParameter("commentone");
        String record=req.getParameter("record");
        String dietarystructure=req.getParameter("dietarystructure");
        String ifsell=req.getParameter("ifsell");
        String commentthree=req.getParameter("commentthree");
       
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = df.format(date);
        Pet pet=new Pet(null,Integer.parseInt(UID),nickname,Integer.parseInt(age),sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,ifsell,times,commentthree);
        petServices.updateByUID(pet,userID);
        return "ok1";
    }
@RequestMapping(value = "/breederselectpet",method = RequestMethod.GET)
public String breederselectpet(HttpServletRequest req, HttpServletResponse resp,Model model){
    String kwd=req.getParameter("keywordpet");
    if (kwd!=""&kwd!=null) {
        List<Pet> selectlistp= petServices.selectbykwdall(Integer.parseInt(kwd));
        model.addAttribute("selectlistp",selectlistp);
    }else {
        List<Pet> selectlistp= petServices.findAll();
        model.addAttribute("selectlistp",selectlistp);
    }
    return "breederlistpet";
}
    @RequestMapping(value = "/breederupdatepetmiddel",method = RequestMethod.GET)
    public String showupdatepetnosell(HttpServletRequest req, HttpServletResponse resp, Model model){
        String UID=req.getParameter("UID");
        Pet pet=petServices.selectbyuid(Integer.valueOf(UID));
        model.addAttribute("petadmin",pet);
        return "breederupdatepetmiddel";
    }
    @RequestMapping("/breederupdatepet")
    public String updatepetnosell(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session=req.getSession();
        User user1= (User) session.getAttribute("user");
        Integer userID= user1.getUID();
        String UID=req.getParameter("UID");
        String nickname=req.getParameter("nickname");
        String age=req.getParameter("age");
        String sex=req.getParameter("sex");
        String classify=req.getParameter("classify");
        String vaccine=req.getParameter("vaccine");
        String aggressivity=req.getParameter("aggressivity");
        String likeq=req.getParameter("likeq");
        String purchasenotes=req.getParameter("purchasenotes");
        String mattersneedattention=req.getParameter("mattersneedattention");
        String offeringprice=req.getParameter("offeringprice");
        String anamnesis=req.getParameter("anamnesis");
        String drill=req.getParameter("drill");
        String physicalcondition=req.getParameter("physicalcondition");
        String commentone=req.getParameter("commentone");
        String record=req.getParameter("record");
        String dietarystructure=req.getParameter("dietarystructure");
        String commentthree=req.getParameter("commentthree");
       
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = df.format(date);
        Pet pet=new Pet(null,Integer.parseInt(UID),nickname,Integer.parseInt(age),sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,"未出售",times,commentthree);
        petServices.updateByUID(pet,userID);
        return "ok1";
    }
@RequestMapping(value = "/clerkselectpet")
public String clerkupdatepetmiddel(HttpServletRequest req, HttpServletResponse resp,Model model){
    String kwd=req.getParameter("keywordpet");
    if (kwd!=""&kwd!=null){
        int UID=Integer.parseInt(kwd);
        List<Pet> selectlistph= petServices.selectbykwdhf(UID);
        model.addAttribute("selectlistph",selectlistph);
    }else {
        List<Pet> selectlistph= petServices.findAll();
        model.addAttribute("selectlistph",selectlistph);
    }
    return "clerklistpet";
}
    @RequestMapping(value = "/clerkupdatepetmiddle")
   public String showpet(HttpServletRequest req, HttpServletResponse resp,Model model){
        String UID=req.getParameter("UID");
        Pet pet =petServices.selectbyuid(Integer.valueOf(UID));
        model.addAttribute("pet",pet);
        return "clerkupdatepetmiddle";
    }
    @RequestMapping(value = "/clerkupdatepet")
    public String sellpet(HttpServletRequest req, HttpServletResponse resp,Model model) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
        User user1= (User) session.getAttribute("user");
        Integer userID= user1.getUID();
        String UID=req.getParameter("UID");
        String nickname=req.getParameter("nickname");
        String age=req.getParameter("age");
        String sex=req.getParameter("sex");
        String classify=req.getParameter("classify");
        String vaccine=req.getParameter("vaccine");
        String aggressivity=req.getParameter("aggressivity");
        String likeq=req.getParameter("likeq");
        String purchasenotes=req.getParameter("purchasenotes");
        String mattersneedattention=req.getParameter("mattersneedattention");
        String offeringprice=req.getParameter("offeringprice");
        String anamnesis=req.getParameter("anamnesis");
        String drill=req.getParameter("drill");
        String physicalcondition=req.getParameter("physicalcondition");
        String commentone=req.getParameter("commentone");
        String record=req.getParameter("record");
        String dietarystructure=req.getParameter("dietarystructure");
        String commentthree=req.getParameter("commentthree");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = df.format(date);
        Pet pet=new Pet(null,Integer.parseInt(UID),nickname,Integer.parseInt(age),sex,classify,vaccine,aggressivity,likeq,purchasenotes,mattersneedattention,offeringprice,anamnesis,drill,physicalcondition,commentone,record,dietarystructure,"已出售",times,commentthree);
        petServices.updateByUID(pet,userID);
        return "ok1";
    }
}