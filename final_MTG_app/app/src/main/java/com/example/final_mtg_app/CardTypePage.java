package com.example.final_mtg_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardTypePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cardtype);
        //retrieve commander data from previous page
        String commanderName = getIntent().getStringExtra("name");
        String commanderCMC = getIntent().getStringExtra("cmc");
        String commanderType = getIntent().getStringExtra("Legendary Creature . . .");
        String[] commanderColorArray = getIntent().getStringArrayExtra("Commander_color");
        List<String> commanderColor = Arrays.asList(commanderColorArray);

        String commanderImage = getIntent().getStringExtra("image_url");
        Log.d("Name Passed through", commanderName);
        Log.d("cmc Passed through", commanderCMC);
        Log.d("type Passed through", commanderType);
        Log.d("color", commanderColor.toString());
        Log.d("color2", String.valueOf(commanderColor.size()));
        Log.d("image Passed through", commanderImage);


        //import all edittexts
        EditText numLands = findViewById(R.id.numLands);
        EditText numArtifacts = findViewById(R.id.numArtifacts);
        EditText numCreatures = findViewById(R.id.numCreatures);
        EditText numEnchantments = findViewById(R.id.numEnchantments);
        EditText numSorceries = findViewById(R.id.numSorceries);
        EditText numInstants = findViewById(R.id.numInstants);
        EditText numPlaneswalker = findViewById(R.id.numPlaneswalker);
        //button
        Button submitButton = findViewById(R.id.submit_filter);






        submitButton.setOnClickListener(v -> {
            int lands = Integer.parseInt(numLands.getText().toString());
            int artifacts = Integer.parseInt(numArtifacts.getText().toString());
            int creatures = Integer.parseInt(numCreatures.getText().toString());
            int enchantments = Integer.parseInt(numEnchantments.getText().toString());
            int sorceries = Integer.parseInt(numSorceries.getText().toString());
            int instants = Integer.parseInt(numInstants.getText().toString());
            int planeswalkers = Integer.parseInt(numPlaneswalker.getText().toString());

            int totalCards = lands + artifacts + creatures + enchantments + sorceries + instants + planeswalkers;
            if (totalCards != 99) {
                throw new Error("Total cards do not add to 100");
            }

            ArrayList<String> combined = new ArrayList<>();

            if (commanderColor.size() > 1) {
                jsonrequest(commanderColor, "land", lands, list1 -> {
                    combined.addAll(list1);

                    jsonrequest(commanderColor, "artifact", artifacts, list2 -> {
                        combined.addAll(list2);

                        jsonrequest(commanderColor, "creature", creatures, list3 -> {
                            combined.addAll(list3);

                            jsonrequest(commanderColor, "enchantment", enchantments, list4 -> {
                                combined.addAll(list4);

                                jsonrequest(commanderColor, "sorcery", sorceries, list5 -> {
                                    combined.addAll(list5);

                                    jsonrequest(commanderColor, "instant", instants, list6 -> {
                                        combined.addAll(list6);

                                        jsonrequest(commanderColor, "planeswalker", planeswalkers, list7 -> {
                                            combined.addAll(list7);


                                            Intent intent = new Intent(CardTypePage.this, FinalView.class);
                                            intent.putStringArrayListExtra("combined", combined);
                                            startActivity(intent);
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            } else {
                jsonrequest(commanderColor, "basic", lands, list1 -> {
                    combined.addAll(list1);

                    jsonrequest(commanderColor, "artifact", artifacts, list2 -> {
                        combined.addAll(list2);

                        jsonrequest(commanderColor, "creature", creatures, list3 -> {
                            combined.addAll(list3);

                            jsonrequest(commanderColor, "enchantment", enchantments, list4 -> {
                                combined.addAll(list4);

                                jsonrequest(commanderColor, "sorcery", sorceries, list5 -> {
                                    combined.addAll(list5);

                                    jsonrequest(commanderColor, "instant", instants, list6 -> {
                                        combined.addAll(list6);

                                        jsonrequest(commanderColor, "planeswalker", planeswalkers, list7 -> {
                                            combined.addAll(list7);

                                            Intent intent = new Intent(CardTypePage.this, FinalView.class);
                                            intent.putStringArrayListExtra("combined", combined);
                                            startActivity(intent);
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            }
        });

    }

    private void jsonrequest(List<String> commanderColorList, String type, int numNeeded, CardListCallback callback) {
        String colorParam = convertColorListToUrlParam(commanderColorList);
        String url = "http://10.2.97.180:5000/get_cards/" + colorParam + "/" + type + "/" + numNeeded;

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    List<String> cardList = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject card = response.getJSONObject(i);
                            String cardName = card.getString("name");
                            cardList.add(cardName);
                        }
                        callback.onCardsReceived(cardList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callback.onCardsReceived(new ArrayList<>());
                    }
                },
                error -> {
                    error.printStackTrace();
                    callback.onCardsReceived(new ArrayList<>());
                }
        );

        queue.add(jsonArrayRequest);
    }
    private String convertColorListToUrlParam(List<String> colorList) {
        return String.join("-", colorList); // parse color input for easier url
    }
    public interface CardListCallback {
        void onCardsReceived(List<String> cards);
    }
}