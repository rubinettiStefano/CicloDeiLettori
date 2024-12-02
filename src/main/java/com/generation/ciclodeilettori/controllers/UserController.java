package com.generation.ciclodeilettori.controllers;

import com.generation.ciclodeilettori.controllers.helpers.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class UserController
{
	@Autowired
	private ControllerHelper helper;


}
