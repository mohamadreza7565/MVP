package com.ryfa.MVP.interfaces;

public interface BasePresenter <T extends BaseView> {

    void onAttach(T view);

    void onDetach();

}
