package com.upendra.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.upendra.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.upendra.microservices.currencyconversionservice.feign.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity  )
	{
		Map<String, String > uriVaraibales=new HashMap<>();
		uriVaraibales.put("from",from);
		uriVaraibales.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,uriVaraibales);
		CurrencyConversionBean response=responseEntity.getBody();
		
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
		
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity  )
	{
		CurrencyConversionBean response=proxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
		
	}
}
