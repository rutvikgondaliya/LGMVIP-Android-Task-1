package com.rutvik.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Countries extends AppCompatActivity {

    EditText search;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<Model> countryList= new ArrayList<>();
    Model cModel;
    Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        search= findViewById(R.id.search);
        listView= findViewById(R.id.listView);
        simpleArcLoader=findViewById(R.id.simpleLoader);

        getSupportActionBar().setTitle("countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        apiData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), PerticularDetails.class).putExtra("position",position));
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                          myAdapter.getFilter().filter(s);
                          myAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home);
             finish();

        return super.onOptionsItemSelected(item);
    }

    private void apiData() {
        String url= "https://disease.sh/v3/covid-19/countries";
        simpleArcLoader.start();
        StringRequest request= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray= new JSONArray(response);
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject= jsonArray.getJSONObject(i);
                                String country= jsonObject.getString("country");
                                String cases= jsonObject.getString("cases");
                                String todayCases= jsonObject.getString("todayCases");
                                String deaths= jsonObject.getString("deaths");
                                String todayDeaths= jsonObject.getString("todayDeaths");
                                String recovered= jsonObject.getString("recovered");
                                String active= jsonObject.getString("active");
                                String critical= jsonObject.getString("critical");

                                JSONObject object= jsonObject.getJSONObject("countryInfo");
                                String flag= object.getString("flag");

                                cModel= new Model(flag,country,cases,todayCases,deaths,todayDeaths,recovered,active,critical);
                                countryList.add(cModel);
                            }

                            myAdapter= new Adapter(Countries.this,countryList);
                            listView.setAdapter(myAdapter);
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Countries.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}