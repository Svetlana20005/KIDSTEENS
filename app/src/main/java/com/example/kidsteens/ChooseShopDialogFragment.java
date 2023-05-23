package com.example.kidsteens;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ChooseShopDialogFragment extends DialogFragment {
    Runnable runnable1, runnable2;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.choose_shop_ask)
                .setPositiveButton(R.string.choose_shop_ask_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        runnable1.run();
                    }
                })
                .setNegativeButton(R.string.choose_shop_ask_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        runnable2.run();
                    }
                });
        return builder.create();
    }

    public ChooseShopDialogFragment(Runnable runnable1, Runnable runnable2) {
        this.runnable1 = runnable1;
        this.runnable2 = runnable2;
    }

    public ChooseShopDialogFragment(int contentLayoutId) {
        super(contentLayoutId);
    }
}
