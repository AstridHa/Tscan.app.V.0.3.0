//package com.tscan.app.Data;
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//
//@Entity(tableName = "model_window_joined_data")
//public class Model_window_joined_data {
//
//    @PrimaryKey(autoGenerate = true)
//    public int joined_id;
//    @ColumnInfo(name = "joined_window_id")
//    public int joined_window_id;
//    @ColumnInfo(name = "joined_definition_id")
//    public int joined_definition_id;
//    @ColumnInfo(name = "joined_definition_name")
//    public String joined_definition_name;
//    @ColumnInfo(name = "joined_company_id")
//    public int joined_definition_company_id;
//    @ColumnInfo(name = "joined_department_id")
//    public int joined_definition_department_id;
//    @ColumnInfo(name = "joined_location_id")
//    public int joined_definition_location_id;
//    @ColumnInfo(name = "joined_category_id")
//    public int joined_definition_joined_category_id;
//    @ColumnInfo(name = "joined_result_type_id")
//    public int joined_definition_joined_result_type_id;
//    @ColumnInfo(name = "joined_instruction_text")
//    public String joined_definition_instruction_text;
//    @ColumnInfo(name = "joined_quantity_required")
//    public int joined_definition_quantity_required;
//    @ColumnInfo(name = "joined_generated_to_unix")
//    public Integer joined_definition_windows_generated_to_unix;
//    @ColumnInfo(name = "joined_category_name")
//    public String joined_category_name;
//    @ColumnInfo(name = "joined_category_sort_order")
//    public Integer joined_category_sort_order;
//    @ColumnInfo(name = "joined_result_type_name")
//    public String joined_result_type_name;
//    @ColumnInfo(name = "joined_window_start_time_unix")
//    public Integer joined_window_start_time_unix;
//    @ColumnInfo(name = "joined_window_end_time_unix")
//    public Integer joined_window_end_time_unix;
//    @ColumnInfo(name = "joined_result_count")
//    public int joined_result_count;
//    @ColumnInfo(name = "joined_window_status")
//    public int joined_window_status;
//
//    public Model_window_joined_data(int joined_id, int joined_definition_id, String joined_definition_name, int joined_definition_company_id, int joined_definition_department_id, int joined_definition_location_id, int joined_definition_joined_category_id, int joined_definition_joined_result_type_id, String joined_definition_instruction_text, int joined_definition_quantity_required, Integer joined_definition_windows_generated_to_unix, String joined_category_name, Integer joined_category_sort_order, String joined_result_type_name, int joined_window_id, Integer joined_window_start_time_unix, Integer joined_window_end_time_unix, int joined_result_count, int joined_window_status) {
//        this.joined_id = joined_id;
//        this.joined_definition_id = joined_definition_id;
//        this.joined_definition_name = joined_definition_name;
//        this.joined_definition_company_id = joined_definition_company_id;
//        this.joined_definition_department_id = joined_definition_department_id;
//        this.joined_definition_location_id = joined_definition_location_id;
//        this.joined_definition_joined_category_id = joined_definition_joined_category_id;
//        this.joined_definition_joined_result_type_id = joined_definition_joined_result_type_id;
//        this.joined_definition_instruction_text = joined_definition_instruction_text;
//        this.joined_definition_quantity_required = joined_definition_quantity_required;
//        this.joined_definition_windows_generated_to_unix = joined_definition_windows_generated_to_unix;
//        this.joined_category_name = joined_category_name;
//        this.joined_category_sort_order = joined_category_sort_order;
//        this.joined_result_type_name = joined_result_type_name;
//        this.joined_window_id = joined_window_id;
//        this.joined_window_start_time_unix = joined_window_start_time_unix;
//        this.joined_window_end_time_unix = joined_window_end_time_unix;
//        this.joined_result_count = joined_result_count;
//        this.joined_window_status = joined_window_status;
//    }
//
//    public Model_window_joined_data() {
//        //KEEP EMPTY
//    }
//
//
//
//    public int getJoined_id() {
//        return joined_id;
//    }
//
//    public void setJoined_id(int joined_id) {
//        this.joined_id = joined_id;
//    }
//
//    public int getJoined_definition_id() {
//        return joined_definition_id;
//    }
//
//    public void setJoined_definition_id(int joined_definition_id) {
//        this.joined_definition_id = joined_definition_id;
//    }
//
//    public String getJoined_definition_name() {
//        return joined_definition_name;
//    }
//
//    public void setJoined_definition_name(String joined_definition_name) {
//        this.joined_definition_name = joined_definition_name;
//    }
//
//    public int getJoined_definition_company_id() {
//        return joined_definition_company_id;
//    }
//
//    public void setJoined_definition_company_id(int joined_definition_company_id) {
//        this.joined_definition_company_id = joined_definition_company_id;
//    }
//
//    public int getJoined_definition_department_id() {
//        return joined_definition_department_id;
//    }
//
//    public void setJoined_definition_department_id(int joined_definition_department_id) {
//        this.joined_definition_department_id = joined_definition_department_id;
//    }
//
//    public int getJoined_definition_location_id() {
//        return joined_definition_location_id;
//    }
//
//    public void setJoined_definition_location_id(int joined_definition_location_id) {
//        this.joined_definition_location_id = joined_definition_location_id;
//    }
//
//    public int getJoined_definition_joined_category_id() {
//        return joined_definition_joined_category_id;
//    }
//
//    public void setJoined_definition_joined_category_id(int joined_definition_joined_category_id) {
//        this.joined_definition_joined_category_id = joined_definition_joined_category_id;
//    }
//
//    public int getJoined_definition_joined_result_type_id() {
//        return joined_definition_joined_result_type_id;
//    }
//
//    public void setJoined_definition_joined_result_type_id(int joined_definition_joined_result_type_id) {
//        this.joined_definition_joined_result_type_id = joined_definition_joined_result_type_id;
//    }
//
//    public String getJoined_definition_instruction_text() {
//        return joined_definition_instruction_text;
//    }
//
//    public void setJoined_definition_instruction_text(String joined_definition_instruction_text) {
//        this.joined_definition_instruction_text = joined_definition_instruction_text;
//    }
//
//    public int getJoined_definition_quantity_required() {
//        return joined_definition_quantity_required;
//    }
//
//    public void setJoined_definition_quantity_required(int joined_definition_quantity_required) {
//        this.joined_definition_quantity_required = joined_definition_quantity_required;
//    }
//
//    public Integer getJoined_definition_windows_generated_to_unix() {
//        return joined_definition_windows_generated_to_unix;
//    }
//
//    public void setJoined_definition_windows_generated_to_unix(Integer joined_definition_windows_generated_to_unix) {
//        this.joined_definition_windows_generated_to_unix = joined_definition_windows_generated_to_unix;
//    }
//
//    public String getJoined_category_name() {
//        return joined_category_name;
//    }
//
//    public void setJoined_category_name(String joined_category_name) {
//        this.joined_category_name = joined_category_name;
//    }
//
//    public Integer getJoined_category_sort_order() {
//        return joined_category_sort_order;
//    }
//
//    public void setJoined_category_sort_order(Integer joined_category_sort_order) {
//        this.joined_category_sort_order = joined_category_sort_order;
//    }
//
//    public String getJoined_result_type_name() {
//        return joined_result_type_name;
//    }
//
//    public void setJoined_result_type_name(String joined_result_type_name) {
//        this.joined_result_type_name = joined_result_type_name;
//    }
//
//    public int getJoined_window_id() {
//        return joined_window_id;
//    }
//
//    public void setJoined_window_id(int joined_window_id) {
//        this.joined_window_id = joined_window_id;
//    }
//
//    public Integer getJoined_window_start_time_unix() {
//        return joined_window_start_time_unix;
//    }
//
//    public void setJoined_window_start_time_unix(Integer joined_window_start_time_unix) {
//        this.joined_window_start_time_unix = joined_window_start_time_unix;
//    }
//
//    public Integer getJoined_window_end_time_unix() {
//        return joined_window_end_time_unix;
//    }
//
//    public void setJoined_window_end_time_unix(Integer joined_window_end_time_unix) {
//        this.joined_window_end_time_unix = joined_window_end_time_unix;
//    }
//
//    public int getJoined_result_count() {
//        return joined_result_count;
//    }
//
//    public void setJoined_result_count(int joined_result_count) {
//        this.joined_result_count = joined_result_count;
//    }
//
//    public int getJoined_window_status() {
//        return joined_window_status;
//    }
//
//    public void setJoined_window_status(int joined_window_status) {
//        this.joined_window_status = joined_window_status;
//    }
//
//
//}
