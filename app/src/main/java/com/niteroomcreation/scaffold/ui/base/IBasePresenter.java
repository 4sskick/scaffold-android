package com.niteroomcreation.scaffold.ui.base;

/**
 * Created by Septian Adi Wijaya on 03/09/19
 */
public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

    void onViewInactive();
}
