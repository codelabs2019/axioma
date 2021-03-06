package com.cjbensan.axiomaapp.dialog;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Button;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;

public class SettingsDialog extends DialogFragment {

    public SettingsDialog() {
        super();
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog dialogObject = (AlertDialog) getDialog();
        int titleId = getResources().getIdentifier( "alertTitle", "id", "android" );
        if (titleId > 0) {
            TextView dialogTitle = (TextView) dialogObject.findViewById(titleId);
            if (dialogTitle != null) {
                dialogTitle.setTextColor(ResourcesCompat.getColor(
                        getResources(), R.color.colorPrimaryText, null));
                dialogTitle.setTypeface(ResourcesCompat.getFont(
                        getActivity(), R.font.varela_round), Typeface.BOLD);
            }
        }

        Button positive = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setTextSize(16.0f);
        positive.setTypeface(ResourcesCompat.getFont(
                getActivity(), R.font.varela_round), Typeface.BOLD);
        positive.setTextColor(ResourcesCompat.getColor(
                        getResources(), R.color.colorPrimary, null));

        Button negative = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE);
        negative.setTextSize(16.0f);
        negative.setTypeface(ResourcesCompat.getFont(
                getActivity(), R.font.varela_round), Typeface.BOLD);
        negative.setTextColor(ResourcesCompat.getColor(
                getResources(), R.color.colorPrimary, null));
    }
}
