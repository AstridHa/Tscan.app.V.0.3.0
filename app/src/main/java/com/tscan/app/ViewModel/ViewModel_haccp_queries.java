package com.tscan.app.ViewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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
import com.tscan.app.Data.Model_join;
import com.tscan.app.Data.Model_mobile_device;
import com.tscan.app.Repositories.Repository_haccp_queries;

import java.util.List;

/**
 * The ViewModel is a class whose role is to provide data to the UI and survive configuration changes.
 * A ViewModel acts as a communication center between the Repository and the UI.
 * */


public class ViewModel_haccp_queries extends AndroidViewModel {
    private Repository_haccp_queries repository_haccp_queries;

    private LiveData<List<Model_haccp_task_definition>> get_haccp_task_definition;
    private LiveData<List<Model_haccp_location>> get_haccp_location;
    private LiveData<List<Model_haccp_task_result_status>> get_haccp_task_result_status;
    private LiveData<List<Model_haccp_task_result_type>> get_haccp_task_result_type;
    private LiveData<List<Model_haccp_task_window>> get_haccp_task_window;
    private LiveData<List<Model_haccp_user>> get_haccp_user_list;
    private LiveData<List<Model_mobile_device>> get_mobile_device;

    private LiveData<List<Model_join>> query_data_to_display_all_task_windows;
    private LiveData<List<Model_haccp_task_category>> get_haccp_task_category;
//    private LiveData<List<Model_window_joined_data>> query_all_joined_data_table;
//    private LiveData<List<Model_window_joined_data>> query_joined_completed_task_windows;

    private LiveData<List<Model_join>> query_data_to_display_uncompleted_task_windows;
    private LiveData<List<Model_join>> query_data_to_display_completed_task_windows;
    private LiveData<List<Model_join>> query_data_to_display_pending_task_windows;

//    private LiveData<List<Model_window_joined_data>> query_joined_uncompleted_task_windows;
//    private LiveData<List<Model_window_joined_data>> query_joined_pending_task_windows;


    public void FullRefresh()
    {
        // call method in repo to delete data
    }


/////////////////////////////////////////////////////////////////////////
//   CONSTRUCTOR                                                       //
/////////////////////////////////////////////////////////////////////////

    public ViewModel_haccp_queries(@NonNull Application application) {
        super(application);
        repository_haccp_queries = new Repository_haccp_queries(application);

        get_haccp_location = repository_haccp_queries.get_all_haccp_location();
        get_haccp_task_category = repository_haccp_queries.get_all_categories();
        get_haccp_task_result_status = repository_haccp_queries.get_all_haccp_task_result_status();
        get_haccp_task_result_type = repository_haccp_queries.get_all_result_types();
        get_haccp_task_window = repository_haccp_queries.get_all_haccp_task_window();
        get_haccp_user_list = repository_haccp_queries.get_all_users();
        get_mobile_device = repository_haccp_queries.getModel_mobile_device();

    }



/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS                                                         //
/////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////
    //   DELETE TABLE CONTENTS                                             //
    /////////////////////////////////////////////////////////////////////////
    public void delete_haccp_location() {
        repository_haccp_queries.delete_haccp_location();
    }
    public void delete_haccp_food_item_categories(){
        repository_haccp_queries.delete_haccp_food_item_categories();
    }
    public void delete_haccp_food_item_types(){
        repository_haccp_queries.delete_haccp_food_item_types();
    }
    public void delete_haccp_corrective_actions(){
        repository_haccp_queries.delete_haccp_corrective_actions();
    }
    public void delete_model_joins() {
        repository_haccp_queries.delete_model_join();
    }

    public void delete_haccp_task_definition() {
        repository_haccp_queries.delete_haccp_task_definition();
    }
    public void delete_haccp_task_category(){
        repository_haccp_queries.delete_haccp_task_category();
    }
    public void delete_haccp_task_result_status(){
        repository_haccp_queries.delete_haccp_task_result_status();
    }
    public void delete_haccp_task_result_type() {
        repository_haccp_queries.delete_haccp_task_result_type();
    }
    public void delete_haccp_task_window() {
        repository_haccp_queries.delete_haccp_task_window();
    }
    public void delete_haccp_core_cooking_records() {
        repository_haccp_queries.delete_record();
    }
    public void delete_user_haccp(Model_haccp_user user_table) {
        repository_haccp_queries.delete_haccp_user(user_table);
    }
    public void delete_mobile_devices() {
        repository_haccp_queries.delete_mobile_device();
    }



