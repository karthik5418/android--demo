package karthik.app.demo.ui.conference_call;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import karthik.app.demo.R;
import karthik.app.demo.data.adapter.ConferenceAdapter;
import karthik.app.demo.data.model.ConferenceModel;
import karthik.app.demo.listeners.HideAddListener;
import karthik.app.demo.listeners.HideRemoveListener;
import karthik.app.demo.listeners.LayoutChangeListener;

/**
 * Created by Flochat on 17-01-2018.
 */

public class ConferenceListFragment extends Fragment implements HideAddListener, HideRemoveListener, LayoutChangeListener {

    @BindView(R.id.rv_conference)
    RecyclerView rvConference;
    @BindView(R.id.bt_remove)
    Button btRemove;
    @BindView(R.id.bt_add)
    Button btAdd;
    Unbinder unbinder;
    private View mView;


    // parent activity
    private Context mContext;
    private ConferenceCallActivity mActivity;

    // layout
    private GridLayoutManager layoutManager;

    // data
    private List<ConferenceModel> list;
    private ConferenceModel model;
    private ConferenceAdapter adapter;

    public ConferenceListFragment() {
    }

    public static ConferenceListFragment newInstance() {
        ConferenceListFragment fragment = new ConferenceListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (ConferenceCallActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_conference, container, false);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initBlankData();
    }

    private void initView() {
        layoutManager = new GridLayoutManager(mContext, 2);
        rvConference.setLayoutManager(layoutManager);
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
                adapter = new ConferenceAdapter(mContext, list,height ,ConferenceListFragment.this, ConferenceListFragment.this, ConferenceListFragment.this);
                rvConference.setAdapter(adapter);

            }
        });
    }


    @OnClick(R.id.bt_add)
    public void addMemberToConference() {
        ConferenceModel model = new ConferenceModel();

        model.setColor(getColor());
        model.setPerson_position(1);

        if (list.size() == 0) { // only 1st is Me.
            model.setMe(true);
            model.setName("My self");
        } else {
            model.setMe(false);
            model.setName("Other people = " + adapter.getItemCount());
        }

        adapter.addMember(model);
    }

    @OnClick(R.id.bt_remove)
    public void removeMemberFromConference() {
        adapter.removeMember(null);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
}
