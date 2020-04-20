package com.niteroomcreation.scaffold.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.niteroomcreation.scaffold.utils.LocalLangHelper;
import com.niteroomcreation.scaffold.utils.LogHelper;
import com.niteroomcreation.scaffold.utils.PrefKeys;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by Septian Adi Wijaya on 04/09/19
 */
public abstract class BaseFragmentView extends Fragment implements IBaseView {

    private static final String TAG = BaseFragmentView.class.getSimpleName();

    private BaseView mActivity;

    private static String fragmentTitle;

    protected abstract int contentLayout();

    abstract protected void initComponents(View view);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = null;
        if (contentLayout() != ((BaseView) getActivity()).EMPTY_LAYOUT)
            view = getActivity().getLayoutInflater().inflate(contentLayout(), container, false);
        else
            throw new IllegalStateException("contentLayout() can't be EMPTY " + this.getClass().getSimpleName());
        ButterKnife.bind(this, view);
        initComponents(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = (BaseView) getActivity();
    }

    /**
     * Called when using API23 and above
     *
     * @param context fragment--activity's context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(LocalLangHelper.wrap(context
                , Prefs.getString(PrefKeys.KEY_LOCAL_LANG_SELECTED, Locale.getDefault()
                        .getLanguage())));

        LogHelper.e(TAG, Prefs.getString(PrefKeys.KEY_LOCAL_LANG_SELECTED, Locale.getDefault()
                .getLanguage()));

        if (context instanceof BaseView) {
            BaseView activity = (BaseView) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        this.mActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }

    public void setFragmentTitle(String fragmentTitle) {
        BaseFragmentView.fragmentTitle = fragmentTitle;
    }

    @Override
    public void showLoading() {
        mActivity.showLoading();
    }

    @Override
    public void hideLoading() {
        mActivity.hideLoading();
    }

    @Override
    public boolean isShownLoading() {
        return mActivity.isShownLoading();
    }

    @Override
    public void showMessage(String message) {
        mActivity.showMessage(message);
    }

    @Override
    public void showErrorMessage(int messageRes, int messageResAction) {
        mActivity.showErrorMessage(messageRes, messageResAction);
    }

    @Override
    public void showEmptyState() {
        mActivity.showEmptyState();
    }

    @Override
    public void hideEmptyState() {
        mActivity.hideEmptyState();
    }

    public interface BaseFragmentCallback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
