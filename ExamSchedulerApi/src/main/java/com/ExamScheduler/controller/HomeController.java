package com.ExamScheduler.controller;

import com.ExamScheduler.config.JwtAuthenticationEntryPoint;
import com.ExamScheduler.config.JwtTokenUtil;
import com.ExamScheduler.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userService;


	@GetMapping({ "/" })
	public String firstPage() {
		return "Welcome to ExamScheduler API";
	}

}