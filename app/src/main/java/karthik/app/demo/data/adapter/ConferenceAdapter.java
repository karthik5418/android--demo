package karthik.app.demo.data.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import karthik.app.demo.R;
import karthik.app.demo.constants.AppConstants;
import karthik.app.demo.data.model.ConferenceModel;
import karthik.app.demo.listeners.FullScreenListener;
import karthik.app.demo.listeners.HideAddListener;
import karthik.app.demo.listeners.HideRemoveListener;
import karthik.app.demo.listeners.LayoutChangeListener;

/**
 * Created by Flochat on 17-01-2018.
 */

public class ConferenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private List<ConferenceModel> list;
    private ConferenceModel model;

    private View itemView;
    private LayoutInflater layoutInflater;


    // callbacks
    private HideRemoveListener hideRemoveListener;
    private HideAddListener hideAddListener;
    private LayoutChangeListener layoutChangeListener;
    private FullScreenListener fullScreenListener;

    // display
    private int mHeight;

    // layout params
    private GridLayoutManager.LayoutParams layoutParams;

    public ConferenceAdapter(Context mContext, List<ConferenceModel> list, int mHeight, HideRemoveListener hideRemoveListener, HideAddListener hideAddListener, LayoutChangeListener layoutChangeListener, FullScreenListener fullScreenListener) {
        this.mContext = mContext;
        this.mActivity = (Activity) mContext;
        this.mHeight = mHeight;
        this.list = list;
        this.hideRemoveListener = hideRemoveListener;
        this.hideAddListener = hideAddListener;
        this.layoutChangeListener = layoutChangeListener;
        this.fullScreenListener = fullScreenListener;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public void addMember(ConferenceModel model) {
        if (list.size() < AppConstants.MAX_PEOPLE) {
            list.add(model);
            //notifyItemChanged(list.size() - 1);
            notifyDataSetChanged();
        }
        setButtonVisibility();
        layoutChangeListener.onLayoutChanged(list.size());
    }

    public void removeMember(ConferenceModel model) {
        if (list.size() > AppConstants.MIN_PEOPLE) {
            list.remove(list.size() - 1);
            //notifyItemChanged(list.size());
            notifyDataSetChanged();
        }
        setButtonVisibility();
        layoutChangeListener.onLayoutChanged(list.size());
    }


    private void setButtonVisibility() {
        // Add button
        if (list.size() >= AppConstants.MIN_PEOPLE && list.size() < AppConstants.MAX_PEOPLE) {
            hideAddListener.onHideAdd(false);
        } else if (list.size() == AppConstants.MAX_PEOPLE) {
            hideAddListener.onHideAdd(true);
        }

        // Remove button
        if (list.size() > AppConstants.MIN_PEOPLE || list.size() == AppConstants.MAX_PEOPLE) {
            hideRemoveListener.onHideRemove(false);
        } else {
            hideRemoveListener.onHideRemove(true);
        }
    }

    @Override
    public ConferenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = layoutInflater.inflate(R.layout.item_conference, parent, false);
        return new ConferenceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ConferenceViewHolder viewHolder = (ConferenceViewHolder) holder;
        model = list.get(position);

        viewHolder.clItem.setBackgroundColor(model.getColor());
        viewHolder.tvName.setText(model.getName());

        if (model.isMe()) {
            viewHolder.btFs.setVisibility(View.GONE);
        } else {
            viewHolder.btFs.setVisibility(View.VISIBLE);
        }
        setItemHeight(viewHolder);
    }

    private void setItemHeight(ConferenceViewHolder viewHolder) {
        layoutParams = (GridLayoutManager.LayoutParams) viewHolder.clItem.getLayoutParams();
        if (list.size() == 1 || list.size() == 2 || list.size() == 3 || list.size() == 4) {
            layoutParams.height = mHeight / 2;
            viewHolder.clItem.setLayoutParams(layoutParams);
        } else if (list.size() == 5) {
            layoutParams.height = mHeight / 3;
            viewHolder.clItem.setLayoutParams(layoutParams);
        } else if (list.size() == 6 || list.size() == 7) {
            layoutParams.height = mHeight / 4;
            viewHolder.clItem.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ConferenceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.bt_fs)
        Button btFs;
        @BindView(R.id.cl_item)
        ConstraintLayout clItem;

        public ConferenceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.bt_fs)
        public void personSelected() {
            ConferenceModel model = null;
            for (int i = 0; i < list.size(); i++) {
                model = list.get(i);
                if (i == getLayoutPosition()) {
                    model.setSelected(true);
                } else {
                    model.setSelected(false);
                }
            }
            fullScreenListener.onFullScreen(model);
            //fullScreenListener.onFullScreen(model);
        }
    }
}
