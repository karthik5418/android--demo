package karthik.app.demo.data.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import karthik.app.demo.R;
import karthik.app.demo.data.model.ProductModel;

/**
 * Created by NiCK on 1/4/2018.
 */

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;

    private List<ProductModel> list;
    private ProductModel model;

    private LayoutInflater inflater;
    private View itemView;

    public ProductsAdapter(Context context,List<ProductModel> list) {
        this.list = list;
        mContext=context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = inflater.inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        model=list.get(position);
        ProductViewHolder viewHolder= (ProductViewHolder) holder;
        Glide.with(mContext)
                .load(model.getThumbnail())
                .into(viewHolder.ivThumbnail);
        viewHolder.tvName.setText(model.getName());
        viewHolder.tvPrice.setText(model.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
