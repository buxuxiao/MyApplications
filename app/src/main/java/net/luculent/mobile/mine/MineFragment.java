package net.luculent.mobile.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * Created by hulifei on 2019/5/16.
 */

public class MineFragment extends Fragment{

    private Activity mActivity;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity=getActivity();
        rootView=inflater.inflate(R.layout.fragment_layout_mine,null);

        initView();
        initData();


        return rootView;
    }

    private void initView(){

    }

    private void initData(){

    }


}
