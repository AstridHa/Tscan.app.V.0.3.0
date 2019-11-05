package com.tscan.app.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.tscan.app.Dao.Dao_join;
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
import com.tscan.app.Database.Database_HACCP;

import java.util.List;

import static com.tscan.app.Activities.Activity_login.ui_listener_login;

/**
 * A Repository is a class that abstracts access to multiple data sources and
 * handles data operations.
 * It manages query threads and allows you to use multiple backends.
 *
 * The Repository is not part of the Architecture Components libraries,
 * but is a suggested best practice for code separation and architecture.
 * It provides a clean API to the rest of the app for app data
 **/

public class Repository_haccp_queries {

    private Dao_join dao_join;
    private Database_HACCP db_definition;

    public LiveData<Integer> get_count_uncompleted;
    public LiveData<Integer> get_count_completed;
    public LiveData<Integer> get_count_pending;

    public LiveData<List<Model_haccp_food_item_types>> getFoodItemTypes_by_category_id;





    /////////////////////////////////////////////////////////////////////////
//   CONSTRUCTOR                                                       //
/////////////////////////////////////////////////////////////////////////
    public Repository_haccp_queries(Application application){
        db_definition = Database_HACCP.getDatabase_haccp(application);
        dao_join = db_definition.dao_join();

//        get_count_uncompleted = dao_join.get_count_uncompleted();
        get_count_completed = dao_join.get_count_completed();
        get_count_pending = dao_join.get_count_pending();

        getFoodItemTypes_by_category_id = dao_join.getFoodItemTypes_by_category_id();

    }


/////////////////////////////////////////////////////////////////////////
//   FUNCTIONS - Sorted by Table name                                  //
/////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////
    //   JOIN QUERY                                                        //
    /////////////////////////////////////////////////////////////////////////
//    public LiveData<List<Model_join>> query_data_to_display_all_task_windows() {
//        return dao_join.query_data_to_display_all_task_windows();
//    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_window_joined_data                                          //
    /////////////////////////////////////////////////////////////////////////
    public void delete_model_join(){
        new delete_model_join(dao_join).execute();
    }

    private static class delete_model_join extends AsyncTask<Model_join, Void, Void> {
        private Dao_join dao;

