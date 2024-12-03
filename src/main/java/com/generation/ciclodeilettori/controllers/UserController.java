package com.generation.ciclodeilettori.controllers;

import com.generation.ciclodeilettori.controllers.helpers.ControllerHelper;
import com.generation.ciclodeilettori.model.entities.Author;
import com.generation.ciclodeilettori.model.entities.Reader;
import com.generation.ciclodeilettori.model.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/credentials")
public class UserController
{
	@Autowired
	private ControllerHelper helper;

	@PostMapping
	public String credentials(@RequestParam String username, @RequestParam String password, @RequestParam String type, HttpSession session)
	{
		User user = null;
		switch (type)
		{
			case "LOGIN" ->
			{
				user = helper.login(username, password);
			}
			case "REGISTER as AUTHOR" ->
			{
				user = helper.registerAuthor(username, password);
			}
			case "REGISTER as READER" ->
			{
				user = helper.registerReader(username, password);
			}
		}

		session.setAttribute("user", user);
		session.setAttribute("type", user instanceof Reader ? "reader" : "author");
		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "index";
	}
}
