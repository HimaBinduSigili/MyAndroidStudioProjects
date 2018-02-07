package com.example.bindu.todolist;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bindu on 2/1/2018.
 * Tasks.java
 * homework Assignment 2
 * Hima Bindu Sigili
 * Group 15
 */

public class Tasks implements Parcelable,Comparable<Tasks> {
    private String title;
    private String  date;
    private  String time;
    private String priority;

    Date date_1,time_1;
    public Tasks(){
        title = "";
        date ="";
        time ="";
        priority="";
    }

    public Tasks(String title, String  date, String time, String priority) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.priority = priority;


    }

    public String getTitle() {
        return title;
    }

    public String  getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        Tasks p = (Tasks) obj;
        return (this.title == p.title && this.date == p.date && this.time == p.time && this.priority==p.priority);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + priority.hashCode();
        return result;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(date);
        out.writeString(priority);
        out.writeString(time);
    }

    public static final Parcelable.Creator<Tasks> CREATOR
            = new Parcelable.Creator<Tasks>() {
        public Tasks createFromParcel(Parcel in) {
            return new Tasks(in);
        }

        public Tasks[] newArray(int size) {
            return new Tasks[size];
        }
    };

    private Tasks(Parcel in) {
        title = in.readString();
        date = in.readString();
        priority = in.readString();
        time = in.readString();
    }

    @Override
    public int compareTo(@NonNull Tasks tasks) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        DateFormat sdf = new SimpleDateFormat("hh:mm aa");

        try {
            this.date_1 = formatter.parse(date);
            tasks.date_1 = formatter.parse(tasks.getDate());
            this.time_1 = sdf.parse(time);
            tasks.time_1 = sdf.parse(tasks.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date.compareTo(tasks.date)==0){
           return this.time_1.compareTo(tasks.time_1);
        }

        return this.date_1.compareTo(tasks.date_1);
    }
}