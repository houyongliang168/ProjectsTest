package com.example.houyongliang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.houyongliang.typesviews.MultiStateManger;
import com.example.houyongliang.typesviews.MultiStateView;
import com.example.houyongliang.typesviews.R;
import com.example.houyongliang.typesviews.SimpleMultiStateView;

/**
 * 测试不同的 View 展示内容
 */
public class Main2Activity extends AppCompatActivity implements BaseContract.BaseView {
    protected View mRootView;/*获取根view*/
    private SimpleMultiStateView mSimpleMultiStateView;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = createView(null, null, savedInstanceState);
        View view = getLayoutInflater().inflate(getContentLayout(), null);
        setContentView(mRootView);
        tv = (TextView) findViewById(R.id.tv);
//        SimpleMultiStateView.
        MultiStateManger manger = new MultiStateManger(this, this, null);
        mSimpleMultiStateView = manger.getSimpleMultiStateView();
        mSimpleMultiStateView.validContentView(tv);
        mSimpleMultiStateView
                .setEmptyResource(R.layout.base_empty)
                .setRetryResource(R.layout.base_retry)
                .setLoadingResource(R.layout.base_loading)
                .setNoNetResource(R.layout.base_empty)
                .build()
                .setonReLoadlistener(new MultiStateView.onReLoadlistener() {
                    @Override
                    public void onReload() {
                        onRetry();
                    }
                });

        showLoading();
    }


    public int getContentLayout() {
        return R.layout.activity_main2;
    }

    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(getContentLayout(), container);

        return view;
    }

    @Override
    public void showLoading() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showLoadingView();
        }

    }

    @Override
    public void showSuccess() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showContent();
        }
    }

    @Override
    public void showFaild() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showErrorView();
        }
    }

    @Override
    public void showNoNet() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showNoNetView();
        }
    }

    @Override
    public void onRetry() {

    }


    @Override
    public View BindView() {
//      return tv;

        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main2;
    }
}
