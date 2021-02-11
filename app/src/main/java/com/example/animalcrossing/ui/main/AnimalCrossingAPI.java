package com.example.animalcrossing.ui.main;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AnimalCrossingAPI {
    private final String BASE_URL = "http://acnhapi.com/v1";
    private final String API_KEY = "<api-key>";

    ArrayList<AnimalCrossing> getVillagers(){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("villagers")
                .build();
        String url = builtUri.toString();
        System.out.println(url);
        System.out.println("holaaaaa1");
        return doCall(url);
    }

    private ArrayList<AnimalCrossing> doCall(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<AnimalCrossing> processJson(String jsonResponse) {
        ArrayList<AnimalCrossing> villagers = new ArrayList<>();
        String especie = MainFragment.especie;

        try{

            JSONObject data = new JSONObject(jsonResponse);
            JSONArray especie2 = data.names();
            for (int i = 0; i < data.length(); i++) {

                JSONObject jsonVillager = data.getJSONObject(especie2.getString(i));
                JSONObject jsonNombre = jsonVillager.getJSONObject("name");
                AnimalCrossing animalCrossing = new AnimalCrossing();
                String especieString = jsonVillager.getString("species");


                        addAnimalCrossing(animalCrossing, jsonVillager, jsonNombre, villagers, especieString);


                }

            } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }


        return villagers;
    }

    private void addAnimalCrossing(AnimalCrossing animalCrossing, JSONObject jsonVillager, JSONObject jsonNombre , ArrayList<AnimalCrossing> animalCrossings, String especie) throws JSONException{
        animalCrossing.setEspecie(especie);
        animalCrossing.setNombre(jsonNombre.getString("name-USen"));
        animalCrossing.setPersonalidad(jsonVillager.getString("personality"));
        if (jsonVillager.has("icon_uri")){
            animalCrossing.setIcono(jsonVillager.getString("icon_uri"));
        }else{
            animalCrossing.setIcono("");
        }
        animalCrossings.add(animalCrossing);
        imprimirArrayList(animalCrossings);
    }
    private void imprimirArrayList(ArrayList<AnimalCrossing> animalCrossings){
    }
}
