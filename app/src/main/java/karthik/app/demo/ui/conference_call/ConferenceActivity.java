package karthik.app.demo.ui.conference_call;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import karthik.app.demo.R;
import karthik.app.demo.data.adapter.ConferenceAdapter;
import karthik.app.demo.data.adapter.OtherPersonAdapter;
import karthik.app.demo.data.model.ConferenceModel;
import karthik.app.demo.listeners.HideAddListener;
import karthik.app.demo.listeners.HideRemoveListener;
import karthik.app.demo.listeners.LayoutChangeListener;
import karthik.app.demo.listeners.FullScreenListener;
import karthik.app.demo.listeners.PersonChangedListener;

/**
 * Created by Flochat on 17-01-2018.
 */

public class ConferenceActivity extends AppCompatActivity implements HideAddListener, HideRemoveListener, LayoutChangeListener, FullScreenListener, PersonChangedListener {

    @BindView(R.id.rv_conference)
    RecyclerView rvConference;
    @BindView(R.id.bt_remove)
    Button btRemove;
    @BindView(R.id.bt_add)
    Button btAdd;
    Unbinder unbinder;
    @BindView(R.id.cl_list)
    ConstraintLayout clList;
    @BindView(R.id.cl_full_screen)
    ConstraintLayout clFullScreen;
    @BindView(R.id.tv_selected_name)
    TextView tvSelectedName;
    @BindView(R.id.cl_selected)
    ConstraintLayout clSelected;
    @BindView(R.id.rv_other_person)
    RecyclerView rvOtherPerson;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.cl_me)
    ConstraintLayout clMe;
    @BindView(R.id.bt_ss)
    Button btSs;


    // layout
    private GridLayoutManager layoutManager;

    // data
    private List<ConferenceModel> list;
    private List<ConferenceModel> remainingPersonList;
    private ConferenceModel model;
    private ConferenceAdapter adapter;
    private OtherPersonAdapter otherPersonAdapter;

    // flag
    private boolean isFullScreenOn = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_call);
        ButterKnife.bind(this);

        initView();
        initBlankData();
    }

    private void initView() {
        layoutManager = new GridLayoutManager(ConferenceActivity.this, 2);
        rvConference.setLayoutManager(layoutManager);
        rvOtherPerson.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initBlankData() {

        ViewTreeObserver vto = rvConference.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    rvConference.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    rvConference.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                int height = rvConference.getMeasuredHeight();

                list = new ArrayList<>(7);

                adapter = new ConferenceAdapter(ConferenceActivity.this, list, height, ConferenceActivity.this, ConferenceActivity.this, ConferenceActivity.this, ConferenceActivity.this);
                rvConference.setAdapter(adapter);


                ConferenceModel model = new ConferenceModel();
                model.setColor(getColor());
                model.setPerson_position(1);
                // only 1st is Me.
                model.setMe(true);
                model.setName("My self");
                adapter.addMember(model);

            }
        });
    }


    @OnClick(R.id.bt_add)
    public void addMemberToConference() {
        ConferenceModel model = new ConferenceModel();
        model.setColor(getColor());
        model.setPerson_position(1);
        model.setMe(false);
        model.setName("Other people = " + adapter.getItemCount());
        adapter.addMember(model);
    }

    @OnClick(R.id.bt_remove)
    public void removeMemberFromConference() {
        adapter.removeMember(null);
    }


    @OnClick(R.id.bt_ss)
    public void goBack() {
        if (isFullScreenOn) {
            clList.setVisibility(View.VISIBLE);
            clFullScreen.setVisibility(View.GONE);
            isFullScreenOn = false;
        }
    }



    private int getColor() {
        switch (list.size()) {
            case 1:
                return getResources().getColor(R.color.person_1);
            case 2:
                return getResources().getColor(R.color.person_2);
            case 3:
                return getResources().getColor(R.color.person_3);
            case 4:
                return getResources().getColor(R.color.person_4);
            case 5:
                return getResources().getColor(R.color.person_5);
            case 6:
                return getResources().getColor(R.color.person_6);
            default:
                return getResources().getColor(R.color.person_7);
        }
    }

    @Override
    public void onHideAdd(boolean isHide) {
        if (isHide) {
            btAdd.setVisibility(View.GONE);
        } else {
            btAdd.setVisibility(View.VISIBLE);
        }
        rvConference.scrollToPosition(list.size() - 1);
    }

    @Override
    public void onHideRemove(boolean isHide) {
        if (isHide) {
            btRemove.setVisibility(View.GONE);
        } else {
            btRemove.setVisibility(View.VISIBLE);
        }
        rvConference.scrollToPosition(list.size() - 1);
    }

    @Override
    public void onLayoutChanged(final int size) {
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (size == 1 || size == 2) {
                    return 2;
                } else if (size == 3) {

                    if (position == 0) {
                        return 2;
                    } else {
                        return 1;
                    }
                } else if (size == 4) {
                    return 1;
                } else if (size == 5) {
                    if (position == 0) {
                        return 2;
                    } else {
                        return 1;
                    }
                } else if (size == 6) {
                    if (position == 0 || position == list.size() - 1) {
                        return 2;
                    } else {
                        return 1;
                    }
                } else if (size == 7) {
                    if (position == 0) {
                        return 2;
                    } else {
                        return 1;
                    }
                }
                return 0;
            }
        });

    }

    @Override
    public void onFullScreen(ConferenceModel model) {

        isFullScreenOn = true;

        clList.setVisibility(View.GONE);
        clFullScreen.setVisibility(View.VISIBLE);

        showSelectedPerson();
    }


    private void showSelectedPerson() {

        remainingPersonList = new ArrayList<>(5);

        for (ConferenceModel conferenceModel : list) {
            if (conferenceModel.isMe()) {
                clMe.setBackgroundColor(conferenceModel.getColor());
                tvMyName.setText(conferenceModel.getName());
            }
            if (conferenceModel.isSelected()) {
                clSelected.setBackgroundColor(conferenceModel.getColor());
                tvSelectedName.setText(conferenceModel.getName());
            }

            if (!conferenceModel.isMe() && !conferenceModel.isSelected()) {

                remainingPersonList.add(conferenceModel);
            }
        }

        otherPersonAdapter = new OtherPersonAdapter(ConferenceActivity.this, remainingPersonList, ConferenceActivity.this);
        rvOtherPerson.setAdapter(otherPersonAdapter);
    }

    @Override
    public void onPersonChanged(ConferenceModel model) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)==(model)){
                list.get(i).setSelected(true);
            }
            else {
                list.get(i).setSelected(false);
            }
        }
        showSelectedPerson();
    }
}
