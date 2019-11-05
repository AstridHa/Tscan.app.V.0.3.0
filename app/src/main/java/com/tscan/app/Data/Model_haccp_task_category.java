package com.tscan.app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "haccp_task_category")
public class Model_haccp_task_category {


    /**
     CREATE TABLE haccp_task_category (
     id INT(11) AUTO_INCREMENT NOT NULL COMMENT 'ID of the category',
     `name` VARCHAR(255) NOT NULL COMMENT 'Name of the category',
     company_id INT(11) NOT NULL COMMENT 'Company id this category applies to',
     department_id INT(11) NOT NULL COMMENT 'Department id this category applies to',
     sort_order INT(11) NOT NULL COMMENT 'Used to sort categories',
     PRIMARY KEY (id),
     KEY ix_haccp_task_category_01 (company_id, department_id, id) );
     **/

//    @PrimaryKey(autoGenerate = true)
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "company_id")
    private int company_id;

    @ColumnInfo(name = "department_id")
    private int department_id;

    @ColumnInfo(name = "sort_order")
    int sort_order;


    public Model_haccp_task_category() {
        //KEEP EMPTY
    }


    public Model_haccp_task_category(int id, String name, int company_id,  int department_id, int sort_order) {
        this.id = id;
        this.name = name;
        this.company_id = company_id;
        this.department_id = department_id;
        this.sort_order = sort_order;
    }




        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }

}
