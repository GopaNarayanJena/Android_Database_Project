package com.wildcardenter.myfab.pr_sir_front_end.ViewModels;

import android.app.Application;

import com.wildcardenter.myfab.pr_sir_front_end.models.Text;
import com.wildcardenter.myfab.pr_sir_front_end.models.TextByCs;
import com.wildcardenter.myfab.pr_sir_front_end.repository.StudentRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TextViewModel extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<Text>> allTextList;
    private LiveData<List<TextByCs>> allTextByCs;

    public TextViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allTextList = repository.getAllText();
        allTextByCs = repository.getAllTextListByCs();

    }

    public LiveData<List<Text>> getAllTextList() {
        return allTextList;
    }

    public LiveData<List<TextByCs>> getAllTextByCs() {
        return allTextByCs;
    }

    public void insertText(Text text) {
        repository.insertText(text);
    }

    public void deleteText(Text text) {
        repository.deleteText(text);
    }

    public void updateText(String book_isbn, String book_title, String publisher, String author, String pk){
        repository.updateText(book_isbn,book_title,publisher,author,pk);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
