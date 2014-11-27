package com.example.kevinnottberg.crimsonwearproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.crimsonwear.supporting.DotBook;
import com.crimsonwear.supporting.DotBookParser;
import com.crimsonwear.xml.XmlParser;
import com.crimsonwear.xml.XmlWriter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;


public class MoblieMainAct extends Activity {

    Boolean isFile;
    String filename;
    DotBookParser dotbookparser;
    DotBook dotBook;
    ArrayList<String> listItems;
    int currDot;
    String title;
    String sideText;
    String frontText;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Check for if there is a xml file

        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Log.d("mobliemain", "onConnected: " + connectionHint);
                        // Now you can use the data layer API
                    }
                    @Override
                    public void onConnectionSuspended(int cause) {
                        Log.d("mobliemain", "onConnectionSuspended: " + cause);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.d("mobliemain", "onConnectionFailed: " + result);
                    }
                })
                        // Request access only to the Wearable API
                .addApi(Wearable.API)
                .build();

        listItems = new ArrayList<String>();
        filename = "mainFile.xml";
        isFile = new File( this.getFilesDir(), filename ).exists();

        ListView listView = (ListView) findViewById( R.id.listView3 );

        Log.d("mobliemain", "checked file 1 ");
        if ( isFile == true ) {
            Log.d("mobliemain", "checked file 1.5 ");
            dotbookparser = new DotBookParser( this, filename );
            Log.d("mobliemain", "checked file 2 ");
            dotbookparser.initRaw();

            Log.d("mobliemain", "checked file 3 ");

            dotBook = dotbookparser.getDotBook();

            for ( int i = 0; i < dotBook.size(); i++ ) {
                listItems.add( dotBook.getId( i ) );
                Log.d("mobliemain", "checked file 4 ");
            }


            Log.d("mobliemain", "checked file 5 ");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, listItems);

            Log.d("mobliemain", "checked file 6 ");

            listView.setAdapter( adapter );

            Log.d("mobliemain", "checked file 7 ");

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Start that allows you to view and edit the dot and add notes
                    currDot = position;
                    setContentView( R.layout.dot_layout );

                    title =
                        "Set: " + dotBook.getId( currDot ) + " Count: " + dotBook.getSetcount( currDot );
                    sideText =
                        "Side " + dotBook.getSide( currDot ) + ": " + dotBook.getHorizontalStep( currDot ) + " stps " + dotBook.getHorizontalDirection( currDot ) + " " + dotBook.getHorizontal( currDot );
                    frontText =
                        "" + dotBook.getVerticalStep( currDot ) + " stps " + dotBook.getVerticalDirection( currDot ) + " of " + dotBook.getVertical( currDot );

                    final TextView dotTitle = (TextView) findViewById( R.id.dotTitle );
                    final TextView sideXSide = (TextView) findViewById( R.id.SideXSide );
                    final TextView frontToBack = (TextView) findViewById( R.id.FrontToBack );

                    dotTitle.setText( title );
                    sideXSide.setText( sideText );
                    frontToBack.setText( frontText );

                    Button backButton = (Button) findViewById( R.id.BackButton );
                    Button editButton = (Button) findViewById( R.id.editButton );
                    Button nextButton = (Button) findViewById( R.id.NextButton );

                    backButton.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currDot = currDot - 1;

                            title =
                                "Set: " + dotBook.getId( currDot ) + " Count: " + dotBook.getSetcount( currDot );
                            sideText =
                                "Side " + dotBook.getSide( currDot ) + ": " + dotBook.getHorizontalStep( currDot ) + " stps " + dotBook.getHorizontalDirection( currDot ) + " " + dotBook.getHorizontal( currDot );
                            frontText =
                                "" + dotBook.getVerticalStep( currDot ) + " stps " + dotBook.getVerticalDirection( currDot ) + " of " + dotBook.getVertical( currDot );

                            dotTitle.setText( title );
                            sideXSide.setText( sideText );
                            frontToBack.setText( frontText );
                        }
                    });

                    editButton.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                    nextButton.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currDot = currDot + 1;

                            String title =
                                    "Set: " + dotBook.getId( currDot ) + " Count: " + dotBook.getSetcount( currDot );
                            String sideText =
                                    "Side " + dotBook.getSide( currDot ) + ": " + dotBook.getHorizontalStep( currDot ) + " stps " + dotBook.getHorizontalDirection( currDot ) + " " + dotBook.getHorizontal( currDot );
                            String frontText =
                                    "" + dotBook.getVerticalStep( currDot ) + " stps " + dotBook.getVerticalDirection( currDot ) + " of " + dotBook.getVertical( currDot );

                            dotTitle.setText( title );
                            sideXSide.setText( sideText );
                            frontToBack.setText( frontText );
                        }
                    });
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.my, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch ( item.getItemId() ){
            case R.id.add_dot:
                addDots();
                return true;
            case R.id.sync_dots:
                //Do the Api data layer sync process
                XmlParser xmlParser = new XmlParser( this );
                xmlParser.getDomElement( filename );
                String xmlFile;
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }

    public void addDots() {
        Intent dots = new Intent( getApplicationContext(), AddDotAct.class );
        startActivity( dots );
    }
}
