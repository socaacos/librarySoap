package com.example.librarySoap.endpoints;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.librarySoap.services.AuthorService;

import io.spring.guides.gs_producing_web_service.AuthorDto;
import io.spring.guides.gs_producing_web_service.GetAuthorsRequest;
import io.spring.guides.gs_producing_web_service.GetAuthorsResponse;

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

}
