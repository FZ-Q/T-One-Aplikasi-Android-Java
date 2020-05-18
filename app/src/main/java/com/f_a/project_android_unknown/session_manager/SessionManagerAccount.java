package com.f_a.project_android_unknown.session_manager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagerAccount {
    SharedPreferences SP;

    SharedPreferences.Editor SPE;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "SPDataAccount";

    public static final String KEY_ID_USER = "Id_User";

    public SessionManagerAccount(Context context){
        this.context = context;
        SP = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SPE = SP.edit();
    }

    public  void createLoginSession(String Id_User){
        SPE.putString(KEY_ID_USER, Id_User);
        SPE.commit();
    }

    public HashMap<String, String> getUser(){
        HashMap<String, String> User = new HashMap<String, String>();

        User.put(KEY_ID_USER, SP.getString(KEY_ID_USER, null));

        return User;
    }

    public void clearSeassion(){
        SPE.clear().apply();
    }
}
