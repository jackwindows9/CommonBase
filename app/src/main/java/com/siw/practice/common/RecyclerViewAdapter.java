package com.siw.practice.common;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import com.siw.practice.R;
import java.util.List;

public abstract class RecyclerViewAdapter<Data> extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements View.OnClickListener, View.OnLongClickListener{
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private List<Data> mList;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter(List<Data> mList) {
        this.mList = mList;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() viewType = " + viewType);
        // viewType from getItemViewType(). Different position, we can use different layout (This
        // viewType actually is layout id, such as R.layout.recycler_view_item).
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        RecyclerViewHolder holder = onCreateViewHolder(view, viewType);
        view.setTag(R.id.recycler_view_tag, holder);
        ButterKnife.bind(holder, view);

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    /**
     * Declare this method for sub class to construct different holder by viewType.
     * @param root The root view to construct holder.
     * @param viewType Construct different holder by viewType.
     * @return
     */
    protected abstract RecyclerViewHolder onCreateViewHolder(View root, int viewType);

    @Override
    public int getItemViewType(int position) {
        if (null != mList && null != mList.get(position)) {
            int viewType = getItemViewType(position, mList.get(position));
            Log.d(TAG, "getItemViewType() viewType = " + viewType);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    /**
     * For sub class, sub class can return own layout xml file.
     * Overload this method from super class, for different data, we can use different layout.
     * @param position In some special case(such as position 0),
     *                 we need position to decide which layout we should to use.
     * @param data With different data, use different layout.
     * @return
     */
    protected abstract int getItemViewType(int position, Data data);

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder,
                                 int position) {
        if (null != mList && null != mList.get(position)) {
            Data data = mList.get(position);
            holder.onBind(data);
        }
    }

    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        RecyclerViewHolder holder = (RecyclerViewHolder) v.getTag(R.id.recycler_view_tag);
        if (null == holder || null == mList || null == mOnItemClickListener){
            return;
        }
        int position = holder.getAdapterPosition();
        if (null == mList.get(position)) {
            return;
        }
        mOnItemClickListener.onItemClick(position, mList.get(position));
    }

    @Override
    public boolean onLongClick(View v) {
        RecyclerViewHolder holder = (RecyclerViewHolder) v.getTag(R.id.recycler_view_tag);
        if (null == holder || null == mList || null == mOnItemClickListener){
            return false;
        }
        int position = holder.getAdapterPosition();
        if (null == mList.get(position)) {
            return false;
        }
        mOnItemClickListener.onItemLongClick(position, mList.get(position));
        return true;
    }

    public static abstract class RecyclerViewHolder<Data> extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * Exposed data to sub class.
         * @param data The data at the position.
         */
        protected abstract void onBind(Data data);
    }

    public interface OnItemClickListener<Data> {
        void onItemClick(int position, Data data);
        void onItemLongClick(int position, Data data);
    }
}
