package com.tscan.app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "haccp_task_result_type")
public class Model_haccp_task_result_type {

    /**
     CREATE TABLE haccp_task_result_type (
     id INT(11) NOT NULL COMMENT 'Primary key',
     `name` VARCHAR(255) NOT NULL COMMENT 'Name of the result type',
     PRIMARY KEY (id));
     */

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;


    public Model_haccp_task_result_type() {
        //KEEP EMPTY
    }


    public Model_haccp_task_result_type(int id, String name) {
        this.id = id;
        this.name = name;
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


}
