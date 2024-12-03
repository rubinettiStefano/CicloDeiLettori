package com.generation.ciclodeilettori.controllers;

import com.generation.ciclodeilettori.controllers.helpers.ControllerHelper;
import com.generation.ciclodeilettori.exception.ForbiddenPageException;
import com.generation.ciclodeilettori.model.entities.Article;
import com.generation.ciclodeilettori.model.entities.Reader;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reader")
public class ReaderController
{
	@Autowired
	ControllerHelper helper;

	@GetMapping
	public String leggi(HttpSession session, Model model)
	{
		if (session.getAttribute("type") == null || !session.getAttribute("type").equals("reader"))
			throw new ForbiddenPageException();
		Reader reader = (Reader) session.getAttribute("user");
		List<Article> articles = helper.getArticlesFor(reader);
		model.addAttribute("articles", articles);
		return "allArticles";
	}

	@GetMapping("/article")
	public String dettaglioArticolo(@RequestParam int id,Model model)
	{
		Article a = helper.getSingleArticle(id);
		model.addAttribute("article", a);
		return "singleArticle";
	}
}
