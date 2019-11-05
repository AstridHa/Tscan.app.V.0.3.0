package com.tscan.app.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface api {

String BASE_URL = "https://192.168.1.100/";

@POST("api/haccp/get_mobile_token")
Call<Model_getToken> login_getToken(@Body String params);

@POST("api/haccp/get_task_data")
Call<Model_getData> login_getData(@Body String data);

@POST("/api/haccp/submit_task_results")
Call<Model_submitResult> save_result(@Body String result);
//
//@POST("api/mobile/request_readings/")
//Call<Model_Readings_Result> request_readings(@Body String data);
//
//@POST("api/mobile/refresh_token/")
//Call<Model_RefreshToken_Result> refresh_token();
//
//@POST("/api/mobile/change_email/")
//Call<Model_ChangeEmail_Result> change_email_address(@Body String data);
//
//@POST("/api/mobile/change_password/")
//Call<Model_ChangePass_Result> change_password(@Body String data);
}