    /////////////////////////////////////////////////////////////////////////
    //   JOIN QUERY                                                        //
    /////////////////////////////////////////////////////////////////////////
//    public LiveData<List<Model_join>> query_data_to_display_all_task_windows() {
//        return query_data_to_display_all_task_windows;
//    }
//    public LiveData<List<Model_join>> query_data_to_display_uncompleted_task_windows(long time) {
//        return query_data_to_display_uncompleted_task_windows;
//    }
//    public LiveData<List<Model_join>> query_data_to_display_completed_task_windows() {
//        return query_data_to_display_completed_task_windows;
//    }
//    public LiveData<List<Model_join>> query_data_to_display_pending_task_windows(long current) {
//        return query_data_to_display_pending_task_windows;
//    }

    public LiveData<List<Model_haccp_task_definition>> query_join_task_definition_window(long current_time) {
        return repository_haccp_queries.query_join_task_definition_window(current_time);
    }

    /////////////////////////////////////////////////////////////////////////
    //   Model_window_joined_data                                          //
    /////////////////////////////////////////////////////////////////////////
//    public LiveData<List<Model_window_joined_data>> query_joined_uncompleted_task_windows() {
//        return query_joined_uncompleted_task_windows;
//    }
//    public LiveData<List<Model_window_joined_data>> query_joined_completed_task_windows() {
//        return query_joined_completed_task_windows;
//    }
//    public LiveData<List<Model_window_joined_data>> query_joined_pending_task_windows() {
//        return query_joined_pending_task_windows;
//    }
//    public LiveData<List<Model_window_joined_data>> query_all_joined_data_table() {
//        return query_all_joined_data_table;
//    }
//    public void insert_windows_joined_data(Model_window_joined_data model_window_joined_data) {
//        repository_haccp_queries.insert_windows_joined_data(model_window_joined_data);
//    }
//
//    public void update_result_count(int id) {
//        repository_haccp_queries.update_result_count(id);
//    }
//
//    public void update_upload_status(int id, int status) {
//        repository_haccp_queries.update_upload_status(id, status);
//    }

    public LiveData<Integer> get_count_uncompleted() {
        return repository_haccp_queries.get_count_uncompleted;
    }

    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_definition                                       //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_definition>> query_task_definition() {
        return repository_haccp_queries.query_task_definition();
    }

    public void insert_haccp_task_definition(Model_haccp_task_definition model_haccp_task_definition) {
        repository_haccp_queries.insert_haccp_task_definition(model_haccp_task_definition);
    }

    public void update_haccp_task_definition(Model_haccp_task_definition model_haccp_task_definition) {
        repository_haccp_queries.update_haccp_task_definition(model_haccp_task_definition);
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_location                                              //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_location>> getAllLocationList() {
        return get_haccp_location;
    }

    public void insert_haccp_location(Model_haccp_location model_haccp_location) {
        repository_haccp_queries.insert_haccp_location(model_haccp_location);
    }

    public void update_haccp_location(Model_haccp_location model_haccp_location) {
        repository_haccp_queries.update_haccp_location(model_haccp_location);
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_category                                         //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_category>> getAllTaskCategoryList() {
        return get_haccp_task_category;
    }

    public void insert_haccp_task_category(Model_haccp_task_category model_haccp_task_category){
        repository_haccp_queries.insert_haccp_task_category(model_haccp_task_category);
    }

    public void update_haccp_task_category(Model_haccp_task_category model_haccp_task_category) {
        repository_haccp_queries.update_haccp_task_category(model_haccp_task_category);
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_status                                    //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_result_status>> getAllTaskResultStatusList() {
        return get_haccp_task_result_status;
    }

    public void insert_haccp_task_result_status(Model_haccp_task_result_status model_haccp_task_result_status){
        repository_haccp_queries.insert_haccp_task_result_status(model_haccp_task_result_status);
    }

    public void update_haccp_task_result_status(Model_haccp_task_result_status model_haccp_task_result_status) {
        repository_haccp_queries.update_haccp_task_result_status(model_haccp_task_result_status);
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_type                                      //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_result_type>> getAllTaskResultTypeList() {
        return get_haccp_task_result_type;
    }


    public LiveData<String> get_definition_result_type_name_by_id(int category) {
        return repository_haccp_queries.get_definition_result_type_name_by_id(category);
    }

