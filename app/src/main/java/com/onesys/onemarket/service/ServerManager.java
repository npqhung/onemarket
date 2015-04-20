package com.onesys.onemarket.service;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

import com.onesys.onemarket.model.OnlinePayment;
import com.onesys.onemarket.model.Payment;
import com.onesys.onemarket.model.ProductComment;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.model.Tablet;
import com.onesys.onemarket.model.TabletDetail;
import com.onesys.onemarket.utils.response.OnlinePaymentResponse;
import com.onesys.onemarket.utils.response.PaymentResponse;
import com.onesys.onemarket.utils.response.ProductCommentResponse;
import com.onesys.onemarket.utils.response.ProductDetailResponse;
import com.onesys.onemarket.utils.response.ProductListResponse;
import com.onesys.onemarket.utils.response.TabletDetailResponse;
import com.onesys.onemarket.utils.response.TabletListResponse;

import java.util.Collections;

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
        requestHeaders.setContentType(new MediaType("text", "html"));

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));

        restTemplate.getMessageConverters().add(converter); //add to decode JSON string

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

    public ProductDetailResponse getProductDetail(String id) {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        restUrl += id;
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            ProductDetailResponse productResponse = restTemplate.getForObject(restUrl, ProductDetailResponse.class,"");
            Log.d(TAG, "server contacted received status code " + productResponse.getCode());
            return productResponse;
        } catch (HttpServerErrorException e) {
            ProductDetailResponse emptyResponse = new ProductDetailResponse();
            emptyResponse.setData(new ProductDetailData());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            ProductDetailResponse emptyResponse = new ProductDetailResponse();
            emptyResponse.setData(new ProductDetailData());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            ProductDetailResponse emptyResponse = new ProductDetailResponse();
            emptyResponse.setData(new ProductDetailData());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }

    public ProductCommentResponse getProductComment(String id) {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        restUrl += id;
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            ProductCommentResponse productResponse = restTemplate.getForObject(restUrl, ProductCommentResponse.class,"");
            Log.d(TAG, "server contacted received status code " + productResponse.getCode());
            return productResponse;
        } catch (HttpServerErrorException e) {
            ProductCommentResponse emptyResponse = new ProductCommentResponse();
            emptyResponse.setData(new ProductComment[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            ProductCommentResponse emptyResponse = new ProductCommentResponse();
            emptyResponse.setData(new ProductComment[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            ProductCommentResponse emptyResponse = new ProductCommentResponse();
            emptyResponse.setData(new ProductComment[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }

    public PaymentResponse getCashPayment() {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            PaymentResponse paymentResponse = restTemplate.getForObject(restUrl, PaymentResponse.class,"");
            Log.d(TAG, "server contacted received status code " + paymentResponse.getCode());
            return paymentResponse;
        } catch (HttpServerErrorException e) {
            PaymentResponse emptyResponse = new PaymentResponse();
            emptyResponse.setData(new Payment());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            PaymentResponse emptyResponse = new PaymentResponse();
            emptyResponse.setData(new Payment());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            PaymentResponse emptyResponse = new PaymentResponse();
            emptyResponse.setData(new Payment());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }

    public OnlinePaymentResponse getOnlinePayment() {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            OnlinePaymentResponse onlineResponse = restTemplate.getForObject(restUrl, OnlinePaymentResponse.class,"");
            Log.d(TAG, "server contacted received status code " + onlineResponse.getCode());
            return onlineResponse;
        } catch (HttpServerErrorException e) {
            OnlinePaymentResponse emptyResponse = new OnlinePaymentResponse();
            emptyResponse.setData(new OnlinePayment());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            OnlinePaymentResponse emptyResponse = new OnlinePaymentResponse();
            emptyResponse.setData(new OnlinePayment());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            OnlinePaymentResponse emptyResponse = new OnlinePaymentResponse();
            emptyResponse.setData(new OnlinePayment());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }

    public TabletListResponse getAllTabletList() {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            TabletListResponse productResponse = restTemplate.getForObject(restUrl, TabletListResponse.class,"");
            Log.d(TAG, "server contacted received status code " + productResponse.getCode());
            return productResponse;
        } catch (HttpServerErrorException e) {
            TabletListResponse emptyResponse = new TabletListResponse();
            emptyResponse.setData(new Tablet[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            TabletListResponse emptyResponse = new TabletListResponse();
            emptyResponse.setData(new Tablet[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            TabletListResponse emptyResponse = new TabletListResponse();
            emptyResponse.setData(new Tablet[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }

    public TabletDetailResponse getTabletDetail(String id) {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        restUrl += id;
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            TabletDetailResponse productResponse = restTemplate.getForObject(restUrl, TabletDetailResponse.class,"");
            Log.d(TAG, "server contacted received status code " + productResponse.getCode());
            return productResponse;
        } catch (HttpServerErrorException e) {
            TabletDetailResponse emptyResponse = new TabletDetailResponse();
            emptyResponse.setData(new TabletDetail());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            TabletDetailResponse emptyResponse = new TabletDetailResponse();
            emptyResponse.setData(new TabletDetail());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            TabletDetailResponse emptyResponse = new TabletDetailResponse();
            emptyResponse.setData(new TabletDetail());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }
}
