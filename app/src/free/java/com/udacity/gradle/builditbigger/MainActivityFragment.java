package com.udacity.gradle.builditbigger;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.a2g.nd.jokereceiver.JokeActivityFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivityFragment extends Fragment {
    private static final int JOKE_ACTIVITY_REQ_CODE = 0;

    InterstitialAd mInterstitialAd;
    private Button mButton;
    private ProgressBar mSpinner;
    private View mJokeLayoutView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mButton = (Button) root.findViewById(R.id.buttonView);
        mSpinner = (ProgressBar) root.findViewById(R.id.progressBar);
        mJokeLayoutView = root.findViewById(R.id.jokeLayoutView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide joke layout and show progress bar when requesting joke
                mJokeLayoutView.setVisibility(View.GONE);
                mSpinner.setVisibility(View.VISIBLE);

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    new FetchJokeTask(new FetchJokeTask.AsyncResponse() {
                        @Override
                        public void processFinish(String result) {
                            Intent intent = JokeActivityFragment.getJokeIntent(getActivity(), result);
                            startActivityForResult(intent, JOKE_ACTIVITY_REQ_CODE);
                        }
                    }).execute();
                }
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                new FetchJokeTask(new FetchJokeTask.AsyncResponse() {
                    @Override
                    public void processFinish(String result) {
                        Intent intent = JokeActivityFragment.getJokeIntent(getActivity(), result);
                        startActivityForResult(intent, JOKE_ACTIVITY_REQ_CODE);
                    }
                }).execute();
            }
        });

        requestNewInterstitial();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //When returning from jokeActivity, show the joke layout and hide the progress bar
        if(resultCode == Activity.RESULT_CANCELED){
            mJokeLayoutView.setVisibility(View.VISIBLE);
            mSpinner.setVisibility(View.GONE);
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
