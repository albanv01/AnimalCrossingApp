package com.example.animalcrossing.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.animalcrossing.R;
import com.example.animalcrossing.SettingsActivity;
import com.example.animalcrossing.databinding.FragmentDetailBinding;
import com.example.animalcrossing.databinding.MainFragmentBinding;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private ListView lvAnimalCrossing;
    private AnimalCrossingAdapter adapter;
    private AnimalCrossingViewModel animalCrossingViewModel;
    private SharedPreferences sharedPreferences;
    private SharedViewModel sharedViewModel;
    public static String especie;
    private MainViewModel mViewModel;
    private Bundle savedInstanceState;
    private MainFragmentBinding binding;
    public static String especiesettings;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static String getEspecieSettings() {
        return especiesettings;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.main_fragment, container, false);

        binding = MainFragmentBinding.inflate(inflater); // <1>
        View view = binding.getRoot();


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        especiesettings = sharedPreferences.getString("animalCrossing", "");

        Toast.makeText(getContext(), especiesettings, Toast.LENGTH_LONG).show();


        ArrayList<AnimalCrossing> items = new ArrayList<>();
        adapter = new AnimalCrossingAdapter(
                getContext(),
                R.layout.lv_animalcrossing_row,
                items
        );

        lvAnimalCrossing = view.findViewById(R.id.lvACNH);
        lvAnimalCrossing.setAdapter(adapter);
        lvAnimalCrossing.setOnItemClickListener((adapterView, view1, i, l) -> {
            AnimalCrossing animalCrossing = (AnimalCrossing) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("animalCrossing", animalCrossing);
                startActivity(intent);
        });

        animalCrossingViewModel = ViewModelProviders.of(this).get(AnimalCrossingViewModel.class);

        if(especiesettings.isEmpty()) {
                animalCrossingViewModel.getAnimalCrossing().observe(getViewLifecycleOwner(), animalCrossing -> {
                adapter.clear();
                    System.out.println("TODOS FROM DAO "+animalCrossing);
                adapter.addAll(animalCrossing);
            });
        }else{
            animalCrossingViewModel.getEspeciesVM().observe(getViewLifecycleOwner(), animalCrossing ->{
            adapter.clear();
                System.out.println("ESPECIE  FROM DAO + SETTINGS "+animalCrossing);
                adapter.addAll(animalCrossing);
        });
        }

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

        if (id == R.id.action_settings){
            Intent i = new Intent(getContext(), SettingsActivity.class);
            startActivity(i);
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




}