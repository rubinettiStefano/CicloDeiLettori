package com.generation.ciclodeilettori.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Article extends BaseEntity
{
	private String title,synopsis,content;
	private String tags;
	private LocalDate wroteOn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id")
	private Author author;

	public List<String> getTagsList()
	{
		List<String> res = new ArrayList<>();
		for(String tag : tags.split(","))
			res.add(tag);
		return res;
	}
}

