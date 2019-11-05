package com.tscan.app.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 CREATE TABLE haccp_corrective_action (

 `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
 `company_id` INT(11) NOT NULL COMMENT 'Company ID',
 `haccp_task_result_type_id` NOT NULL COMMENT 'task type to which this applies',
 `name` VARCHAR(255) COMMENT 'Display text',
 `new_haccp_task_result_status_id` INT(11) NOT NULL COMMENT 'New task status when this action is applied',

 PRIMARY KEY(`id`),
 KEY `fk_haccp_corrective_action01` (`company_id`),
 KEY `fk_haccp_corrective_action02` (`haccp_task_result_type_id`),
 KEY `fk_haccp_corrective_action03` (`new_haccp_task_result_status_id`)
 ) ENGINE=INNODB CHARACTER SET utf8mb4;

 **/

@Entity(tableName = "haccp_corrective_action_type")
public class Model_haccp_corrective_action_type {

    public Model_haccp_corrective_action_type(int corrective_action_id, int corrective_action_company_id, int corrective_action_haccp_task_result_type_id, String corrective_action_name, int corrective_action_new_haccp_task_result_status_id) {
        this.corrective_action_id = corrective_action_id;
        this.corrective_action_company_id = corrective_action_company_id;
        this.corrective_action_haccp_task_result_type_id = corrective_action_haccp_task_result_type_id;
        this.corrective_action_name = corrective_action_name;
        this.corrective_action_new_haccp_task_result_status_id = corrective_action_new_haccp_task_result_status_id;
    }

    public Model_haccp_corrective_action_type() {
        //KEEP EMPTY
    }

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

    @PrimaryKey
    @SerializedName("corrective_action_id")
    private int corrective_action_id;
    @SerializedName("corrective_action_company_id")
    private int corrective_action_company_id;
    @SerializedName("corrective_action_haccp_task_result_type_id")
    private int corrective_action_haccp_task_result_type_id;
    @SerializedName("corrective_action_name")
    private String corrective_action_name;
    @SerializedName("corrective_action_new_haccp_task_result_status_id")
    private int corrective_action_new_haccp_task_result_status_id;


}
