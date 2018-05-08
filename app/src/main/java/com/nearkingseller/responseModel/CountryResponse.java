package com.nearkingseller.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 27-Apr-18.
 */

public class CountryResponse {
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
