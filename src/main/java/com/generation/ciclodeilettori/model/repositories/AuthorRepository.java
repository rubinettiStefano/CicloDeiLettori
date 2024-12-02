package com.generation.ciclodeilettori.model.repositories;

import com.generation.ciclodeilettori.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer>
{
}
