package com.gumilang.rezza.challengeapps.holder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rezza on 18/11/17.
 */

public class UserHolder {
    String username;
    String email;
    String fullname;
    String gender;
    String phone;
    String province_name;
    String region_name;
    String subdistrict_name;
    String urlPic;

    public UserHolder unpack(JSONObject pData){
        try {
            JSONObject jData    = pData;
            username            = jData.getString("username");
            email               = jData.getString("email");
            fullname            = jData.getString("fullname");
            gender              = jData.getString("gender");
            phone               = jData.getString("phone");
            province_name       = jData.getString("province_name");
            region_name         = jData.getString("region_name");
            subdistrict_name    = jData.getString("subdistrict_name");
            urlPic              = jData.getString("pict");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public void setSubdistrict_name(String subdistrict_name) {
        this.subdistrict_name = subdistrict_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getSubdistrict_name() {
        return subdistrict_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlPic() {
        return urlPic;
    }
}
