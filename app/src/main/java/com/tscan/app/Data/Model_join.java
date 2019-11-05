package com.tscan.app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "model_join",
        foreignKeys = {
                @ForeignKey(entity = Model_haccp_task_definition.class,
                        parentColumns = "definition_id",
                        childColumns = "task_definition_id"),

                @ForeignKey(entity = Model_haccp_task_window.class,
                        parentColumns = "task_window_id",
                        childColumns = "window_id",
                        onUpdate = ForeignKey.CASCADE
                )
        }
)
public class Model_join {

    @PrimaryKey
    @ColumnInfo(name = "task_definition_id")
    public int task_definition_id;

    @ColumnInfo(name = "task_definition_name")
    public String task_definition_name;

    @ColumnInfo(name = "company_id")
    public int task_definition_company_id;

    @ColumnInfo(name = "department_id")
    public int task_definition_department_id;

    @ColumnInfo(name = "location_id")
    public int task_definition_location_id;

    @ColumnInfo(name = "haccp_task_category_id")
    public int task_definition_task_category_id;

    @ColumnInfo(name = "haccp_task_result_type_id")
    public int task_definition_task_result_type_id;

    @ColumnInfo(name = "instruction_text")
    public String task_definition_instruction_text;

    @ColumnInfo(name = "quantity_required")
    public int task_definition_quantity_required;

    @ColumnInfo(name = "valid_from_unix")
    public int task_definition_valid_from_unix;

    @ColumnInfo(name = "valid_to_unix")
    public Integer task_definition_valid_to_unix;

    @ColumnInfo(name = "windows_generated_to_unix")
    public Integer task_definition_windows_generated_to_unix;

    @ColumnInfo(name = "category_name")
    public String category_name;

    @ColumnInfo(name = "category_sort_order")
    public Integer category_sort_order;

    @ColumnInfo(name = "result_type_id")
    public Integer result_type_id;

    @ColumnInfo(name = "result_type_name")
    public String result_type_name;

    @ColumnInfo(name = "window_id")
    public Integer window_id;

    @ColumnInfo(name = "window_start_time_unix")
    public Integer window_start_time_unix;

    @ColumnInfo(name = "window_end_time_unix")
    public Integer window_end_time_unix;

    @ColumnInfo(name = "result_count")
    public Integer result_count;

//    @ColumnInfo(name = "upload_status")
//    public int upload_status;

    public Model_join() {
        //KEEP EMPTY
    }

    public Model_join(int task_definition_id, String task_definition_name, int task_definition_company_id, int task_definition_department_id, int task_definition_location_id, int task_definition_task_category_id, int task_definition_task_result_type_id, String task_definition_instruction_text, int task_definition_quantity_required, int task_definition_valid_from_unix, Integer task_definition_valid_to_unix, Integer task_definition_windows_generated_to_unix, String category_name, Integer category_sort_order, Integer result_type_id, String result_type_name, Integer window_id, Integer window_start_time_unix, Integer window_end_time_unix, Integer result_count) {
        this.task_definition_id = task_definition_id;
        this.task_definition_name = task_definition_name;
        this.task_definition_company_id = task_definition_company_id;
        this.task_definition_department_id = task_definition_department_id;
        this.task_definition_location_id = task_definition_location_id;
        this.task_definition_task_category_id = task_definition_task_category_id;
        this.task_definition_task_result_type_id = task_definition_task_result_type_id;
        this.task_definition_instruction_text = task_definition_instruction_text;
        this.task_definition_quantity_required = task_definition_quantity_required;
        this.task_definition_valid_from_unix = task_definition_valid_from_unix;
        this.task_definition_valid_to_unix = task_definition_valid_to_unix;
        this.task_definition_windows_generated_to_unix = task_definition_windows_generated_to_unix;
        this.category_name = category_name;
        this.category_sort_order = category_sort_order;
        this.result_type_id = result_type_id;
        this.result_type_name = result_type_name;
        this.window_id = window_id;
        this.window_start_time_unix = window_start_time_unix;
        this.window_end_time_unix = window_end_time_unix;
        this.result_count = result_count;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getCategory_sort_order() {
        return category_sort_order;
    }

    public void setCategory_sort_order(Integer category_sort_order) {
        this.category_sort_order = category_sort_order;
    }

    public Integer getResult_type_id() {
        return result_type_id;
    }

    public void setResult_type_id(Integer result_type_id) {
        this.result_type_id = result_type_id;
    }

    public String getResult_type_name() {
        return result_type_name;
    }

    public void setResult_type_name(String result_type_name) {
        this.result_type_name = result_type_name;
    }

    public Integer getWindow_id() {
        return window_id;
    }

    public void setWindow_id(Integer window_id) {
        this.window_id = window_id;
    }

    public Integer getWindow_start_time_unix() {
        return window_start_time_unix;
    }

    public void setWindow_start_time_unix(Integer window_start_time_unix) {
        this.window_start_time_unix = window_start_time_unix;
    }

    public Integer getWindow_end_time_unix() {
        return window_end_time_unix;
    }

    public void setWindow_end_time_unix(Integer window_end_time_unix) {
        this.window_end_time_unix = window_end_time_unix;
    }

    public Integer getResult_count() {
        return result_count;
    }

    public void setResult_count(Integer result_count) {
        this.result_count = result_count;
    }


}
