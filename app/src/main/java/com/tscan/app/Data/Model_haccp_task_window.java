package com.tscan.app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "haccp_task_window")
public class Model_haccp_task_window {

    @PrimaryKey
    @ColumnInfo(name = "task_window_id")
    private int id;

    @ColumnInfo(name = "task_window_task_definition_id")
    private int haccp_task_definition_id;

    @ColumnInfo(name = "task_window_start_time_unix")
    private int start_time_unix;

    @ColumnInfo(name = "task_window_end_time_unix")
    private int end_time_unix;

//    @ColumnInfo(name = "upload_status")  // 1: yet to upload, 2: uploaded to National
//    public String upload_status;


    public Model_haccp_task_window() {
        //KEEP EMPTY
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHaccp_task_definition_id() {
        return haccp_task_definition_id;
    }

    public void setHaccp_task_definition_id(int haccp_task_definition_id) {
        this.haccp_task_definition_id = haccp_task_definition_id;
    }

    public int getStart_time_unix() {
        return start_time_unix;
    }

    public void setStart_time_unix(int start_time_unix) {
        this.start_time_unix = start_time_unix;
    }

    public int getEnd_time_unix() {
        return end_time_unix;
    }

    public void setEnd_time_unix(int end_time_unix) {
        this.end_time_unix = end_time_unix;
    }

//    public String getUpload_status() {
//        return upload_status;
//    }
//
//    public void setUpload_status(String upload_status) {
//        this.upload_status = upload_status;
//    }



    public Model_haccp_task_window(int id, int haccp_task_definition_id, int start_time_unix, int end_time_unix) {
        this.id = id;

        this.haccp_task_definition_id = haccp_task_definition_id;
        this.start_time_unix = start_time_unix;
        this.end_time_unix = end_time_unix;
//        this.upload_status = upload_status;
    }
}
