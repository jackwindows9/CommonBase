package com.siw.practice;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import butterknife.BindView;
import java.util.List;

public class PicRecyclerViewAdapter extends RecyclerViewAdapter<Drawable> {

    public PicRecyclerViewAdapter(List<Drawable> mList) {
        super(mList);
    }

    @Override
    protected RecyclerViewHolder onCreateViewHolder(View root, int viewType) {
        return new PicViewHolder(root);
    }

    @Override
    protected int getItemViewType(int position, Drawable drawable) {
        return R.layout.recycler_view_item;
    }

    class PicViewHolder extends RecyclerViewHolder<Drawable> {
        @BindView(R.id.item_pic)
        ImageView mImageView;

        public PicViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Drawable drawable) {
            mImageView.setImageDrawable(drawable);
        }
    }
}
