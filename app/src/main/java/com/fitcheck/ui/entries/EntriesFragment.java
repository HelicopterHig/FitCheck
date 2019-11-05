package com.fitcheck.ui.entries;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fitcheck.R;

public class EntriesFragment extends Fragment {

    private EntriesViewModel entriesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entriesViewModel =
                ViewModelProviders.of(this).get(EntriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_entries, container, false);
        final TextView textView = root.findViewById(R.id.text_entries);
        entriesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}