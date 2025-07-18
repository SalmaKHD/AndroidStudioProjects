package com.salmakhd.android.onlineshop.claases;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.salmakhd.android.onlineshop.HomeFragmentViewModel;

public class HomeFragmentViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
            return (T) (new HomeFragmentViewModel());
        } else throw new IllegalArgumentException("Unknown Viewmodel class");
    }
}
