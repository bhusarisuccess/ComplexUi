package com.codepath.apps.mysimpletweet.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mohinish on 6/19/15.
 */


/*
{
     {
        "text":"just another test",
        "contributors":null,
        "id":240558470661799936,
        "retweet_count":0,
        "in_reply_to_status_id_str":null,
        "geo":null,
        "retweeted":false,
        "in_reply_to_user_id":null,
        "place":null,
        "source":"<a href="//realitytechnicians.com\"" rel="\"nofollow\"">OAuth Dancer Reborn</a>",
        "user":{
        "name":"OAuth Dancer",
        "profile_sidebar_fill_color":"DDEEF6",
        "profile_background_tile":true,
        "profile_sidebar_border_color":"C0DEED",
        "profile_image_url":"http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
        "created_at":"Wed Mar 03 19:37:35 +0000 2010",
        "location":"San Francisco, CA",
        "follow_request_sent":false,
        "id_str":"119476949",
        "is_translator":false,
        "profile_link_color":"0084B4",
        },
        {
        ....
        }

  }

*/

// parse the json+ store the data,encapsulate state logic
public class Tweet {

    //list out the atribute
    private  String body;
    private long uid;    //unique id for the tweet
    private User user;
    private  String createdAt;

    //Desirialise the json

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Tweet.fromJson({..})==> <Tweet>
    public  static  Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet= new Tweet();
     //extract values from the json,store them
        try {
            tweet.body=jsonObject.getString("text");
            tweet.uid=jsonObject.getLong("id");
            tweet.createdAt=jsonObject.getString("created_at");
            tweet.user= User.fromJSON(jsonObject.getJSONObject("user"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


     // Return the Tweet object
        return  tweet;
    }

    public  static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets= new ArrayList<>();
        //itret the json array and creat tweets
        for (int i=0; i< jsonArray.length();i++){
            JSONObject tweetJson= null;
            try {
                tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet= Tweet.fromJSON(tweetJson);
                if (tweet!= null){
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //return the finish list
        return  tweets;

    }
}

