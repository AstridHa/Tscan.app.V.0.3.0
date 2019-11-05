package com.tscan.app.API;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_getData {

        @SerializedName("message")
        private String message;

        @SerializedName("success")
        private Boolean success;

        @SerializedName("task_data")
        private Model_getData_reading task_data  ;


        public Model_getData(String message, Boolean success, Model_getData_reading task_data  ) {
            this.success = success;
            this.message = message;
            this.task_data   = task_data  ;
        }

        public Boolean getSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public Model_getData_reading getTask_data() {
        return task_data ;
    }



}
