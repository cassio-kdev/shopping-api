package br.com.kdev.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	private final static SimpleDateFormat dataDb = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date formatDataDB(Date data) {
		String dataFormatada = dataDb.format(data);
		
		try {
			return dataDb.parse(dataFormatada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
