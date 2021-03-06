package br.com.kdev.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.kdev.dto.ProductDTO;
import br.com.kdev.exception.ProductNotFoundException;

@Service
public class ProductService {

	public ProductDTO getProductByIdentifier(String productIdentifier) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8081/product/" + productIdentifier;

			ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

			return response.getBody();

		} catch (HttpClientErrorException.NotFound e) {
			throw new ProductNotFoundException();
		}
	}
}
