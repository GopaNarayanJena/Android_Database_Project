package com.wildcardenter.myfab.pr_sir_front_end.ViewModels;

import android.app.Application;

import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.repository.StudentRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BookAdaptViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Book_Adaptation>> allBookList;

    public BookAdaptViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allBookList = repository.getAllAdapt();
    }

    public void insertAdapt(Book_Adaptation adaptation) {
        repository.insertAdapt(adaptation);
    }

    public void deleteAdapt(Book_Adaptation adaptation) {
        repository.deleteAdaptation(adaptation);
    }

    public LiveData<List<Book_Adaptation>> getAllAdaptList() {
        return allBookList;
    }

    public void updateAdapt(int course, int sem, int book_isbn, int pk1, int pk2){
        repository.updateAdapt(course,sem,book_isbn,pk1,pk2);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
