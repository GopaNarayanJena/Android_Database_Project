package com.wildcardenter.myfab.pr_sir_front_end.ViewModels;

import android.app.Application;

import com.wildcardenter.myfab.pr_sir_front_end.models.Course;
import com.wildcardenter.myfab.pr_sir_front_end.repository.StudentRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CourseViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Course>> allCourse;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allCourse = repository.getAllCourse();
    }

    public LiveData<List<Course>> getAllCourseList() {
        return allCourse;
    }

    public LiveData<List<String>> getAllDeptByPublisher(String publisher) {
        return repository.getDeptByPublisher(publisher);
    }

    public void insertCourse(Course course) {
        repository.insertCourse(course);
    }

    public void deleteCourse(Course course) {
        repository.deleteCourse(course);
    }

    public void updateCourse(String course, String cname, String dept, String pk){
        repository.updateCourse(course,cname,dept,pk);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
