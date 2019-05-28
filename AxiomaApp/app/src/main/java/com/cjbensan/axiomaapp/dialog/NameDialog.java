package com.cjbensan.axiomaapp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.cjbensan.axiomaapp.R;

public class NameDialog extends SettingsDialog {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        final String KEY = args.getString("ITEM_ID");
        String title = args.getString("LABEL");
        String value = args.getString("VALUE");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_name, null);

        final EditText input = (EditText) dialog.findViewById(R.id.input_name);
        input.setText(value);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialog)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY, input.getText().toString());
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
}
