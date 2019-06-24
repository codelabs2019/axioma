package com.cjbensan.axiomaapp.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.adapter.UserSessionAdapter;
import com.cjbensan.axiomaapp.fragment.ClassesFragment;
import com.cjbensan.axiomaapp.fragment.ExamsFragment;
import com.cjbensan.axiomaapp.fragment.ExercisesFragment;
import com.cjbensan.axiomaapp.fragment.SettingsFragment;

public class UserSessionActivity extends AppCompatActivity {

    private static final int CLASSES_FRAGMENT = 0;
    private static final int EXERCISES_FRAGMENT = 1;
    private static final int EXAMS_FRAGMENT = 2;
    private static final int SETTINGS_FRAGMENT = 3;

    private BottomNavigationView bottomNavigation;
    private ViewPager viewPager;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_session);

        getWindow().setStatusBarColor(ResourcesCompat.getColor(
                getResources(), R.color.colorIndigo, null));

        setupViewPager();
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.ic_classes:
                                viewPager.setCurrentItem(CLASSES_FRAGMENT);
                                return true;
                            case R.id.ic_exercises:
                                viewPager.setCurrentItem(EXERCISES_FRAGMENT);
                                return true;
                            case R.id.ic_exams:
                                viewPager.setCurrentItem(EXAMS_FRAGMENT);
                                return true;
                            case R.id.ic_settings:
                                viewPager.setCurrentItem(SETTINGS_FRAGMENT);
                                return true;
                        }

                        return false;
                    }
                }
        );
    }

    private void setupViewPager() {
        UserSessionAdapter adapter = new UserSessionAdapter(getSupportFragmentManager());
        adapter.addFragment(new ClassesFragment());
        adapter.addFragment(new ExercisesFragment());
        adapter.addFragment(new ExamsFragment());
        adapter.addFragment(new SettingsFragment());

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else {
                    bottomNavigation.getMenu().getItem(CLASSES_FRAGMENT).setChecked(false);
                }

                bottomNavigation.getMenu().getItem(i).setChecked(true);
                prevMenuItem = bottomNavigation.getMenu().getItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
