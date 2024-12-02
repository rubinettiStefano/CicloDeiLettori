package com.generation.ciclodeilettori.controllers.helpers;

import com.generation.ciclodeilettori.exception.DuplicateUsernameException;
import com.generation.ciclodeilettori.exception.InvalidCredentialsException;
import com.generation.ciclodeilettori.exception.InvalidPasswordException;
import com.generation.ciclodeilettori.model.entities.Author;
import com.generation.ciclodeilettori.model.entities.Reader;
import com.generation.ciclodeilettori.model.entities.SubscriptionType;
import com.generation.ciclodeilettori.model.entities.User;
import com.generation.ciclodeilettori.model.repositories.ArticleRepository;
import com.generation.ciclodeilettori.model.repositories.AuthorRepository;
import com.generation.ciclodeilettori.model.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//beanizza la classe. crea in automatico un oggetto di questa classe all'avvio del programma
//che viene messo nell'application context.
//le altri parti dell'applicazione possono richiederlo così con @Autowired
//tutto questo meccanismo viene chiamato da SPRING Dependency Injection
@Service
public class ControllerHelperImpl implements ControllerHelper
{
	@Autowired
	private ReaderRepository reaRepo;
	@Autowired
	private AuthorRepository autRepo;
	@Autowired
	private ArticleRepository artRepo;


	@Override
	public Reader registerReader(String username, String password) throws DuplicateUsernameException,InvalidPasswordException
	{
		checkUsernameAndPassword(username,password);
		Reader reader = new Reader();
		reader.setUsername(username);
		reader.setPassword(password);
		reader.setSubscriptionType(SubscriptionType.FREE);

		reaRepo.save(reader);
		return reader;
	}

	@Override
	public Author registerAuthor(String username, String password) throws DuplicateUsernameException,InvalidPasswordException
	{
		checkUsernameAndPassword(username,password);
		Author author = new Author();
		author.setUsername(username);
		author.setPassword(password);

		autRepo.save(author);
		return author;
	}

	@Override
	public User login(String username, String password) throws InvalidCredentialsException
	{
		for(User user : getAllUsers())
			if(user.getUsername().equals(username) && user.getPassword().equals(password))
				return user;

		throw new InvalidCredentialsException();
	}

	private List<User> getAllUsers()
	{
		List<User> all = new ArrayList<>();
		all.addAll(reaRepo.findAll());
		all.addAll(autRepo.findAll());
		return all;
	}

	private void checkUsername(String username) throws DuplicateUsernameException
	{
		for (User user : getAllUsers())
			if (user.getUsername().equals(username))
				throw new DuplicateUsernameException();
	}

	private void checkPassword(String password) throws InvalidPasswordException
	{
		if
		(
				password.length() < 8 ||
				password.equals(password.toLowerCase()) ||
				password.equals(password.toUpperCase())
		)
			throw new InvalidPasswordException();

	}

	private void checkUsernameAndPassword(String username, String password)
	{
		checkUsername(username);
		checkPassword(password);
	}
}
