package com.tscan.app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

/**
 * CREATE TABLE location (
 * id INT(11) AUTO_INCREMENT NOT NULL COMMENT 'ID of the location',
 * name VARCHAR(255) NOT NULL COMMENT 'Name of the location',
 * company_id INT(11) NOT NULL COMMENT 'Company id this location applies to',
 * department_id INT(11) NOT NULL COMMENT 'Department id this location applies to',
 * PRIMARY KEY ( id),
 * KEY ix_location_01 (company_id, department_id, id) );
 **/


@Entity(tableName = "haccp_location")
public class Model_haccp_location {

    @PrimaryKey
    @SerializedName("location_id")
    private int id;

    @SerializedName("location_name")
    private String name;

    @SerializedName("location_company_id")
    private int company_id;

    @SerializedName("location_department_id")
    private int department_id;


    public Model_haccp_location() {
        //KEEP EMPTY
    }


    public Model_haccp_location(int id, String name, int company_id, int department_id) {
        this.id = id;
        this.name = name;
        this.company_id = company_id;
        this.department_id = department_id;
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

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

}
