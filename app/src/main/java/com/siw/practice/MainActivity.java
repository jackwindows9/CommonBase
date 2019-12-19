package com.siw.practice;

import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import com.siw.practice.common.Activity;
import com.siw.practice.common.RecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private List<Drawable> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBefore() {
        super.initBefore();
        mList = new ArrayList<>();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mList.add(getDrawable(R.drawable.a));
        mList.add(getDrawable(R.drawable.b));
        mList.add(getDrawable(R.drawable.c));
        mList.add(getDrawable(R.drawable.d));
        mList.add(getDrawable(R.drawable.e));
        mList.add(getDrawable(R.drawable.f));
        mList.add(getDrawable(R.drawable.a));
        mList.add(getDrawable(R.drawable.b));
        mList.add(getDrawable(R.drawable.c));
        mList.add(getDrawable(R.drawable.d));
        mList.add(getDrawable(R.drawable.e));
        mList.add(getDrawable(R.drawable.f));
    }

    @Override
    protected void initData() {
        super.initData();
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        PicRecyclerViewAdapter picRecyclerViewAdapter = new PicRecyclerViewAdapter(mList);
        picRecyclerViewAdapter.setmOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(picRecyclerViewAdapter);
    }

    RecyclerViewAdapter.OnItemClickListener mOnItemClickListener = new RecyclerViewAdapter.OnItemClickListener<Drawable>() {
        @Override
        public void onItemClick(int position, Drawable drawable) {
            Log.d(TAG, "onItemClick() position = " + position);
        }

        @Override
        public void onItemLongClick(int position, Drawable drawable) {
            Log.d(TAG, "onItemLongClick() position = " + position);
        }
    };
}
