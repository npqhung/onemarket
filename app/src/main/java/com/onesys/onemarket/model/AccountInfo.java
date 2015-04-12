package com.onesys.onemarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class AccountInfo
        implements Serializable
{

    private String avatar;
    private String birthday;
    private String city;
    private String cmnd;

    @JsonProperty("company_city_id_id")
    private String companyCityId;

    @JsonProperty("company_district_id_id")
    private String companyDistrictId;

    @JsonProperty("company_name")
    private String companyName;

    private String district;
    private String email;
    private String fullname;
    private String gender ;

    @JsonProperty("gender_id")
    private String genderId;
    private String id;
    private String level;

//    private String mstId = parse(paramJSONObject, "mst_id");

    @JsonProperty("phone_number")
    private String phoneNumber;
    private String point;
    private String street;
    private String username;
    private String ward;

    public AccountInfo() {}

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getCompanyCityId() {
        return companyCityId;
    }

    public void setCompanyCityId(String companyCityId) {
        this.companyCityId = companyCityId;
    }

    public String getCompanyDistrictId() {
        return companyDistrictId;
    }

    public void setCompanyDistrictId(String companyDistrictId) {
        this.companyDistrictId = companyDistrictId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}

