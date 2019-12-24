package com.siw.practice.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.siw.practice.PicRecyclerViewAdapter;
import com.siw.practice.R;
import com.siw.practice.common.Fragment;
import com.siw.practice.common.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private final static String TAG = MainFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private List<Drawable> mList;

    @Override
    protected void initArgs(Bundle bundle) {
        mList = new ArrayList<>();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initWidget() {
        if (null == getActivity()) {
            return;
        }
        Context context = getActivity();
        mRecyclerView = mRoot.findViewById(R.id.recycler_view);
        mList.add(context.getDrawable(R.drawable.a));
        mList.add(context.getDrawable(R.drawable.b));
        mList.add(context.getDrawable(R.drawable.c));
        mList.add(context.getDrawable(R.drawable.d));
        mList.add(context.getDrawable(R.drawable.e));
        mList.add(context.getDrawable(R.drawable.f));
        mList.add(context.getDrawable(R.drawable.a));
        mList.add(context.getDrawable(R.drawable.b));
        mList.add(context.getDrawable(R.drawable.c));
        mList.add(context.getDrawable(R.drawable.d));
        mList.add(context.getDrawable(R.drawable.e));
        mList.add(context.getDrawable(R.drawable.f));
    }

    @Override
    protected void initData() {
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        PicRecyclerViewAdapter picRecyclerViewAdapter = new PicRecyclerViewAdapter(mList);
        picRecyclerViewAdapter.setmOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(picRecyclerViewAdapter);
    }

    @Override
    public boolean onBackPressed() {
        // Pop up from back stack.
        getFragmentManager().popBackStack();
        return true;
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
