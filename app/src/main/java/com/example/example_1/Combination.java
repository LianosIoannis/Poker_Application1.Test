package com.example.example_1;

import android.util.Log;

public class Combination {

    private String name;
    private int value_of_comb;
    private int my_value;
    private Card[] card_comb= new Card[5];

    public Combination()
    {
    }


    public void setValue_of_comb(int v)
    {
        switch (v)
        {
            case 0 :
                this.name = "HIGH CARD : " + ch_of(card_comb[0].get_value()) + " " + ch_of(card_comb[1].get_value()) + " " + ch_of(card_comb[2].get_value()) + " " + ch_of(card_comb[3].get_value()) + " " + ch_of(card_comb[4].get_value());
                break;
            case 1:
                this.name = "ONE PAIR : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value()) + " " + ch_of(card_comb[2].get_value()) + " " + ch_of(card_comb[3].get_value()) + " " + ch_of(card_comb[4].get_value());
                break;
            case 2:
                this.name = "TWO PAIR : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value()) + " " + ch_of(card_comb[2].get_value()) + ch_of(card_comb[3].get_value()) + " " + ch_of(card_comb[4].get_value());
                break;
            case 3:
                this.name = "THREE OF A KIND : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value())  + ch_of(card_comb[2].get_value()) + " " + ch_of(card_comb[3].get_value()) + " " + ch_of(card_comb[4].get_value());
                break;
            case 4:
                this.name = "STRAIGHT : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value())  + ch_of(card_comb[2].get_value())  + ch_of(card_comb[3].get_value())  + ch_of(card_comb[4].get_value());
                break;
            case 5:
                this.name = "FLUSH : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value())  + ch_of(card_comb[2].get_value())  + ch_of(card_comb[3].get_value())  + ch_of(card_comb[4].get_value());
                break;
            case 6:
                this.name = "FULL HOUSE : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value())  + ch_of(card_comb[2].get_value()) + " " + ch_of(card_comb[3].get_value())  + ch_of(card_comb[4].get_value());
                break;
            case 7:
                this.name = "FOUR OF A KIND : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value())  + ch_of(card_comb[2].get_value())  + ch_of(card_comb[3].get_value()) + " " + ch_of(card_comb[4].get_value());
                break;
            case 8: this.name = "STRAIGHT FLUSH : " + ch_of(card_comb[0].get_value()) + ch_of(card_comb[1].get_value())  + ch_of(card_comb[2].get_value())  + ch_of(card_comb[3].get_value())  + ch_of(card_comb[4].get_value());
                break;
        }
        this.value_of_comb = v;
    }

    public void setCard_comb(Card[] c)
    {
        for (int i = 0; i < 5; i++)
        {
            card_comb[i] = new Card(c[i].get_value(), c[i].get_colour());
        }
    }

    public void setMy_value(int mv)
    {
        this.my_value = mv;
    }

    public String get_name()
    {
        return this.name;
    }

    public int getValue_of_comb()
    {
        return this.value_of_comb;
    }

    public Card[] getCard_comb()
    {
        return this.card_comb;
    }

    public int getmy_value()
    {
        return  this.my_value;
    }

    public char ch_of(int cardval)
    {
        char r = '0';
        switch (cardval) {
            case 0:
                r = 'A';
                break;
            case 1:
                r = '2';
                break;
            case 2:
                r = '3';
                break;
            case 3:
                r = '4';
                break;
            case 4:
                r = '5';
                break;
            case 5:
                r = '6';
                break;
            case 6:
                r = '7';
                break;
            case 7:
                r = '8';
                break;
            case 8:
                r = '9';
                break;
            case 9:
                r = 'T';
                break;
            case 10:
                r = 'J';
                break;
            case 11:
                r = 'Q';
                break;
            case 12:
                r = 'K';
                break;
            case 13:
                r = 'A';
                break;
        }

        return r;
    }

}
