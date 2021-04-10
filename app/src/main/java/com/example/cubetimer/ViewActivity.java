package com.example.cubetimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recordRV;
    RecyclerView.LayoutManager layoutManager;
    ArrayList recordArrayList;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recordRV=findViewById(R.id.recordRV);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recordRV.setLayoutManager(layoutManager);
        new LoadData().execute();
    }

    class LoadData extends AsyncTask<Void,Void,Void>{

        RecordRepository recordRepository;
        List<Record> recordList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            recordRepository= new RecordRepository(getApplicationContext());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            customAdapter= new CustomAdapter(recordArrayList,ViewActivity.this);
            recordRV.setAdapter(customAdapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
             recordList = recordRepository.getRecords();
             recordArrayList = new ArrayList<>();

             for (int i = 0 ; i<recordList.size();i++){
                recordArrayList.add(recordList.get(i));
             }
            return null;
        }
    }

}