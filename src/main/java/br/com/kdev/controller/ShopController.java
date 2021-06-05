package br.com.kdev.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kdev.dto.ShopDTO;
import br.com.kdev.model.dto.ShopReportDTO;
import br.com.kdev.service.ReportService;
import br.com.kdev.service.ShopService;
import br.com.kdev.service.UserService;
import br.com.kdev.util.Utils;

@RestController
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private UserService userService;

	@Autowired
	private ReportService reportService;

	@GetMapping("/shopping")
	public List<ShopDTO> getShops() {
		List<ShopDTO> produtos = shopService.getAll();
		return produtos;
	}

	@GetMapping("/shopping/shopByUser/{userIdentifier}")
	public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
		List<ShopDTO> produtos = shopService.getByUser(userIdentifier);
		return produtos;
	}

	@GetMapping("/shopping/shopByDate")
	public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {

		List<ShopDTO> produtos = shopService.getByDate(shopDTO);

		return produtos;
	}

	@GetMapping("/shopping/{id}")
	public ShopDTO findById(@PathVariable Long id) {
		return shopService.findById(id);
	}

	@PostMapping("/shopping")
	public ShopDTO newShop(@RequestHeader(name = "key", required = true) String key,
			@Valid @RequestBody ShopDTO shopDTO) {

		return shopService.save(shopDTO, key);
	}

	@GetMapping("/shopping/search")
	public List<ShopDTO> getShopsByFilter(
			@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
			@RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
			@RequestParam(name = "valorMinimo", required = false) Float valorMinimo) {
		System.out.println("Inicio: " + dataInicio + "\nFim: " + dataFim);

		Date formatDataDBInicio = Utils.formatDataDB(dataInicio);
		Date formatDataDBFim = Utils.formatDataDB(dataFim);

		System.out.println("Inicio: " + formatDataDBInicio + "\nFim: " + formatDataDBFim);

		return reportService.getShopsByFilter(formatDataDBInicio, formatDataDBFim, valorMinimo);
	}

	@GetMapping("/shopping/report")
	public ShopReportDTO getReportByDate(
			@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
			@RequestParam(name = "dataFim", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {

		return reportService.getReportByDate(dataInicio, dataFim);
	}

}
