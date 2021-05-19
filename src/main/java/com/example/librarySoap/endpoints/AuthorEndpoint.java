package com.example.librarySoap.endpoints;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.librarySoap.services.AuthorService;

import io.spring.guides.gs_producing_web_service.AuthorDto;
import io.spring.guides.gs_producing_web_service.CreateAuthorRequest;
import io.spring.guides.gs_producing_web_service.CreateAuthorResponse;
import io.spring.guides.gs_producing_web_service.DeleteAuthorRequest;
import io.spring.guides.gs_producing_web_service.DeleteAuthorResponse;
import io.spring.guides.gs_producing_web_service.GetAllAuthorsResponse;
import io.spring.guides.gs_producing_web_service.GetAuthorsByNameRequest;
import io.spring.guides.gs_producing_web_service.GetAuthorsByNameResponse;
import io.spring.guides.gs_producing_web_service.GetAuthorsRequest;
import io.spring.guides.gs_producing_web_service.GetAuthorsResponse;
import io.spring.guides.gs_producing_web_service.UpdateAuthorRequest;
import io.spring.guides.gs_producing_web_service.UpdateAuthorResponse;

@Endpoint
public class AuthorEndpoint {
	
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private AuthorService authorService;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	public AuthorEndpoint(AuthorService authorService) {
		this.authorService = authorService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorsRequest")
	@ResponsePayload
	public GetAuthorsResponse getAuthors(@RequestPayload GetAuthorsRequest request) {
		int id = request.getId();
		AuthorDto response = (AuthorDto) authorService.getById(id);
		GetAuthorsResponse author = modelMapper.map(response, GetAuthorsResponse.class);
		return author;
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorsByNameRequest")
	@ResponsePayload
	public GetAuthorsByNameResponse getAuthorsByName(@RequestPayload GetAuthorsByNameRequest request) {
		String name  = request.getName();
		System.out.println(name);
		
		List<AuthorDto> response = (List<AuthorDto>) authorService.searchByName(name);
		System.out.println(response.size());
		//GetAuthorsByNameResponse authors = modelMapper.map(response, GetAuthorsByNameResponse.class);
		//System.out.println(authors.getAuthor().size());
		//return authors;
		
		GetAuthorsByNameResponse authors = new GetAuthorsByNameResponse();
		for (AuthorDto authorDto : response) {
			authors.getAuthor().add(authorDto);
		}
		
		System.out.println(authors.getAuthor().size());
		return authors;
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllAuthorSRequest")
	@ResponsePayload
	public GetAllAuthorsResponse getAllAuthors() {
		List<AuthorDto> response = (List<AuthorDto>) authorService.getAll();
		GetAllAuthorsResponse authors = new GetAllAuthorsResponse();
		for (AuthorDto authorDto : response) {
			authors.getAuthor().add(authorDto);
		}		
		return authors;		
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAuthorRequest")
	@ResponsePayload
	public DeleteAuthorResponse delete(@RequestPayload DeleteAuthorRequest request) {
		int id = request.getId();
		authorService.delete(id);
		DeleteAuthorResponse res = new DeleteAuthorResponse();
		res.setMessagge("Deleted successfuly!");
		return res;
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAuthorRequest")
	@ResponsePayload
	public CreateAuthorResponse delete(@RequestPayload CreateAuthorRequest request) {
		String name = request.getName();
		AuthorDto author = new AuthorDto();
		author.setName(name);
		
		AuthorDto newAuthor = authorService.create(author);
		CreateAuthorResponse res = modelMapper.map(newAuthor, CreateAuthorResponse.class);
		return res;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAuthorRequest")
	@ResponsePayload
	public UpdateAuthorResponse update(@RequestPayload UpdateAuthorRequest request) {
		int id = request.getId();
		String name = request.getName();
		AuthorDto author = new AuthorDto();
		author.setName(name);
		
		
		AuthorDto newAuthor = authorService.update(id, author);
		UpdateAuthorResponse res = modelMapper.map(newAuthor, UpdateAuthorResponse.class);
		return res;
	}
	
	

}
