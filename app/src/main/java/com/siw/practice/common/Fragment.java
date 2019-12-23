package com.siw.practice.common;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class Fragment extends androidx.fragment.app.Fragment {
    private final static String TAG = Fragment.class.getSimpleName();

    protected View mRoot;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // When create the instance of fragment, activity could set args by this.
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRoot) {
            int layoutId = getContentLayoutId();
            View view = LayoutInflater.from(container.getContext()).inflate(layoutId, container, false);
            mRoot = view;
        } else {
            if (null == mRoot.getParent()) {
                Log.e(TAG, "mRoot.getParent() = null");
                return mRoot;
            }
            try {
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            } catch (ClassCastException e) {
                Log.e(TAG, "mRoot.getParent() is not a ViewGroup.");
                e.printStackTrace();
            }
        }
        initWidget();
        initData();
        return mRoot;
    }

    /**
     * Initialize by the args from activity.
     * @param bundle
     */
    protected abstract void initArgs(Bundle bundle);

    /**
     * Expose this method to sub class, for customizing the layout id.
     * @return The layout resource id.
     */
    protected abstract int getContentLayoutId();

    /**
     * Initialize widgets.
     */
    protected abstract void initWidget();

    /**
     * Initialize data.
     */
    protected abstract void initData();

    /**
     * Back key press event will be handle by current fragment.
     * The Activity will actively callback this method when trigger the activity's
     * {@link Activity#onBackPressed()}
     * @return True if Back press event handled by this current fragment, else false.
     */
    public boolean onBackPressed() {
        return false;
    }
}
