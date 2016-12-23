package com.example.esidemjnr.movieappstage1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.esidemjnr.movieappstage1.models.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public static final String TAG = DetailActivityFragment.class.getSimpleName();

    static final String DETAIL_MOVIE = "DETAIL_MOVIE";

    private Movie mMovie;

    private ImageView mImageView;

    private TextView mTitleView;
    private TextView mOverviewView;
    private TextView mDateView;
    private TextView mVoteAverageView;

    private ScrollView mDetailLayout;

    private Toast mToast;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
        mMovie = arguments.getParcelable(DetailActivityFragment.DETAIL_MOVIE);
        }

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mDetailLayout = (ScrollView) rootView.findViewById(R.id.detail_layout);

        if (mMovie != null) {
        mDetailLayout.setVisibility(View.VISIBLE);
        } else {
        mDetailLayout.setVisibility(View.INVISIBLE);
        }

        mImageView = (ImageView) rootView.findViewById(R.id.detail_image);

        mTitleView = (TextView) rootView.findViewById(R.id.detail_title);
        mOverviewView = (TextView) rootView.findViewById(R.id.detail_overview);
        mDateView = (TextView) rootView.findViewById(R.id.detail_date);
        mVoteAverageView = (TextView) rootView.findViewById(R.id.detail_vote_average);
        if (mMovie != null) {

            String image_url = buildImageUrl(342, mMovie.getImage2());

        Glide.with(this).load(image_url).into(mImageView);

        mTitleView.setText(mMovie.getTitle());
        mOverviewView.setText(mMovie.getOverview());

        String movie_date = mMovie.getDate();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
        String date = DateUtils.formatDateTime(getActivity(),
        formatter.parse(movie_date).getTime(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        mDateView.setText(date);
        } catch (ParseException e) {
        e.printStackTrace();
        }

        mVoteAverageView.setText(Integer.toString(mMovie.getRating()));
        }

        return rootView;
        }
    public static String buildImageUrl(int width, String fileName) {
        return "http://image.tmdb.org/t/p/w" + Integer.toString(width) + fileName;
    }
}