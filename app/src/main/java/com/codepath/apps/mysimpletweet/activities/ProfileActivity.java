package com.codepath.apps.mysimpletweet.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweet.R;
import com.codepath.apps.mysimpletweet.TwitterApplication;
import com.codepath.apps.mysimpletweet.TwitterClient;
import com.codepath.apps.mysimpletweet.fragments.UserTimeLineFragment;
import com.codepath.apps.mysimpletweet.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class ProfileActivity extends ActionBarActivity {

    TwitterClient client;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowHomeEnabled(true);
        bar.setIcon(R.drawable.ic_back);
        // bar.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeResource(getResources(),R.color.abc_background_cache_hint_selector_material_dark)));
        bar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));

        client = TwitterApplication.getRestClient();
        //get the count info
        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user = User.fromJSON(response);

                getSupportActionBar().setTitle("@" + user.getScreen_name());

                populateProfileHeader(user);
            }
        });
        //get the screen name
        String screenName = getIntent().getStringExtra("screen_name");

        if (savedInstanceState == null) {
            //create the user Time line fragment
            UserTimeLineFragment userTimeLineFragment = UserTimeLineFragment.newInstance(screenName);
            //display user fragment in that activity (Dinamically)
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, userTimeLineFragment);
            ft.commit();  //change the fragment
        }

    }

    private void populateProfileHeader(User user) {
        TextView tvuserName = (TextView) findViewById(R.id.tvuser_name);
        TextView tvtext = (TextView) findViewById(R.id.tvtext);
        TextView tvfollowers = (TextView) findViewById(R.id.tvFolowers);
        TextView tvfollowing = (TextView) findViewById(R.id.tvfolowing);
        ImageView ivuserphoto = (ImageView) findViewById(R.id.ivProfilePhoto);

        tvuserName.setText(user.getName());
        tvtext.setText(user.getTagline());
        tvfollowers.setText(user.getFollowers() + "Followers");
        tvfollowing.setText(user.getFollowing() + " Following");
        Picasso.with(getApplicationContext()).load(user.getProfileImageUrl()).into(ivuserphoto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
