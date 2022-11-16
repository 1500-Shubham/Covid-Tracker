package com.example.corona_tracker_thirdattempt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<JobCandidates> example=new ArrayList<JobCandidates>();
    static ArrayList<JobCandidates> sortedbycases=new ArrayList<JobCandidates>();
    private RecyclerView mRecyclerView;
    private  RecyclerView.Adapter mAdapter;
    TextView secret,timer,staysafe;
    View earth,punch;
    private InterstitialAd mInterstitialAd;
    private  RecyclerView.LayoutManager mLayoutManager;
    ConnectivityManager connectivityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context=MainActivity.this;
        secret=findViewById(R.id.secretmessaged);
        earth=findViewById(R.id.earthsick);
        timer=findViewById(R.id.timer);
        punch=findViewById(R.id.getpunch);
        staysafe=findViewById(R.id.staysafe);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3750729195835584/3604845812");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());



         connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() != NetworkInfo.State.CONNECTED &&
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() != NetworkInfo.State.CONNECTED) {
            staysafe.setText("Check Your Internet!!");
            earth.setVisibility(View.VISIBLE);
            secret.setVisibility(View.VISIBLE);
            secret.setText("No Internet Connection!! \n Go Back & Try Again");
            punch.setVisibility(View.INVISIBLE);
            timer.setVisibility(View.INVISIBLE);

        }


        else{   timer.setText("Just A Moment... \n Please Wait...");
           //timer suru kar do aage kaam hota rahe
            new CountDownTimer(8000,1000){

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    timer.setText("Slow Internet! \n Go Back & Try Again");
                    if(sortedbycases.size()!=0){
                    mAdapter.notifyDataSetChanged();
                }}
            }.start();
            String url="https://api.covid19api.com/summary";

            RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String countries = response.getString("Countries");
                            JSONArray totalcountries = new JSONArray(countries);
                            for (int i = 0; i < totalcountries.length(); ++i) {
                                //rearrange karunga according to cases

                                JSONObject JO = (JSONObject) totalcountries.get(i);
                                example.add(new JobCandidates(JO.getString("Country"), JO.getString("TotalDeaths"), Integer.parseInt(JO.getString("TotalConfirmed")), JO.getString("NewConfirmed"), JO.getString("TotalRecovered")));

                            } //rearrange according to cases aur us index ko bhej dena example mein finally
                            JobCandidateSorter jobCandidateSorter = new JobCandidateSorter(example);
                            sortedbycases.addAll(jobCandidateSorter.getSortedJobCandidateByAge());

                            mRecyclerView = findViewById(R.id.recycler);
                            mRecyclerView.setHasFixedSize(true);
                            mLayoutManager = new LinearLayoutManager(MainActivity.this);
                            mAdapter = new ExampleAdapter(sortedbycases);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mAdapter.notifyDataSetChanged();
                            timer.setVisibility(View.INVISIBLE);
                            punch.setVisibility(View.INVISIBLE);


                        } catch (Exception e){}
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });requestQueue.add(jsonObjectRequest);



        }}


    @Override
    public void onBackPressed() {
        super.onBackPressed();



        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    // Load the next interstitial.example.clear();
                    //                    sortedbycases.clear();
                    example.clear();
                    sortedbycases.clear();
                    Intent i=new Intent(MainActivity.this,entry.class);
                    startActivity(i);

                    finish();
                }

            });
        }
        else {   example.clear();
            sortedbycases.clear();
            Intent i=new Intent(MainActivity.this,entry.class);
            startActivity(i);

            finish();
        }
    }


}
