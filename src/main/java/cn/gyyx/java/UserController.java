package cn.gyyx.java;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.fabric.Response;

import cn.gyyx.java.model.User;

@Controller
public class UserController {

	 @ModelAttribute("user")  
     public User addUser(HttpServletRequest request, HttpServletResponse response) {  
        return new User(request.getParameter("name"),request.getParameter("password"));  
     }  
	 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale,Model model) {
		model.addAttribute("message", "");
		return "login";
	}
	
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public String loginOut(Model model,HttpServletResponse response,HttpSession session) {
		model.addAttribute("message", "");
		session.setAttribute("User", null);
		
		return "login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user,Model model, HttpServletResponse response,HttpSession session) {		
		
		if(user.name.equals("xuguochun")&&user.password.equals("123456"))
		{
			user.message="用户名或密码正确";
			try {
				session.setAttribute("User", user.name);
				response.sendRedirect("/java");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			user.message="用户名或密码错误!";
		}
		
		model.addAttribute("message", user.message);
		
		return "login";
	}
	

}
