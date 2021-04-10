package com.example.cubetimer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;

public class RecordRepository {

    private String db_name = "Records";
    private RecordDB recordDB;
    Context context;

    public RecordRepository (Context context){
        this.context = context;
        recordDB = Room.databaseBuilder(context,RecordDB.class,db_name).build();
    }

    public String getBest(){
        String best = recordDB.recordDAO().getBest();
        return best;
    }

    public String getWorst(){
        String worst = recordDB.recordDAO().getWorst();
        return worst;
    }

    public List<Record> getRecords(){
        List<Record> recordList=recordDB.recordDAO().getAll();
        return recordList;
    }

    public void InsertTask(final Record record){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                recordDB.recordDAO().insertTask(record);
                return null;
            }
            @Override
            protected void onPostExecute(Void avoids) {
                super.onPostExecute(avoids);
            }

        }.execute();
    }

    public void UpdateTask(final Record record){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                recordDB.recordDAO().updateTask(record);
                return null;
            }
            @Override
            protected void onPostExecute(Void avoids) {
                super.onPostExecute(avoids);
            }

        }.execute();
    }

    public void DeleteTask(final Record record){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                recordDB.recordDAO().deleteTask(record);
                return null;
            }
            @Override
            protected void onPostExecute(Void avoids) {
                super.onPostExecute(avoids);
            }

        }.execute();
    }


}
