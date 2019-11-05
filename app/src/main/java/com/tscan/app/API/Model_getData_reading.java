package com.tscan.app.API;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_getData_reading {

    @SerializedName("haccp_task_result_types")
    private List<Model_gethaccp_task_result_types> haccp_task_result_types;

    @SerializedName("haccp_task_categories")
    private List<Model_gethaccp_task_categories> haccp_task_categories;

    @SerializedName("haccp_task_result_statuses")
    private List<Model_gethaccp_task_result_statuses> haccp_task_result_statuses;

    @SerializedName("haccp_food_item_categories")
    private List<Model_gethaccp_food_item_categories> haccp_food_item_categories;

    @SerializedName("haccp_food_item_types")
    private List<Model_getHaccp_food_item_types> haccp_food_item_types;

    @SerializedName("haccp_corrective_actions")
    private List<Model_getHaccp_corrective_actions> haccp_corrective_actions;

    @SerializedName("locations")
    private List<Model_getlocations> locations;



    public Model_getData_reading(
            List<Model_gethaccp_task_result_types> haccp_task_result_types,
            List<Model_gethaccp_task_result_statuses> haccp_task_result_statuses,
            List<Model_gethaccp_task_categories> haccp_task_categories,
            List<Model_getlocations> locations,
            List<Model_gethaccp_food_item_categories> haccp_food_item_categories,
            List<Model_getHaccp_food_item_types> haccp_food_item_types,
            List<Model_getHaccp_corrective_actions> haccp_corrective_actions)
    {
        this.haccp_task_result_types = haccp_task_result_types;
        this.haccp_task_result_statuses = haccp_task_result_statuses;
        this.haccp_task_categories = haccp_task_categories;
        this.locations = locations;
        this.haccp_food_item_categories = haccp_food_item_categories;
        this.haccp_food_item_types = haccp_food_item_types;
        this.haccp_corrective_actions = haccp_corrective_actions;
    }



    public List<Model_gethaccp_task_result_types> getHaccp_task_result_types() {
        return haccp_task_result_types;
    }

    public List<Model_gethaccp_task_categories> getHaccp_task_categories() {
        return haccp_task_categories;
    }

    public List<Model_gethaccp_task_result_statuses> getHaccp_task_result_statuses() {
        return haccp_task_result_statuses;
    }

    public List<Model_gethaccp_food_item_categories> getHaccp_food_item_categories() {
        return haccp_food_item_categories;
    }

    public List<Model_getHaccp_food_item_types> getHaccp_food_item_types() {
        return haccp_food_item_types;
    }

    public List<Model_getHaccp_corrective_actions> getHaccp_corrective_actions() {
        return haccp_corrective_actions;
    }




    public List<Model_getlocations> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }




    public class Model_gethaccp_task_result_types {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;


        public Model_gethaccp_task_result_types(int id, String name) {
            this.id = id;
            this.name = name;

        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    public class Model_gethaccp_task_categories {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("company_id")
        private int company_id;

        @SerializedName("department_id")
        private int department_id;

        @SerializedName("sort_order")
        private String sort_order;


        public Model_gethaccp_task_categories(int id, String name, int company_id, int department_id, String sort_order) {
            this.id = id;
            this.name = name;
            this.company_id = company_id;
            this.department_id = department_id;
            this.sort_order = sort_order;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCompany_id() {
            return company_id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public String getSort_order() {
            return sort_order;
        }
    }

    public class Model_gethaccp_task_result_statuses {

        @SerializedName("haccp_task_result_type_id")
        private int haccp_task_result_type_id;

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("corrective_action_required")
        private String corrective_action_required;


        public Model_gethaccp_task_result_statuses(int haccp_task_result_type_id, int id, String name, String corrective_action_required) {
            this.haccp_task_result_type_id = haccp_task_result_type_id;
            this.id = id;
            this.name = name;
            this.corrective_action_required = corrective_action_required;
        }

        public int getHaccp_task_result_type_id() {
            return haccp_task_result_type_id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCorrective_action_required() {
            return corrective_action_required;
        }
    }

    public class Model_gethaccp_food_item_categories {

        @SerializedName("id")
        private int id_food_item_categories;
        @SerializedName("company_id")
        private int company_id_food_item_categories;
        @SerializedName("name")
        private String name_food_item_categories;
        @SerializedName("core_cooking_pass_temperature")
        private int food_item_categories_core_cooking_pass_temperature;


        public Model_gethaccp_food_item_categories(int id_food_item_categories, int company_id_food_item_categories, String name_food_item_categories, int food_item_categories_core_cooking_pass_temperature) {
            this.id_food_item_categories = id_food_item_categories;
            this.company_id_food_item_categories = company_id_food_item_categories;
            this.name_food_item_categories = name_food_item_categories;
            this.food_item_categories_core_cooking_pass_temperature = food_item_categories_core_cooking_pass_temperature;
        }


        public int getId_food_item_categories() {
            return id_food_item_categories;
        }

        public void setId_food_item_categories(int id_food_item_categories) {
            this.id_food_item_categories = id_food_item_categories;
        }

        public int getCompany_id_food_item_categories() {
            return company_id_food_item_categories;
        }

        public void setCompany_id_food_item_categories(int company_id_food_item_categories) {
            this.company_id_food_item_categories = company_id_food_item_categories;
        }

        public String getName_food_item_categories() {
            return name_food_item_categories;
        }

        public void setName_food_item_categories(String name_food_item_categories) {
            this.name_food_item_categories = name_food_item_categories;
        }

        public int getFood_item_categories_core_cooking_pass_temperature() {
            return food_item_categories_core_cooking_pass_temperature;
        }

        public void setFood_item_categories_core_cooking_pass_temperature(int food_item_categories_core_cooking_pass_temperature) {
            this.food_item_categories_core_cooking_pass_temperature = food_item_categories_core_cooking_pass_temperature;
        }
    }

    public class Model_getHaccp_food_item_types{
        @SerializedName("id")
        private int id_food_item_types;
        @SerializedName("company_id")
        private int haccp_food_item_company_id;
        @SerializedName("haccp_food_item_category_id")
        private int haccp_food_item_category_id;
        @SerializedName("name")
        private String name_food_item_types;
        @SerializedName("core_cooking_pass_temperature")
        private int core_cooking_pass_temperature;


        public int getId_food_item_types() {
            return id_food_item_types;
        }

        public void setId_food_item_types(int id_food_item_types) {
            this.id_food_item_types = id_food_item_types;
        }

        public int getHaccp_food_item_category_id() {
            return haccp_food_item_category_id;
        }

        public void setHaccp_food_item_category_id(int haccp_food_item_category_id) {
            this.haccp_food_item_category_id = haccp_food_item_category_id;
        }

        public int getHaccp_food_item_company_id() {
            return haccp_food_item_company_id;
        }

        public void setHaccp_food_item_company_id(int haccp_food_item_company_id) {
            this.haccp_food_item_company_id = haccp_food_item_company_id;
        }

        public String getName_food_item_types() {
            return name_food_item_types;
        }

        public void setName_food_item_types(String name_food_item_types) {
            this.name_food_item_types = name_food_item_types;
        }

        public int getCore_cooking_pass_temperature() {
            return core_cooking_pass_temperature;
        }

        public void setCore_cooking_pass_temperature(int core_cooking_pass_temperature) {
            this.core_cooking_pass_temperature = core_cooking_pass_temperature;
        }


        public Model_getHaccp_food_item_types(int id, int haccp_food_item_category_id, int haccp_food_item_company_id, String name, int core_cooking_pass_temperature) {
            this.id_food_item_types = id;
            this.haccp_food_item_category_id = haccp_food_item_category_id;
            this.haccp_food_item_company_id = haccp_food_item_company_id;
            this.name_food_item_types = name;
            this.core_cooking_pass_temperature = core_cooking_pass_temperature;
        }
    }

    public class Model_getHaccp_corrective_actions{
        @SerializedName("id")
        private int corrective_action_id;
        @SerializedName("company_id")
        private int corrective_action_company_id;
        @SerializedName("haccp_task_result_type_id")
        private int corrective_action_haccp_task_result_type_id;
        @SerializedName("name")
        private String corrective_action_name;
        @SerializedName("new_haccp_task_result_status_id")
        private int corrective_action_new_haccp_task_result_status_id;



        public int getCorrective_action_id() {
            return corrective_action_id;
        }

        public void setCorrective_action_id(int corrective_action_id) {
            this.corrective_action_id = corrective_action_id;
        }

        public int getCorrective_action_company_id() {
            return corrective_action_company_id;
        }

        public void setCorrective_action_company_id(int corrective_action_company_id) {
            this.corrective_action_company_id = corrective_action_company_id;
        }

        public int getCorrective_action_haccp_task_result_type_id() {
            return corrective_action_haccp_task_result_type_id;
        }

        public void setCorrective_action_haccp_task_result_type_id(int corrective_action_haccp_task_result_type_id) {
            this.corrective_action_haccp_task_result_type_id = corrective_action_haccp_task_result_type_id;
        }

        public String getCorrective_action_name() {
            return corrective_action_name;
        }

        public void setCorrective_action_name(String corrective_action_name) {
            this.corrective_action_name = corrective_action_name;
        }

        public int getCorrective_action_new_haccp_task_result_status_id() {
            return corrective_action_new_haccp_task_result_status_id;
        }

        public void setCorrective_action_new_haccp_task_result_status_id(int corrective_action_new_haccp_task_result_status_id) {
            this.corrective_action_new_haccp_task_result_status_id = corrective_action_new_haccp_task_result_status_id;
        }


        public Model_getHaccp_corrective_actions(int corrective_action_id, int corrective_action_company_id, int corrective_action_haccp_task_result_type_id, String corrective_action_name, int corrective_action_new_haccp_task_result_status_id) {
            this.corrective_action_id = corrective_action_id;
            this.corrective_action_company_id = corrective_action_company_id;
            this.corrective_action_haccp_task_result_type_id = corrective_action_haccp_task_result_type_id;
            this.corrective_action_name = corrective_action_name;
            this.corrective_action_new_haccp_task_result_status_id = corrective_action_new_haccp_task_result_status_id;
        }





    }

    public class Model_getlocations {

        @SerializedName("haccp_task_definitions")
        public List<Model_getTask_definitions> haccp_task_definitions;
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("company_id")
        private int company_id;
        @SerializedName("department_id")
        private int department_id;


        public Model_getlocations(int id, String name, int company_id, int department_id, List<Model_getTask_definitions> haccp_task_definitions) {
            this.id = id;
            this.name = name;
            this.company_id = company_id;
            this.department_id = department_id;
            this.haccp_task_definitions = haccp_task_definitions;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCompany_id() {
            return company_id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public List<Model_getTask_definitions> getHaccp_task_definitions() {
            return haccp_task_definitions;
        }
    }

    public class Model_getTask_definitions {

        @SerializedName("id")
        private int task_definition_id;

        @SerializedName("name")
        private String task_definition_name;

        @SerializedName("company_id")
        private int task_definition_company_id;

        @SerializedName("department_id")
        private int task_definition_department_id;

        @SerializedName("location_id")
        private int task_definition_location_id;

        @SerializedName("haccp_task_category_id")
        private int task_definition_task_category_id;

        @SerializedName("haccp_task_result_type_id")
        private int task_definition_task_result_type_id;

        @SerializedName("instruction_text")
        private String task_definition_instruction_text;

        @SerializedName("quantity_required")
        private int task_definition_quantity_required;

        @SerializedName("valid_from_unix")
        private int task_definition_valid_from_unix;

        @SerializedName("valid_to_unix")
        private Integer task_definition_valid_to_unix;

        @SerializedName("windows_generated_to_unix")
        private Integer task_definition_windows_generated_to_unix;

        @SerializedName("haccp_task_windows")
        private List<Model_gethaccp_task_windows> task_definition_task_windows;


        public Model_getTask_definitions(int id, String name, int company_id, int department_id, int location_id, int haccp_task_category_id, int haccp_task_result_type_id,
                                         String instruction_text, int quantity_required, int valid_from_unix, Integer valid_to_unix,
                                         Integer windows_generated_to_unix, List<Model_gethaccp_task_windows> haccp_task_windows) {
            this.task_definition_id = id;
            this.task_definition_name = name;
            this.task_definition_company_id = company_id;
            this.task_definition_department_id = department_id;
            this.task_definition_location_id = location_id;
            this.task_definition_task_category_id = haccp_task_category_id;
            this.task_definition_task_result_type_id = haccp_task_result_type_id;
            this.task_definition_instruction_text = instruction_text;
            this.task_definition_quantity_required = quantity_required;
            this.task_definition_valid_from_unix = valid_from_unix;
            this.task_definition_valid_to_unix = valid_to_unix;
            this.task_definition_windows_generated_to_unix = windows_generated_to_unix;
            this.task_definition_task_windows = haccp_task_windows;
        }

        public int getTask_definition_id() {
            return task_definition_id;
        }

        public void setTask_definition_id(int task_definition_id) {
            this.task_definition_id = task_definition_id;
        }

        public String getTask_definition_name() {
            return task_definition_name;
        }

        public void setTask_definition_name(String task_definition_name) {
            this.task_definition_name = task_definition_name;
        }

        public int getTask_definition_company_id() {
            return task_definition_company_id;
        }

        public void setTask_definition_company_id(int task_definition_company_id) {
            this.task_definition_company_id = task_definition_company_id;
        }

        public int getTask_definition_department_id() {
            return task_definition_department_id;
        }

        public void setTask_definition_department_id(int task_definition_department_id) {
            this.task_definition_department_id = task_definition_department_id;
        }

        public int getTask_definition_location_id() {
            return task_definition_location_id;
        }

        public void setTask_definition_location_id(int task_definition_location_id) {
            this.task_definition_location_id = task_definition_location_id;
        }

        public int getTask_definition_task_category_id() {
            return task_definition_task_category_id;
        }

        public void setTask_definition_task_category_id(int task_definition_task_category_id) {
            this.task_definition_task_category_id = task_definition_task_category_id;
        }

        public int getTask_definition_task_result_type_id() {
            return task_definition_task_result_type_id;
        }

        public void setTask_definition_task_result_type_id(int task_definition_task_result_type_id) {
            this.task_definition_task_result_type_id = task_definition_task_result_type_id;
        }

        public String getTask_definition_instruction_text() {
            return task_definition_instruction_text;
        }

        public void setTask_definition_instruction_text(String task_definition_instruction_text) {
            this.task_definition_instruction_text = task_definition_instruction_text;
        }

        public int getTask_definition_quantity_required() {
            return task_definition_quantity_required;
        }

        public void setTask_definition_quantity_required(int task_definition_quantity_required) {
            this.task_definition_quantity_required = task_definition_quantity_required;
        }

        public int getTask_definition_valid_from_unix() {
            return task_definition_valid_from_unix;
        }

        public void setTask_definition_valid_from_unix(int task_definition_valid_from_unix) {
            this.task_definition_valid_from_unix = task_definition_valid_from_unix;
        }

        public Integer getTask_definition_valid_to_unix() {
            return task_definition_valid_to_unix;
        }

        public void setTask_definition_valid_to_unix(Integer task_definition_valid_to_unix) {
            this.task_definition_valid_to_unix = task_definition_valid_to_unix;
        }

        public Integer getTask_definition_windows_generated_to_unix() {
            return task_definition_windows_generated_to_unix;
        }

        public void setTask_definition_windows_generated_to_unix(Integer task_definition_windows_generated_to_unix) {
            this.task_definition_windows_generated_to_unix = task_definition_windows_generated_to_unix;
        }

        public List<Model_gethaccp_task_windows> getTask_definition_task_windows() {
            return task_definition_task_windows;
        }

        public void setTask_definition_task_windows(List<Model_gethaccp_task_windows> task_definition_task_windows) {
            this.task_definition_task_windows = task_definition_task_windows;
        }
    }

    public class Model_gethaccp_task_windows {

        @SerializedName("id")
        private int id;

        @SerializedName("company_id")
        private int company_id;

        @SerializedName("department_id")
        private int department_id;

        @SerializedName("location_id")
        private int location_id;

        @SerializedName("haccp_task_definition_id")
        private int haccp_task_definition_id;

        @SerializedName("start_time_unix")
        private int start_time_unix;

        @SerializedName("end_time_unix")
        private int end_time_unix;


        public Model_gethaccp_task_windows(int id, int company_id, int department_id, int location_id, int haccp_task_definition_id, int start_time_unix, int end_time_unix) {
            this.id = id;
            this.company_id = company_id;
            this.department_id = department_id;
            this.location_id = location_id;
            this.haccp_task_definition_id = haccp_task_definition_id;
            this.start_time_unix = start_time_unix;
            this.end_time_unix = end_time_unix;
        }

        public int getId() {
            return id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public int getLocation_id() {
            return location_id;
        }

        public int getHaccp_task_definition_id() {
            return haccp_task_definition_id;
        }

        public int getStart_time_unix() {
            return start_time_unix;
        }

        public int getEnd_time_unix() {
            return end_time_unix;
        }
    }

}


