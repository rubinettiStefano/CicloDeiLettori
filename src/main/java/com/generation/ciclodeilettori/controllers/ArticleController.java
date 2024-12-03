package com.generation.ciclodeilettori.controllers;

import com.generation.ciclodeilettori.controllers.helpers.ControllerHelper;
import com.generation.ciclodeilettori.exception.ForbiddenPageException;
import com.generation.ciclodeilettori.model.entities.Article;
import com.generation.ciclodeilettori.model.entities.Author;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
public class ArticleController
{
	@Autowired
	ControllerHelper helper;
	//io accetto le request inviate a ROOT+MAPPING
	//ROOT -> localhost:8080
	//localhost:8080/articles

	@PostMapping
	public String createArticle(@ModelAttribute Article article, HttpSession session)
	{
		article.setAuthor((Author) session.getAttribute("user"));
		helper.saveArticle(article);
		return "createArticle";
	}

	//	@PostMapping
//	public String createArticleV
//	(@RequestParam String title, @RequestParam String content, @RequestParam String synopsis,@RequestParam String tags)
//	{
//		Article article = new Article();
//		article.setTitle(title);
//		article.setContent(content);
//		article.setSynopsis(synopsis);
//		article.setTags(tags);
//		//è uguale a scriverlo qui
//		return null;
//	}



	@GetMapping
	public String getArticleCreationPage(HttpSession session)
	{
		if(session.getAttribute("type")==null || !session.getAttribute("type").equals("author"))
			throw new ForbiddenPageException();

		return "createArticle";
	}
}
