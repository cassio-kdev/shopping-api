package br.com.kdev.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kdev.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
	public List<Shop> findAllByUserIdentifier(String userIdentifier);

	public List<Shop> findAllByTotalGreaterThan(Float total);

	public List<Shop> findAllByDateGreaterThan(Date date);

}
