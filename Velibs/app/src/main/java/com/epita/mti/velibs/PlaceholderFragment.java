package com.epita.mti.velibs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lorris on 12/05/2017.
 */

public class PlaceholderFragment extends Fragment {

    public static PlaceholderFragment newInstance(Velib velib) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString("name", velib.getFields().getName());
        args.putString("status", velib.getFields().getStatus());
        args.putString("bike_stands", String.valueOf(velib.getFields().getBike_stands()));
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

        TextView textViewName = (TextView) rootView.findViewById(R.id.name);
        textViewName.setText(getArguments().getString("name"));
        TextView textViewStatus = (TextView) rootView.findViewById(R.id.status);
        textViewStatus.setText(getArguments().getString("status"));
        TextView textViewStands = (TextView) rootView.findViewById(R.id.stands);
        textViewStands.setText(getArguments().getString("bike_stands"));
        TextView textViewAvStands = (TextView) rootView.findViewById(R.id.available_stands);
        textViewAvStands.setText(getArguments().getString("available_stands"));
        TextView textViewAddress = (TextView) rootView.findViewById(R.id.address);
        textViewAddress.setText(address);
        TextView textViewUpdate = (TextView) rootView.findViewById(R.id.last_update);
        textViewUpdate.setText(getArguments().getString("last_update"));

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
}
