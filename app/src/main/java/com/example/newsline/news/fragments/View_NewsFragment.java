package com.example.newsline.news.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsline.R;
import com.example.newsline.news.Hit;
import com.example.newsline.news.entity.News;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link View_NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class View_NewsFragment extends Fragment {
    public interface OnView_NewsFragmentListener {
        void onBtnBackClick();
    }

    private OnView_NewsFragmentListener listener;
    public ImageView imageViewV;
    public TextView tvTitleV, tvContentV, tvPubDateV, tvAuthorsV;
    private Hit item;
    public Button btnBackV;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public View_NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPublicationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static  View_NewsFragment newInstance(String param1, String param2) {
        View_NewsFragment fragment = new  View_NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view__news, container, false);
        tvTitleV = view.findViewById(R.id.tvTitleV);
        tvContentV = view.findViewById(R.id.tvContentV);
        tvPubDateV = view.findViewById(R.id.tvPubDateV);
        tvAuthorsV = view.findViewById(R.id.tvAuthorsV);
        imageViewV = view.findViewById(R.id.imageViewV);
        btnBackV = view.findViewById(R.id.btnBackV);

        tvTitleV.setText(item.getTitle());
        tvContentV.setText(item.getContent());
        tvPubDateV.setText(item.getPubDate());
        tvAuthorsV.setText(item.getAuthors().toString());
        Picasso.get().load(Uri.parse(item.getImageUrl())).into(imageViewV);
        btnBackV.setOnClickListener(v -> {
            boolean b = true;
            try {
                News n = News.find(News.class, "title=?", item.getTitle()).get(0);
                item.setViewing(item.getViewing() + 1);
                n.setViewing(n.getViewing() + 1);
                n.save();
                b = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (b) {
                item.setViewing(item.getViewing() + 1);
                News n = new News(
                        item.getUrl(),
                        item.getSource(),
                        item.getAuthors(),
                        item.getTitle(),
                        item.getPubDate(),
                        item.getCountry(),
                        item.getLanguage(),
                        item.getDescription(),
                        item.getImageUrl(),
                        item.getContent(),
                        item.getViewing()
                );
                n.save();
            }
            listener.onBtnBackClick();
        });

        return view;
    }

    public void setData(Hit item) {
        this.item = item;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnView_NewsFragmentListener) {
            listener = (OnView_NewsFragmentListener) context;
        }
    }
}