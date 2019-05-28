package com.cjbensan.axiomaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.util.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity {

    public static final String FRAGMENT = "com.cjbensan.axiomaapp.FRAGMENT";
    public static final int SIGN_UP_FRAGMENT = 0;
    public static final int LOGIN_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPreferencesManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, UserSessionActivity.class));
            return;
        }

        Button signUpBtn = (Button) findViewById(R.id.btn_sign_up);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpLoginActivity.class);
                intent.putExtra(FRAGMENT, SIGN_UP_FRAGMENT);
                startActivity(intent);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpLoginActivity.class);
                intent.putExtra(FRAGMENT, LOGIN_FRAGMENT);
                startActivity(intent);
            }
        });
    }
}
