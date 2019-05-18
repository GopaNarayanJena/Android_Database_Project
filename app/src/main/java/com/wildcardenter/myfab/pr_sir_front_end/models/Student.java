package com.wildcardenter.myfab.pr_sir_front_end.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "STUDENT")
public class Student {
    @NonNull
    @PrimaryKey
    private String regno;
    @NonNull
    private String name;

    @NonNull
    private String major;
    @NonNull
    private int bdate;

    public Student(@NonNull String regno, @NonNull String name, @NonNull String major, int bdate) {
        this.regno = regno;
        this.name = name;
        this.major = major;
        this.bdate = bdate;
    }


    @NonNull
    public String getRegno() {
        return regno;
    }

    public void setRegno(@NonNull String regno) {
        this.regno = regno;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getMajor() {
        return major;
    }

    public void setMajor(@NonNull String major) {
        this.major = major;
    }

    public int getBdate() {
        return bdate;
    }

    public void setBdate(int bdate) {
        this.bdate = bdate;
    }
}
