package com.gumilang.rezza.challengeapps.holder;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rezza on 18/11/17.
 */

public class LoginUserHolder {

    String username;
    String email;
    String fullname;
    String gender;
    String phone;
    String province_name;
    String region_name;
    String subdistrict_name;
    String pict;
    String token;

    String message_login = "200 OK";

    public LoginUserHolder unpackDataFromServer(JSONObject pData){
        Log.d("TAGRZ", pData.toString());
        try {
            JSONObject _data    = pData.getJSONObject("_data");
            username    = _data.getString("username");
            email       = _data.getString("email");
            fullname    = _data.getString("fullname");
            token       = _data.getString("token");

            JSONObject profile  = _data.getJSONObject("profile");
            gender      = profile.getString("gender");
            phone       = profile.getString("phone");
            pict        = profile.getString("pict");

            JSONObject default_address  = _data.getJSONObject("default_address");
            subdistrict_name    = default_address.getString("subdistrict_name");
            province_name       = default_address.getString("province_name");
            region_name         = default_address.getString("region_name");

            message_login   = "200 OK";

        } catch (JSONException e) {
            e.printStackTrace();
            try {
                message_login   = pData.getString("_message");
            } catch (JSONException e1) {
                message_login = "Login gagal";
                e1.printStackTrace();
            }
        }

        return this;
    }

    public String pack(){
        JSONObject jData = new JSONObject();
        try {
            jData.put("username",username);
            jData.put("email",email);
            jData.put("fullname",fullname);
            jData.put("gender",gender);
            jData.put("phone",phone);
            jData.put("province_name",province_name);
            jData.put("region_name",region_name);
            jData.put("subdistrict_name",subdistrict_name);
            jData.put("pict",pict);
            jData.put("token",token);
            jData.put("message_login",message_login);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jData.toString();
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public void setMessage_login(String message_login) {
        this.message_login = message_login;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public void setSubdistrict_name(String subdistrict_name) {
        this.subdistrict_name = subdistrict_name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getGender() {
        return gender;
    }

    public String getMessage_login() {
        return message_login;
    }

    public String getPhone() {
        return phone;
    }

    public String getPict() {
        return pict;
    }

    public String getProvince_name() {
        return province_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public String getSubdistrict_name() {
        return subdistrict_name;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
