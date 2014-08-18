package com.crimsonwear.supporting;

import java.util.ArrayList;

/**
 * Created by Kevin Nottberg on 7/27/2014.
 */
public class DotBook {

    ArrayList<Dot> dotBook;

    public DotBook() {
        dotBook = new ArrayList<Dot>();
    }

    public void init() {
        dotBook = new ArrayList<Dot>();
    }

    public void addDot( Dot dot ) {
        dotBook.add( dot );
    }

    public String getId( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getid();
    }

    public int getSetcount( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getSetcount();
    }

    public int getSide( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getSide();
    }

    public int getHorizontal( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getHoriz();
    }

    public String getHorizontalDirection( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getHorizdir();
    }

    public int getHorizontalStep( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getHorizstep();
    }

    public String getVertical( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getVert();
    }

    public String getVerticalDirection( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getVertdir();
    }

    public int getVerticalStep( int currDot ) {
        Dot dot = new Dot();
        dot = dotBook.get( currDot );
        return dot.getVertstep();
    }

    public int size() {
        return dotBook.size();
    }
}
