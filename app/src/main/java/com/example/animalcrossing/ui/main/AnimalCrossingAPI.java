package com.example.animalcrossing.ui.main;

import android.net.Uri;

import java.io.IOException;
import java.util.ArrayList;

public class AnimalCrossingAPI {
    private final String BASE_URL = "http://acnhapi.com/v1";
    private final String API_KEY = "<api-key>";

    String getVillagers(){
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
        String especie = MainFragment
    }
}
