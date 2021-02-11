package com.example.animalcrossing.ui.main;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.security.acl.AclNotFoundException;
import java.util.List;

@Dao
public interface AnimalCrossingDAO {
    @Query("select * from animalcrossing")
    LiveData<List<AnimalCrossing>> getVillagers();


    @Query("select * from animalcrossing where especie = :especie")
    LiveData<List<AnimalCrossing>> getEspecie(String especie);

    @Insert
    void addVillagers(AnimalCrossing add);

    @Insert
    void addVillagers(List<AnimalCrossing> villagers);

    @Delete
    void deleteVillagers(AnimalCrossing villager);

    @Query("DELETE FROM animalcrossing")
    void deleteVillagers();
}