        delete_model_join(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_join... model_join){
            dao.delete_model_join();
            return null;
        }
    }
//
//    public LiveData<List<Model_window_joined_data>> query_all_joined_data_table() {
//        return dao_join.query_all_joined_data_table();
//    }
//
//    public LiveData<List<Model_window_joined_data>> query_joined_uncompleted_task_windows() {
//        return dao_join.query_joined_uncompleted_task_windows();
//    }
//
//    public LiveData<List<Model_window_joined_data>> query_joined_completed_task_windows() {
//        return dao_join.query_joined_completed_task_windows();
//    }
//
//    public LiveData<List<Model_window_joined_data>> query_joined_pending_task_windows() {
//        return dao_join.query_joined_pending_task_windows();
//    }

//    public LiveData<Integer> get_count_uncompleted() {
//        return dao_join.get_count_uncompleted();
//    }


//    public void insert_windows_joined_data(Model_window_joined_data model_window_joined_data){
//        new insert_windows_joined_data(dao_join).execute(model_window_joined_data);
//    }
//    private static class insert_windows_joined_data extends AsyncTask<Model_window_joined_data, Void, Boolean> {
//        private Dao_join dao_haccp;
//
//        insert_windows_joined_data(Dao_join dao) {
//            dao_haccp = dao;
//        }
//
//        @Override
//        protected Boolean doInBackground(Model_window_joined_data... model_window_joined_data){
//            dao_haccp.insert_windows_joined_data(model_window_joined_data[0]);
//            return true;
//        }
//    }

//    public void update_result_count(int id){
//        new update_result_count(dao_join).execute(id);
//    }
//
//    private static class update_result_count extends AsyncTask<Integer, Void, Void>    {
//        private Dao_join update_result_count_dao;
//
//        update_result_count(Dao_join dao_haccp) {
//            update_result_count_dao = dao_haccp;
//        }
//
//        @Override
//        protected Void doInBackground(Integer... id){
//            update_result_count_dao.update_result_count(id[0]);
//            return null;
//        }
//    }
//
//    public void update_upload_status(int id, int status){
//        new update_upload_status(dao_join).execute(id, status);
//    }
//    private static class update_upload_status extends AsyncTask<Integer, Void, Void>    {
//        private Dao_join update_upload_status_dao;
//
//        update_upload_status(Dao_join dao_haccp) {
//            update_upload_status_dao = dao_haccp;
//        }
//
//        @Override
//        protected Void doInBackground(Integer... id){
//            Log.i("REPO", String.valueOf(id[0]));
//            Log.i("REPO", String.valueOf(id[1]));
//            update_upload_status_dao.update_upload_status(id[0], id[1]);
//            return null;
//        }
//    }

//    public void delete_model_window_joined_data(){
//        new delete_model_window_joined_data(dao_join).execute();
//    }
//    private static class delete_model_window_joined_data extends AsyncTask<Model_window_joined_data, Void, Void> {
//        private Dao_join dao;
//
//        delete_model_window_joined_data(Dao_join dao_haccp) {
//            dao = dao_haccp;
//        }
//
//        @Override
//        protected Void doInBackground(Model_window_joined_data... model_window_joined_data){
//            dao.delete_model_window_joined_data();
//            return null;
//        }
//    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_definition                                       //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_definition>> query_task_definition() {
        return dao_join.query_task_definition();
    }

    public void insert_haccp_task_definition(Model_haccp_task_definition haccp_task_definition){
        new Insert_async_Task_def(dao_join).execute(haccp_task_definition);
    }

    private static class Insert_async_Task_def extends AsyncTask<Model_haccp_task_definition, Void, Void> {
        private Dao_join dao_haccp;

        Insert_async_Task_def(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_definition... haccp_task_definition){
            dao_haccp.insert_haccp_task_definition(haccp_task_definition[0]);
            return null;
        }
    }

    public void update_haccp_task_definition(Model_haccp_task_definition haccp_task_definition){
        new Repository_haccp_queries.Update_async_Task_def(dao_join).execute(haccp_task_definition);
    }

    private static class Update_async_Task_def extends AsyncTask<Model_haccp_task_definition, Void, Void> {
        private Dao_join async_Task_def_dao;

        Update_async_Task_def(Dao_join dao_haccp) {
            async_Task_def_dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_definition... haccp_task_definition){
            async_Task_def_dao.update_haccp_task_definition(haccp_task_definition[0]);
            return null;
        }
    }

    public void delete_haccp_task_definition(){
        new Repository_haccp_queries.Delete_async_Task_def(dao_join).execute();
    }

    private static class Delete_async_Task_def extends AsyncTask<Model_haccp_task_definition, Void, Void> {
        private Dao_join dao;

        Delete_async_Task_def(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_definition... haccp_task_definition){
            dao.delete_haccp_task_definition();
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_location                                              //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_location>> get_all_haccp_location() {
        return dao_join.get_all_haccp_location();
    }

    public void insert_haccp_location(Model_haccp_location haccp_location){
        new Repository_haccp_queries.Insert_Async_Location(dao_join).execute(haccp_location);
    }

    private static class Insert_Async_Location extends AsyncTask<Model_haccp_location, Void, Void> {
        private Dao_join dao_haccp;

        Insert_Async_Location(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_location... haccp_location){
            dao_haccp.insert_haccp_location(haccp_location[0]);
            return null;
        }
    }

    public void update_haccp_location(Model_haccp_location haccp_location){
        new Repository_haccp_queries.Update_Async_Location(dao_join).execute(haccp_location);
    }

    private static class Update_Async_Location extends AsyncTask<Model_haccp_location, Void, Void> {
        private Dao_join async_task_dao;

        Update_Async_Location(Dao_join dao_haccp) {
            async_task_dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_location... haccp_location){
            async_task_dao.update_haccp_location(haccp_location[0]);
            return null;
        }
    }

    public void delete_haccp_location(){
        new Repository_haccp_queries.Delete_Async_Location(dao_join).execute();
    }

    private static class Delete_Async_Location extends AsyncTask<Model_haccp_location, Void, Void> {
        private Dao_join dao;

        Delete_Async_Location(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_location... haccp_location){
            dao.delete_haccp_location();
            return null;
        }
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_category                                         //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_category>> get_all_categories() {
        return dao_join.get_all_category_list();
    }

    public void insert_haccp_task_category(Model_haccp_task_category haccp_task_category){
        new Insert_Async_task_category(dao_join).execute(haccp_task_category);
    }

    private static class Insert_Async_task_category extends AsyncTask<Model_haccp_task_category, Void, Void> {
        private Dao_join dao_haccp;

        Insert_Async_task_category(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_category... haccp_task_category){
            dao_haccp.insert_haccp_task_category(haccp_task_category[0]);
            return null;
        }
    }

    public void update_haccp_task_category(Model_haccp_task_category haccp_task_category){
        new Update_Async_task_category(dao_join).execute(haccp_task_category);
    }

    private static class Update_Async_task_category extends AsyncTask<Model_haccp_task_category, Void, Void> {
        private Dao_join dao_category;

        Update_Async_task_category(Dao_join dao_haccp) {
            dao_category = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_category... haccp_task_category){
            dao_category.update_haccp_task_category(haccp_task_category[0]);
            return null;
        }
    }

    public void delete_haccp_task_category(){
        new Delete_Async_task_category(dao_join).execute();
    }

    private static class Delete_Async_task_category extends AsyncTask<Model_haccp_task_category, Void, Void> {
        private Dao_join _dao_category;

        Delete_Async_task_category(Dao_join dao_category) {
            _dao_category = dao_category;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_category... haccp_task_category){
            _dao_category.delete_haccp_task_category();
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_status                                    //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_result_status>> get_all_haccp_task_result_status() {
        return dao_join.get_all_haccp_task_result_status();
    }

    public void insert_haccp_task_result_status(Model_haccp_task_result_status haccp_task_result_status){
        new Insert_Async_Result_Status(dao_join).execute(haccp_task_result_status);
    }

    private static class Insert_Async_Result_Status extends AsyncTask<Model_haccp_task_result_status, Void, Void> {
        private Dao_join dao_result_status;

        Insert_Async_Result_Status(Dao_join _dao_result_status) {
            dao_result_status = _dao_result_status;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_status... haccp_task_result_status){
            dao_result_status.insert_haccp_task_result_status(haccp_task_result_status[0]);
            return null;
        }
    }


    public void update_haccp_task_result_status(Model_haccp_task_result_status haccp_task_result_status){
        new Update_Async_Result_Status(dao_join).execute(haccp_task_result_status);
    }

    private static class Update_Async_Result_Status extends AsyncTask<Model_haccp_task_result_status, Void, Void> {
        private Dao_join Async_Result_Status_dao;

        Update_Async_Result_Status(Dao_join dao_haccp) {
            Async_Result_Status_dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_status... haccp_task_result_status){
            Async_Result_Status_dao.update_haccp_task_result_status(haccp_task_result_status[0]);
            return null;
        }
    }


    public void delete_haccp_task_result_status(){
        new Delete_Async_Result_Status(dao_join).execute();
    }

    private static class Delete_Async_Result_Status extends AsyncTask<Model_haccp_task_result_status, Void, Void> {
        private Dao_join dao_task_result_status;

        Delete_Async_Result_Status(Dao_join dao_) {
            dao_task_result_status = dao_;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_status... haccp_task_result_status){
            dao_task_result_status.delete_haccp_task_result_status();
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_type                                      //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_result_type>> get_all_result_types() {
        return  dao_join.get_all_haccp_task_result_type();
    }

    public void insert_haccp_task_result_type(Model_haccp_task_result_type model_haccp_task_result_type){
        new Insert_Async_task_result_type(dao_join).execute(model_haccp_task_result_type);
    }

    private static class Insert_Async_task_result_type extends AsyncTask<Model_haccp_task_result_type, Void, Void> {
        private Dao_join dao_haccp;

        Insert_Async_task_result_type(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_type... model_haccp_task_result_type){
            dao_haccp.insert_haccp_task_result_type(model_haccp_task_result_type[0]);
            return null;
        }
    }

    public void update_haccp_task_result_type(Model_haccp_task_result_type model_haccp_task_result_type){
        new Update_Async_task_result_type(dao_join).execute(model_haccp_task_result_type);
    }

    private static class Update_Async_task_result_type extends AsyncTask<Model_haccp_task_result_type, Void, Void> {
        private Dao_join dao_category;

        Update_Async_task_result_type(Dao_join dao_haccp) {
            dao_category = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_type... model_haccp_task_result_type){
            dao_category.update_haccp_task_result_type(model_haccp_task_result_type[0]);
            return null;
        }
    }

    public void delete_haccp_task_result_type(){
        new Delete_Async_task_result_type(dao_join).execute();
    }

    private static class Delete_Async_task_result_type extends AsyncTask<Model_haccp_task_result_type, Void, Void> {
        private Dao_join _dao_category;

        Delete_Async_task_result_type(Dao_join dao_category) {
            _dao_category = dao_category;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_type... model_haccp_task_result_type){
            _dao_category.delete_haccp_task_result_type();
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_window                                           //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_task_window>> get_all_haccp_task_window() {
        return dao_join.get_all_haccp_task_window();
    }

    public void insert_haccp_task_window(Model_haccp_task_window haccp_task_window){
        new Insert_Async_task_window(dao_join).execute(haccp_task_window);
    }

    private static class Insert_Async_task_window extends AsyncTask<Model_haccp_task_window, Void, Void> {
        private Dao_join dao_haccp;

        Insert_Async_task_window(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_window... haccp_task_window){
            dao_haccp.insert_haccp_task_window(haccp_task_window[0]);
            return null;
        }
    }

    public void update_haccp_task_window(Model_haccp_task_window haccp_task_window){
        new Update_Async_task_window(dao_join).execute(haccp_task_window);
    }

    private static class Update_Async_task_window extends AsyncTask<Model_haccp_task_window, Void, Void> {
        private Dao_join Async_task_window_dao;

        Update_Async_task_window(Dao_join dao_haccp) {
            Async_task_window_dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_window... haccp_task_window){
            Async_task_window_dao.update_haccp_task_window(haccp_task_window[0]);
            return null;
        }
    }

    public void delete_haccp_task_window(){
        new Delete_Async_task_window(dao_join).execute();
    }

    private static class Delete_Async_task_window extends AsyncTask<Model_haccp_task_window, Void, Void> {
        private Dao_join dao;

        Delete_Async_task_window(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_window... haccp_task_window){
            dao.delete_haccp_task_window();
            return null;
        }
    }

    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_food_item_category                                    //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_food_item_categories>> getAllFoodItemCategories() {
        return dao_join.getAllFoodItemCategories();
    }

    public void delete_haccp_food_item_categories(){
        new Delete_Async_food_item_category(dao_join).execute();
    }

    private static class Delete_Async_food_item_category extends AsyncTask<Model_haccp_food_item_categories, Void, Void> {
        private Dao_join dao;

        Delete_Async_food_item_category(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_food_item_categories... haccp_food_item_category){
            dao.delete_Async_food_item_category();
            return null;
        }
    }

    public void insert_haccp_food_category(Model_haccp_food_item_categories food_item_categories){
        new Insert_Async_food_category(dao_join).execute(food_item_categories);
    }
    private static class Insert_Async_food_category extends AsyncTask<Model_haccp_food_item_categories, Void, Void> {
        private Dao_join daaao;

        Insert_Async_food_category(Dao_join dao) {
            daaao = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_food_item_categories... food_item_categories){
            daaao.insert_haccp_food_category(food_item_categories[0]);
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_food_item_types                                       //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_food_item_types>> getAllFoodItemTypes() {
        return dao_join.getAllFoodItemTypes();
    }

    public LiveData<List<Model_haccp_food_item_types>> getFoodItemTypes_by_category_id() {
        return dao_join.getFoodItemTypes_by_category_id();
    }

    public Model_haccp_food_item_types[] getFoodItemTypes_by_category_id_bis(int category_id) {
        return dao_join.getFoodItemTypes_by_category_id_bis(category_id);
    }

    public void delete_haccp_food_item_types(){
        new Delete_Async_food_item_types(dao_join).execute();
    }

    private static class Delete_Async_food_item_types extends AsyncTask<Model_haccp_food_item_types, Void, Void> {
        private Dao_join dao;

        Delete_Async_food_item_types(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_food_item_types... haccp_food_item_types){
            dao.delete_Async_food_item_types();
            return null;
        }
    }

    public void insert_haccp_food_type(Model_haccp_food_item_types food_item_types){
        new Insert_Async_food_type(dao_join).execute(food_item_types);
    }
    private static class Insert_Async_food_type extends AsyncTask<Model_haccp_food_item_types, Void, Void> {
        private Dao_join dao_haccp;

        Insert_Async_food_type(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_food_item_types... food_item_types){
            dao_haccp.insert_haccp_food_type(food_item_types[0]);
            return null;
        }
    }



    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_corrective_action                                     //
    /////////////////////////////////////////////////////////////////////////
    public void delete_haccp_corrective_actions(){
        new Delete_haccp_corrective_actions(dao_join).execute();
    }

    private static class Delete_haccp_corrective_actions extends AsyncTask<Model_haccp_corrective_action_type, Void, Void> {
        private Dao_join dao;

        Delete_haccp_corrective_actions(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_corrective_action_type... corrective_action_type){
            dao.delete_haccp_corrective_actions();
            return null;
        }
    }

    public void insert_haccp_corrective_actions(Model_haccp_corrective_action_type corrective_action_type){
        new Insert_Async_corrective_actions(dao_join).execute(corrective_action_type);
    }
    private static class Insert_Async_corrective_actions extends AsyncTask<Model_haccp_corrective_action_type, Void, Void> {
        private Dao_join dao_haccp;

        Insert_Async_corrective_actions(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_corrective_action_type... corrective_action_type){
            dao_haccp.insert_haccp_corrective_actions(corrective_action_type[0]);
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_user                                                  //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_haccp_user>> get_all_users() {
        return dao_join.getAllUsers();
    }

    public void insert_haccp_user(Model_haccp_user haccp_user){
        new Insert_Async_Task(dao_join).execute(haccp_user);
    }

    private static class Insert_Async_Task extends AsyncTask<Model_haccp_user, Void, String> {
        private Dao_join dao_haccp_user;

        Insert_Async_Task(Dao_join dao) {
            dao_haccp_user = dao;
        }

        @Override
        protected String doInBackground(Model_haccp_user... user){
            String result = null;
            try {
                dao_haccp_user.saveUser(user[0]);
            } catch (Exception e){
                new Async_run_result_on_UI().execute((Void[])null);
            }
            return result;
        }
        }  private static class Async_run_result_on_UI extends AsyncTask<Void, Void, Void> {

                            protected Void doInBackground(Void... param) {
                                //No need
                                return null;
                            }

                            protected void onPostExecute(Void param) {
                            ui_listener_login.callUserDuplicate("duplicate");
                            }
                        }

    public void update_haccp_user(Model_haccp_user haccp_user){
        new Update_Async_Task(dao_join).execute(haccp_user);
    }

    private static class Update_Async_Task extends AsyncTask<Model_haccp_user, Void, Void> {
        private Dao_join async_task_dao;

        Update_Async_Task(Dao_join dao_haccp) {
            async_task_dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_user... haccp_user){
            async_task_dao.updateUser(haccp_user[0]);
            return null;
        }
    }

    public void delete_haccp_user(Model_haccp_user user_table){
        new Delete_Async_Task(dao_join).execute(user_table);
    }

    private static class Delete_Async_Task extends AsyncTask<Model_haccp_user, Void, Void> {
        private Dao_join dao_user_;

        Delete_Async_Task(Dao_join dao_user) {
            dao_user_ = dao_user;
        }

        @Override
        protected Void doInBackground(Model_haccp_user... haccp_user){
            dao_user_.deleteUser(haccp_user[0]);
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_mobile_device                                               //
    /////////////////////////////////////////////////////////////////////////
    public LiveData<List<Model_mobile_device>> getModel_mobile_device() {
        return dao_join.get_mobile_device();
    }

    public void insert_moble_device(Model_mobile_device model_mobile_device){
        new Insert_async_mobile(dao_join).execute(model_mobile_device);
    }

    private static class Insert_async_mobile extends AsyncTask<Model_mobile_device, Void, Void> {
        private Dao_join dao_haccp;

        Insert_async_mobile(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_mobile_device... model_mobile_device){
            dao_haccp.insert_moble_device(model_mobile_device[0]);
            return null;
        }
    }

    public void update_mobile_device(Model_mobile_device model_mobile_device){
        new Update_async_mobile(dao_join).execute(model_mobile_device);
    }

    private static class Update_async_mobile extends AsyncTask<Model_mobile_device, Void, Void> {
        private Dao_join async_task_dao;

        Update_async_mobile(Dao_join dao_haccp) {
            async_task_dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_mobile_device... model_mobile_device){
            async_task_dao.update_mobile_device(model_mobile_device[0]);
            return null;
        }
    }

    public void delete_mobile_device(){
        new Delete_async_mobile(dao_join).execute();
    }

    private static class Delete_async_mobile extends AsyncTask<Model_mobile_device, Void, Void> {
        private Dao_join dao;

        Delete_async_mobile(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_mobile_device... model_mobile_device){
            dao.delete_mobile_device();
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////
    //   Model_haccp_task_result_core_cooking                              //
    /////////////////////////////////////////////////////////////////////////
    public List<Model_haccp_task_result_core_cooking> getModel_core_cooking_results() {
        return dao_join.get_all_tasks_records();
    }

//    public LiveData<List<Model_haccp_task_result_core_cooking>> get_record_by_window_id_repo(int genere) {
//        return dao_join.get_record_by_window_id(genere);
//    }

    public LiveData<List<Model_haccp_task_result_core_cooking>> query_completed_records() {
        return dao_join.query_completed_records();
    }

    public LiveData<List<Model_haccp_task_result_core_cooking>> query_pending_records() {
        return dao_join.query_pending_records();
    }

    public void insert_record(Model_haccp_task_result_core_cooking model_haccp_recordz){
        new Insert_async_record(dao_join).execute(model_haccp_recordz);
    }

    private static class Insert_async_record extends AsyncTask<Model_haccp_task_result_core_cooking, Void, Void> {
        private Dao_join dao_haccp;

        Insert_async_record(Dao_join dao) {
            dao_haccp = dao;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_core_cooking... model_haccp_recordz){
            if(model_haccp_recordz != null && model_haccp_recordz.length == 1) {
                dao_haccp.insert_record(model_haccp_recordz[0]);
//                dao_haccp.update_result_count(model_haccp_recordz[0].getRecords_task_definition_id());
            }
            return null;
        }
    }

    public void update_record(Integer original_unix, Integer temperature){
        Log.i("SAVE_CALLED1", String.valueOf(original_unix));
        Log.i("SAVE_CALLED1", String.valueOf(temperature));


        new Update_async_record(dao_join).execute(original_unix, temperature );
    }

    private static class Update_async_record extends AsyncTask<Integer, Void, Void> {
        private Dao_join async_task_dao;

        Update_async_record(Dao_join dao_haccp) {
            async_task_dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Integer... model_haccp_recordz){
            Log.i("SAVE_CALLED2", String.valueOf(model_haccp_recordz[0]));
            Log.i("SAVE_CALLED2", String.valueOf(model_haccp_recordz[1]));



            async_task_dao.update_record(model_haccp_recordz[0], model_haccp_recordz[1]);
            return null;
        }
    }

    public void delete_record(){
        new Delete_async_record(dao_join).execute();
    }

    private static class Delete_async_record extends AsyncTask<Model_haccp_task_result_core_cooking, Void, Void> {
        private Dao_join dao;

        Delete_async_record(Dao_join dao_haccp) {
            dao = dao_haccp;
        }

        @Override
        protected Void doInBackground(Model_haccp_task_result_core_cooking... model_haccp_task_result_core_cookings){
            dao.delete_record();
            return null;
        }
    }

    public LiveData<Integer> get_count_completed() {
        return dao_join.get_count_completed();
    }

    public LiveData<Integer> get_count_pending() {
        return dao_join.get_count_pending();
    }

    public void update_timestamp_uploaded_record(int task_def_id, int timestamp_unix, Integer timestamp_uploaded){
        new update_timestamp_uploaded_record(dao_join).execute(task_def_id, timestamp_unix, timestamp_uploaded);
    }

    private static class update_timestamp_uploaded_record extends AsyncTask<Integer, Void, Void>    {
        private Dao_join dao_update_timestamp;

        update_timestamp_uploaded_record(Dao_join dao_haccp) {
            dao_update_timestamp = dao_haccp;
        }

        @Override
        protected Void doInBackground(Integer... passed_data){
            Log.i("Repository_update", String.valueOf(passed_data[0] + " " + passed_data[1]));
            dao_update_timestamp.update_timestamp_uploaded_record(passed_data[0], passed_data[1],  passed_data[2]);
            return null;
        }
    }
}
