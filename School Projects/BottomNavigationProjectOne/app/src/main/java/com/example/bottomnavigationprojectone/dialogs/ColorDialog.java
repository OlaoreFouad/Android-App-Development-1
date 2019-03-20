package com.example.bottomnavigationprojectone.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.bottomnavigationprojectone.R;
import com.example.bottomnavigationprojectone.listeners.OnColorSelectedListener;
import com.example.bottomnavigationprojectone.util.Utils;

public class ColorDialog extends DialogFragment {
    private LayoutInflater inflater;
    private View view;

    private OnColorSelectedListener mOnColorSelectedListener;

    private int colorcode = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        inflater = requireActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_color, null);

        RadioGroup radioGroup = view.findViewById(R.id.colorRadioGroupId);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = view.findViewById(checkedId);

                switch (radioButton.getId()) {
                    case R.id.redColorId: {
                        colorcode = 1;
                    }
                    break;
                    case R.id.blueColorId: {
                        colorcode = 2;
                    }
                    break;
                    case R.id.greenColorId: {
                        colorcode = 3;
                    }
                    break;
                    default: {
                        break;
                    }
                }
            }
        });


        builder.setTitle(R.string.select_color)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        determine();
                        dismiss();
                    }
                });

        Dialog dialog = builder.create();
        return dialog;
    }

    private void determine() {
        mOnColorSelectedListener.colorSelected(colorcode);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnColorSelectedListener = (OnColorSelectedListener) context;
    }
}
