package com.tokbox.poc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.opentok.api.OpenTokSDK;
import com.opentok.api.constants.RoleConstants;
import com.opentok.exception.OpenTokException;

@Controller
public class HomeController {

	private static final int APIKEY = 0;

	private static Map<String, Integer> sessions = new HashMap<String, Integer>();

	@RequestMapping("/home.html")
	public ModelAndView index(Model model) {
		model.addAttribute("sessions", sessions.keySet());
		return new ModelAndView("home");
	}

	@RequestMapping("/joinChat.html")
	public ModelAndView getHome(Model model,
			@RequestParam("sessionId") String sessionId)
			throws OpenTokException {
		OpenTokSDK sdk = new OpenTokSDK(APIKEY,
				"");
		// Generate a basic session
		String token;
		if (sessions.get(sessionId) == 4) {
			token = sdk.generate_token(sessionId, RoleConstants.SUBSCRIBER);
		} else {
			token = sdk.generate_token(sessionId);
		}
		sessions.put(sessionId, sessions.get(sessionId) + 1);
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("token", token);
		model.addAttribute("apiKey", APIKEY);
		return new ModelAndView("chatroom");
	}

	@RequestMapping("/createChat.html")
	public ModelAndView createSession(Model model) throws OpenTokException {
		OpenTokSDK sdk = new OpenTokSDK(APIKEY,
				"");
		// Generate a basic session
		String sessionId = sdk.create_session().getSessionId();
		sessions.put(sessionId, 1);
		String token = sdk.generate_token(sessionId);
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("token", token);
		model.addAttribute("apiKey", APIKEY);
		return new ModelAndView("chatroom");
	}

}
