package com.cjbensan.axiomaapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.fragment.ClassFragment;

import java.util.Locale;

public class ClassesActivity extends AppCompatActivity implements View.OnClickListener {

    private final String[] classNames = {
            "Potenciación: definición y propiedades",
            "Radicación: definición y propiedades",
            "Ejercicio resuelto 1",
            "Ejercicio resuelto 2",
            "Ejercicio resuelto 3"
    };

    private final String[] videoIds= {
            "y4NMrL1AGzw",
            "73nEd-bSrNM",
            "hGCg7pGjhtA",
            "CkshLYyRgoo",
            "8n96AUDg8ow"
    };

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView classCounterTxt;
    private MenuItem currentMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setupNavigationView();
        setupToolbar();
        setupBottomNavigationBar();
        setupClassCounterTextView();

        Intent intent = getIntent();
        String topicName = intent.getStringExtra("TOPIC_NAME");
        View view = navigationView.getHeaderView(0);
        TextView headerNavViewTxt = (TextView) view.findViewById(R.id.txt_header_navigation_view);
        headerNavViewTxt.setText(topicName);
    }

    private void setupNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                currentMenuItem = menuItem;

                menuItem.setChecked(true);
                menuItem.setCheckable(true);
                toolbar.setTitle(menuItem.getTitle());
                classCounterTxt.setText(
                        String.format(
                                Locale.US,
                                "%d/%d",
                                menuItem.getItemId() + 1,
                                navigationView.getMenu().size()));

                updateFragment(
                        currentMenuItem.getTitle().toString(),
                        videoIds[currentMenuItem.getItemId()]);

                drawerLayout.closeDrawers();

                return true;
            }
        });

        Menu menu = navigationView.getMenu();
        for (int i = 0; i < classNames.length; i++) {
            menu.add(R.id.nav_refer, i, Menu.NONE, classNames[i]);
        }

        navigationView.getMenu().getItem(0).setChecked(true).setCheckable(true);
        currentMenuItem = navigationView.getCheckedItem();

        ClassFragment classFragment = ClassFragment.getInstance(
                currentMenuItem.getTitle().toString(), videoIds[0]);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, classFragment)
                .commit();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_classes);
        toolbar.setBackgroundColor(ResourcesCompat.getColor(getResources(), android.R.color.white, null));
        toolbar.setTitleTextAppearance(this, R.style.ClasseAppBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(navigationView.getMenu().getItem(0).getTitle());
    }

    private void setupBottomNavigationBar() {
        ImageView nextIc = (ImageView) findViewById(R.id.img_next);
        ImageView previousIc = (ImageView) findViewById(R.id.img_previous);
        TextView nextTxt = (TextView) findViewById(R.id.txt_next);
        TextView previousTxt = (TextView) findViewById(R.id.txt_previous);

        nextIc.setOnClickListener(this);
        nextTxt.setOnClickListener(this);
        previousIc.setOnClickListener(this);
        previousTxt.setOnClickListener(this);
    }

    private void setupClassCounterTextView() {
        classCounterTxt = (TextView) findViewById(R.id.txt_class_counter);
        classCounterTxt.setText(
                String.format(Locale.US,
                        "%d/%d",
                        navigationView.getMenu().getItem(0).getItemId() + 1,
                        navigationView.getMenu().size()));

    }

    private void updateFragment(String className, String videoId) {
        ClassFragment classFragment = ClassFragment.getInstance(className, videoId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, classFragment)
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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.ic_close:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_next:
            case R.id.txt_next:
                if (currentMenuItem.getItemId() != (navigationView.getMenu().size() - 1)) {
                    navigationView.getMenu().getItem(currentMenuItem.getItemId() + 1)
                            .setChecked(true).setCheckable(true);

                    currentMenuItem = navigationView.getCheckedItem();

                    classCounterTxt.setText(
                            String.format(Locale.US,
                                    "%d/%d",
                                    currentMenuItem.getItemId() + 1,
                                    classNames.length)
                    );

                    toolbar.setTitle(currentMenuItem.getTitle());
                    updateFragment(
                            currentMenuItem.getTitle().toString(),
                            videoIds[currentMenuItem.getItemId()]);
                }

                break;
            case R.id.img_previous:
            case R.id.txt_previous:
                if (currentMenuItem.getItemId() != 0) {
                    navigationView.getMenu().getItem(currentMenuItem.getItemId() - 1)
                            .setChecked(true).setCheckable(true);

                    currentMenuItem = navigationView.getCheckedItem();

                    classCounterTxt.setText(
                            String.format(Locale.US,
                                    "%d/%d",
                                    currentMenuItem.getItemId() + 1,
                                    classNames.length)
                    );

                    toolbar.setTitle(currentMenuItem.getTitle());
                    updateFragment(
                            currentMenuItem.getTitle().toString(),
                            videoIds[currentMenuItem.getItemId()]);
                }

                break;
        }

    }
}
