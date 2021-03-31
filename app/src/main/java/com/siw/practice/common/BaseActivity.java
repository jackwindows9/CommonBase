package com.siw.practice.common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import java.util.List;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public abstract class BaseActivity extends AppCompatActivity implements FragmentController{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        initBefore();
        initWidget();
        initData();
    }

    /**
     * Expose this method to sub class, for customizing the layout id.
     * @return The layout resource id.
     *
     */
    protected abstract int getContentLayoutId();

    /**
     * Init some args before initializing widget.
     */
    protected void initBefore() {}

    /**
     * Use the ButterKnife and init widgets.
     */
    protected void initWidget() {
        ButterKnife.bind(this);
    }

    /**
     * Init data.
     */
    protected void initData() {}

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (null == fragments || 1 >= fragments.size()) {
            // 如果当前没有或只有一个fragment在activity上，那么直接调用activity的onBackPressed
            super.onBackPressed();
            return;
        }
        for (Fragment fragment: fragments) {
            if (fragment instanceof com.siw.practice.common.Fragment) {
                // Own Fragment.
                if (((com.siw.practice.common.Fragment) fragment).onBackPressed()) {
                    // 已被fragment处理
                    return;
                }
            }
        }
        super.onBackPressed();
    }

    /**
     * When the back key on the Navigation is clicked, callback this method.
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        // Finish current activity.
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void add(@NonNull Fragment fragment, @NonNull String fragmentTag) {

    }

    @Override
    public void popUp() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void popUp(@NonNull String fragmentTag) {
        getSupportFragmentManager().popBackStack(fragmentTag, 0);
    }

    @Override
    public void popUpInclusive(@NonNull String fragmentTag) {
        getSupportFragmentManager().popBackStack(fragmentTag, POP_BACK_STACK_INCLUSIVE);
    }
}
