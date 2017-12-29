package karthik.app.demo;

import android.content.Context;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerFragment;

/**
 * Created by NiCK on 12/29/2017.
 */

public class BaseFragment extends DaggerFragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
