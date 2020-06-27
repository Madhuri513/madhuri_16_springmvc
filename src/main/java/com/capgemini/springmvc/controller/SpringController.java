package com.capgemini.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringController {
	
	@RequestMapping(path="/getMessage",method = RequestMethod.GET)
	public ModelAndView showMessage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/WEB-INF/views/message.jsp");
		return model;
	}
	
	@RequestMapping(path="/getEmp",method = RequestMethod.GET)
	public String getEmp(@RequestParam(name = "empId") int empIdVal, HttpServletRequest req) {
		req.setAttribute("empId", empIdVal);
		return "/WEB-INF/views/getEmp.jsp";
	}

}
