package br.com.kdev.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kdev.dto.ShopDTO;
import br.com.kdev.model.Shop;
import br.com.kdev.model.dto.DTOConverter;
import br.com.kdev.model.dto.ShopReportDTO;
import br.com.kdev.repository.ShopRepository;

@Service
public class ReportService {
	
	@Autowired
	private ShopRepository reportRepository;
	
	public List<ShopDTO> getShopsByFilter(Date dataInicio, Date dataFim, Float valorMinimo) {
		
		
		List<Shop> shops = reportRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}

	public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
		return reportRepository.getReportByDate(dataInicio, dataFim);
	}

}
