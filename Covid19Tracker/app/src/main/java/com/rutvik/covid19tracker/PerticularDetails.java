package com.rutvik.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class PerticularDetails extends AppCompatActivity {

    TextView cases,recovered, critical, active,todayCases,totalDeaths,todayDeaths,affectedCountries;
    private  int countryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perticular_details);

        Intent intent= getIntent();
        countryPosition= intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of " + Countries.countryList.get(countryPosition).getCountries());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        cases= findViewById(R.id.tvCases);
        recovered= findViewById(R.id.tvRecovered);
        critical=findViewById(R.id.tvCritical);
        active=findViewById(R.id.tvActive);
        todayCases=findViewById(R.id.tvTodayCases);
        totalDeaths=findViewById(R.id.tvDeaths);
        todayDeaths=findViewById(R.id.tvTodayDeaths);
        affectedCountries=findViewById(R.id.tvCountry);

        cases.setText(Countries.countryList.get(countryPosition).getCases());
        recovered.setText(Countries.countryList.get(countryPosition).getRecovered());
        critical.setText(Countries.countryList.get(countryPosition).getCritical());
        active.setText(Countries.countryList.get(countryPosition).getActive());
        todayCases.setText(Countries.countryList.get(countryPosition).getTodayCases());
        totalDeaths.setText(Countries.countryList.get(countryPosition).getDeath());
        todayDeaths.setText(Countries.countryList.get(countryPosition).getTodayDeath());
        affectedCountries.setText(Countries.countryList.get(countryPosition).getCountries());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home);
        finish();

        return super.onOptionsItemSelected(item);
    }

}