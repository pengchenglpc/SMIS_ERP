package com.smis.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/account")
public class BaseConstroller {
	@RequestMapping(value = "/base", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public void base(){
		System.out.println("这是一个测试");
	}
}
