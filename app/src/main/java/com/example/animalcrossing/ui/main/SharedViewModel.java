package com.example.animalcrossing.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<AnimalCrossing> selected = new MutableLiveData<AnimalCrossing>();

    public void select(AnimalCrossing animalCrossing){
        selected.setValue(animalCrossing);
    }

    public LiveData<AnimalCrossing> getSelected(){
        return selected;
    }
}
