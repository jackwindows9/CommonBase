package com.siw.practice;

import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private List<Drawable> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPic();

        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        PicRecyclerViewAdapter picRecyclerViewAdapter = new PicRecyclerViewAdapter(mList);
        picRecyclerViewAdapter.setmOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(picRecyclerViewAdapter);

    }

    private void initPic() {
        mList = new ArrayList<>();
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
