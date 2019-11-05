package com.tscan.app.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
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

@Database(entities = {Model_haccp_corrective_action_type.class, Model_haccp_food_item_categories.class, Model_haccp_food_item_types.class, Model_join.class, Model_haccp_task_result_core_cooking.class, Model_mobile_device.class, Model_haccp_user.class, Model_haccp_task_window.class, Model_haccp_task_definition.class, Model_haccp_task_category.class, Model_haccp_task_result_type.class, Model_haccp_location.class, Model_haccp_task_result_status.class}, version = 1)
public abstract class Database_HACCP extends RoomDatabase{

        public abstract Dao_join dao_join();

    private static Database_HACCP database_HACCP;

        public static Database_HACCP getDatabase_haccp(final Context context){
            if (database_HACCP == null) {
                synchronized (RoomDatabase.class) {
                    if (database_HACCP == null)
                    { database_HACCP = Room.databaseBuilder(context.getApplicationContext(),
                            Database_HACCP.class, "database_haccp").build();
                    }
                }
            }
            return database_HACCP;
        }


    /**
     * THIS IS CALLED AT EVERY START OF THE APP TO DELETE THE CONTENT OF MOBILE_DEVICE TABLE
     * AT LOGIN_NEXT ONCLICK, THE NEW DATA ARE UPLOADED TO THE TABLE
     **/
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(database_HACCP).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final Dao_join mDao;

        public PopulateDbAsync(Database_HACCP database_haccp) {
            mDao = database_haccp.dao_join();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.delete_mobile_device();
            return null;
        }
    }
}

