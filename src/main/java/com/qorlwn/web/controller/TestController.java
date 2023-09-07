package com.qorlwn.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.qorlwn.web.entity.Test;
import com.qorlwn.web.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public String test(Model model) {
		List<Test> list = testService.list();
		model.addAttribute("list", list);
		System.out.println(list);
		return "test";
	}
}
