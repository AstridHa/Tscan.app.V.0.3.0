package com.tscan.app.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 CREATE TABLE haccp_food_item_type (

 `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
 `company_id` INT(11) NOT NULL COMMENT 'Company ID',
 `haccp_food_item_category_id` NOT NULL COMMENT 'Parent food item category ID',
 `name` VARCHAR(255) COMMENT 'Display text',
 `core_cooking_pass_temperature` FLOAT NOT NULL COMMENT 'min temperature to pass core cooking test',

 PRIMARY KEY(`id`),
 KEY `fk_haccp_food_item_type01` (`company_id`),
 KEY `fk_haccp_food_item_type02` (`haccp_food_item_category_id`)
 ) ENGINE=INNODB CHARACTER SET utf8mb4;

 **/

@Entity(tableName = "haccp_food_item_types")
public class Model_haccp_food_item_types {


    @PrimaryKey
    @SerializedName("food_type_id")
    private int food_type_id;
    @SerializedName("food_type_name")
    private String food_type_name;
    @SerializedName("food_type_company_id")
    private int food_type_company_id;
    @SerializedName("food_category_id")
    private int food_category_id;
    @SerializedName("food_type_temperature")
    private int food_type_temperature;


    public Model_haccp_food_item_types(int food_type_id, String food_type_name, int food_type_company_id, int food_category_id, int food_type_temperature) {
        this.food_type_id = food_type_id;
        this.food_type_name = food_type_name;
        this.food_type_company_id = food_type_company_id;
        this.food_category_id = food_category_id;
        this.food_type_temperature = food_type_temperature;
    }

    public Model_haccp_food_item_types() {
        //KEEP EMPTY
    }

    public int getFood_type_id() {
        return food_type_id;
    }

    public void setFood_type_id(int food_type_id) {
        this.food_type_id = food_type_id;
    }

    public int getFood_category_id() {
        return food_category_id;
    }

    public void setFood_category_id(int food_category_id) {
        this.food_category_id = food_category_id;
    }

    public int getFood_type_company_id() {
        return food_type_company_id;
    }

    public void setFood_type_company_id(int food_type_company_id) {
        this.food_type_company_id = food_type_company_id;
    }

    public String getFood_type_name() {
        return food_type_name;
    }

    public void setFood_type_name(String food_type_name) {
        this.food_type_name = food_type_name;
    }

    public int getFood_type_temperature() {
        return food_type_temperature;
    }

    public void setFood_type_temperature(int food_type_temperature) {
        this.food_type_temperature = food_type_temperature;
    }
}
