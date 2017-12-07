package ru.qds.audiobookreader.model;

import java.sql.Time;

/**
 * Created by eduard on 02.12.17.
 */

public class Book {
    private int id;
    private String name;
    private String path;
    private float percent;
    private Time fulltime;
    private Time curTime;

    public Book(int id, String name, String path, float percent, Time fulltime, Time curTime) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.percent = percent;
        this.fulltime = fulltime;
        this.curTime = curTime;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public Time getFulltime() {
        return fulltime;
    }

    public void setFulltime(Time fulltime) {
        this.fulltime = fulltime;
    }

    public Time getCurTime() {
        return curTime;
    }

    public void setCurTime(Time curTime) {
        this.curTime = curTime;
    }
}
