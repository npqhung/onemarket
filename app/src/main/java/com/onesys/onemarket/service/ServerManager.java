package com.onesys.onemarket.service;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

import com.onesys.onemarket.model.ProductData;

import java.util.Arrays;
import java.util.List;

/**
 * For sending records to server...
 *
 *
 */
public class ServerManager {

	public ServerManager(String restUrl) {
		super();
		this.restUrl = restUrl;
	}

	private String restUrl; //set this to the host server to be initialised from the constructor...
	
	public int sendProductData(ProductData data) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("application","json"));
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); //add to decode JSON string
		// The URL for making the GET request
 		Log.d(this.getClass().getName(), " url is " + restUrl);
        ProductData[] dataArr = new ProductData[1];
 		dataArr[0] = data;
 		HttpEntity<ProductData[]> requestEntity = new HttpEntity<ProductData[]>(dataArr, requestHeaders);
 		try {
//	 		restTemplate.put(restUrl, data);
 			restTemplate.postForEntity(restUrl, requestEntity, null);
			
	 		return HttpStatus.SC_OK;
 		} catch (RestClientException e) {
 			Log.e(this.getClass().getName(),e.getMessage());
 			return HttpStatus.SC_EXPECTATION_FAILED;
 		} catch (Exception e) {
 			Log.e(this.getClass().getName(),e.getMessage());
 			return HttpStatus.SC_EXPECTATION_FAILED;
 		}
	}

    public List<ProductData> getProductList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); //add to decode JSON string
        // The URL for making the GET request
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
//	 		restTemplate.put(restUrl, data);
            ProductData[] productArr = restTemplate.getForObject(restUrl, ProductData[].class);

            return Arrays.asList(productArr);
        } catch (RestClientException e) {
            Log.e(this.getClass().getName(),e.getMessage());
            return null;
        } catch (Exception e) {
            Log.e(this.getClass().getName(),e.getMessage());
            return null;
        }
    }

}
