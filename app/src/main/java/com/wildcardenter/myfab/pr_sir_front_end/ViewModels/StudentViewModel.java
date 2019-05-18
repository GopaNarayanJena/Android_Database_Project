package com.wildcardenter.myfab.pr_sir_front_end.ViewModels;

import android.app.Application;

import com.wildcardenter.myfab.pr_sir_front_end.models.Student;
import com.wildcardenter.myfab.pr_sir_front_end.repository.StudentRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Student>> allStudentList;
    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudentList = repository.getAllstudent();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }

    public LiveData<List<Student>> getAllStudentList() {
        return allStudentList;
    }

    public void insertStudent(Student student) {
        repository.insertStudent(student);
    }

    public void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }

    public void updateStudent(String reno,String nme,String mjor,String dob,String pk){
        repository.updateStudent(reno,nme,mjor,dob,pk);
    }

}
