package com.example.kevinnottberg.crimsonwearproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.crimsonwear.supporting.DotBookParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

import java.io.File;


public class MoblieMainAct extends Activity {

    Boolean isFile;
    String filename;
    DotBookParser dotbookparser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Check for if there is a xml file
        filename = "mainFile.xml";
        isFile = new File( this.getFilesDir(), filename ).exists();

        if ( isFile ) {
            dotbookparser = new DotBookParser( this, filename );
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_dot:
                addDots();
                return true;
            case R.id.sync_dots:
                //Do the Api data layer sync process
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addDots() {
        Intent dots = new Intent(getApplicationContext(), AddDotAct.class);
        startActivity(dots);
    }
}
