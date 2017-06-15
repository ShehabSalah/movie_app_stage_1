package com.platformhouse.movieszone.movieszone.ui.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.platformhouse.movieszone.movieszone.R;
import com.platformhouse.movieszone.movieszone.data.movie.MovieColumnHolder;
import com.platformhouse.movieszone.movieszone.util.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shehab Salah on 8/20/16.
 *
 */
public class DetailsFragment extends Fragment {
    //associate variables with xml views
    @BindView(R.id.detail_scroll)
    ScrollView detail_scroll;
    @BindView(R.id.movie_item_poster_DetailsActivity)
    ImageView posterImage;
    @BindView(R.id.movie_item_name_DetailsActivity)
    TextView movieName;
    @BindView(R.id.movie_item_date_DetailsActivity)
    TextView movieDate;
    @BindView(R.id.movie_item_rate_DetailsActivity)
    TextView movieRate;
    @BindView(R.id.movie_item_overview_DetailsActivity)
    TextView movieOverview;
    //Variables initialization
    int detail_id = 0;
    MovieColumnHolder movieColumnHolder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the movie_details view into details Activity
        View detailsView = inflater.inflate(R.layout.movie_details, container, false);

        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        //Start associating variables with views
        bindViews(detailsView);
        movieColumnHolder = null;
        //Intent intent = getActivity().getIntent();
        // Get the extras (if there are any)
        Bundle extras = getArguments();
        if (extras != null) {
            if (extras.containsKey(getString(R.string.movie_list))) {
                movieColumnHolder = extras.getParcelable(getString(R.string.movie_list));
            }
        }
        //If chosen movie content exits
        if (movieColumnHolder != null) {
            Picasso.with(getActivity().getApplicationContext())
                    .load(Constants.IMAGE_URL + movieColumnHolder.getPoster_path())
                    .error(R.mipmap.error_background)
                    .placeholder(R.mipmap.placeholder_background)
                    .into(posterImage);
            movieName.setText(movieColumnHolder.getOriginal_title());
            movieDate.setText(movieColumnHolder.getRelease_date());
            movieRate.setText(getString(R.string.vote,Float.toString(movieColumnHolder.getVote_average())));
            movieOverview.setText(movieColumnHolder.getOverview());
            detail_id = movieColumnHolder.getId();
            detail_scroll.smoothScrollTo(0,0);

        }
        return detailsView;
    }

    private void bindViews(View detailsView){
        ButterKnife.bind(this, detailsView);
    }
}