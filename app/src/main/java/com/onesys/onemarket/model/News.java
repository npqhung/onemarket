package com.onesys.onemarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Hung on 24/04/2015.
 */
public class News implements Serializable {
    @JsonProperty("category_id")
    private String categoryId;
    private String content;
    private String cover;
    @JsonProperty("created_date")
    private String createdDate;
    private String id;
    @JsonProperty("modified_date")
    private String modifiedDate;
    @JsonProperty("short_content")
    private String shortContent;
    private String title;

    @JsonProperty("ios_title")
    private String iosTitle;
    @JsonProperty("ios_short_content")
    private String iosShortContent;
    @JsonProperty("ios_content")
    private String iosContent;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIosTitle() {
        return iosTitle;
    }

    public void setIosTitle(String iosTitle) {
        this.iosTitle = iosTitle;
    }

    public String getIosShortContent() {
        return iosShortContent;
    }

    public void setIosShortContent(String iosShortContent) {
        this.iosShortContent = iosShortContent;
    }

    public String getIosContent() {
        return iosContent;
    }

    public void setIosContent(String iosContent) {
        this.iosContent = iosContent;
    }
}