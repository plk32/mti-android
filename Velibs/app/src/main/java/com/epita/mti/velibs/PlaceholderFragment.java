package com.epita.mti.velibs;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PlaceholderFragment extends Fragment {

    public static PlaceholderFragment newInstance(Velib velib) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString("name", velib.getFields().getName());
        args.putString("status", velib.getFields().getStatus());
        args.putString("stands", String.valueOf(velib.getFields().getBike_stands()));
        args.putString("available_stands", String.valueOf(velib.getFields().getAvailable_bike_stands()));
        args.putString("address", velib.getFields().getAddress());
        args.putString("last_update", velib.getFields().getLastUpdate());
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_for_view_pager, container, false);

        final String address = getArguments().getString("address");
        String last_update = getArguments().getString("last_update");
        String format_date = ConvertDate(last_update);

        SetIcon(rootView, R.id.status_icon, "status");
        SetTextView(rootView, R.id.name, "name");
        SetTextView(rootView, R.id.stands, "stands");
        SetTextView(rootView, R.id.available_stands, "available_stands");
        SetTextView(rootView, R.id.address, "address");

        TextView textViewUpdate = (TextView) rootView.findViewById(R.id.last_update);
        String value = textViewUpdate.getText() + " " + format_date;
        textViewUpdate.setText(value);
        TextView textViewAddress = (TextView) rootView.findViewById(R.id.address);

        textViewAddress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                v.getContext().startActivity(mapIntent);
            }
        });

        return rootView;
    }

    private void SetTextView(View rootView, int TextId, String tag) {
        Bundle args = getArguments();
        TextView textView = (TextView) rootView.findViewById(TextId);
        textView.setText(args.getString(tag));
    }

    private void SetIcon(View rootView, int imageId, String tag) {
        Bundle args = getArguments();
        String status = args.getString(tag);
        ImageView imageView = (ImageView) rootView.findViewById(imageId);

        if (status.equals("OPEN")) {
            imageView.setImageResource(R.drawable.ic_check_circle);
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.statusOK));
        }
        else {
            imageView.setImageResource(R.drawable.ic_remove_circle);
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.statusKO));
        }
    }

    private String ConvertDate(String last_update) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String outputDate  = new String();

        Date convertedDate = new Date();
        try {
            convertedDate = inFormat.parse(last_update);
            outputDate  = outFormat.format(convertedDate);
            Log.e("CONVERTDATE", convertedDate.toString());
            Log.e("NEWDATE", outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }
}
