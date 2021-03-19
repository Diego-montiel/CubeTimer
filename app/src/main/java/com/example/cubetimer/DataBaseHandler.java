package com.example.cubetimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int versionDataBase = 1;
    private static final String nameDadaBase = "cubetimer";
    private static final String nameTable = "times";
    private static final String primaryKey = "id";
    private static final String timeColumn = "time";
    private static final String scrambleComulmn = "scramble";
    private static final String averageColumn = "average";
    private static final String bestColumn = "best";
    private static final String worstColumn = "worst";
    private static final String dateColumn = "date";

    public DataBaseHandler(Context context) {
        super(context, nameDadaBase, null, versionDataBase);
    }

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        db.execSQL("CREATE TABLE times( id INTEGER PRIMARY KEY AUTOINCREMENT,time TEXT,scramble TEXT,average TEXT,best TEXT,worst TEXT,date TEXT)");
    }

    @Override
    public void onUpgrade(@NotNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + nameTable);
        onCreate(db);
    }

    void insertNewTime(String time, String scramble, String average, String best, String worst, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues params = new ContentValues();
        params.put(timeColumn, time);
        params.put(scrambleComulmn, scramble);
        params.put(averageColumn, average);
        params.put(bestColumn, best);
        params.put(worstColumn, worst);
        params.put(dateColumn, date);
        long newRowId = db.insert(nameTable, null, params);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getTimes() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> timesList = new ArrayList<>();
        String query = "Select * from times Order by id DESC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> record = new HashMap<>();
            record.put(timeColumn, cursor.getString(cursor.getColumnIndex(timeColumn)));
            record.put(scrambleComulmn, cursor.getString(cursor.getColumnIndex(scrambleComulmn)));
            record.put(dateColumn, cursor.getString(cursor.getColumnIndex(dateColumn)));
            timesList.add(record);
        }
        cursor.close();
        return timesList;

    }

    public void daleteTime(int timeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(nameTable, primaryKey + " = ?", new String[]{String.valueOf(timeId)});
        db.close();
    }

}
