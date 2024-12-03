package com.generation.ciclodeilettori.controllers.helpers;

import com.generation.ciclodeilettori.exception.DuplicateUsernameException;
import com.generation.ciclodeilettori.exception.ForbiddenPageException;
import com.generation.ciclodeilettori.exception.InvalidCredentialsException;
import com.generation.ciclodeilettori.exception.InvalidPasswordException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UniversalExceptionHandler
{
	@ExceptionHandler(InvalidCredentialsException.class)
	public String handleInvalidCredentials(InvalidCredentialsException e, Model model)
	{
		model.addAttribute("message", "Vergogna, metti giusto");
		return "index";
	}

	@ExceptionHandler(DuplicateUsernameException.class)
	public String handleDuplicate(DuplicateUsernameException e, Model model)
	{
		model.addAttribute("message", "Utente già esiste zio");
		return "index";
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public String handlePassword(InvalidPasswordException e, Model model)
	{
		model.addAttribute("message", "Password fa schifo, più lettere, più maiuscole, più minuscole");
		return "index";
	}

	@ExceptionHandler(ForbiddenPageException.class)
	public String handleForbidden(ForbiddenPageException e)
	{
		return "forbidden";
	}
}
