package com.yitsol.quickmeal.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.yitsol.quickmeal.R;


/**
 * Created by RAMU on 09-09-2015.
 */
public class CaldroidCustomfragment extends CaldroidFragment {
    TextView textView;
    GridView gv;
    Button rg, lg;
    int theme;
    View view;
    RelativeLayout rl;

    /* @Override
     public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year)
     {
         // TODO Auto-generated method stub
         return new CustomCaldroidAdapter(getActivity(), month, year,
                 getCaldroidData(), extraData);
     }*/
    @Override
    protected int getGridViewRes() {
        return R.layout.your_custom_grid_fragment;
    }


}