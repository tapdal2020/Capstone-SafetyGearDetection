package com.capstone.safetygeardetection.logs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogViewModel extends ViewModel {
    private MutableLiveData<String> _description = new MutableLiveData<String>();
//    String description = new LiveData<String>();
//            get() = _description;

    public String getDescription() {
        return _description.getValue();
    }

}
