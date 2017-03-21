package com.example.bon.filmgalaxy;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.bon.filmgalaxy.adapter.CustomViewPagerAdapter;
import com.example.bon.filmgalaxy.app.AppController;
import com.example.bon.filmgalaxy.app.LinkInternet;
import com.example.bon.filmgalaxy.fragment.FragmentDangChieu;
import com.example.bon.filmgalaxy.fragment.FragmentSapChieu;
import com.example.bon.filmgalaxy.model.TypeImagePager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    // Log tag
    private TabLayout tabLayout;
    private int count = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerlayout_left;

    List<TypeImagePager> arrayBanner = new ArrayList<>();
    private CustomViewPagerAdapter pagerAdapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        ClassFMDangChieu();
        CustomToolBar();
        ImageBanner();
        funtionTablayout();

    }

    public void CustomToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionbar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout_left.openDrawer(GravityCompat.START);
            }
        });

        pagerAdapter = new CustomViewPagerAdapter(this,arrayBanner);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= 5){
                            viewPager.setCurrentItem(count);
                            count++;
                        }else {
                            count = 0;
                            viewPager.setCurrentItem(count);
                        }
                    }
                });
            }
        },500,3000);
    }

    // Doc Image Json URL Banner
    public void ImageBanner(){
        JsonArrayRequest jsonArray = new JsonArrayRequest(LinkInternet.Link_Banner, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0;i < response.length();i++){
                    try {
                        JSONObject json = response.getJSONObject(i);
                        TypeImagePager typeImage = new TypeImagePager();
                        typeImage.setHinhAnh(json.getString("urlHinh"));
                        arrayBanner.add(typeImage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                pagerAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"Error" + error.getMessage());
            }
        });

        AppController.getmInstance().addToRequestQueue(jsonArray);
    }

    public void AnhXa(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerlayout_left = (DrawerLayout)findViewById(R.id.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout =  (TabLayout)findViewById(R.id.tabLayout);
    }

    public void funtionTablayout(){
        tabLayout.addTab(tabLayout.newTab().setText("Phim Đang Chiếu"));
        tabLayout.addTab(tabLayout.newTab().setText("Phim Sắp Chiếu"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        ClassFMDangChieu();
                        break;
                    case 1:
                        ClassFMSapChieu();
                        break;
                    default:
                        ClassFMDangChieu();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.icon_User:
                drawerlayout_left.openDrawer(GravityCompat.END);
        }
        return super.onOptionsItemSelected(item);
    }

    public void ClassFMDangChieu(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentDangChieu fragmentDangChieu = new FragmentDangChieu();
        fragmentTransaction.replace(R.id.framLayoutFragment,fragmentDangChieu);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void ClassFMSapChieu(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentSapChieu fragmentSapChieu = new FragmentSapChieu();
        fragmentTransaction.replace(R.id.framLayoutFragment,fragmentSapChieu);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
