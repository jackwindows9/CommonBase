package com.siw.practice.common;

public class Fragment extends androidx.fragment.app.Fragment {

    protected FragmentController mFragmentController;

    /**
     * Back key press event will be handle by current fragment.
     * The Activity will active callback this method when trigger the activity's
     * {@link Activity#onBackPressed()}
     * @return True if Back press event handled by this current fragment, else false.
     */
    public boolean onBackPressed() {
        return false;
    }

    public void setmFragmentController(FragmentController mFragmentController) {
        this.mFragmentController = mFragmentController;
    }

    /**
     * Controls the transition of fragment.
     */
    public interface FragmentController {
        /**
         * Launches fragment in the main container of current activity.
         */
        void launchFragment(Fragment fragment);
    }
}
