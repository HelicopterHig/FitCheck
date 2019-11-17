package com.fitcheck.ui.more;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoreViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MoreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is more fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}