package com.example.cubetimer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDAO {

    @Insert
    Long insertTask(Record record);

    @Update
    void updateTask(Record record);

    @Delete
    void deleteTask(Record record);

    @Query("select * from record order by id desc")
    List<Record> getAll();

    @Query("select time from record order by time asc limit 1 ")
    String getBest() ;

    @Query("select time from record order by time desc limit 1 ")
    String getWorst() ;

}
