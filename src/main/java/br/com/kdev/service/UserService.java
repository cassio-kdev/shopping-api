package br.com.kdev.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.kdev.dto.UserDTO;
import br.com.kdev.exception.UserNotFoundException;

@Service
public class UserService {

	public UserDTO getUserByCpf(String cpf, String key) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String userApiURL = "http://localhost:8080";

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(userApiURL + "/user/cpf/" + cpf);
			builder.queryParam("key", key);

			ResponseEntity<UserDTO> response = restTemplate.getForEntity(builder.toUriString(), UserDTO.class);
			return response.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new UserNotFoundException();
		}
	}
}
