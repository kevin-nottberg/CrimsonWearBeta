package com.example.kevinnottberg.crimsonwearproj;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {

    String currText;
    GridViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currText = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.d("FragmentManager", "Set content view");
        final Resources res = getResources();
        pager = (GridViewPager) findViewById(R.id.pager);
        Log.d("FragmentManager", "GridView Pager created");
        pager.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                // Adjust page margins:
                //   A little extra horizontal spacing between pages looks a bit
                //   less crowded on a round display.
                final boolean round = insets.isRound();
                int rowMargin = res.getDimensionPixelOffset(R.dimen.page_row_margin);
                int colMargin = res.getDimensionPixelOffset(round ?
                        R.dimen.page_column_margin_round : R.dimen.page_column_margin);
                pager.setPageMargins(rowMargin, colMargin);
                return insets;
            }
        });
        Log.d("FragmentManager", "Line before setting");
        pager.setAdapter(new SampleGridPagerAdapter(this, getFragmentManager()));
    }

    public void setCurrDot( String text ) {
        int dot = Integer.parseInt( text );
        pager.setCurrentItem( dot - 1, 0 );
    }
}
