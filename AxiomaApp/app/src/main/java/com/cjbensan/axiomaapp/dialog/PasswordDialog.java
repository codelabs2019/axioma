package com.cjbensan.axiomaapp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.util.SharedPreferencesManager;
import com.cjbensan.axiomaapp.util.ValidateInput;


public class PasswordDialog extends SettingsDialog {

    private Button positiveBtn;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        final String KEY = args.getString("ITEM_ID");
        String title = args.getString("LABEL");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password, null);

        final String currentPass = SharedPreferencesManager.getInstance(getActivity()).getStudent().getPassword();

        final TextInputLayout currentPassInputLayout = (TextInputLayout) view.findViewById(R.id.input_layout_current_password);
        final TextInputEditText currentPassInput = (TextInputEditText) view.findViewById(R.id.input_current_password);
        currentPassInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String pass = currentPassInput.getText().toString();

                if (!hasFocus) {
                    if (!pass.equals(currentPass)) {
                        currentPassInputLayout.setErrorEnabled(true);
                        currentPassInputLayout.setError("La contraseña es incorrecta.");
                    }
                    else {
                        currentPassInputLayout.setErrorEnabled(false);
                    }
                }
            }
        });

        final TextInputLayout newPassInputLayout = (TextInputLayout) view.findViewById(R.id.input_layout_new_password);
        final TextInputEditText newPassInput = (TextInputEditText) view.findViewById(R.id.input_new_password);
        newPassInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newPass = newPassInput.getText().toString();
                boolean validPassword = ValidateInput.validatePassword(newPass);

                if(validPassword && !newPass.equals(currentPass)) {
                    positiveBtn.setEnabled(true);
                    positiveBtn.setTextColor(0xFF424242);
                }
                else {
                    positiveBtn.setEnabled(false);
                    positiveBtn.setTextColor(0xFFBDBDBD);
                }
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY, newPassInput.getText().toString());
                        Intent intent = new Intent().putExtras(bundle);
                        getTargetFragment().onActivityResult(
                                getTargetRequestCode(), Activity.RESULT_OK, intent);
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

        positiveBtn = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positiveBtn.setEnabled(false);
        positiveBtn.setTextColor(0xFFBDBDBD);
    }
}
