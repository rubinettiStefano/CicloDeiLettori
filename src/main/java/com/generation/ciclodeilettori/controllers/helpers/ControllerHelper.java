package com.generation.ciclodeilettori.controllers.helpers;

import com.generation.ciclodeilettori.exception.DuplicateUsernameException;
import com.generation.ciclodeilettori.exception.InvalidCredentialsException;
import com.generation.ciclodeilettori.exception.InvalidPasswordException;
import com.generation.ciclodeilettori.model.entities.Author;
import com.generation.ciclodeilettori.model.entities.Reader;
import com.generation.ciclodeilettori.model.entities.User;

public interface ControllerHelper
{
	Reader registerReader(String username, String password) throws DuplicateUsernameException, InvalidPasswordException;

	Author registerAuthor(String username, String password) throws DuplicateUsernameException,InvalidPasswordException;

	User login(String username, String password) throws InvalidCredentialsException;
}
