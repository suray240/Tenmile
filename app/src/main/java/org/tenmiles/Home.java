package org.tenmiles;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.tenmiles.fragment.AboutUsFragment;
import org.tenmiles.fragment.DashBoardFragment;
import org.tenmiles.fragment.RateUsFragment;
import org.tenmiles.jumblr.JumblrClient;
import org.tenmiles.jumblr.types.Post;
import org.tenmiles.jumblr.types.User;
import org.tenmiles.utils.API;

import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity  {

    private final static int DASHBOARD_FRAGMENT = 0;
    private static int RATEUS_FRAGMENT = DASHBOARD_FRAGMENT + 1;
    private static int ABOUTUS_FRAGMENT = RATEUS_FRAGMENT + 1;
    private final static int FRAGMENT_COUNT = ABOUTUS_FRAGMENT + 1;

    // Fragment Initializations
    private Fragment[] fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    /* Navigation Item */
    private DrawerLayout navigationDrawer;
    private NavigationView navigationView;

    // Toolbar
    private Toolbar toolbar;

    private Menu menuReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setActionBar();
        init();
        initializeFragments();
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

    }

    private void init() {

        navigationDrawer = (DrawerLayout) findViewById(R.id.homeDrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.homeNavigationView);


        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void initializeFragments() {

        fragment = new Fragment[FRAGMENT_COUNT];
        fragment[DASHBOARD_FRAGMENT] = new DashBoardFragment();
        fragment[RATEUS_FRAGMENT] = new RateUsFragment();
        fragment[ABOUTUS_FRAGMENT] = new AboutUsFragment();

        changeFragments(DASHBOARD_FRAGMENT);



    }

    private void changeFragments(int fragments) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment[fragments], "" + fragments);
        fragmentTransaction.commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        navigationDrawer.closeDrawers();
                        int id = menuItem.getItemId();

                        switch (id) {
                            case R.id.logout:
                                finish();
                                break;

                            case R.id.home:
                                changeFragments(DASHBOARD_FRAGMENT);
                                toolbar.setTitle(menuItem.getTitle());
                                break;

                            case R.id.rateUs:
                                changeFragments(RATEUS_FRAGMENT);
                                toolbar.setTitle(menuItem.getTitle());
                                break;

                            case R.id.about:
                                changeFragments(ABOUTUS_FRAGMENT);
                                toolbar.setTitle(menuItem.getTitle());
                                break;
                        }

                        return true;
                    }

                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);
        menuReport = menu;

        return super.onCreateOptionsMenu(menuReport);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                navigationDrawer.openDrawer(GravityCompat.START);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
