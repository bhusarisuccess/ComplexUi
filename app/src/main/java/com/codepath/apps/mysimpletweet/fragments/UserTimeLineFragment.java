package com.codepath.apps.mysimpletweet.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codepath.apps.mysimpletweet.TwitterApplication;
import com.codepath.apps.mysimpletweet.TwitterClient;
import com.codepath.apps.mysimpletweet.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserTimeLineFragment extends TweetListFragment {

    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the client
        client = TwitterApplication.getRestClient();  // singletone client
        populateTimeLine();
    }

    public static UserTimeLineFragment newInstance(String screenName) {
        UserTimeLineFragment userfragment = new UserTimeLineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);

        userfragment.setArguments(args);
        return userfragment;
    }

    //send the api request to get json responce
    //
    private void populateTimeLine() {
        String screenneme = getArguments().getString("screen_name");
        client.getUserTimeLine("screen_name" , new JsonHttpResponseHandler() {
            //success
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.e("DEBUG", json.toString());


                //Desirialise json and add them to adapter
                //Create a models
                //load data into listview
                addAll(Tweet.fromJSONArray(json));
                progressBar.setVisibility(View.GONE);

            }
            //failuar

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("DEBUG", errorResponse.toString());
            }
        });
    }
}
