package com.crimsonwear.xml;

/**
 * Created by Kevin Nottberg on 7/27/2014.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class XmlParser {

    private Document doc;
    Context context;

    public XmlParser( Context cont ) {
        context = cont;
    }

    public String getValue( Element item, String str ) {
        NodeList n = item.getElementsByTagName( str );
        return this.getElementValue( n.item( 0 ) );
    }

    public final String getElementValue( Node elem ) {
        Node child;
        if( elem != null ){
            if ( elem.hasChildNodes() ){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public Document getDomElement( String xmlPath ){
        try {
            FileInputStream file = new FileInputStream( new File( context.getFilesDir(), xmlPath ) );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse( file, null );

            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            Log.d("Exceptions", "ParserException");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Exceptions", "IOException");
            return null;
        } catch (SAXException e) {
            Log.d("Exceptions", "SaxExceptions");
            e.printStackTrace();
        }

        // return DOM
        return doc;
    }

    public String getStringFromDoc() {
        try {
            DOMSource domSource = new DOMSource( doc );
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult( writer );
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform( domSource, result );
            writer.flush();
            return writer.toString();
        }
        catch( TransformerException ex ) {
            ex.printStackTrace();
            return null;
        }
    }
}