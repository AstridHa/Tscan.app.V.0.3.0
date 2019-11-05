package com.tscan.app.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "user_table")
public class Model_haccp_user {

//    @ColumnInfo(name = "user_id")
//    private int user_id;

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "user_name")
    private String user_name;

    @ColumnInfo(name = "user_signature")
    private String user_signature;

    @ColumnInfo(name = "user_initials")
    private String user_initials;


    public Model_haccp_user(String user_name, String user_initials, String user_signature) {
        this.user_name = user_name;
        this.user_initials = user_initials;
        this.user_signature = user_signature;
    }
    

//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_signature() {
        return user_signature;
    }

    public void setUser_signature(String user_signature) {
        this.user_signature = user_signature;
    }

    public String getUser_initials() {
        return user_initials;
    }

    public void setUser_initials(String user_initials) {
        this.user_initials = user_initials;
    }

}
