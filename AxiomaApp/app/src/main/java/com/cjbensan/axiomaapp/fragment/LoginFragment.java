package com.cjbensan.axiomaapp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.activity.UserSessionActivity;
import com.cjbensan.axiomaapp.domain.Student;
import com.cjbensan.axiomaapp.util.SharedPreferencesManager;
import com.cjbensan.axiomaapp.util.URLs;
import com.cjbensan.axiomaapp.util.ValidateInput;
import com.cjbensan.axiomaapp.util.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private Button loginBtn;
    private TextView signUpTxt;

    private View view;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);

        emailInput = (TextInputEditText) view.findViewById(R.id.input_email);
        passwordInput = (TextInputEditText) view.findViewById(R.id.input_password);

        loginBtn = (Button) view.findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                boolean validEmail = ValidateInput.validateEmail(email);
                boolean validPassword = ValidateInput.validatePassword(password);

                if (!validEmail)
                    emailInput.setError("Ingrese su dirección de correo electrónico.");

                if (!validPassword)
                    passwordInput.setError("Ingrese su contraseña.");

                if (validEmail && validPassword)
                    loginStudent(email, password);
            }
        });

        signUpTxt = (TextView) view.findViewById(R.id.txt_sign_up);
        signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment fragment = new SignUpFragment();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.enter_from_right,
                                R.anim.exit_to_left,
                                R.anim.enter_from_left,
                                R.anim.exit_to_right)
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        return view;
    }

    private void loginStudent(String email, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        JSONObject jsonObject = new JSONObject(map);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URLs.URL_LOGIN,
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

            switch (state) {
                case "1":
                    JSONObject obj = response.getJSONObject("student");

                    Student student = new Student(
                            obj.getString("id"),
                            obj.getString("forename"),
                            obj.getString("surname"),
                            obj.getString("email"),
                            obj.getString("password")
                    );

                    SharedPreferencesManager.getInstance(getActivity()).login(student);

                    Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_OK);

                    getActivity().finish();
                    Intent intent = new Intent(view.getContext(), UserSessionActivity.class);
                    view.getContext().startActivity(intent);
                    break;

                case "2":
                    String message = response.getString("message");
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
