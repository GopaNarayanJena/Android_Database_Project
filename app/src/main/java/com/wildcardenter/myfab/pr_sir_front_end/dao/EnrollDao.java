package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface EnrollDao {
    @Insert(onConflict = REPLACE)
    void insertEnoll(Enroll enroll);

    @Delete
    void deleteEnroll(Enroll enroll);

    @Query("select * from enroll order by regno")
    LiveData<List<Enroll>> getAllEnrolls();

    @Query("UPDATE ENROLL SET regno=:regno,course=:course,sem=:sem,marks=:marks where regno like :pk1 and course=:pk2 and sem=:pk3")
    void updateEnroll(String regno,int course,int sem,int marks,String pk1,int pk2,int pk3);
}
