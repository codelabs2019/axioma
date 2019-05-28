package com.cjbensan.axiomaapp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.util.SharedPreferencesManager;
import com.cjbensan.axiomaapp.util.URLs;
import com.cjbensan.axiomaapp.util.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DeleteDialog extends SettingsDialog {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        final String id = args.getString("STUDENT_ID");
        String title = args.getString("LABEL");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_delete, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialog)
                .setTitle(title)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteAccount(id);
                        getActivity().finish();
                        SharedPreferencesManager.getInstance(getActivity()).logout();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

    private void deleteAccount(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        JSONObject jsonObject = new JSONObject(map);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URLs.URL_DELETE,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(Fragment.class.getSimpleName(), "Error Volley" + error.getMessage());
                    }
                }
        );

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

    private void processResponse(JSONObject response) {
        try {
            String state = response.getString("state");
            String message = response.getString("message");

            switch (state) {
                case "1":
                    //Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    //getActivity().setResult(Activity.RESULT_OK);

                    //getActivity().finish();
                    //SharedPreferencesManager.getInstance(getActivity()).logout();
                    break;
                case "2":
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    break;
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
