package com.crimsonwear.supporting;

/**
 * Created by Kevin Nottberg on 7/27/2014.
 */
public class Dot {

    private String id;
    private int setcount;
    private int side;
    private int horiz;
    private String horizdir;
    private int horizstep;
    private String vert;
    private String vertdir;
    private int vertstep;

    public String getid() {
        return id;
    }

    public void setId( String i ) {
        id = i;
    }

    public int getSetcount() {
        return setcount;
    }

    public void setSetcount( int count ) {
        setcount = count;
    }

    public int getSide() {
        return side;
    }

    public void setSide( int s ) {
        side = s;
    }

    public int getHoriz() {
        return horiz;
    }

    public void setHoriz( int h ) {
        horiz = h;
    }

    public String getHorizdir() {
        return horizdir;
    }

    public void setHorizdir( String dir ) {
        horizdir = dir;
    }

    public int getHorizstep() {
        return horizstep;
    }

    public void setHorizstep( int num ) {
        horizstep = num;
    }

    public String getVert()  {
        return vert;
    }

    public void setVert( String v ) {
        vert = v;
    }

    public String getVertdir() {
        return vertdir;
    }

    public void setVertdir( String dir ) {
        vertdir = dir;
    }

    public int getVertstep() {
        return vertstep;
    }

    public void setVertstep( int s ) {
        vertstep = s;
    }
}
