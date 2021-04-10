package com.example.cubetimer;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class},version = 1,exportSchema = false)
public abstract class RecordDB extends RoomDatabase {
    public abstract RecordDAO recordDAO();
}
