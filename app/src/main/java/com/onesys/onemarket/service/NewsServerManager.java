package com.onesys.onemarket.service;

import android.util.Log;

import com.onesys.onemarket.model.News;
import com.onesys.onemarket.utils.response.NewsDetailResponse;
import com.onesys.onemarket.utils.response.NewsListResponse;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hung Nguyen on 4/25/2015.
 */
public class NewsServerManager extends ServerManager {

    private static final String TAG = NewsServerManager.class.getName();

    public NewsServerManager(String restUrl) {
        super();
        this.restUrl = restUrl;
    }

    public NewsListResponse getAllNewsList() {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            NewsListResponse productResponse = restTemplate.getForObject(restUrl, NewsListResponse.class, "");
            Log.d(TAG, "server contacted received status code " + productResponse.getCode());
            return productResponse;
        } catch (HttpServerErrorException e) {
            NewsListResponse emptyResponse = new NewsListResponse();
            emptyResponse.setData(new News[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            NewsListResponse emptyResponse = new NewsListResponse();
            emptyResponse.setData(new News[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            NewsListResponse emptyResponse = new NewsListResponse();
            emptyResponse.setData(new News[0]);
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }

    public NewsDetailResponse getNewsDetail(String id) {
        RestTemplate restTemplate = getRestTemplate();
        // The URL for making the GET request
        restUrl += id;
        Log.d(this.getClass().getName(), " url is " + restUrl);

        try {
            NewsDetailResponse productResponse = restTemplate.getForObject(restUrl, NewsDetailResponse.class, "");
            Log.d(TAG, "server contacted received status code " + productResponse.getCode());
            return productResponse;
        } catch (HttpServerErrorException e) {
            NewsDetailResponse emptyResponse = new NewsDetailResponse();
            emptyResponse.setData(new News());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (HttpClientErrorException e) {
            NewsDetailResponse emptyResponse = new NewsDetailResponse();
            emptyResponse.setData(new News());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        } catch (ResourceAccessException e) {
            NewsDetailResponse emptyResponse = new NewsDetailResponse();
            emptyResponse.setData(new News());
            emptyResponse.setMessage(e.getMessage());
            Log.e(TAG, "error" + e.getMessage());
            return emptyResponse;
        }
    }
}
