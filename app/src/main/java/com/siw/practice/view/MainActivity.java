package com.siw.practice.view;

import com.siw.practice.R;
import com.siw.practice.common.Activity;
import com.siw.practice.common.Fragment;

public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        launchFragment(new MainFragment());
    }

    /**
     * Launch fragment.
     * @param fragment
     */
    private void launchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
