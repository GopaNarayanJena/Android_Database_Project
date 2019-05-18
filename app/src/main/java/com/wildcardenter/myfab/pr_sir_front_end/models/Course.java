package com.wildcardenter.myfab.pr_sir_front_end.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "COURSE")
public class Course {
    @PrimaryKey
    @NonNull
    private int course;
    @NonNull
    private String cname;
    @NonNull
    private String dept;

    public Course(int course, @NonNull String cname, @NonNull String dept) {
        this.course = course;
        this.cname = cname;
        this.dept = dept;
    }



    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @NonNull
    public String getCname() {
        return cname;
    }

    public void setCname(@NonNull String cname) {
        this.cname = cname;
    }

    @NonNull
    public String getDept() {
        return dept;
    }

    public void setDept(@NonNull String dept) {
        this.dept = dept;
    }
}
