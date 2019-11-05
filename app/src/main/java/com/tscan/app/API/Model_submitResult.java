package com.tscan.app.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_submitResult {

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("results")
    private Model_getResults results ;




    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Model_getResults getResults() {
        return results;
    }



    public Model_submitResult(String message, Boolean success, Model_getResults results  ) {
        this.success = success;
        this.message = message;
        this.results = results;
    }


    public class Model_getResults {
        private Model_getHaccp_task_result_core_cooking haccp_task_result_core_cooking;

        public Model_getHaccp_task_result_core_cooking getHaccp_task_result_core_cooking() {
            return haccp_task_result_core_cooking;
        }

        public Model_getResults(Model_getHaccp_task_result_core_cooking haccp_task_result_core_cooking){
            this.haccp_task_result_core_cooking = haccp_task_result_core_cooking;
        }
    }

    public class Model_getHaccp_task_result_core_cooking {
        private List<Model_getSuccesses> successes;
        private List<Model_getFailures> failures;


        public List<Model_getSuccesses> getSuccesses() {
            return successes;
        }

        public List<Model_getFailures> getFailures() {
            return failures;
        }

        public Model_getHaccp_task_result_core_cooking(List<Model_getSuccesses> successes, List<Model_getFailures> failures) {
            this.successes = successes;
            this.failures = failures;
        }
    }

    public class Model_getSuccesses {
        private String haccp_task_window_id;
        private String timestamp_unix;
        private String result;

        public String getHaccp_task_window_id() {
            return haccp_task_window_id;
        }

        public String getTimestamp_unix() {
            return timestamp_unix;
        }

        public String getResult() {
            return result;
        }

        public Model_getSuccesses(String haccp_task_window_id, String timestamp_unix, String result) {
            this.haccp_task_window_id = haccp_task_window_id;
            this.timestamp_unix = timestamp_unix;
            this.result = result;
        }

    }

    private class Model_getFailures {
        private String haccp_task_window_id;
        private String timestamp_unix;
        private String result;

        public String getHaccp_task_window_id() {
            return haccp_task_window_id;
        }

        public String getTimestamp_unix() {
            return timestamp_unix;
        }

        public String getResult() {
            return result;
        }

        public Model_getFailures(String haccp_task_window_id, String timestamp_unix, String result) {
            this.haccp_task_window_id = haccp_task_window_id;
            this.timestamp_unix = timestamp_unix;
            this.result = result;
        }

    }
}
