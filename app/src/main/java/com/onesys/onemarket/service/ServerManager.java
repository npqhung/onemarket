package com.onesys.onemarket.service;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.utils.ProductListResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * For sending records to server...
 *
 *
 */
public class ServerManager {

    private static final String TAG = ServerManager.class.getName();
    private String restUrl; //set this to the host server to be initialised from the constructor...

	public ServerManager(String restUrl) {
		super();
		this.restUrl = restUrl;
	}

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(new MediaType("text", "html"));
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); //add to decode JSON string
        requestHeaders.setContentType(new MediaType("text","html"));
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter()); //add to decode JSON string
        return restTemplate;
    }

	public int sendProductData(ProductData data) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
//		requestHeaders.setContentType(new MediaType("application","json"));
//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); //add to decode JSON string

        requestHeaders.setContentType(new MediaType("text","html"));
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter()); //add to decode JSON string

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

    public ProductListResponse getAllProductList() {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            ProductListResponse productResponse = restTemplate.getForObject(restUrl, ProductListResponse.class,"");
            Log.d(TAG, "server contacted received status code " + productResponse.getCode());
            return productResponse;
        } catch (HttpServerErrorException e) {
            ProductListResponse emptyResponse = new ProductListResponse();
            emptyResponse.setData(new ProductData[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            ProductListResponse emptyResponse = new ProductListResponse();
            emptyResponse.setData(new ProductData[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            ProductListResponse emptyResponse = new ProductListResponse();
            emptyResponse.setData(new ProductData[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }

}