//    public LiveData<Integer> get_count_pending() {
//        return repository_haccp_queries.get_count_pending;
//    }



    public void insert_haccp_task_result_type(Model_haccp_task_result_type model_haccp_task_result_type) {
        repository_haccp_queries.insert_haccp_task_result_type(model_haccp_task_result_type);
    }

    public void update_haccp_task_result_type(Model_haccp_task_result_type model_haccp_task_result_type) {
        repository_haccp_queries.update_haccp_task_result_type(model_haccp_task_result_type);
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_food_item_category                                    //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_food_item_categories>> getAllFoodItemCategories() {
        return repository_haccp_queries.getAllFoodItemCategories();
    }

    public void insert_haccp_food_category(Model_haccp_food_item_categories food_item_categories) {
        repository_haccp_queries.insert_haccp_food_category(food_item_categories);
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_food_item_types                                       //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_food_item_types>> getAllFoodItemTypes() {
        return repository_haccp_queries.getAllFoodItemTypes();
    }

    public LiveData<List<Model_haccp_food_item_types>> getFoodItemTypes_by_category_id() {
        Log.i("viewmodel_category)", String.valueOf("hgjh"));
        return repository_haccp_queries.getFoodItemTypes_by_category_id;
    }

    public void insert_haccp_food_type(Model_haccp_food_item_types food_item_types) {
        repository_haccp_queries.insert_haccp_food_type(food_item_types);
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_corrective_action                                     //
    /////////////////////////////////////////////////////////////////////////
    public void insert_haccp_corrective_actions(Model_haccp_corrective_action_type corrective_action_type) {
        repository_haccp_queries.insert_haccp_corrective_actions(corrective_action_type);
    }

    public LiveData<List<Model_haccp_corrective_action_type>> getCorrectiveActionByCompanyId(int company_id) {
        return repository_haccp_queries.getCorrectiveActionByCompanyId(company_id);
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_window                                           //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_window>> getAllTaskWindowList() {
        return get_haccp_task_window;
    }

    public void insert_haccp_task_window(Model_haccp_task_window model_haccp_task_window) {
        repository_haccp_queries.insert_haccp_task_window(model_haccp_task_window);
    }

    public void update_haccp_task_window(Model_haccp_task_window model_haccp_task_window) {
        repository_haccp_queries.update_haccp_task_window(model_haccp_task_window);
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_user                                                  //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_user>> getAllUserList() {
        return get_haccp_user_list;
    }


    public void insert_user_haccp(Model_haccp_user haccp_user){
        repository_haccp_queries.insert_haccp_user(haccp_user);
    }

    public void update_user_haccp(Model_haccp_user haccp_user) {
        repository_haccp_queries.update_haccp_user(haccp_user);
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_mobile_device                                               //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_mobile_device>> getallaboutmobiledevice() {
        return get_mobile_device;
    }

    public void insert_moble_device(Model_mobile_device model_mobile_device) {
        repository_haccp_queries.insert_moble_device(model_mobile_device);
    }

    public void update_mobile_device(Model_mobile_device model_mobile_device) {
        repository_haccp_queries.update_mobile_device(model_mobile_device);
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_core_cooking                              //
    /////////////////////////////////////////////////////////////////////////
    public List<Model_haccp_task_result_core_cooking> get_all_task_records() {
        return repository_haccp_queries.getModel_core_cooking_results();
    }

    public void insert_record(Model_haccp_task_result_core_cooking model_haccp_task_result_core_cooking) {
        Log.i("Viewmodel_insert", String.valueOf(model_haccp_task_result_core_cooking));
        repository_haccp_queries.insert_record(model_haccp_task_result_core_cooking);
    }

    public void update_record(Integer original_unix, Integer temperature, int remedial_action_selected, int status, String remedial_action_freetext, long current_time, String new_sensor_id, String new_sensor_serial_number, String username) {
        repository_haccp_queries.update_record(original_unix, temperature, remedial_action_selected, status, remedial_action_freetext, current_time, new_sensor_id, new_sensor_serial_number, username );
    }

    public void update_timestamp_uploaded_record(int task_def_id, int timestamp_unix, Integer timestamp_uploaded) {
        repository_haccp_queries.update_timestamp_uploaded_record(task_def_id, timestamp_unix, timestamp_uploaded);
    }

//    public LiveData<List<Model_haccp_task_result_core_cooking>> get_record_by_window_id(int id) {
//       return repository_haccp_queries.get_record_by_window_id_repo(id);
//    }

    public LiveData<List<Model_haccp_task_result_core_cooking>> query_pending_records() {
        return repository_haccp_queries.query_pending_records();
    }

    public LiveData<List<Model_haccp_task_result_core_cooking>> query_completed_records() {
        return repository_haccp_queries.query_completed_records();
    }

    public LiveData<Integer> get_count_completed() {
        return repository_haccp_queries.get_count_completed;
    }

    public LiveData<Integer> get_count_pending() {
        return repository_haccp_queries.get_count_pending;
    }







    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
