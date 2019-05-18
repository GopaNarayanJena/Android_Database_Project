package com.wildcardenter.myfab.pr_sir_front_end.ViewModels;

import android.app.Application;

import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;
import com.wildcardenter.myfab.pr_sir_front_end.repository.StudentRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EnrollViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Enroll>> allEnrollList;

    public EnrollViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allEnrollList = repository.getAllEnroll();
    }

    public void insrtEnroll(Enroll enroll) {
        repository.insertEnroll(enroll);
    }

    public void deleteEnroll(Enroll enroll) {
        repository.deleteEnroll(enroll);
    }

    public void updateEnroll(String regno,String course,String sem,String  marks,String pk1,String pk2,String pk3){
        repository.updateEnroll(regno,course,sem,marks,pk1,pk2,pk3);
    }

    public LiveData<List<Enroll>> getAllEnrollList() {
        return allEnrollList;
    }
}
