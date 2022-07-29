package com.example.example_1;

public class Card {

    private int value, colour;
    String[] Scolour = new String[4];
    String[] Svalue = new String[14];
    String Scol, Sval;


    public Card (int value, int colour)
    {
        this.value = value;
        this.colour = colour;

        Scolour[0] = "SPADES";
        Scolour[1] = "DIAMONDS";
        Scolour[2] = "HEARTS";
        Scolour[3] = "CLUBS";


        Svalue[0] = "ACE" ;
        Svalue[1] = "DEUCE" ;
        Svalue[2] = "THREE" ;
        Svalue[3] = "FOUR" ;
        Svalue[4] = "FIVE" ;
        Svalue[5] = "SIX" ;
        Svalue[6] = "SEVEN" ;
        Svalue[7] = "EIGHT" ;
        Svalue[8] = "NINE" ;
        Svalue[9] = "TEN" ;
        Svalue[10] = "JACK" ;
        Svalue[11] = "QUEEN" ;
        Svalue[12] = "KING" ;
        Svalue[13] = "ACE";


        this.Scol = Scolour[colour];
        this.Sval = Svalue[value];

    }

    public int get_value()
    {
        return this.value;
    }

    public int get_colour()
    {
        return this.colour;
    }

    public void setValue(int v)
    {
        this.value = v;
    }

    public void setColour(int c)
    {
        this.colour = c;
    }

    public String getSval()
    {
        return this.Sval;
    }

    public String getScol()
    {
        return this.Scol;
    }


    public String get_name()
    {
        String name = this.Sval + " of " + this.Scol;
        return name;
    }

}
















