package com.example.kevinnottberg.crimsonwearproj;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.ImageReference;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

/**
 * Created by Kevin Nottberg on 7/8/2014.
 */
class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;

    public SampleGridPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    /** A simple container for static data in each page */
    private static class Page {
        String titleRes;
        String textRes;
        int iconRes;
        int cardGravity = Gravity.BOTTOM;
        boolean expansionEnabled = true;
        float expansionFactor = 1.0f;
        int expansionDirection = CardFragment.EXPAND_DOWN;

        public Page(String titleRes, String textRes, boolean expansion) {
            this.titleRes = titleRes;
            this.textRes = textRes;
            this.expansionEnabled = expansion;
        }
    }

    private final Page[][] PAGES = {
            {
                    new Page("1", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true),
                    new Page("2", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true)
            },
            {
                    new Page("2", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true),
                    new Page("2", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true)
            },
            {
                    new Page("3", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true),
                    new Page("3", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true)
            },
            {
                    new Page("4", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true),
                    new Page("4", "3.0 stps in Side 1 45 yd line    3.0 stps in frnt of frnt hash", true)
            }
    };

    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        Log.d("FragmentManager", "getFragment hit: Column: " + col);
        Log.d("FragmentManager", "getFragment hit: Row: " + row);
        if(col == 1){
            Log.d("FragmentManager", "KeyPadFragment");
            KeyPadFragment fragment = new KeyPadFragment().newInstance();
            fragment = new KeyPadFragment();
            return fragment;
        }else {
            String title = page.titleRes;
            String text = page.textRes;
            CardFragment fragment = CardFragment.create(title, text, page.iconRes);
            // Advanced settings
            fragment.setCardGravity(page.cardGravity);
            fragment.setExpansionEnabled(page.expansionEnabled);
            fragment.setExpansionDirection(page.expansionDirection);
            fragment.setExpansionFactor(page.expansionFactor);
            return fragment;
        }
    }

    public ImageReference getBackground(int row, int column) {
        return ImageReference.forDrawable(R.drawable.crimsonlogo);
    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    @Override
    public int getColumnCount(int rowNum) {
        return PAGES[rowNum].length;
    }
}
