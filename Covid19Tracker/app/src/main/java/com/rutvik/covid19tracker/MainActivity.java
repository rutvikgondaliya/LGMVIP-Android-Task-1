package com.rutvik.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cases,recovered, critical, active,todayCases,totalDeaths,todayDeaths,affectedCountries;
    SimpleArcLoader Loader;
    ScrollView scrollView;
    PieChart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cases= findViewById(R.id.testCases);
        recovered= findViewById(R.id.testRecovored);
        critical=findViewById(R.id.testCritical);
        active=findViewById(R.id.testActive);
        todayCases=findViewById(R.id.testTodayCases);
        totalDeaths=findViewById(R.id.testTotalDeaths);
        todayDeaths=findViewById(R.id.testTodayDeaths);
        affectedCountries=findViewById(R.id.testAffectedCountries);

        Loader= findViewById(R.id.loader);
        scrollView= findViewById(R.id.scrollStatus);
        chart= findViewById(R.id.piechart);

        apiData();

    }

    private void apiData() {
         String url= "https://disease.sh/v3/covid-19/all";
         Loader.start();
        StringRequest request= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response.toString());
                            cases.setText(jsonObject.getString("cases"));
                            recovered.setText(jsonObject.getString("recovered"));
                            critical.setText(jsonObject.getString("critical"));
                            active.setText(jsonObject.getString("active"));
                            todayCases.setText(jsonObject.getString("todayCases"));
                            totalDeaths.setText(jsonObject.getString("deaths"));
                            todayDeaths.setText(jsonObject.getString("todayDeaths"));
                            affectedCountries.setText(jsonObject.getString("affectedCountries"));

                            chart.addPieSlice(new PieModel("Cases", Integer.parseInt(cases.getText().toString()), Color.parseColor("#ffa726")));
                            chart.addPieSlice(new PieModel("Recovered", Integer.parseInt(recovered.getText().toString()), Color.parseColor("#66bb6a")));
                            chart.addPieSlice(new PieModel("Death", Integer.parseInt(todayDeaths.getText().toString()), Color.parseColor("#EF5350")));
                            chart.addPieSlice(new PieModel("Active", Integer.parseInt(active.getText().toString()), Color.parseColor("#29b6f6")));

                            chart.startAnimation();
                            Loader.stop();
                            Loader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            Loader.stop();
                            Loader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Loader.stop();
                Loader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void trackCountries(View view) {
        startActivity(new Intent(getApplicationContext(), Countries.class));
    }
}