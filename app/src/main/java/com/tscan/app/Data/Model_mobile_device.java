package com.tscan.app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * CREATE TABLE mobile_device (
 * company_id INT(11) NOT NULL  COMMENT 'Company device is assigned to',
 * department_id INT(11) NOT NULL COMMENT 'Department device is assigned to',
 * device_serial_number VARCHAR(255) NOT NULL COMMENT 'Unique serial number of the device',
 * `name` VARCHAR(255) NOT NULL COMMENT 'Name of the device',
 * enabled INT(11) NOT NULL COMMENT 'Is the device enabled',
 * api_key VARCHAR(255) NOT NULL COMMENT 'API authorisation key of the device',
 * PRIMARY KEY (company_id, department_id, device_serial_number ),
 * KEY ix_mobile_device_01 (enabled));
 **/


@Entity(tableName = "mobile_device")
public class Model_mobile_device {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("company_id")
    private int company_id;

    @SerializedName("department_id")
    private int department_id;

    @SerializedName("device_serial_nuber")
    private int device_serial_nuber;

    @SerializedName("name")
    private String name;

    @SerializedName("enabled")
    private int enabled;

    @SerializedName("api_key")
    private String api_key;


    public Model_mobile_device() {
        //KEEP EMPTY
    }


    public Model_mobile_device(int company_id, int department_id, int device_serial_nuber, String name, int enabled, String api_key) {
        this.company_id = company_id;
        this.department_id = department_id;
        this.device_serial_nuber = device_serial_nuber;
        this.enabled = enabled;
        this.name = name;
        this.api_key = api_key;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDevice_serial_nuber() {
        return device_serial_nuber;
    }

    public void setDevice_serial_nuber(int device_serial_nuber) {
        this.device_serial_nuber = device_serial_nuber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

}
