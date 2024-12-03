package com.generation.ciclodeilettori.controllers.helpers;

import com.generation.ciclodeilettori.exception.DuplicateUsernameException;
import com.generation.ciclodeilettori.exception.InvalidCredentialsException;
import com.generation.ciclodeilettori.exception.InvalidPasswordException;
import com.generation.ciclodeilettori.model.entities.*;
import com.generation.ciclodeilettori.model.repositories.ArticleRepository;
import com.generation.ciclodeilettori.model.repositories.AuthorRepository;
import com.generation.ciclodeilettori.model.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Override
	public void saveArticle(Article article)
	{
		artRepo.save(article);
	}

	@Override
	public List<Article> getArticlesFor(Reader reader)
	{
		List<String> tagsReader = reader.getPreferredTagsList();
		List<Article> allArticles = artRepo.findAll();
		List<Article> res = new ArrayList<>();
		for(Article a : allArticles)
		{
			List<String> tagsArticle = a.getTagsList();

			//art tags -> Storia Filosofia News
			//rea tags -> News Arte
			//tra i due sopra c'è un tag in comune, il lettore può leggere articolo

			//art tags -> Storia Filosofia News
			//rea tags -> Politica Arte
			//tra i due sopra NON c'è un tag in comune, il lettore NON può leggere articolo
//			boolean comune = false;
//			for(String tag : tagsArticle)
//			{
//				for (String tagReader : tagsReader)
//					if(tag.equals(tagReader))
//						comune = true;
//			}


			//solo se c'è una String, quindi un tag in comune

//			Set<String> tuttiTags = new HashSet<>();
//			tuttiTags.addAll(tagsArticle);
//			tuttiTags.addAll(tagsReader);
//			if(tuttiTags.size()!= (tagsReader.size()+tagsArticle.size()))
//				res.add(a);

			List<String> tags = new ArrayList<>(tagsArticle);
			tags.removeAll(tagsReader);

			if(tags.size()!=tagsArticle.size())
				res.add(a);

		}

		return res;
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
