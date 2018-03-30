package com.example.houyongliang.typesviews;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;


import com.example.houyongliang.BaseContract;
import com.example.houyongliang.typesviews.viewz.LoadingAndRetryLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyongliang on 2018/3/13 16:16.
 * Function(功能):
 */

public class MultiStateManger {
    private Context mContext;
    private TheListener listener;
    public SimpleMultiStateView simpleMultiStateView;

    //    /*单例模式*/
//    private MultiStateManger() {
//    }
//
//    private static class Bulid {
//        private static final MultiStateManger single = new MultiStateManger();
//    }
//
//    public static MultiStateManger getInstance() {
//        return MultiStateManger.Bulid.single;
//    }
    public MultiStateManger(BaseContract.BaseView view, Object activityOrFragment, TheListener listener) {

        this.listener = listener;
        ViewGroup contentParent = null;
        Context context;
        if (view.BindView() == null) {//默认不绑定view
            if (activityOrFragment instanceof Activity) {
                Activity activity = (Activity) activityOrFragment;
                context = activity;
                contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
            } else if (activityOrFragment instanceof Fragment) {
                Fragment fragment = (Fragment) activityOrFragment;
                context = fragment.getActivity();
                contentParent = (ViewGroup) (fragment.getView().getParent());
            } else {
                throw new IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)");
            }

        } else {/*定义绑定view*/

            View view1 = view.BindView();
            contentParent = (ViewGroup) (view1.getParent());
            context = view1.getContext();
        }

         /*获取 子控件个数*/
        int childCount = contentParent.getChildCount();
        //get contentParent
        int index = 0;
        View oldContent;
        if (view.BindView() != null) {
            oldContent = (View) view.BindView();
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);
        //setup content layout
        SimpleMultiStateView simpleView = new SimpleMultiStateView(context);

        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        contentParent.addView(simpleView, index, lp);
        simpleView.addView(oldContent);
//        // setup loading,retry,empty layout
//        setupLoadingLayout(listener, loadingAndRetryLayout);
//        setupRetryLayout(listener, loadingAndRetryLayout);
//        setupEmptyLayout(listener, loadingAndRetryLayout);
//        //callback
//        listener.setRetryEvent(loadingAndRetryLayout.getRetryView());
//        listener.setLoadingEvent(loadingAndRetryLayout.getLoadingView());
//        listener.setEmptyEvent(loadingAndRetryLayout.getEmptyView());
        simpleMultiStateView = simpleView;

    }
    public SimpleMultiStateView getSimpleMultiStateView(){
        return simpleMultiStateView;
    }

}
