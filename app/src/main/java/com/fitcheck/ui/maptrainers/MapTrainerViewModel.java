package com.fitcheck.ui.maptrainers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapTrainerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MapTrainerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is maptrainer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
