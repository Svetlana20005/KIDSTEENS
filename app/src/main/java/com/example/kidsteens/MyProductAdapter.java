package com.example.kidsteens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyProductAdapter extends ArrayAdapter<Product> {
    public MyProductAdapter(Context context, ArrayList<Product> arr){
        super(context, R.layout.product_item, arr);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_item, null);
        }
        ((TextView) convertView.findViewById(R.id.tvName)).setText(String.valueOf(product.getName()));
        ((TextView) convertView.findViewById(R.id.tvPrice)).setText(String.valueOf(product.getPrice()));
        return convertView;
    }
}
