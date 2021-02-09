package com.example.animalcrossing.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.example.animalcrossing.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFragment extends Fragment {
    private ListView lvAnimalCrossing;
    private AnimalCrossingAdapter adapter;
    private AnimalCrossingViewModel animalCrossingViewModel;
    private SharedPreferences sharedPreferences;
    private SharedViewModel sharedViewModel;
    public static String especie;
    private MainViewModel mViewModel;
    private Bundle savedInstanceState;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.getString("Especie", "Especie");






        ArrayList<AnimalCrossing> items = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_animalcrossing_row,
                items
        );

        sharedViewModel = ViewModelProviders.of(getActivity()).get(
                SharedViewModel.class
        );

        lvAnimalCrossing = view.findViewById(R.id.lvACNH);
        lvAnimalCrossing.setAdapter(adapter);

        lvAnimalCrossing.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int i, long l){
                AnimalCrossing animalCrossing = (AnimalCrossing) adapterView.getItemAtPosition(i);
                if (!isTablet()) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("AnimalCrossing", animalCrossing);
                    startActivity(intent);
           }else {
                    sharedViewModel.select(animalCrossing);
                }
            }
        });

        animalCrossingViewModel = ViewModelProviders.of(this).get(AnimalCrossingViewModel.class);

        animalCrossingViewModel.getVillagers().observe(getViewLifecycleOwner(), animalCrossing ->{
            adapter.clear();
            adapter.addAll();
        })

        return view;

    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<AnimalCrossing>> {
        @Override
        protected ArrayList<AnimalCrossing> doInBackground(Void... voids){
            AnimalCrossingAPI api = new AnimalCrossingAPI();
            ArrayList<AnimalCrossing> result = api.getVillagers();
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<AnimalCrossing> animalCrossings){
            adapter.clear();
            for (AnimalCrossing animalCrossing : animalCrossings){
                adapter.add(animalCrossing);
            }
        }


    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_animalcrossing_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_refresh){
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh(){
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
        animalCrossingViewModel.reload();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    boolean isTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }


}