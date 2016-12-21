package com.yitsol.quickmeal.fragment;

/**
 * Created by sai on 5/2/2016.
 */

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;
import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.adapter.CaldroidSampleCustomAdapter;


public class CaldroidSampleCustomFragment extends CaldroidFragment {


    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub
        return new CaldroidSampleCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
    }

    @Override
    protected int getGridViewRes() {
        return R.layout.calendar_grid_view;
    }
}