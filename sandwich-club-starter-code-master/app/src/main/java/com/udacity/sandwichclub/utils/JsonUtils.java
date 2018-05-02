package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArrayNames = jsonObject.getJSONObject("name").getJSONArray("alsoKnownAs");
            JSONArray jsonArrayIngredients = jsonObject.getJSONArray("ingredients");

            List<String> listIngredients = new ArrayList<String>();
            List<String> listNames = new ArrayList<String>();

            for (int i = 0; i < jsonArrayIngredients.length(); i++) {
                listIngredients.add(jsonArrayIngredients.getString(i));
            }

            for (int i = 0; i < jsonArrayNames.length(); i++) {
                listNames.add(jsonArrayNames.getString(i));
            }

            Sandwich sandwich = new Sandwich(jsonObject.getJSONObject("name").getString("mainName"),
                    listNames,
                    jsonObject.getString("placeOfOrigin"),
                    jsonObject.getString("description"),
                    jsonObject.getString("image"),
                    listIngredients);
            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
