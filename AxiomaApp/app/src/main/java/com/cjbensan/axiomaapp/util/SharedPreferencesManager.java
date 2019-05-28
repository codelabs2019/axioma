package com.cjbensan.axiomaapp.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.cjbensan.axiomaapp.activity.MainActivity;
import com.cjbensan.axiomaapp.domain.Student;

public class SharedPreferencesManager {

    private static final String SHARED_PREF_NAME = "com.cjbensan.axiomaapp.shared_pref";
    private static final String KEY_ID = "key_id";
    private static final String KEY_FORENAME = "key_forename";
    private static final String KEY_SURNAME = "key_surname";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_PASSWORD = "key_password";

    private static SharedPreferencesManager instance;
    private static Context context;

    private SharedPreferencesManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }

        return instance;
    }

    public void login(Student student) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, student.getId());
        editor.putString(KEY_FORENAME, student.getForename());
        editor.putString(KEY_SURNAME, student.getSurname());
        editor.putString(KEY_EMAIL, student.getEmail());
        editor.putString(KEY_PASSWORD, student.getPassword());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_ID, null) != null;
    }

    public Student getStudent() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new Student(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_FORENAME, null),
                sharedPreferences.getString(KEY_SURNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null)
        );
    }

    public void logout() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        context.startActivity(new Intent(context, MainActivity.class));
    }
}
