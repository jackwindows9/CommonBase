package com.siw.practice.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import java.util.List;

public abstract class Activity extends AppCompatActivity {

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
        if (null == fragments || 0 == fragments.size()) {
            return;
        }
        for (Fragment fragment: fragments) {
            if (null != fragment && fragment instanceof com.siw.practice.common.Fragment) {
                // Own Fragment.
                if (((com.siw.practice.common.Fragment) fragment).onBackPressed()) {
                    // Handled by fragment.
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
}
