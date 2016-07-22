package com.a2g.nd.jokereceiver;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ND on 7/21/2016.
 */
public class JokeActivityFragment  extends Fragment {

  public static final String JOKE_KEY = "jokeKey";
  private String aJoke;
  private TextView mJokeTextView;

  public JokeActivityFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_joke, container, false);

    Intent intent = getActivity().getIntent();
    if(intent != null){
      aJoke = intent.getStringExtra(JOKE_KEY);
    } else{
      aJoke = "I have no jokes =(";
    }

    mJokeTextView = (TextView) rootView.findViewById(R.id.joke_textView);
    mJokeTextView.setText(aJoke);

    return rootView;
  }

  //Returns an intent attached with a joke
  public static Intent getJokeIntent(Context context, String aJoke){
    Intent intent = new Intent(context, JokeActivity.class);
    intent.putExtra(JOKE_KEY, aJoke);
    return intent;
  }
}
