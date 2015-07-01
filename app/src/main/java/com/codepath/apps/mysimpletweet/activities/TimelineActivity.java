package com.codepath.apps.mysimpletweet.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweet.R;
import com.codepath.apps.mysimpletweet.fragments.HomeTimeLineFragment;
import com.codepath.apps.mysimpletweet.fragments.MentionsTimeLineFragment;

public class TimelineActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowHomeEnabled(true);
        bar.setIcon(R.drawable.twitter_logo);
        // bar.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeResource(getResources(),R.color.abc_background_cache_hint_selector_material_dark)));
        bar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));

         //get the view pager
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);

        //set the view pager adapter
       viewPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        //find the pager
        PagerSlidingTabStrip  tabStrip= (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //atach view pager
        tabStrip.setViewPager(viewPager);
    }

            @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_user) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onProfileView(MenuItem item) {
        //launch the profile view
        Intent i=new Intent(this,ProfileActivity.class);
        startActivity(i);
    }

    //return the fragment to the view pager
    public  class  TweetsPagerAdapter extends FragmentStatePagerAdapter{
        final  int Page_Count=2;
        private String tabtitle[]={"Home","Mentions"};

        public TweetsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new HomeTimeLineFragment();
            }else if(position==1){
                return new MentionsTimeLineFragment();
            }else{
                return  null;
            }
        }


        //return the tab title
        @Override
        public CharSequence getPageTitle(int position) {
            return tabtitle[position];
        }


        //return how many fragment there swipe between
        @Override
        public int getCount() {
            return tabtitle.length;
        }
    }
}
