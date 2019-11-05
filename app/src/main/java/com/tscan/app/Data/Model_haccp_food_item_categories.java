package com.tscan.app.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 CREATE TABLE haccp_food_item_category (

 `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
 `company_id` INT(11) NOT NULL COMMENT 'Company ID',
 `name` VARCHAR(255) COMMENT 'Display text',
 `core_cooking_pass_temperature` FLOAT NOT NULL COMMENT 'min temperature to pass core cooking test',

 PRIMARY KEY(`id`),
 KEY `fk_haccp_food_item_category01` (`company_id`)
 ) ENGINE=INNODB CHARACTER SET utf8mb4;
 **/

@Entity(tableName = "haccp_food_item_categories")
public class Model_haccp_food_item_categories {

    @PrimaryKey
    @SerializedName("food_category_id")
    private int food_category_id;
    @SerializedName("food_category_company_id")
    private int food_category_company_id;
    @SerializedName("food_category_name")
    private String food_category_name;
    @SerializedName("food_category_temperature")
    private int food_category_temperature;


    public Model_haccp_food_item_categories(int food_category_id, int food_category_company_id, String food_category_name, int food_category_temperature) {
        this.food_category_id = food_category_id;
        this.food_category_company_id = food_category_company_id;
        this.food_category_name = food_category_name;
        this.food_category_temperature = food_category_temperature;
    }

    public Model_haccp_food_item_categories() {
        //KEEP EMPTY
    }


    public int getFood_category_id() {
        return food_category_id;
    }

    public void setFood_category_id(int food_category_id) {
        this.food_category_id = food_category_id;
    }

    public int getFood_category_company_id() {
        return food_category_company_id;
    }

    public void setFood_category_company_id(int food_category_company_id) {
        this.food_category_company_id = food_category_company_id;
    }

    public String getFood_category_name() {
        return food_category_name;
    }

    public void setFood_category_name(String food_category_name) {
        this.food_category_name = food_category_name;
    }

    public int getFood_category_temperature() {
        return food_category_temperature;
    }

    public void setFood_category_temperature(int food_category_temperature) {
        this.food_category_temperature = food_category_temperature;
    }
}
