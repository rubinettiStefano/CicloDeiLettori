package com.generation.ciclodeilettori.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Reader extends User
{
	@Enumerated(EnumType.STRING)
	private SubscriptionType subscriptionType;

	//Storia,Arte,News
	private String preferredTags;


	public List<String> getPreferredTagsList()
	{
		List<String> res = new ArrayList<String>();
		String[] comeVettore = preferredTags.split(",");
		for(String s : comeVettore)
			res.add(s);
		return res;
	}
}
