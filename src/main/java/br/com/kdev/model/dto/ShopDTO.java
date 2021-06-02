package br.com.kdev.model.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.kdev.model.Item;
import br.com.kdev.model.Shop;

public class ShopDTO {

	@NotBlank
	private String userIdentifier;
	@NotNull
	private Float total;
	@NotNull
	private Date date;
	@NotNull
	private List<ItemDTO> items;

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setUserIdentifier(shop.getUserIdentifier());
		shopDTO.setTotal(shop.getTotal());
		shopDTO.setDate(shop.getDate());
		shopDTO.setItems(
				shop.getItems()
				.stream()
				.map(ItemDTO::convert)
				.collect(Collectors.toList())
				);
		return shopDTO;
	}

}
