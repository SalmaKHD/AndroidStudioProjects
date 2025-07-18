package com.salmakhd.android.onlineshop;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeFragmentViewModel extends ViewModel {
    // something
    public MutableLiveData<String> _helloTextWord = new MutableLiveData<String>();
    public LiveData<String> helloText = _helloTextWord;

    public HomeFragmentViewModel() {
        _helloTextWord.postValue("Hello once again Salma!");
    }

    public void textClickListener() {
        Log.i("Log", "Text just clicked!");
    }

}
