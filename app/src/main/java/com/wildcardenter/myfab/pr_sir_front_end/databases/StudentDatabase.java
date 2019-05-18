package com.wildcardenter.myfab.pr_sir_front_end.databases;

import android.content.Context;

import com.wildcardenter.myfab.pr_sir_front_end.dao.Book_Adapt_Dao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.CourseDao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.EnrollDao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.StudentDao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.TextDao;
import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.models.Course;
import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;
import com.wildcardenter.myfab.pr_sir_front_end.models.Student;
import com.wildcardenter.myfab.pr_sir_front_end.models.Text;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class, Course.class,
        Enroll.class, Book_Adaptation.class, Text.class}, version = 2)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase instance = null;

    public static StudentDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, StudentDatabase.class, "Student_Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }

    public abstract StudentDao getStudentDao();

    public abstract CourseDao getCourseDao();

    public abstract EnrollDao getEnrollDao();

    public abstract Book_Adapt_Dao getAdaptDao();

    public abstract TextDao getTextDao();
}
