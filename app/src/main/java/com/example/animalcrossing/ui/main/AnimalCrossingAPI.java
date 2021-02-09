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
            JSONArray jsonVillagers = data.getJSONArray("villagers");

            for (int i = 0; i < jsonVillagers.length(); i++) {
                JSONObject jsonVillager = jsonVillagers.getJSONObject(i);
                AnimalCrossing animalCrossing = new AnimalCrossing();
                String especieString = jsonVillager.getString("species");

                switch (especie){
                    case "0":
                        addAnimalCrossing(animalCrossing, jsonVillager, villagers, especieString);
                        break;
                    case "1":
                        if (especieString.equals("ant")){
                            addAnimalCrossing(animalCrossing,jsonVillager,villagers,especieString);
                        }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        imprimirArrayList(villagers);
        return villagers;
    }

    private void addAnimalCrossing(AnimalCrossing animalCrossing, JSONObject jsonVillager, ArrayList<AnimalCrossing> animalCrossings, String especie) throws JSONException{
        animalCrossing.setEspecie(especie);
        animalCrossing.setNombre(jsonVillager.getString("name"));
        animalCrossing.setPersonalidad(jsonVillager.getString("personality"));
        if (jsonVillager.has("icon_url")){
            animalCrossing.setIcono(jsonVillager.getString("icon_url"));
        }else{
            animalCrossing.setIcono("");
        }
        animalCrossings.add(animalCrossing);
    }
    private void imprimirArrayList(ArrayList<AnimalCrossing> animalCrossings){
    }
}
