package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	@ResponseBody
	@RequestMapping(value="/api/test", method = {RequestMethod.GET, RequestMethod.POST})
	public String test() {
		System.out.println("HelloController.test()");
		return "api서버테스트";
	}
	
}
