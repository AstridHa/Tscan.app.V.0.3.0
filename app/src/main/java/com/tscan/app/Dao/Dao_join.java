package com.tscan.app.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tscan.app.Data.Model_haccp_corrective_action_type;
import com.tscan.app.Data.Model_haccp_food_item_categories;
import com.tscan.app.Data.Model_haccp_food_item_types;
import com.tscan.app.Data.Model_haccp_location;
import com.tscan.app.Data.Model_haccp_task_category;
import com.tscan.app.Data.Model_haccp_task_definition;
import com.tscan.app.Data.Model_haccp_task_result_core_cooking;
import com.tscan.app.Data.Model_haccp_task_result_status;
import com.tscan.app.Data.Model_haccp_task_result_type;
import com.tscan.app.Data.Model_haccp_task_window;
import com.tscan.app.Data.Model_haccp_user;
import com.tscan.app.Data.Model_mobile_device;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface Dao_join {

    /////////////////////////////////////////////////////////////////////////
    //   JOIN QUERY                                                        //
    /////////////////////////////////////////////////////////////////////////

//    @Query("SELECT htd.definition_id AS task_definition_id, htd.name AS task_definition_name,htd.company_id,htd.department_id,htd.location_id,htd.haccp_task_category_id,htd.haccp_task_result_type_id,htd.instruction_text,htd.quantity_required,htd.valid_from_unix,htd.valid_to_unix,htd.windows_generated_to_unix,htc.name category_name,htc.sort_order category_sort_order,htrt.id result_type_id,htrt.name result_type_name,htw.task_window_id window_id,htw.task_window_start_time_unix window_start_time_unix, htw.task_window_end_time_unix window_end_time_unix,COUNT(htrcc.time) result_count FROM haccp_task_definition htd JOIN haccp_task_category htc ON htc.id = htd.haccp_task_category_id AND htc.company_id = htd.company_id AND htc.department_id = htd.department_id JOIN haccp_task_result_type htrt ON htrt.id = htd.haccp_task_result_type_id JOIN haccp_task_window htw ON htw.task_window_task_definition_id = htd.definition_id GROUP BY htd.definition_id,htd.name,htd.company_id,htd.department_id,htd.location_id,htd.haccp_task_category_id,htd.haccp_task_result_type_id,htd.instruction_text,htd.quantity_required,htd.valid_from_unix,htd.valid_to_unix,htd.windows_generated_to_unix,htc.name ,htc.sort_order ,htrt.id ,htrt.name,htw.task_window_id ,htw.task_window_start_time_unix ,htw.task_window_end_time_unix")
//    List<Model_join> query_test();

//    @Query("SELECT htd.definition_id AS task_definition_id, htd.name AS task_definition_name,htd.company_id,htd.department_id,htd.location_id,htd.haccp_task_category_id,htd.haccp_task_result_type_id,htd.instruction_text,htd.quantity_required,htd.valid_from_unix,htd.valid_to_unix,htd.windows_generated_to_unix,htc.name category_name,htc.sort_order category_sort_order,htrt.id result_type_id,htrt.name result_type_name,htw.task_window_id window_id,htw.task_window_start_time_unix window_start_time_unix, htw.task_window_end_time_unix window_end_time_unix,COUNT(htrcc.records_initiated_timestamp_unix) result_count FROM haccp_task_definition htd JOIN haccp_task_category htc ON htc.id = htd.haccp_task_category_id AND htc.company_id = htd.company_id AND htc.department_id = htd.department_id JOIN haccp_task_result_type htrt ON htrt.id = htd.haccp_task_result_type_id JOIN haccp_task_window htw ON htw.task_window_task_definition_id = htd.definition_id LEFT JOIN haccp_task_result_core_cooking htrcc ON htrcc.haccp_task_window_id = htw.task_window_id GROUP BY htd.definition_id,htd.name,htd.company_id,htd.department_id,htd.location_id,htd.haccp_task_category_id,htd.haccp_task_result_type_id,htd.instruction_text,htd.quantity_required,htd.valid_from_unix,htd.valid_to_unix,htd.windows_generated_to_unix,htc.name ,htc.sort_order ,htrt.id ,htrt.name,htw.task_window_id ,htw.task_window_start_time_unix ,htw.task_window_end_time_unix")
//    @Query("SELECT htd.definition_id AS task_definition_id, htd.name AS task_definition_name,htd.company_id,htd.department_id,htd.location_id,htd.haccp_task_category_id,htd.haccp_task_result_type_id,htd.instruction_text,htd.quantity_required,htd.valid_from_unix,htd.valid_to_unix,htd.windows_generated_to_unix,htc.name category_name,htc.sort_order category_sort_order,htrt.id result_type_id,htrt.name result_type_name,htw.task_window_id window_id,htw.task_window_start_time_unix window_start_time_unix, htw.task_window_end_time_unix window_end_time_unix,COUNT(htrcc.records_initiated_timestamp_unix) result_count FROM haccp_task_definition htd JOIN haccp_task_category htc ON htc.id = htd.haccp_task_category_id AND htc.company_id = htd.company_id AND htc.department_id = htd.department_id JOIN haccp_task_result_type htrt ON htrt.id = htd.haccp_task_result_type_id JOIN haccp_task_window htw ON htw.task_window_task_definition_id = htd.definition_id GROUP BY htd.definition_id,htd.name,htd.company_id,htd.department_id,htd.location_id,htd.haccp_task_category_id,htd.haccp_task_result_type_id,htd.instruction_text,htd.quantity_required,htd.valid_from_unix,htd.valid_to_unix,htd.windows_generated_to_unix,htc.name ,htc.sort_order ,htrt.id ,htrt.name,htw.task_window_id ,htw.task_window_start_time_unix ,htw.task_window_end_time_unix")
//    LiveData<List<Model_join>> query_data_to_display_all_task_windows();

    /////////////////////////////////////////////////////////////////////////
    //   Model_window_joined_data                                          //
    /////////////////////////////////////////////////////////////////////////
    @Query("DELETE FROM model_join")
    void delete_model_join();

    /////////////////////////////////////////////////////////////////////////
    //   Model_window_joined_data                                          //
    /////////////////////////////////////////////////////////////////////////
//    @Query("SELECT * FROM model_window_joined_data")
//    LiveData<List<Model_window_joined_data>> query_all_joined_data_table();
//
//    @Query("SELECT * FROM model_window_joined_data WHERE joined_window_status <=2 AND joined_result_count < joined_quantity_required AND joined_window_end_time_unix > strftime('%s', 'now') ORDER BY joined_window_end_time_unix ASC, joined_definition_id ASC")
//    LiveData<List<Model_window_joined_data>> query_joined_uncompleted_task_windows();
//
//    @Query("SELECT * FROM model_window_joined_data WHERE joined_result_count >= joined_quantity_required ORDER BY joined_window_end_time_unix DESC, joined_definition_id ASC")
//    LiveData<List<Model_window_joined_data>> query_joined_completed_task_windows();
//
//    @Query("SELECT * FROM model_window_joined_data WHERE joined_window_status = 4 AND joined_result_count < joined_quantity_required ORDER BY joined_window_end_time_unix DESC, joined_definition_id ASC")
//    LiveData<List<Model_window_joined_data>> query_joined_pending_task_windows();
//
//    @Insert
//    void insert_windows_joined_data(Model_window_joined_data model_window_joined_data);
//
//    @Query("DELETE FROM model_window_joined_data")
//    void delete_model_window_joined_data();
//
//    @Query("UPDATE model_window_joined_data SET joined_result_count = joined_result_count + 1 WHERE joined_definition_id = :id")
//    void update_result_count(int id);
//
//    @Query("UPDATE model_window_joined_data SET joined_window_status = :status WHERE joined_window_id = :id")
//    void update_upload_status(int id, int status);
//
//    @Query("SELECT COUNT(joined_window_id) FROM model_window_joined_data WHERE joined_result_count < joined_quantity_required AND joined_window_end_time_unix > strftime('%s', 'now')")
//    LiveData<Integer> get_count_uncompleted();


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_location                                              //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_haccp_location(Model_haccp_location model_haccp_location);

    @Update
    void update_haccp_location(Model_haccp_location model_haccp_location);

    @Query("DELETE FROM haccp_location")
    void delete_haccp_location();

    @Query("SELECT * FROM haccp_location")
    LiveData<List<Model_haccp_location>> get_all_haccp_location();

    @Query("SELECT name FROM haccp_location WHERE id = :id")
    String get_location_name_by_id(int id);



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_category                                         //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_haccp_task_category(Model_haccp_task_category category);

    @Update
    void update_haccp_task_category(Model_haccp_task_category category);

    @Query("DELETE FROM haccp_task_category")
    void delete_haccp_task_category();

    @Query("SELECT * FROM haccp_task_category")
    LiveData<List<Model_haccp_task_category>> get_all_category_list();


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_definition                                       //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_haccp_task_definition(Model_haccp_task_definition model_haccp_task_definition);

    @Update
    void update_haccp_task_definition(Model_haccp_task_definition model_haccp_task_definition);

    @Query("DELETE FROM haccp_task_definition")
    void delete_haccp_task_definition();

    @Query("SELECT * FROM haccp_task_definition")
    LiveData<List<Model_haccp_task_definition>> query_task_definition();


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_status                                    //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_haccp_task_result_status(Model_haccp_task_result_status model_haccp_task_result_status);

    @Update
    void update_haccp_task_result_status(Model_haccp_task_result_status model_haccp_task_result_status);

    @Query("DELETE FROM haccp_task_result_status")
    void delete_haccp_task_result_status();

    @Query("SELECT * FROM haccp_task_result_status")
    LiveData<List<Model_haccp_task_result_status>> get_all_haccp_task_result_status();


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_type                                      //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_haccp_task_result_type(Model_haccp_task_result_type model_haccp_task_result_type);

    @Update
    void update_haccp_task_result_type(Model_haccp_task_result_type model_haccp_task_result_type);

    @Query("DELETE FROM haccp_task_result_type")
    void delete_haccp_task_result_type();

    @Query("SELECT * FROM haccp_task_result_type")
    LiveData<List<Model_haccp_task_result_type>> get_all_haccp_task_result_type();


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_food_item_category                                    //
    /////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM haccp_food_item_categories")
    LiveData<List<Model_haccp_food_item_categories>> getAllFoodItemCategories();

    @Query("DELETE FROM haccp_food_item_categories")
    void delete_Async_food_item_category();

    @Insert
    void insert_haccp_food_category(Model_haccp_food_item_categories haccp_food_item_category);


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_food_item_types                                       //
    /////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM haccp_food_item_types")
    LiveData<List<Model_haccp_food_item_types>> getAllFoodItemTypes();

    @Query("SELECT * FROM haccp_food_item_types WHERE food_type_id = 3")
    LiveData<List<Model_haccp_food_item_types>> getFoodItemTypes_by_category_id( );

//    @Query("SELECT * FROM haccp_food_item_types WHERE food_type_id = :food_category_id")
//    List<Model_haccp_food_item_types> getFoodItemTypes_by_category_id_bis(int food_category_id);
    @Query("SELECT * FROM haccp_food_item_types WHERE food_type_id = :food_category_id")
    Model_haccp_food_item_types[] getFoodItemTypes_by_category_id_bis(int food_category_id);




    @Query("DELETE FROM haccp_food_item_types")
    void delete_Async_food_item_types();

    @Insert
    void insert_haccp_food_type(Model_haccp_food_item_types food_item_types);


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_corrective_action                                     //
    /////////////////////////////////////////////////////////////////////////
    @Query("DELETE FROM haccp_corrective_action_type")
    void delete_haccp_corrective_actions();

    @Insert
    void insert_haccp_corrective_actions(Model_haccp_corrective_action_type corrective_action_type);




    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_window                                           //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_haccp_task_window(Model_haccp_task_window model_haccp_task_window);

    @Update (onConflict = REPLACE)
    void update_haccp_task_window(Model_haccp_task_window... model_haccp_task_window);

    @Query("DELETE FROM haccp_task_window")
    void delete_haccp_task_window();

    @Query("SELECT * FROM haccp_task_window")
    LiveData<List<Model_haccp_task_window>> get_all_haccp_task_window();


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_user                                                  //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void saveUser(Model_haccp_user user);

    @Update
    void updateUser(Model_haccp_user user);

    @Delete
    void deleteUser(Model_haccp_user user);


    @Query("SELECT * FROM user_table")
    LiveData<List<Model_haccp_user>> getAllUsers();

    @Query("SELECT * FROM user_table ORDER BY user_name ASC")
    LiveData<List<Model_haccp_user>> getAllUsers_1();


    /////////////////////////////////////////////////////////////////////////
    //   Model_mobile_device                                               //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_moble_device(Model_mobile_device model_mobile_device);

    @Update
    void update_mobile_device(Model_mobile_device model_mobile_device);

    @Query("DELETE FROM mobile_device")
    void delete_mobile_device();

    @Query("SELECT * FROM mobile_device")
    LiveData<List<Model_mobile_device>> get_mobile_device();


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_core_cooking                              //
    /////////////////////////////////////////////////////////////////////////
    @Insert
    void insert_record(Model_haccp_task_result_core_cooking result_core_cooking);

    @Query("DELETE FROM haccp_task_result_core_cooking")
    void delete_record();

    @Query("SELECT * FROM haccp_task_result_core_cooking")
    List<Model_haccp_task_result_core_cooking> get_all_tasks_records();

    @Query("SELECT * FROM haccp_task_result_core_cooking WHERE records_task_result_status_id = 2") // status = failed
    LiveData<List<Model_haccp_task_result_core_cooking>> query_pending_records();

    @Query("SELECT * FROM haccp_task_result_core_cooking WHERE records_task_result_status_id = 1") // status = passed
    LiveData<List<Model_haccp_task_result_core_cooking>> query_completed_records();

//    @Query("SELECT * FROM haccp_task_result_core_cooking WHERE haccp_task_window_id = :window_id")
//    LiveData<List<Model_haccp_task_result_core_cooking>> get_record_by_window_id(int window_id);

    @Query("SELECT * FROM haccp_task_result_core_cooking rcd WHERE rcd.records_timestamp_uploaded_unix IS NULL ")
    List<Model_haccp_task_result_core_cooking> get_records_to_upload();

//    @Query("SELECT COUNT(*) FROM haccp_task_result_core_cooking WHERE haccp_task_window_id = :id AND timestamp_uploaded_unix is null")
//    Integer get_count_uploaded_record_by_window_id(int id);

    @Query("UPDATE haccp_task_result_core_cooking SET records_timestamp_uploaded_unix = :uploaded_time WHERE `records_task_definition_id ` = :id AND records_initiated_timestamp_unix = :timestamp_unix")
    void update_timestamp_uploaded_record(int id, int timestamp_unix, int uploaded_time);

//    @Query("UPDATE haccp_task_result_core_cooking SET reading = :temperature AND remedial_text = :remedial AND haccp_task_result_status_id = :result_status_id AND mobile_sensor_id = :sensor_id AND timestamp_unix = :unix AND user_details = :username WHERE timestamp_unix = :original_unix")
    @Query("UPDATE haccp_task_result_core_cooking SET records_latest_reading = :temperature WHERE records_initiated_timestamp_unix = :original_unix")
    void update_record(Integer original_unix, Integer temperature);

    @Query("SELECT COUNT(records_task_result_status_id) FROM haccp_task_result_core_cooking  WHERE records_task_result_status_id = 1")//Status = passed
    LiveData<Integer> get_count_completed();

    @Query("SELECT COUNT(records_task_result_status_id) FROM haccp_task_result_core_cooking WHERE records_task_result_status_id = 2")//Status = failed
    LiveData<Integer> get_count_pending();
}

