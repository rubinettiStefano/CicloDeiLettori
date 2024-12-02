package com.generation.ciclodeilettori.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author extends User
{
	@OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
	private List<Article> articles = new ArrayList<>();
}
