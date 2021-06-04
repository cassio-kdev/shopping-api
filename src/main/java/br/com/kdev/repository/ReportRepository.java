package br.com.kdev.repository;

import java.util.Date;
import java.util.List;

import br.com.kdev.model.Shop;
import br.com.kdev.model.dto.ShopReportDTO;

public interface ReportRepository {

	public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

	public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);

}
