package com.siw.practice.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * 控制Fragment的add和popup。
 */
public interface FragmentController {

    void add(@NonNull Fragment fragment, @NonNull String fragmentTag);

    void popUp();

    // 针对指定的fragmentTag进行popUp，不包括当前fragmentTag
    void popUp(@NonNull String fragmentTag);

    // 针对指定的fragmentTag进行popUp，包括当前fragmentTag
    void popUpInclusive(@NonNull String fragmentTag);
}
