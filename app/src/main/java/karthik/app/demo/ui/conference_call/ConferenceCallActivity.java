package karthik.app.demo.ui.conference_call;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import karthik.app.demo.BaseActivity;
import karthik.app.demo.R;

/**
 * Created by Flochat on 17-01-2018.
 */

public class ConferenceCallActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
        ButterKnife.bind(this);

        addListScreen();
    }

    private void addListScreen() {
        replaceFragment(R.id.fl_container, ConferenceListFragment.newInstance(), ConferenceListFragment.class.getSimpleName(), ConferenceListFragment.class.getSimpleName());

    }
}
