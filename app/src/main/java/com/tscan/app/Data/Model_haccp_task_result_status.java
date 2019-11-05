package com.tscan.app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "haccp_task_result_status")
public class Model_haccp_task_result_status {

    /**
     CREATE TABLE haccp_task_result_status (
     haccp_task_result_type_id INT(11) NOT NULL COMMENT 'Result type id this status applies to',
     id INT(11) NOT NULL COMMENT 'Id of this status',
     `name` VARCHAR(255) NOT NULL COMMENT 'Name of the status',
     corrective_action_required INT(11) NOT NULL COMMENT 'Is a corrective action required for this status',
     PRIMARY KEY (haccp_task_result_type_id,id));
     */

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "haccp_task_result_type_id")
    private int haccp_task_result_type_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "corrective_action_required")
    private String corrective_action_required;


    public Model_haccp_task_result_status() {
        //KEEP EMPTY
    }


    public Model_haccp_task_result_status(int id, int haccp_task_result_type_id, String name, String corrective_action_required) {
        this.id = id;
        this.name = name;
        this.haccp_task_result_type_id = haccp_task_result_type_id;
        this.corrective_action_required = corrective_action_required;
    }



    public int getHaccp_task_result_type_id() {
        return haccp_task_result_type_id;
    }

    public void setHaccp_task_result_type_id(int haccp_task_result_type_id) {
        this.haccp_task_result_type_id = haccp_task_result_type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorrective_action_required() {
        return corrective_action_required;
    }

    public void setCorrective_action_required(String corrective_action_required) {
        this.corrective_action_required = corrective_action_required;
    }


}
