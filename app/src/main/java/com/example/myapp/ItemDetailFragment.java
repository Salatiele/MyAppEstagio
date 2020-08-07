package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.page.PageContent;

/** Esse fragmet representa o Detalhe de cada página do listView
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets (não programado).
 */
public class ItemDetailFragment extends Fragment {

    /** ID da página que este Fragment representa*/
    public static final String ARG_ITEM_ID = "item_id";

    /** Página representada*/
    private PageContent.PageItem pagItem;

    /** Construtor vazio obrigatótio para o fragment manager instanciar o fragment*/
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the page content specified by the fragment arguments
            pagItem = PageContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(pagItem.content);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the page content as text in a TextView.
        if (pagItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(pagItem.details);
        }
        return rootView;
    }
}