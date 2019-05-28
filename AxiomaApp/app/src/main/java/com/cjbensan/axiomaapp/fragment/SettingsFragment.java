package com.cjbensan.axiomaapp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.adapter.SettingsAdapter;
import com.cjbensan.axiomaapp.dialog.DeleteDialog;
import com.cjbensan.axiomaapp.dialog.EmailDialog;
import com.cjbensan.axiomaapp.dialog.LogoutDialog;
import com.cjbensan.axiomaapp.dialog.NameDialog;
import com.cjbensan.axiomaapp.dialog.PasswordDialog;
import com.cjbensan.axiomaapp.domain.SettingsRow;
import com.cjbensan.axiomaapp.domain.Student;
import com.cjbensan.axiomaapp.util.SharedPreferencesManager;
import com.cjbensan.axiomaapp.util.URLs;
import com.cjbensan.axiomaapp.util.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public static final int DIALOG_REQUEST_CODE = 1;

    private static final String FORENAME = "forename";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String LOGOUT = "logout";
    private static final String DELETE = "delete";

    private List<SettingsRow> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private SettingsAdapter adapter;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new SettingsAdapter(items, new SettingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SettingsRow item) {
                Bundle args = new Bundle();
                args.putString("ITEM_ID", item.getId());
                args.putString("LABEL", item.getLabel());

                DialogFragment dialog = null;

                switch (item.getId()) {
                    case FORENAME:
                    case SURNAME:
                        dialog = new NameDialog();
                        args.putString("VALUE", item.getValue());
                        break;
                    case EMAIL:
                        dialog = new EmailDialog();
                        args.putString("VALUE", item.getValue());
                        break;
                    case PASSWORD:
                        dialog = new PasswordDialog();
                        break;
                    case LOGOUT:
                        dialog = new LogoutDialog();
                        break;
                    case DELETE:
                        dialog = new DeleteDialog();
                        args.putString("STUDENT_ID",
                                SharedPreferencesManager
                                        .getInstance(getActivity())
                                        .getStudent()
                                        .getId());
                        break;
                }

                dialog.setArguments(args);
                dialog.setTargetFragment(SettingsFragment.this, DIALOG_REQUEST_CODE);
                dialog.show(getActivity().getSupportFragmentManager(), "dialog");
            }
        });

        setupSettingsData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView.LayoutManager manager =
                new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void setupSettingsData() {
        Student student = SharedPreferencesManager.getInstance(getActivity()).getStudent();


        items.add(new SettingsRow(FORENAME, "Nombre", student.getForename()));
        items.add(new SettingsRow(SURNAME, "Apellido", student.getSurname()));
        items.add(new SettingsRow(EMAIL, "Correo", student.getEmail()));
        items.add(new SettingsRow(PASSWORD, "Contraseña", "********"));
        items.add(new SettingsRow(LOGOUT, "Cerrar sesión", ""));
        items.add(new SettingsRow(DELETE, "Borrar cuenta", ""));

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DIALOG_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getExtras().containsKey(FORENAME)) {
                    items.get(0).setValue(data.getExtras().getString(FORENAME));
                    adapter.notifyItemChanged(0);
                }
                else if (data.getExtras().containsKey(SURNAME)) {
                    items.get(1).setValue(data.getExtras().getString(SURNAME));
                    adapter.notifyItemChanged(1);
                }
                else if (data.getExtras().containsKey(EMAIL)) {
                    items.get(2).setValue(data.getExtras().getString(EMAIL));
                    adapter.notifyItemChanged(2);
                }
            }
        }

        Student student = new Student(
                SharedPreferencesManager.getInstance(getActivity()).getStudent().getId(),
                items.get(0).getValue(),
                items.get(1).getValue(),
                items.get(2).getValue(),
                SharedPreferencesManager.getInstance(getActivity()).getStudent().getPassword()
        );

        updateStudent(student);
    }

    private void updateStudent(final Student student) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", student.getId());
        map.put("forename", student.getForename());
        map.put("surname", student.getSurname());
        map.put("email", student.getEmail());
        map.put("password", student.getPassword());

        JSONObject jsonObject = new JSONObject(map);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URLs.URL_UPDATE,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String state = response.getString("state");
                            String message = response.getString("message");

                            switch (state) {
                                case "1":
                                    SharedPreferencesManager.getInstance(getActivity()).login(student);

                                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                                    getActivity().setResult(Activity.RESULT_OK);
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
}
