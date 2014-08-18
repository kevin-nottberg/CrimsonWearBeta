package com.crimsonwear.supporting;

/**
 * Created by Kevin Nottberg on 7/27/2014.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.crimsonwear.xml.XmlParser;
import com.crimsonwear.xml.XmlWriter;

import android.content.Context;

public class DotBookParser {

    Context context;

    static final String DOT = "dot";
    static final String SETCOUNT = "setcount";
    static final String SIDE = "side";
    static final String HORIZONTAL = "horiz";
    static final String HORIZONTALDIRECTION = "horizdir";
    static final String HORIZONTALSTEP = "horizstep";
    static final String VERTICAL = "vert";
    static final String VERTICALDIRECTION = "vertdir";
    static final String VERTICALSTEP = "vertstep";
    static final String ID = "id";

    XmlParser parser;
    DotBook dotBook;
    String filename;

    public DotBookParser( Context cont, String fn ) {
        context = cont;
        filename = fn;
    }

    public void initRaw() {
        parser = new XmlParser( context );
        Document doc = parser.getDomElement( filename );
        NodeList dots = doc.getElementsByTagName( DOT );
        dotBook = new DotBook();
        for( int j = 0; j < dots.getLength(); j++ ){
            if ( dots.item( j ).getNodeType() == Node.ELEMENT_NODE ) {
                Dot dot = new Dot();
                Element el = (Element) dots.item( j );
                dot.setId( el.getAttribute( ID  ) );
                dot.setSetcount( Integer.parseInt( parser.getValue( el, SETCOUNT ) ) );
                dot.setSide( Integer.parseInt( parser.getValue( el, SIDE ) ) );
                dot.setHoriz( Integer.parseInt( parser.getValue( el, HORIZONTAL ) ) );
                dot.setHorizdir( parser.getValue( el, HORIZONTALDIRECTION ) );
                dot.setHorizstep( Integer.parseInt( parser.getValue( el, HORIZONTALSTEP ) ) );
                dot.setVert( parser.getValue( el, VERTICAL ) );
                dot.setVertdir( parser.getValue( el, VERTICALDIRECTION ) );
                dot.setVertstep( Integer.parseInt( parser.getValue( el, VERTICALSTEP ) ) );
                dotBook.addDot( dot );
            }
        }
    }

    public void addDot( String dotId, int setCount, int side, int horizontal, String horizDir,
                        int horizStep, String vertical, String vertDir, int vertStep ) {

        Dot dot = new Dot();
        dot.setId( dotId );
        dot.setSetcount( setCount );
        dot.setSide( side );
        dot.setHoriz( horizontal );
        dot.setHorizdir( horizDir );
        dot.setHorizstep( horizStep );
        dot.setVert( vertical );
        dot.setVertdir( vertDir );
        dot.setVertstep( vertStep );
        dotBook.addDot( dot );

    }

    public void write() {
        XmlWriter xmlWriter = new XmlWriter();
        xmlWriter.startNewDoc();

        for( int i = 0; i < dotBook.size(); i++ ) {
            xmlWriter.addDot( dotBook.getId( i ), dotBook.getSetcount( i ), dotBook.getSide( i ),
                                dotBook.getHorizontal( i ), dotBook.getHorizontalDirection( i ),
                                    dotBook.getHorizontalStep( i ), dotBook.getVertical( i ),
                                        dotBook.getVerticalDirection( i ), dotBook.getVerticalStep( i ));
        }

        xmlWriter.finishAndWriteDoc( context, filename );
    }
}
