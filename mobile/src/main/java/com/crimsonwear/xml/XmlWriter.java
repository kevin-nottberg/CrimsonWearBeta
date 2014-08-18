package com.crimsonwear.xml;

/**
 * Created by Kevin Nottberg on 7/27/2014.
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.content.Context;
import android.util.Log;

public class XmlWriter {

    Document doc;
    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    Element rootElement;

    public XmlWriter() {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void startNewDoc() {
        doc = docBuilder.newDocument();
        rootElement = doc.createElement( "bandutil" );
        doc.appendChild( rootElement );

    }

    public void addDot( String dotId, int setcount, int side, int horiz, String horizdir,
                        int horizstep, String vert, String vertdir, int vertstep ) {

        Element dotElement = doc.createElement( "dot" );
        dotElement.setAttribute( "id", dotId );
        rootElement.appendChild( dotElement );

        Element setcountElement = doc.createElement( "setcount" );
        setcountElement.appendChild( doc.createTextNode( Integer.toString( setcount ) ) );
        dotElement.appendChild( setcountElement );

        Element sideElement = doc.createElement( "side" );
        sideElement.appendChild( doc.createTextNode( Integer.toString( side ) ) );
        dotElement.appendChild( sideElement );

        Element horizElement = doc.createElement( "horiz" );
        horizElement.appendChild( doc.createTextNode( Integer.toString( horiz ) ) );
        dotElement.appendChild( horizElement );

        Element horizdirElement = doc.createElement( "horizdir" );
        horizdirElement.appendChild( doc.createTextNode( horizdir ) );
        dotElement.appendChild( horizdirElement );

        Element horizstepElement = doc.createElement( "horizstep" );
        horizstepElement.appendChild( doc.createTextNode( Integer.toString( horizstep ) ) );
        dotElement.appendChild( horizstepElement );

        Element vertElement = doc.createElement( "vert" );
        vertElement.appendChild( doc.createTextNode( vert ) );
        dotElement.appendChild( vertElement );

        Element vertdirElement = doc.createElement( "vertdir" );
        vertdirElement.appendChild( doc.createTextNode( vertdir ) );
        dotElement.appendChild( vertdirElement );

        Element vertstepElement = doc.createElement( "vertstep" );
        vertstepElement.appendChild( doc.createTextNode( Integer.toString( vertstep ) ) );
        dotElement.appendChild( vertstepElement );

    }


    public void finishAndWriteDoc( Context context, String path ) {
        // Write the new doc to the android assets.
        FileOutputStream fileOut = null;
        Transformer transformer = null;
        try {
            fileOut = context.openFileOutput( path, Context.MODE_WORLD_WRITEABLE );
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult streamResult = new StreamResult( fileOut );
        Log.d("driveTest", "Rewrite contents: " + fileOut.toString() );
        try {
            transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
