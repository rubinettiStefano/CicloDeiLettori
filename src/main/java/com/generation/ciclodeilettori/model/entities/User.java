package com.generation.ciclodeilettori.model.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Data
public abstract class User extends BaseEntity
{
	protected String name,surname,username,password;
	protected LocalDate dob;

	public void setDob(String dobString)
	{
		this.dob = LocalDate.parse(dobString);
	}

	public void setDob(String dobString,String format)
	{
		if(format.equals("db"))
			setDob(dobString);
		else
			this.dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}
