package com.niteroomcreation.scaffold.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.niteroomcreation.scaffold.R;
import com.niteroomcreation.scaffold.ui.base.BaseView;
import com.niteroomcreation.scaffold.ui.fragment.gallery.GalleryFragment;
import com.niteroomcreation.scaffold.ui.fragment.home.HomeFragment;
import com.niteroomcreation.scaffold.ui.fragment.slideshow.SlideshowFragment;
import com.niteroomcreation.scaffold.ui.fragment.tool.ToolFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseView implements MainContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;

    @Override
    protected int parentLayout() {
        return 0;
    }

    @Override
    protected int contentLayout() {
        return R.layout.a_main;
    }

    @Override
    protected void initComponents() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drawerLayout
                , toolbar
                , R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        moveToFragment(flMainContent.getId(), HomeFragment.newInstance(), HomeFragment.class.getSimpleName());
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        moveToFragment(flMainContent.getId(), HomeFragment.newInstance(), HomeFragment.class.getSimpleName());
                        break;

                    case R.id.nav_gallery:
                        moveToFragment(flMainContent.getId(), GalleryFragment.newInstance(), GalleryFragment.class.getSimpleName());
                        break;

                    case R.id.nav_slideshow:
                        moveToFragment(flMainContent.getId(), SlideshowFragment.newInstance(), SlideshowFragment.class.getSimpleName());
                        break;

                    case R.id.nav_tools:
                        moveToFragment(flMainContent.getId(), ToolFragment.newInstance(), ToolFragment.class.getSimpleName());
                        break;

                    case R.id.nav_share:
                        break;

                    case R.id.nav_send:
                        break;
                }

                onBackPressed();

                return true;
            }
        });
    }

    @OnClick({R.id.fab})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (!(getBaseMainFragmentManager().findFragmentById(flMainContent.getId()) instanceof HomeFragment)) {
                moveToFragment(flMainContent.getId(), HomeFragment.newInstance(), HomeFragment.class.getSimpleName());
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
