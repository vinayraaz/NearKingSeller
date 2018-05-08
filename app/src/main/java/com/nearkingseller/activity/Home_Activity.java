package com.nearkingseller.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.nearkingseller.Navigation_Activity;
import com.nearkingseller.R;
import com.nearkingseller.constants.CommonConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 12-Apr-18.
 */

public class Home_Activity extends Navigation_Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    String UserEmail="",UserPass="";
   /* private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.banner_1,R.drawable.banner_1,R.drawable.banner_1,R.drawable.banner_1};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
*/
   SliderLayout sliderLayout;
    HashMap<String,Integer> Hash_file_maps ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll_main_data = (LinearLayout) findViewById(R.id.main_include);
        View child = getLayoutInflater().inflate(R.layout.home__landing_layout, null);
        ll_main_data.addView(child);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Home");
        //getSupportActionBar().setIcon(R.drawable.movilo_icon); //also displays wide logo
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        final SharedPreferences prefs = getSharedPreferences("nearking_login", MODE_PRIVATE);
        UserEmail = prefs.getString("user_email", "");
        UserPass = prefs.getString("user_pass", "");
        CommonConstant.UserEmail = UserEmail;
        CommonConstant.UserPass = UserPass;

        Hash_file_maps = new HashMap<String, Integer>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        Hash_file_maps.put("Near King1", R.drawable.banner_1);
        Hash_file_maps.put("Near King2", R.drawable.banner_1);
        Hash_file_maps.put("Near King3", R.drawable.banner_1);
        Hash_file_maps.put("Near King4", R.drawable.banner_1);
        Hash_file_maps.put("Near King5", R.drawable.banner_1);


        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(Home_Activity.this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);



    }
    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
