package com.wildcardenter.myfab.pr_sir_front_end.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ENROLL",primaryKeys = {"regno","course","sem"})
public class Enroll {
    @NonNull
    private String regno;
    @NonNull
    private int course;
    @NonNull
    private int sem;
    @NonNull
    private int marks;

    public Enroll(@NonNull String regno, int course, int sem, int marks) {
        this.regno = regno;
        this.course = course;
        this.sem = sem;
        this.marks = marks;
    }



    @NonNull
    public String getRegno() {
        return regno;
    }

    public void setRegno(@NonNull String regno) {
        this.regno = regno;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
