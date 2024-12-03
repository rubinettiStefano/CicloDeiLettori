package com.generation.ciclodeilettori.controllers.helpers;

import com.generation.ciclodeilettori.exception.DuplicateUsernameException;
import com.generation.ciclodeilettori.exception.InvalidCredentialsException;
import com.generation.ciclodeilettori.exception.InvalidPasswordException;
import com.generation.ciclodeilettori.model.entities.Article;
import com.generation.ciclodeilettori.model.entities.Author;
import com.generation.ciclodeilettori.model.entities.Reader;
import com.generation.ciclodeilettori.model.entities.User;

import java.util.List;

public interface ControllerHelper
{
	/**
	 * Ci permette di salvare nel database un nuovo Reader
	 */
	Reader registerReader(String username, String password) throws DuplicateUsernameException, InvalidPasswordException;
	/**
	 * Ci permette di salvare nel database un nuovo Author
	 */
	Author registerAuthor(String username, String password) throws DuplicateUsernameException,InvalidPasswordException;
	/**
	 * Ci permette prendere nel database uno User (Author o Reader) in base alle sue credenziali
	 * Se non lo trova lancia eccezione
	 */
	User login(String username, String password) throws InvalidCredentialsException;
	/**
	 * Salva un articolo nel db
	 */
	void saveArticle(Article article);

	/**
	 * Prendo solo gli articoli con tag in comune al lettore
	 */
	List<Article> getArticlesFor(Reader reader);

	Article getSingleArticle(int id);
}
