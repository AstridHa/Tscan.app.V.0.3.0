package com.tscan.app.Utils;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.tscan.app.Activities.Activity_login;
import com.tscan.app.Data.Model_mobile_device;
import com.tscan.app.Data.Singleton_Settings;
import com.tscan.app.ViewModel.ViewModel_haccp_queries;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class Decode_Token {

    private ViewModel_haccp_queries viewModel_haccp_queries;

    public void decode_token(String token, Activity_login activity_login) {

        viewModel_haccp_queries = ViewModelProviders.of(activity_login).get(ViewModel_haccp_queries.class);

        try {
                String[] split = token.split("\\.");
                try {
                    JSONObject obj = new JSONObject( getJSON(split[1]));
                    Singleton_Settings.getsettings_instance().setToken(token);
                    Singleton_Settings.getsettings_instance().setCompany_id(obj.getJSONObject("tscan").getInt("company_id"));
                    Singleton_Settings.getsettings_instance().setCompany_name(String.valueOf(obj.getJSONObject("tscan").getString("company_name")));
                    Singleton_Settings.getsettings_instance().setDepartment_id(obj.getJSONObject("tscan").getInt("department_id"));
                    Singleton_Settings.getsettings_instance().setDepartment_name(String.valueOf(obj.getJSONObject("tscan").getString("department_name")));
                    Singleton_Settings.getsettings_instance().setExp(String.valueOf((obj.getString("iss"))));
                    Singleton_Settings.getsettings_instance().setIat(String.valueOf((obj.getString("iat"))));
                    Singleton_Settings.getsettings_instance().setIss(String.valueOf((obj.getString("iss"))));
                    Singleton_Settings.getsettings_instance().setMobile_device_name(String.valueOf(obj.getJSONObject("tscan").getString("mobile_device_name")));
                    Singleton_Settings.getsettings_instance().setMobile_device_serial_number(String.valueOf(obj.getJSONObject("tscan").getString("mobile_device_serial_number")));

                    Model_mobile_device device_table = new Model_mobile_device();
                    device_table.setApi_key(String.valueOf(000));
                    device_table.setCompany_id(Integer.parseInt(obj.getJSONObject("tscan").getString("company_id")));
                    device_table.setDepartment_id(Integer.parseInt(obj.getJSONObject("tscan").getString("department_id")));
                    device_table.setDevice_serial_nuber(000);
                    device_table.setEnabled(0);
                    device_table.setName("Why name");
                    viewModel_haccp_queries.insert_moble_device(device_table);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    private String getJSON(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }

}


