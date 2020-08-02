package com.panda.memo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MemoViewModel extends ViewModel {
    public MutableLiveData<Integer> progress = new MutableLiveData<>(); // test

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MemoViewModel();
        }
    }
}
