package com.cjbensan.axiomaapp.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.fragment.LoginFragment;
import com.cjbensan.axiomaapp.fragment.SignUpFragment;

public class SignUpLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);

        setupFragment();
        setupActionBar();
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupFragment() {
        int fragmentCode = getIntent().getIntExtra(MainActivity.FRAGMENT, 0);
        Fragment fragment = null;

        switch (fragmentCode) {
            case MainActivity.SIGN_UP_FRAGMENT:
                fragment = new SignUpFragment();
                break;
            case MainActivity.LOGIN_FRAGMENT:
                fragment = new LoginFragment();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.close_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_close:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
