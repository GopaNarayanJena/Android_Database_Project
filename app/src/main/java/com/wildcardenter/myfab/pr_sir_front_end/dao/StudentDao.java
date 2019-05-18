package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Student;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentDao {
    @Insert(onConflict = REPLACE)
    void insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);


    @Query("select * from STUDENT order by name")
    LiveData<List<Student>> getAllStudents();

    @Query("Update STUDENT SET regno=:reno,name=:nme,major=:mjor,bdate=:dob WHERE regno like :pk")
    void updateStudent(String reno,String nme,String mjor,int dob,String pk);

}
