package com.example.cubetimer;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Record {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "scramble")
    String scramble;
    @ColumnInfo(name = "date")
    String date;

    @Ignore
    public Record(int id, String time, String scramble, String date) {
        this.id = id;
        this.time = time;
        this.scramble = scramble;
        this.date = date;
    }

    public Record( String time, String scramble, String date) {
        this.time = time;
        this.scramble = scramble;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
