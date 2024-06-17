package com.unla.stock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.stock.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//modelAndView.addObject("username", user.getUsername());
		return modelAndView;
	}
}
