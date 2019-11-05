package com.tscan.app.Data;


import android.support.annotation.Nullable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * CREATE TABLE haccp_task_definition (
 * id INT(11) AUTO_INCREMENT NOT NULL COMMENT 'ID of the task definition',
 * `name` VARCHAR(255) NOT NULL COMMENT 'Name of the task definition',
 * company_id INT(11) NOT NULL COMMENT 'Company id this task definition applies to',
 * department_id INT(11) NOT NULL COMMENT 'Department id this task definition applies to',
 * location_id INT(11) NOT NULL COMMENT 'Location id this task definition applies to',
 * haccp_task_category_id INT(11) NOT NULL COMMENT 'Category of this task definition',
 * haccp_task_result_type_id INT(11) NOT NULL COMMENT 'Result type of this task definition',
 * instruction_text TEXT NOT NULL COMMENT 'Instructions to perform this task',
 * quantity_required INT(11) NOT NULL COMMENT 'Number of task results required per window',
 * valid_from_unix INT(11) NOT NULL COMMENT 'Only create windows after this time',
 * valid_to_unix INT(11) COMMENT 'Only create windows up to this time' ,
 * windows_generated_to_unix INT(11) COMMENT 'Windows have been created up to this time',
 * PRIMARY KEY (id),
 * KEY ix_haccp_task_definition_01 (company_id, department_id, id));
 */

@Entity(tableName = "haccp_task_definition")
public class Model_haccp_task_definition {


    @PrimaryKey
    @ColumnInfo(name = "definition_id")
    public int task_definition_id;

    @ColumnInfo(name = "name")
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

    @Nullable
    @ColumnInfo(name = "valid_to_unix")
    public Integer task_definition_valid_to_unix;

    @Nullable
    @ColumnInfo(name = "windows_generated_to_unix")
    public Integer task_definition_windows_generated_to_unix;


    public Model_haccp_task_definition() {
        //KEEP EMPTY
    }

    public Model_haccp_task_definition(int task_definition_id, String name, int company_id, int department_id, int location_id, int haccp_task_category_id, int haccp_task_result_type_id,
                                       String instruction_text, int quantity_required, int valid_from_unix, Integer valid_to_unix,
                                       Integer windows_generated_to_unix) {
        this.task_definition_id = task_definition_id;
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

}
