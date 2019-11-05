package com.tscan.app.Data;

import android.support.annotation.Nullable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "haccp_task_result_core_cooking")
public class Model_haccp_task_result_core_cooking {

    @ColumnInfo(name = "records_task_definition_id ")
    public int records_task_definition_id;
    @Nullable
    @ColumnInfo(name = "records_task_definition_name ")// nullable because not required when uploading to server
    public String records_task_definition_name;
    @Nullable
    @ColumnInfo(name = "records_task_result_type_id ")
    public int records_task_result_type_id;
    @ColumnInfo(name = "records_food_item_category_id ")
    public int records_food_item_category_id;
    @Nullable
    @ColumnInfo(name = "records_food_item_type_id ")
    public int records_food_item_type_id;
    @ColumnInfo(name = "records_food_item_freetext ")
    public String records_food_item_freetext;
    @Nullable
    @ColumnInfo(name = "records_batch_number")
    public int records_batch_number;
    @ColumnInfo(name = "records_pass_temperature")
    public int records_pass_temperature;
    @ColumnInfo(name = "records_task_result_status_id")
    public int records_task_result_status_id;
    @ColumnInfo(name = "records_initiated_by_user")
    public String records_initiated_by_user;
    @PrimaryKey
    @ColumnInfo(name = "records_initiated_timestamp_unix")
    public int records_initiated_timestamp_unix; // time reading taken
    @ColumnInfo(name = "records_initiated_device_serial_number")
    public int records_initiated_device_serial_number;
    @ColumnInfo(name = "records_latest_corrective_action_type_id")
    public int records_latest_corrective_action_type_id;

    @Nullable // nullable because if status PASSED then no further action required
    @ColumnInfo(name = "records_latest_corrective_action_freetext")
    public String records_latest_corrective_action_freetext;
    @ColumnInfo(name = "records_latest_reading")
    public int records_latest_reading; // MOT nullable because the 1st temperature check is stored here
    @Nullable // nullable because if status PASSED then no further action required
    @ColumnInfo(name = "records_latest_reading_timestamp_unix")
    public int records_latest_reading_timestamp_unix;
    @Nullable // nullable because if status PASSED then no further action required
    @ColumnInfo(name = "records_latest_reading_mobile_sensor_id")
    public int records_latest_reading_mobile_sensor_id;
    @Nullable // nullable because if status PASSED then no further action required
    @ColumnInfo(name = "records_latest_reading_device_serial_number")
    public int records_latest_reading_device_serial_number;
    @Nullable // nullable because if status PASSED then no further action required
    @ColumnInfo(name = "records_latest_reading_user")
    public String records_latest_reading_user;
    @Nullable // nullable because not required when uploading to server
    @ColumnInfo(name = "records_timestamp_uploaded_unix")
    public Integer records_timestamp_uploaded_unix;




    public Model_haccp_task_result_core_cooking(int records_task_definition_id, String records_task_definition_name, int records_task_result_type_id, int records_food_item_category_id, int records_food_item_type_id, String records_food_item_freetext, int records_batch_number, int records_pass_temperature, int records_task_result_status_id, String records_initiated_by_user, int records_initiated_timestamp_unix, int records_initiated_device_serial_number, int records_latest_corrective_action_type_id, String records_latest_corrective_action_freetext, int records_latest_reading, int records_latest_reading_timestamp_unix, int records_latest_reading_mobile_sensor_id, int records_latest_reading_device_serial_number, String records_latest_reading_user, Integer records_timestamp_uploaded_unix) {
        this.records_task_definition_id = records_task_definition_id;
        this.records_task_definition_name = records_task_definition_name;
        this.records_task_result_type_id = records_task_result_type_id;
        this.records_food_item_category_id = records_food_item_category_id;
        this.records_food_item_type_id = records_food_item_type_id;
        this.records_food_item_freetext = records_food_item_freetext;
        this.records_batch_number = records_batch_number;
        this.records_pass_temperature = records_pass_temperature;
        this.records_task_result_status_id = records_task_result_status_id;
        this.records_initiated_by_user = records_initiated_by_user;
        this.records_initiated_timestamp_unix = records_initiated_timestamp_unix;
        this.records_initiated_device_serial_number = records_initiated_device_serial_number;
        this.records_latest_corrective_action_type_id = records_latest_corrective_action_type_id;
        this.records_latest_corrective_action_freetext = records_latest_corrective_action_freetext;
        this.records_latest_reading = records_latest_reading;
        this.records_latest_reading_timestamp_unix = records_latest_reading_timestamp_unix;
        this.records_latest_reading_mobile_sensor_id = records_latest_reading_mobile_sensor_id;
        this.records_latest_reading_device_serial_number = records_latest_reading_device_serial_number;
        this.records_latest_reading_user = records_latest_reading_user;
        this.records_timestamp_uploaded_unix = records_timestamp_uploaded_unix;
    }

    public Model_haccp_task_result_core_cooking() {
        //KEEP EMPTY
    }

