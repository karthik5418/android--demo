package karthik.app.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import dagger.android.DaggerActivity;

/**
 * Created by NiCK on 1/2/2018.
 */

public class BaseActivity extends DaggerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
