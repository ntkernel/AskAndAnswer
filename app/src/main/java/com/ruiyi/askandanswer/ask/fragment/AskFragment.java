package com.ruiyi.askandanswer.ask.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiyi.askandanswer.R;
import com.ruiyi.askandanswer.base.BaseFragment;

public class AskFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask, container, false);
        return view;

    }
}
