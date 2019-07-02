package com.cjbensan.axiomaapp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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
    private TextInputLayout forenameInputLayout;
    private TextInputLayout surnameInputLayout;
    private TextInputLayout emailInputLayout;
    private TextInputLayout passwordInputLayout;
    private ProgressBar progressBar;

    private View view;

    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        forenameInput = (TextInputEditText) view.findViewById(R.id.input_forename);
        forenameInput.requestFocus();
        surnameInput = (TextInputEditText) view.findViewById(R.id.input_surname);
        emailInput = (TextInputEditText) view.findViewById(R.id.input_email);
        passwordInput = (TextInputEditText) view.findViewById(R.id.input_password);

        forenameInputLayout = (TextInputLayout) view.findViewById(R.id.input_layout_forename);
        surnameInputLayout = (TextInputLayout) view.findViewById(R.id.input_layout_surname);
        emailInputLayout = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        passwordInputLayout = (TextInputLayout) view.findViewById(R.id.input_layout_password);

        setTypefaceToInputLayout(forenameInputLayout, R.font.varela_round);
        setTypefaceToInputLayout(surnameInputLayout, R.font.varela_round);
        setTypefaceToInputLayout(emailInputLayout, R.font.varela_round);
        setTypefaceToInputLayout(passwordInputLayout, R.font.varela_round);

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

                String errorMessage;

                if (!validForename) {
                    forenameInputLayout.setErrorEnabled(true);
                    errorMessage = "El formato del nombre no es válido.";

                    if (forename.isEmpty())
                        errorMessage = "Ingrese su nombre.";

                    forenameInputLayout.setError(errorMessage);
                }

                if (!validSurname) {
                    surnameInputLayout.setErrorEnabled(true);
                    errorMessage = "El formato del apellido no es válido.";

                    if (surname.isEmpty())
                        errorMessage = "Ingrese su apellido.";

                    surnameInputLayout.setError(errorMessage);
                }

                if (!validEmail) {
                    emailInputLayout.setErrorEnabled(true);
                    errorMessage = "El formato de dirección de correo electrónico no es válido.";

                    if (email.isEmpty())
                        errorMessage = "Ingrese su dirección de correo electrónico.";

                    emailInputLayout.setError(errorMessage);
                }

                if (!validPassword) {
                    passwordInputLayout.setErrorEnabled(true);
                    errorMessage = "La contraseña debe tener al menos 8 caracteres.";

                    if (password.isEmpty())
                        errorMessage = "Ingrese su contraseña.";

                    passwordInputLayout.setError(errorMessage);
                }

                if (validForename && validSurname && validEmail && validPassword) {
                    progressBar.setVisibility(View.VISIBLE);
                    registerStudent(forename, surname, email, password);
                }
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
                        progressBar.setVisibility(View.GONE);
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
            String message = null;

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

    private void setTypefaceToInputLayout(TextInputLayout inputLayout, int fontId) {
        Typeface typeface = ResourcesCompat.getFont(getActivity(), fontId);
        inputLayout.setTypeface(typeface);
        inputLayout.getEditText().setTypeface(typeface);
    }
}
