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
import com.cjbensan.axiomaapp.activity.UniversityActivity;
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
public class SignUpFragment extends Fragment {

    private TextInputEditText forenameInput;
    private TextInputEditText surnameInput;
    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private Button signUpBtn;
    private TextView loginTxt;

    private View view;

    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        forenameInput = (TextInputEditText) view.findViewById(R.id.input_forename);
        surnameInput = (TextInputEditText) view.findViewById(R.id.input_surname);
        emailInput = (TextInputEditText) view.findViewById(R.id.input_email);
        passwordInput = (TextInputEditText) view.findViewById(R.id.input_password);

        signUpBtn = (Button) view.findViewById(R.id.btn_sign_up);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String forename = forenameInput.getText().toString().trim();
                String surname = surnameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                boolean validForename = ValidateInput.validateForename(forename);
                boolean validSurname = ValidateInput.validateSurname(surname);
                boolean validEmail = ValidateInput.validateEmail(email);
                boolean validPassword = ValidateInput.validatePassword(password);

                if (!validForename)
                    forenameInput.setError("Ingrese su nombre.");

                if (!validSurname)
                    surnameInput.setError("Ingrese su apellido.");

                if (!validEmail)
                    emailInput.setError("El formato de dirección de correo electrónico no es válido.");

                if (!validPassword)
                    passwordInput.setError("La contraseña debe tener al menos 8 caracteres.");

                if (validForename && validSurname && validEmail && validPassword)
                    registerStudent(forename, surname, email, password);
            }
        });

        loginTxt = (TextView) view.findViewById(R.id.txt_login);
        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment fragment = new LoginFragment();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.enter_from_left,
                                R.anim.exit_to_right,
                                R.anim.enter_from_right,
                                R.anim.exit_to_left)
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

        return view;
    }

    private void registerStudent(String forename, String surname, String email, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("forename", forename);
        map.put("surname", surname);
        map.put("email", email);
        map.put("password", password);

        JSONObject jsonObject = new JSONObject(map);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URLs.URL_REGISTER,
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
                    String message = null;

                    Student student = new Student(
                            obj.getString("id"),
                            obj.getString("forename"),
                            obj.getString("surname"),
                            obj.getString("email"),
                            obj.getString("password")
                    );

                    SharedPreferencesManager.getInstance(getActivity()).login(student);

                    Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_OK);

                    getActivity().finish();
                    Intent intent = new Intent(view.getContext(), UniversityActivity.class);
                    view.getContext().startActivity(intent);
                    break;

                case "2":
                    message = response.getString("message");
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    break;

                case "3":
                    message = response.getString("message");
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
