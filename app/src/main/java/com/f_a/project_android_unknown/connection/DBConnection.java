package com.f_a.project_android_unknown.connection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.f_a.project_android_unknown.model.ModelAccount;
import com.f_a.project_android_unknown.service.DAOAccount;

@Database
        (entities = {
                ModelAccount.class
        }, version = 1, exportSchema = true)
public abstract class DBConnection extends RoomDatabase {
        private static DBConnection INSTANCE;

        public abstract DAOAccount DAOAccount();


        public static DBConnection getDatabase(Context context){
                if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                DBConnection.class, "db_t_one")
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build();
                }
                return INSTANCE;
        }

        public static void destroyInstance(){
                INSTANCE = null;
        }
}
