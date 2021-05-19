package com.example.librarySoap.endpoints;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.librarySoap.services.CityService;

import io.spring.guides.gs_producing_web_service.CityDto;
import io.spring.guides.gs_producing_web_service.GetCityRequest;
import io.spring.guides.gs_producing_web_service.GetCityResponse;

@Endpoint
public class CityEndpoint {
	
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private CityService cityService;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	public CityEndpoint(CityService cityService) {
		this.cityService = cityService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCityRequest")
	@ResponsePayload
	public GetCityResponse getCity(@RequestPayload GetCityRequest request) {
		int id = request.getId();
		CityDto response = (CityDto) cityService.getById(id);
		GetCityResponse city = modelMapper.map(response, GetCityResponse.class);
		return city;
		
	}

}