    public int getRecords_task_definition_id() {
        return records_task_definition_id;
    }

    public void setRecords_task_definition_id(int records_task_definition_id) {
        this.records_task_definition_id = records_task_definition_id;
    }

    public String getRecords_task_definition_name() {
        return records_task_definition_name;
    }

    public void setRecords_task_definition_name(String records_task_definition_name) {
        this.records_task_definition_name = records_task_definition_name;
    }

    public int getRecords_task_result_type_id() {
        return records_task_result_type_id;
    }

    public void setRecords_task_result_type_id(int records_task_result_type_id) {
        this.records_task_result_type_id = records_task_result_type_id;
    }

    public int getRecords_food_item_category_id() {
        return records_food_item_category_id;
    }

    public void setRecords_food_item_category_id(int records_food_item_category_id) {
        this.records_food_item_category_id = records_food_item_category_id;
    }

    public int getRecords_food_item_type_id() {
        return records_food_item_type_id;
    }

    public void setRecords_food_item_type_id(int records_food_item_type_id) {
        this.records_food_item_type_id = records_food_item_type_id;
    }

    public String getRecords_food_item_freetext() {
        return records_food_item_freetext;
    }

    public void setRecords_food_item_freetext(String records_food_item_freetext) {
        this.records_food_item_freetext = records_food_item_freetext;
    }

    public int getRecords_batch_number() {
        return records_batch_number;
    }

    public void setRecords_batch_number(int records_batch_number) {
        this.records_batch_number = records_batch_number;
    }

    public int getRecords_pass_temperature() {
        return records_pass_temperature;
    }

    public void setRecords_pass_temperature(int records_pass_temperature) {
        this.records_pass_temperature = records_pass_temperature;
    }

    public String getRecords_initiated_by_user() {
        return records_initiated_by_user;
    }

    public void setRecords_initiated_by_user(String records_initiated_by_user) {
        this.records_initiated_by_user = records_initiated_by_user;
    }

    public int getRecords_initiated_timestamp_unix() {
        return records_initiated_timestamp_unix;
    }

    public void setRecords_initiated_timestamp_unix(int records_initiated_timestamp_unix) {
        this.records_initiated_timestamp_unix = records_initiated_timestamp_unix;
    }

    public int getRecords_initiated_device_serial_number() {
        return records_initiated_device_serial_number;
    }

    public void setRecords_initiated_device_serial_number(int records_initiated_device_serial_number) {
        this.records_initiated_device_serial_number = records_initiated_device_serial_number;
    }

    public int getRecords_latest_corrective_action_type_id() {
        return records_latest_corrective_action_type_id;
    }

    public void setRecords_latest_corrective_action_type_id(int records_latest_corrective_action_type_id) {
        this.records_latest_corrective_action_type_id = records_latest_corrective_action_type_id;
    }

    public String getRecords_latest_corrective_action_freetext() {
        return records_latest_corrective_action_freetext;
    }

    public void setRecords_latest_corrective_action_freetext(String records_latest_corrective_action_freetext) {
        this.records_latest_corrective_action_freetext = records_latest_corrective_action_freetext;
    }

    public int getRecords_latest_reading() {
        return records_latest_reading;
    }

    public void setRecords_latest_reading(int records_latest_reading) {
        this.records_latest_reading = records_latest_reading;
    }

    public int getRecords_latest_reading_timestamp_unix() {
        return records_latest_reading_timestamp_unix;
    }

    public void setRecords_latest_reading_timestamp_unix(int records_latest_reading_timestamp_unix) {
        this.records_latest_reading_timestamp_unix = records_latest_reading_timestamp_unix;
    }

    public int getRecords_latest_reading_mobile_sensor_id() {
        return records_latest_reading_mobile_sensor_id;
    }

    public void setRecords_latest_reading_mobile_sensor_id(int records_latest_reading_mobile_sensor_id) {
        this.records_latest_reading_mobile_sensor_id = records_latest_reading_mobile_sensor_id;
    }

    public int getRecords_latest_reading_device_serial_number() {
        return records_latest_reading_device_serial_number;
    }

    public void setRecords_latest_reading_device_serial_number(int records_latest_reading_device_serial_number) {
        this.records_latest_reading_device_serial_number = records_latest_reading_device_serial_number;
    }

    public String getRecords_latest_reading_user() {
        return records_latest_reading_user;
    }

    public void setRecords_latest_reading_user(String records_latest_reading_user) {
        this.records_latest_reading_user = records_latest_reading_user;
    }

    public int getRecords_task_result_status_id() {
        return records_task_result_status_id;
    }

    public void setRecords_task_result_status_id(int records_task_result_status_id) {
        this.records_task_result_status_id = records_task_result_status_id;
    }

    public Integer getRecords_timestamp_uploaded_unix() {
        return records_timestamp_uploaded_unix;
    }

    public void setRecords_timestamp_uploaded_unix(Integer records_timestamp_uploaded_unix) {
        this.records_timestamp_uploaded_unix = records_timestamp_uploaded_unix;
    }

}
