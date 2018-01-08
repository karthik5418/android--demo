package karthik.app.demo.ui.on_boarding;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import karthik.app.demo.R;

/**
 * Created by NiCK on 1/8/2018.
 */

public class ScreenSlideFragment extends Fragment {

    MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity= (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        int position = args.getInt("position");
        int layoutId = getLayoutId(position);


        ViewGroup rootView = (ViewGroup) inflater.inflate(layoutId, container, false);
        if (position == 0) {

            mainActivity.initFirstScreenViews(rootView, savedInstanceState);
        }
        if (position == 1) {

            mainActivity.initSecondScreenViews(rootView, savedInstanceState);
        }
        if (position == 2) {

            mainActivity.initThirdScreenViews(rootView, savedInstanceState);
        }

        return rootView;
    }

    private int getLayoutId(int position) {

        int id = 0;
        if (position == 0) {

            id = R.layout.first_screen;

        } else if (position == 1) {

            id = R.layout.second_screen;
        } else if (position == 2) {

            id = R.layout.third_screen;
        }
        return id;
    }


}
