package com.example.librarySoap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.librarySoap.entities.Author;


public interface AuthorRepository extends CrudRepository<Author, Integer> {
	
	@Query("select a from Author a where a.name like '%'|| :name ||'%'")
	public List<com.example.librarySoap.entities.Author> searchByName(@Param("name") String name);

}