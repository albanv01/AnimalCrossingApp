package com.example.animalcrossing.ui.main;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class AnimalCrossingViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDB appDB;
    private final AnimalCrossingDAO animalCrossingDAO;
    private ArrayList<AnimalCrossing> result = new ArrayList<>();

    public AnimalCrossingViewModel(Application application){
        super(application);
        this.app=application;
        this.appDB=AppDB.getDatabase(
                (this.getApplication()));
        this.animalCrossingDAO=appDB.getAnimalCrossingDAO();
    }

    public void reload(){
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    public LiveData<List<AnimalCrossing>> getAnimalCrossing(){
        return animalCrossingDAO.getVillagers();
    }

    public LiveData<List<AnimalCrossing>> getEspeciesVM(){
        return animalCrossingDAO.getEspecie(MainFragment.getEspecieSettings());
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids){
            AnimalCrossingAPI api = new AnimalCrossingAPI();
            if(result.size()==0) {
                result = api.getVillagers();
            }
            animalCrossingDAO.deleteVillagers();
            animalCrossingDAO.addVillagers(result);
            return null;
        }
    }
}
