package com.example.lorris.tp_retrofit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lorris on 12/05/2017.
 */

public class PlaceholderFragment extends Fragment {

    public static PlaceholderFragment newInstance(Article article) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString("uri", article.getUri());
        args.putString("title", article.getTitle());
        args.putString("author", article.getAuthor());
        args.putString("type", article.getType());
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_for_view_pager, container, false);
        TextView textViewURI = (TextView) rootView.findViewById(R.id.uri);
        textViewURI.setText(getArguments().getString("uri"));
        TextView textViewTitle = (TextView) rootView.findViewById(R.id.title);
        textViewTitle.setText(getArguments().getString("title"));
        TextView textViewAuthor = (TextView) rootView.findViewById(R.id.author);
        textViewAuthor.setText(getArguments().getString("author"));
        TextView textViewType = (TextView) rootView.findViewById(R.id.type);
        textViewType.setText(getArguments().getString("type"));
        return rootView;
    }
}
