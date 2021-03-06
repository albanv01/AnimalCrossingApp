package com.example.animalcrossing.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.animalcrossing.R;
import com.example.animalcrossing.databinding.FragmentDetailBinding;


public class DetailActivityFragment extends Fragment {

    private View view;
    private ImageView ivVillagerImage;
    private TextView tvAnimalCrossing;
    private TextView tvEspecie;
    private TextView tvPersonalidad;
    private FragmentDetailBinding binding;

    public static DetailActivityFragment newInstance(){
        return new DetailActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //view = inflater.inflate(R.layout.fragment_detail, container, false);

        binding = FragmentDetailBinding.inflate(inflater); // <1>
        view = binding.getRoot();
        Intent i = getActivity().getIntent();
        if (i!=null){
            AnimalCrossing animalCrossing = (AnimalCrossing) i.getSerializableExtra("animalCrossing");
            if(animalCrossing !=null){
                updateUI(animalCrossing);
            }
        }
        SharedViewModel sharedViewModel = ViewModelProviders.of(
                getActivity()
        ).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<AnimalCrossing>() {
            @Override
            public void onChanged(AnimalCrossing animalCrossing) {
                updateUI(animalCrossing);
            }
        });
        return view;
    }

    private void updateUI(AnimalCrossing animalCrossing){
        Log.d("VILLAGER", animalCrossing.toString());
        System.out.println( "OBJETO: " +animalCrossing);

        binding.tvAnimalCrossing2.setText(animalCrossing.getNombre());
        binding.tvEspecie2.setText(animalCrossing.getEspecie());

        Glide.with(getContext()).load(
                animalCrossing.getIcono()
        ).into(binding.ivVillagerImage2);
    }
}
