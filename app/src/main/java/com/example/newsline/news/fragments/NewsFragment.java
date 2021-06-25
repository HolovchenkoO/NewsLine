package com.example.newsline.news.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.newsline.R;
import com.example.newsline.news.Hit;
import com.example.newsline.news.adapters.NewsAdapter;
import com.example.newsline.news.dummy.DummyContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment
  implements NewsAdapter.OnNewsAdapterListener {


    public RecyclerView.Adapter adapter;

    @Override
        public void onItemClick(Hit item) {
            listener.onItemSelect(item);
        }
        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);

            if (context instanceof OnNewsFragmentListener) {
                listener = (OnNewsFragmentListener) context;
            }
        }

        public interface OnNewsFragmentListener {
            void onItemSelect(Hit item);
        }

        // TODO: Customize parameter argument names
        private static final String ARG_COLUMN_COUNT = "column-count";
        // TODO: Customize parameters
        private int mColumnCount = 1;

        private OnNewsFragmentListener listener;

        /**
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    public NewsFragment() {
        }

        // TODO: Customize parameter initialization
        @SuppressWarnings("unused")
        public static NewsFragment newInstance(int columnCount) {
            NewsFragment fragment = new NewsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_COLUMN_COUNT, columnCount);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (getArguments() != null) {
                mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_list_news, container, false);

            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                if (mColumnCount <= 1) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                }
                recyclerView.setAdapter(new NewsAdapter(this, DummyContent.ITEMS));
                adapter = recyclerView.getAdapter();
            }
            return view;
        }
    }