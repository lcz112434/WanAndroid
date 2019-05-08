package com.lcz.wanandroid.fragment.setting;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcz.wanandroid.R;
import com.lcz.wanandroid.base.Check;
import com.lcz.wanandroid.utils.ACache;
import com.lcz.wanandroid.utils.ChekedUtils;
import com.lcz.wanandroid.utils.Constants;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = "SettingFragment";
    private View view;
    private CheckBox mCheckboxSett1;
    private CheckBox mCheckboxSett2;
    private CheckBox mCheckboxSett3;
    public boolean mCheceke;
    private Check mCheck;
    /**
     * 106.81KB
     */
    private TextView mTvCache;
    private LinearLayout mClearLl;
    private File cacheFile;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mCheckboxSett1 = (CheckBox) inflate.findViewById(R.id.checkbox_sett1);
        mCheckboxSett2 = (CheckBox) inflate.findViewById(R.id.checkbox_sett2);
        mCheckboxSett3 = (CheckBox) inflate.findViewById(R.id.checkbox_sett3);

        mCheckboxSett2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences none = getActivity().getSharedPreferences("none", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = none.edit();
                if (isChecked) {
                    edit.putBoolean("checeke", isChecked);
                    mCheck = new Check();
                    mCheck.setId(null);
                    mCheck.setCheckle(isChecked);
                    ChekedUtils.getmMyUtils().insert(mCheck);
//                    Log.d(TAG, "mCheckboxSett2 onCheckedChanged:      "+"yes");
                } else {
                    edit.putBoolean("checeke", isChecked);
                    mCheck.setId(null);
                    mCheck.setCheckle(isChecked);
                    ChekedUtils.getmMyUtils().UpData(mCheck);
//                    Log.d(TAG, "mCheckboxSett2 onCheckedChanged:      "+"no");
                }
                edit.commit();
            }
        });

        SharedPreferences none = getActivity().getSharedPreferences("none", Context.MODE_PRIVATE);
        mCheceke = none.getBoolean("checeke", false);
        Log.d(TAG, "initView: " + mCheceke);


        if (mCheceke) {

            mCheckboxSett2.setChecked(true);
        } else {
            mCheckboxSett2.setChecked(false);
        }


        mCheckboxSett3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    Log.d(TAG, "mCheckboxSett3 onCheckedChanged:      "+"yes");
                } else {
//                    Log.d(TAG, "mCheckboxSett3 onCheckedChanged:      "+"no");
                }
            }
        });

//        cacheFile = new File(Constants.PATH_CACHE);
        mTvCache = (TextView) inflate.findViewById(R.id.tv_cache);
//        mTvCache.setText(ACache.getCacheSize(cacheFile));
        mClearLl = (LinearLayout) inflate.findViewById(R.id.clear_ll);
//        mClearLl.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.clear_ll:
//                clearCache();
                break;
        }
    }

//    private void clearCache() {
//        ACache.deleteDir(cacheFile);
//        mTvCache.setText(ACache.getCacheSize(cacheFile));
//    }
}
