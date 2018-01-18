package karthik.app.demo.data.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import karthik.app.demo.R;
import karthik.app.demo.data.model.ConferenceModel;
import karthik.app.demo.listeners.PersonChangedListener;

/**
 * Created by Flochat on 17-01-2018.
 */

public class OtherPersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private List<ConferenceModel> list;
    private ConferenceModel model;

    private View itemView;
    private LayoutInflater layoutInflater;


    // callbacks
    private PersonChangedListener personChangedListener;
    // display
    private DisplayMetrics displayMetrics;

    // layout params
    private GridLayoutManager.LayoutParams layoutParams;

    public OtherPersonAdapter(Context mContext, List<ConferenceModel> list, PersonChangedListener personChangedListener) {
        this.mContext = mContext;
        this.mActivity = (Activity) mContext;
        this.list = list;
        this.personChangedListener = personChangedListener;
        layoutInflater = LayoutInflater.from(mContext);
        displayMetrics = mContext.getResources().getDisplayMetrics();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public OtherPersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = layoutInflater.inflate(R.layout.item_other_person, parent, false);
        return new OtherPersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        OtherPersonViewHolder viewHolder = (OtherPersonViewHolder) holder;
        model = list.get(position);


        if (!model.isMe() && !model.isSelected()) {

            viewHolder.clItem.setBackgroundColor(model.getColor());
            viewHolder.tvName.setText(model.getName());
        }


    }

    @Override
    public int getItemCount() {

        /*int returnVal = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isMe() && !list.get(i).isSelected()) {
                returnVal = returnVal + 1;
            }
        }*/
        return list.size();
    }

    public class OtherPersonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.cl_item)
        ConstraintLayout clItem;

        public OtherPersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.cl_item)
        public void personSelected() {

            ConferenceModel model = list.get(getLayoutPosition());
            model.setSelected(true);
            personChangedListener.onPersonChanged(model);
        }
    }
}
