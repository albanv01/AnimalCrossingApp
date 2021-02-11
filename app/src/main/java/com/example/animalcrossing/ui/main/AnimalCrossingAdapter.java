package com.example.animalcrossing.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animalcrossing.R;

import java.util.List;

public class AnimalCrossingAdapter extends ArrayAdapter<AnimalCrossing> {
    public AnimalCrossingAdapter(Context context, int resource, List<AnimalCrossing> objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        AnimalCrossing animalCrossing = getItem(position);
        Log.w("XXXX", animalCrossing.toString());

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_animalcrossing_row, parent, false);
        }


        TextView tvAnimalCrossing = convertView.findViewById(R.id.tvAnimalCrossing);
        TextView tvEspecie = convertView.findViewById(R.id.tvEspecie);
        ImageView ivVillagerImage = convertView.findViewById(R.id.ivVillagerImage);

        tvAnimalCrossing.setText(animalCrossing.getNombre());
        tvEspecie.setText(animalCrossing.getEspecie());

        Glide.with(getContext()).load(
                animalCrossing.getIcono()
        ).into(ivVillagerImage);

        return convertView;
    }
}
