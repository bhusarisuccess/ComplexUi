package com.codepath.apps.mysimpletweet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.codepath.apps.mysimpletweet.R;
import com.codepath.apps.mysimpletweet.adapters.TweetArrayAdapter;
import com.codepath.apps.mysimpletweet.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohinish on 6/29/15.
 */
public class TweetListFragment extends Fragment {
    private ArrayList<Tweet> tweet;
    private ArrayAdapter atweet;
    ListView ivTweet;
    public ProgressBar progressBar;
    // Inflation logic
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_tweets_list,parent,false);
        progressBar=  (ProgressBar)v.findViewById(R.id.pbProgressAction);
        //Find the listview
        ListView ivTweet = (ListView)v. findViewById(R.id.lvTweets);
        //connect adapter to listview
        ivTweet.setAdapter(atweet);
        return  v;
    }

    // create ragment lifecycle

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create arraylist
        tweet = new ArrayList<>();
        //construct the Adapter
        atweet = new TweetArrayAdapter(getActivity(), tweet);
    }
    public void addAll(List<Tweet> twets){
        atweet.addAll(twets);

    }
}
