package cn.gyyx.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gyyx.java.bll.GameBLL;
import cn.gyyx.java.bll.ServerBLL;
import cn.gyyx.java.entity.GameInfo;
import cn.gyyx.java.entity.ServerInfo;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private GameBLL gamebll;
	private ServerBLL serverbll;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpSession session,HttpServletResponse response) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String account=(String) session.getAttribute("User");
		if(account==null)
		{
			
			response.sendRedirect("/java/login");
		}
		
		model.addAttribute("account", account);
		
		gamebll = new GameBLL();
		List<GameInfo> list = gamebll.queryList();

		model.addAttribute("gameList", list);
	
		return "home";
	}


	/*
	 * 根据游戏ID获取区服列表
	 */
	@RequestMapping(value = "/getServerList", method = RequestMethod.GET)
	public @ResponseBody List<ServerInfo> getServerList(
			@RequestParam("gameId") int gameId) {
		serverbll = new ServerBLL();
		List<ServerInfo> list = serverbll.queryListByGameId(gameId);
		return list;
	}
}


