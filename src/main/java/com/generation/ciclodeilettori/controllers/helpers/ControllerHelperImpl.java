package com.generation.ciclodeilettori.controllers.helpers;

import com.generation.ciclodeilettori.model.repositories.ArticleRepository;
import com.generation.ciclodeilettori.model.repositories.AuthorRepository;
import com.generation.ciclodeilettori.model.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
