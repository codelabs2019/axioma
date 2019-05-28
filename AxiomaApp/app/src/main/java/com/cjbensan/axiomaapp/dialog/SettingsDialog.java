package com.cjbensan.axiomaapp.dialog;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.widget.Button;

public class SettingsDialog extends DialogFragment {

    public SettingsDialog() {
        super();
    }

    @Override
    public void onStart() {
        super.onStart();

        Button positive = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setTextSize(16.0f);
        positive.setTextColor(Color.BLACK);

        Button negative = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE);
        negative.setTextSize(16.0f);
        negative.setTextColor(Color.BLACK);
    }
}
