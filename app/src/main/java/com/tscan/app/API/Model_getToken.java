package com.tscan.app.API;

import com.google.gson.annotations.SerializedName;

public class Model_getToken {

        @SerializedName("message")
        private String message;

        @SerializedName("success")
        private Boolean success;

        @SerializedName("token")
        private String token;


        public Model_getToken(String message, Boolean success, String token) {
            this.success = success;
            this.message = message;
            this.token = token;
        }

        public Boolean getSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public String getToken() {
            return token;
        }


}
