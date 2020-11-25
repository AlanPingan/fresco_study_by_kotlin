package com.project.alan.frescolearningbykotlin.base.fagment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Alan on 2020/10/16.
 * 可见的时候加载不可见的时候停止加载
 */

public abstract class LazyFragment extends Fragment {

    View rootView;
    boolean isViewCreated = false;
    boolean currentVisibleState = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        initView();
        isViewCreated = true;
        // 弥补第一次创建的时候先调用setUserVisibleHint（true）而isViewCreated为false 在disPatchUserVisibleHint中事件被拦截的问题
        if (getUserVisibleHint()) {
            disPatchUserVisibleHint(true);
        }
        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isViewCreated) {
            return;
        }
        // 只有可见到不可见  以及  不可见到可见 这两种情况才分发事件
        if (isVisibleToUser && !currentVisibleState) {
            disPatchUserVisibleHint(true);
        } else if (!isVisibleToUser && currentVisibleState) {
            disPatchUserVisibleHint(false);
        }
    }

    private void disPatchUserVisibleHint(boolean isVisible) {
        // 如果当前的状态和传进来的状态相同时直接返回
        if (currentVisibleState == isVisible) {
            return;
        }
        currentVisibleState = isVisible;
        if (!isViewCreated) {
            // 只有view创建的时候才去分发事件
            return;
        }
        if (isVisible) {
            onFragmentLoad();
        } else {
            onFragmentLoadStop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 当从其他地方回来的时候，要加载数据加载数据
        if (!currentVisibleState && getUserVisibleHint()) {
            disPatchUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // 当从fragment调走的时候，不许停止加载数据
        if (currentVisibleState && getUserVisibleHint()) {
            disPatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 销毁的时候需要将其他状态置为初始值
        isViewCreated = false;
        currentVisibleState = false;
    }

    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected void onFragmentLoad() {
    }

    protected void onFragmentLoadStop() {
    }
}
