package com.example.kevinnottberg.crimsonwearproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crimsonwear.supporting.DotBookParser;
import com.crimsonwear.xml.XmlParser;
import com.example.kevinnottberg.crimsonwearproj.R;

import java.io.File;

public class AddDotAct extends Activity {

    Boolean isFile;
    String filename;

    String dotId;
    int setCount;
    int side;
    int horizontal;
    String horizDir;
    int horizStep;
    String vertical;
    String vertDir;
    int vertStep;

    EditText dotIdText;
    EditText setCountText;
    EditText sideText;
    EditText horizText;
    EditText horizdirText;
    EditText horizstepText;
    EditText vertText;
    EditText vertdirText;
    EditText vertstepText;

    Button doneAndAdd;
    Button cancelAdd;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_activity2 );

        filename = "mainFile.xml";
        isFile = new File( this.getFilesDir(), filename ).exists();

        dotIdText = (EditText) findViewById( R.id.idTextField );
        setCountText = (EditText) findViewById( R.id.setCountTextField );
        sideText = (EditText) findViewById( R.id.sideTextField );
        horizText = (EditText) findViewById( R.id.horizTextField );
        horizdirText = (EditText) findViewById( R.id.horizdirTextField );
        horizstepText = (EditText) findViewById( R.id.horizStepTextField );
        vertText = (EditText) findViewById( R.id.vertTextField );
        vertdirText = (EditText) findViewById( R.id.vertdirTextField );
        vertstepText = (EditText) findViewById( R.id.vertStepTextField );

        doneAndAdd = (Button) findViewById( R.id.doneButton );
        cancelAdd = (Button) findViewById( R.id.cancelButton );

        doneAndAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDot();
                addToXml();
            }
        });

        cancelAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainAct = new Intent( getApplicationContext(), MoblieMainAct.class );
                startActivity( mainAct );
            }
        });

    }

    public void addDot() {

        dotId = dotIdText.getText().toString();
        setCount = Integer.parseInt( setCountText.getText().toString() );
        side = Integer.parseInt( sideText.getText().toString() );
        horizontal = Integer.parseInt( horizText.getText().toString() );
        horizDir = horizdirText.getText().toString();
        horizStep = Integer.parseInt( horizstepText.getText().toString() );
        vertical = vertText.getText().toString();
        vertDir = vertdirText.getText().toString();
        vertStep = Integer.parseInt( vertstepText.getText().toString() );

    }

    public void addToXml() {
        DotBookParser dotbookparser = new DotBookParser( this, filename );
        dotbookparser.initRaw();
        dotbookparser.addDot( dotId, setCount, side, horizontal, horizDir, horizStep, vertical, vertDir, vertStep );
        dotbookparser.write();
    }
}
