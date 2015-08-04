package com.meritconinc.shiplinx.api.model;


import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Default_address {

@Expose
private String address1;
@Expose
private String address2;
@Expose
private String city;
@Expose
private String company;
@Expose
private String country;
@SerializedName("first_name")
@Expose
private String firstName;
@Expose
private Integer id;
@SerializedName("last_name")
@Expose
private String lastName;
@Expose
private String phone;
@Expose
private String province;
@Expose
private String zip;
@Expose
private String name;
@SerializedName("province_code")
@Expose
private String provinceCode;
@SerializedName("country_code")
@Expose
private String countryCode;
@SerializedName("country_name")
@Expose
private String countryName;
@SerializedName("default")
@Expose
private Boolean _default;

/**
*
* @return
* The address1
*/
public String getAddress1() {
return address1;
}

/**
*
* @param address1
* The address1
*/
public void setAddress1(String address1) {
this.address1 = address1;
}

/**
*
* @return
* The address2
*/
public String getAddress2() {
return address2;
}

/**
*
* @param address2
* The address2
*/
public void setAddress2(String address2) {
this.address2 = address2;
}

/**
*
* @return
* The city
*/
public String getCity() {
return city;
}

/**
*
* @param city
* The city
*/
public void setCity(String city) {
this.city = city;
}

/**
*
* @return
* The company
*/
public String getCompany() {
return company;
}

/**
*
* @param company
* The company
*/
public void setCompany(String company) {
this.company = company;
}

/**
*
* @return
* The country
*/
public String getCountry() {
return country;
}

/**
*
* @param country
* The country
*/
public void setCountry(String country) {
this.country = country;
}

/**
*
* @return
* The firstName
*/
public String getFirstName() {
return firstName;
}

/**
*
* @param firstName
* The first_name
*/
public void setFirstName(String firstName) {
this.firstName = firstName;
}

/**
*
* @return
* The id
*/
public Integer getId() {
return id;
}

/**
*
* @param id
* The id
*/
public void setId(Integer id) {
this.id = id;
}

/**
*
* @return
* The lastName
*/
public String getLastName() {
return lastName;
}

/**
*
* @param lastName
* The last_name
*/
public void setLastName(String lastName) {
this.lastName = lastName;
}

/**
*
* @return
* The phone
*/
public String getPhone() {
return phone;
}

/**
*
* @param phone
* The phone
*/
public void setPhone(String phone) {
this.phone = phone;
}

/**
*
* @return
* The province
*/
public String getProvince() {
return province;
}

/**
*
* @param province
* The province
*/
public void setProvince(String province) {
this.province = province;
}

/**
*
* @return
* The zip
*/
public String getZip() {
return zip;
}

/**
*
* @param zip
* The zip
*/
public void setZip(String zip) {
this.zip = zip;
}

/**
*
* @return
* The name
*/
public String getName() {
return name;
}

/**
*
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
*
* @return
* The provinceCode
*/
public String getProvinceCode() {
return provinceCode;
}

/**
*
* @param provinceCode
* The province_code
*/
public void setProvinceCode(String provinceCode) {
this.provinceCode = provinceCode;
}

/**
*
* @return
* The countryCode
*/
public String getCountryCode() {
return countryCode;
}

/**
*
* @param countryCode
* The country_code
*/
public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

/**
*
* @return
* The countryName
*/
public String getCountryName() {
return countryName;
}

/**
*
* @param countryName
* The country_name
*/
public void setCountryName(String countryName) {
this.countryName = countryName;
}

/**
*
* @return
* The _default
*/
public Boolean getDefault() {
return _default;
}

/**
*
* @param _default
* The default
*/
public void setDefault(Boolean _default) {
this._default = _default;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

}