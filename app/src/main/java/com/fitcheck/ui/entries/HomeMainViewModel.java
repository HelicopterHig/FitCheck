package com.fitcheck.ui.entries;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeMainViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeMainViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is entries fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}