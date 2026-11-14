package com.example.example_1;



import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

    ArrayList<Integer> brd;
    ArrayList<Integer> temp;
    int[][] test;
    String[][] Stest;
    int[] ml;
    int[] ml_fact;
    int mf;
    Combination board_comb;
    int num_of_combs;


    public Board()
    {

    }

    public Board(ArrayList<String> comb_text)
    {

        num_of_combs = fact_of(comb_text.size());
        ml = new int[comb_text.size()];
        ml_fact = new int[comb_text.size()];
        brd = new ArrayList<Integer>();
        temp = new ArrayList<Integer>();
        board_comb = new Combination();


        ArrayList<Integer> deck = new ArrayList<Integer>();
        for (int i = 0; i < comb_text.size(); i++) deck.add(i+1);

        for (int i = 0; i < comb_text.size(); i++)
        {
            this.brd.add(deck.get(i));
            this.temp.add(deck.get(i));
        }

        this.test = new int[fact_of(comb_text.size())][comb_text.size()];
        this.Stest = new String[fact_of(comb_text.size())][comb_text.size()];
        this.mf = 0;

        for (int i = 0; i < fact_of(brd.size()); i++) for (int j = 0; j < brd.size(); j++) this.test[i][j] = 0;
        for (int i = 0; i < fact_of(brd.size()); i++) for (int j = 0; j < brd.size(); j++) this.Stest[i][j] = "";

        for (int i = 0; i < deck.size() - 1; i ++) this.ml[i] = 0;

        int q = deck.size() - 1;
        for (int i = 1; i < deck.size() - 1; i ++)
        {
            ml_fact[i] = fact_of(q);
            q--;
        }

        rec(0);

        for (int i = 0; i < fact_of(deck.size()); i++)
        {
            for (int j = 0; j < deck.size(); j++)
            {
                Stest[i][j] = comb_text.get(test[i][j] - 1);
            }
        }

        find_comb();

    }

    public void setBoardArray(ArrayList<String> comb_text)
    {
        num_of_combs = fact_of(comb_text.size());
        ml = new int[comb_text.size()];
        ml_fact = new int[comb_text.size()];
        brd = new ArrayList<Integer>();
        temp = new ArrayList<Integer>();
        board_comb = new Combination();


        ArrayList<Integer> deck = new ArrayList<Integer>();
        for (int i = 0; i < comb_text.size(); i++) deck.add(i+1);

        for (int i = 0; i < comb_text.size(); i++)
        {
            this.brd.add(deck.get(i));
            this.temp.add(deck.get(i));
        }

        this.test = new int[fact_of(comb_text.size())][comb_text.size()];
        this.Stest = new String[fact_of(comb_text.size())][comb_text.size()];
        this.mf = 0;

        for (int i = 0; i < fact_of(brd.size()); i++) for (int j = 0; j < brd.size(); j++) this.test[i][j] = 0;
        for (int i = 0; i < fact_of(brd.size()); i++) for (int j = 0; j < brd.size(); j++) this.Stest[i][j] = "";

        for (int i = 0; i < deck.size() - 1; i ++) this.ml[i] = 0;

        int q = deck.size() - 1;
        for (int i = 1; i < deck.size() - 1; i ++)
        {
            ml_fact[i] = fact_of(q);
            q--;
        }

        rec(0);

        for (int i = 0; i < fact_of(deck.size()); i++)
        {
            for (int j = 0; j < deck.size(); j++)
            {
                Stest[i][j] = comb_text.get(test[i][j] - 1);
            }
        }

        find_comb();
    }

    public int[][] Comb_array()
    {
        //for (int i = 0; i < fact_of(brd.size()); i++) for (int j = 0; j < brd.size(); j++) this.test[i][j] = 0;

        //rec(0);

        return this.test;
    }

    public String[][] comb_texture()
    {
        //for (int i = 0; i < fact_of(brd.size()); i++) for (int j = 0; j < brd.size(); j++) this.Stest[i][j] = "";

        return Stest;
    }

    public int fact_of(int x)
    {
        int r = 1;
        for (int i = 2; i < x+1; i++) r*=i;
        return r;
    }

    public void rec(int index)
    {
        for (int i = index; i < this.brd.size(); i++)
        {
            if (i-index != 0 )
            {
                this.temp.clear();

                for (int iel = 0; iel < this.brd.size(); iel++) this.temp.add(this.test[ml[index]][iel]);

                for (int k = index + 1; k < this.brd.size()-1; k++) ml[k]+=ml_fact[k];

                Collections.swap(this.temp, index, i);
            }

            if (index == this.brd.size() - 2)
            {
                for (int m = 0; m < this.temp.size(); m++) this.test[mf][m] = this.temp.get(m);
                //Log.d("FLOP", Integer.toString(this.mf) + ": " + Integer.toString(this.test[mf][0]) + " " + Integer.toString(this.test[mf][1]) + " " + Integer.toString(this.test[mf][2]) + " " + Integer.toString(this.test[mf][3])+ " " + Integer.toString(this.test[mf][4]));
                this.mf++;
                //break;
            }

            rec(index + 1);
        }
    }

    public String Brd_Text_Combination()
    {
        //for (int i = 0; i < num_of_combs; i++)  Log.d("FLOP", Integer.toString(i) + " : " + Stest[i][0] + " " + Stest[i][1] + " " + Stest[i][2] + " " + Stest[i][3] + " " + Stest[i][4]);
        //Log.d("FLOP", "THE COMBINATION IS: " + board_comb.get_name());
        //for (int i = 0;i < 5; i++) Log.d("FLOP", "Brd_Text_Combination: " + Integer.toString(board_comb.getCard_comb()[i].get_value()));
        return board_comb.get_name();
    }

    public Card[] getBest_comb()
    {
        return board_comb.getCard_comb();
    }


    private Card Str_to_Card(String text_card)
    {
        Card the_card;

        int val = 0;
        int col = 0;

        String[] txt = text_card.split(" ");

        switch (txt[0])
        {
            case "ACE":
                val = 0;
                break;
            case "DEUCE":
                val = 1;
                break;
            case "THREE":
                val = 2;
                break;
            case "FOUR":
                val = 3;
                break;
            case "FIVE":
                val = 4;
                break;
            case "SIX":
                val = 5;
                break;
            case "SEVEN":
                val = 6;
                break;
            case "EIGHT":
                val = 7;
                break;
            case "NINE":
                val = 8;
                break;
            case "TEN":
                val = 9;
                break;
            case "JACK":
                val = 10;
                break;
            case "QUEEN":
                val = 11;
                break;
            case "KING":
                val = 12;
                break;
        }

        switch (txt[2])
        {
            case "SPADES" :
                col = 0;
                break;
            case "DIAMONDS" :
                col = 1;
                break;
            case "HEARTS" :
                col = 2;
                break;
            case "CLUBS" :
                col = 3;
                break;
        }

        the_card = new Card(val, col);

        return the_card;
    }

    public void find_comb()
    {
        check_for_StraightFlush();
        if (board_comb.getmy_value() == 0) check_for_kare();
        if (board_comb.getmy_value() == 0) check_for_FullHouse();
        if (board_comb.getmy_value() == 0) check_for_flush();
        if (board_comb.getmy_value() == 0) check_for_straight();
        if (board_comb.getmy_value() == 0) check_for_threeOfakind();
        if (board_comb.getmy_value() == 0) check_for_twoPair();
        if (board_comb.getmy_value() == 0) check_for_onePair();
        if (board_comb.getmy_value() == 0) check_for_highCard();
    }

    private void setBoard_comb(int voc, int v, Card[] c)
    {
        this.board_comb.setCard_comb(c);
        this.board_comb.setMy_value(v);
        this.board_comb.setValue_of_comb(voc);
    }


    public void check_for_StraightFlush()
    {

        boolean Straight_flush = false;

        boolean flush = true;
        boolean straight = true;
        int value = 0;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);


        for (int k = 0; k < num_of_combs; k++)
        {

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            Straight_flush = false;
            straight = true;
            flush = true;
            value = 0;


            for (int i = 0;i < 4; i++) if (card_comb[0].get_colour() != card_comb[i+1].get_colour()) flush = false;

            for (int i = 0;i < 4; i++) if ( (card_comb[0].get_value() != card_comb[i+1].get_value() - (i+1))) straight = false;

            if (card_comb[4].get_value() == 0)
            {
                straight = true;
                for (int i = 0;i < 3; i++) if ( (card_comb[0].get_value() != card_comb[i+1].get_value() - (i+1)) || card_comb[0].get_value() != 9) straight = false;
            }

            if (straight && flush && card_comb[4].get_value() == 0)
            {
                Straight_flush = true;
                value = 13;
            }
            else if (straight && flush)
            {
                Straight_flush = true;
                value = card_comb[4].get_value();
            }

            if (Straight_flush && value > board_comb.getmy_value()) setBoard_comb(8,value,card_comb);

        }
    }

    public void check_for_kare()
    {
        boolean kare = true;
        int value = 0;
        int valuek = 0;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);

        for (int k = 0; k < num_of_combs; k++)
        {

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            for (int i = 0; i < 5; i++)
            {
                if (card_comb[i].get_value() == 0)
                {
                    card_comb[i].setValue(13);
                }
            }

            kare = true;
            value = 0;
            valuek = 0;

            for (int i = 0;i < 3; i++) if (card_comb[0].get_value() != card_comb[i+1].get_value()) kare = false;

            if (kare)
            {
                value = card_comb[0].get_value();
                valuek = card_comb[4].get_value();
            }

            if (kare && value > board_comb.getmy_value())
            {
                setBoard_comb(7,value,card_comb);
            }
            else if (kare && value == board_comb.getmy_value() && valuek > board_comb.getCard_comb()[4].get_value())
            {
                setBoard_comb(7,value,card_comb);
            }

        }
    }

    public void check_for_FullHouse()
    {
        boolean full = false;

        boolean three_same = true;
        boolean two_same = true;
        int value1, value2;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);

        for (int k = 0; k < num_of_combs; k++)
        {

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            for (int i = 0; i < 5; i++)
            {
                if (card_comb[i].get_value() == 0)
                {
                    card_comb[i].setValue(13);
                }
            }

            full = false;
            three_same = true;
            two_same = true;
            value1 = 0;
            value2 = 0;

            for (int i = 0;i < 2; i++) if (card_comb[0].get_value() != card_comb[i+1].get_value()) three_same = false;
            for (int i = 3;i < 4; i++) if (card_comb[3].get_value() != card_comb[i+1].get_value()) two_same = false;

            if (three_same && two_same)
            {
                full = true;
                value1 = card_comb[0].get_value();
                value2 = card_comb[3].get_value();
            }


            if (full && value1 > board_comb.getmy_value())
            {
                setBoard_comb(6,value1,card_comb);
            }
            else if (full && value1 == board_comb.getmy_value() && value2 > board_comb.getCard_comb()[3].get_value())
            {
                setBoard_comb(6,value1,card_comb);
            }

        }
    }

    public void check_for_flush()
    {
        int [] value = new int[5];
        boolean flush = true;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);

        for (int k = 0; k < num_of_combs; k++)
        {
            for (int i = 0; i < 5; i++) value[i] = 0;
            flush = true;

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            for (int i = 0; i < 5; i++)
            {
                if (card_comb[i].get_value() == 0)
                {
                    card_comb[i].setValue(13);
                }
            }

            for (int i = 0;i < 4; i++) if (card_comb[0].get_colour() != card_comb[i+1].get_colour()) flush = false;

            if (flush) for (int i = 0; i < 5; i++) value[i] = card_comb[i].get_value();

            if (flush && value[0] > board_comb.getmy_value())
            {
                setBoard_comb(5,value[0],card_comb);
            }
            else if (flush && value[0] == board_comb.getmy_value() && value[1] > board_comb.getCard_comb()[1].get_value())
            {
                setBoard_comb(5,value[0],card_comb);
            }
            else if (flush && value[0] == board_comb.getmy_value() && value[1] == board_comb.getCard_comb()[1].get_value() && value[2] > board_comb.getCard_comb()[2].get_value())
            {
                setBoard_comb(5,value[0],card_comb);
            }
            else if (flush && value[0] == board_comb.getmy_value() && value[1] == board_comb.getCard_comb()[1].get_value() && value[2] == board_comb.getCard_comb()[2].get_value() && value[3] > board_comb.getCard_comb()[3].get_value())
            {
                setBoard_comb(5,value[0],card_comb);
            }
            else if (flush && value[0] == board_comb.getmy_value() && value[1] == board_comb.getCard_comb()[1].get_value() && value[2] == board_comb.getCard_comb()[2].get_value() && value[3] == board_comb.getCard_comb()[3].get_value() && value[4] > board_comb.getCard_comb()[4].get_value())
            {
                setBoard_comb(5,value[0],card_comb);
            }

        }

    }

    public void check_for_straight()
    {

        boolean straight = true;
        int value = 0;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);

        for (int k = 0; k < num_of_combs; k++)
        {

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            straight = true;
            value = 0;

            for (int i = 0;i < 4; i++) if ( (card_comb[0].get_value() != card_comb[i+1].get_value() - (i+1))) straight = false;

            if (card_comb[4].get_value() == 0)
            {
                straight = true;
                for (int i = 0;i < 3; i++) if ( (card_comb[0].get_value() != card_comb[i+1].get_value() - (i+1)) || card_comb[0].get_value() != 9) straight = false;
            }

            if (straight && card_comb[4].get_value() == 0) value = 13;
            else if (straight) value = card_comb[4].get_value();

            if (straight && value > board_comb.getmy_value()) setBoard_comb(4,value,card_comb);

        }

    }

    public  void check_for_threeOfakind()
    {
        boolean three = true;
        int value = 0;
        int valuek1,valuek2;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);

        for (int k = 0; k < num_of_combs; k++)
        {

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            for (int i = 0; i < 5; i++)
            {
                if (card_comb[i].get_value() == 0)
                {
                    card_comb[i].setValue(13);
                }
            }

            three = true;
            value = 0;
            valuek1 = 0;
            valuek2 = 0;

            for (int i = 0;i < 2; i++) if (card_comb[0].get_value() != card_comb[i+1].get_value()) three = false;

            if (three)
            {
                value = card_comb[0].get_value();
                valuek1 = card_comb[3].get_value();
                valuek2 = card_comb[4].get_value();
            }


            if (three && value > board_comb.getmy_value())
            {
                setBoard_comb(3,value,card_comb);
            }
            else if (three && value == board_comb.getmy_value() && valuek1 > board_comb.getCard_comb()[3].get_value())
            {
                setBoard_comb(3,value,card_comb);
            }
            else if (three && value == board_comb.getmy_value() && valuek1 == board_comb.getCard_comb()[3].get_value() && valuek2 > board_comb.getCard_comb()[4].get_value())
            {
                setBoard_comb(3,value,card_comb);
            }

        }
    }

    public void check_for_twoPair()
    {
        boolean twopair = false;

        boolean two_same1 = true;
        boolean two_same2 = true;
        int value1, value2,valuek;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);

        for (int k = 0; k < num_of_combs; k++)
        {

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            for (int i = 0; i < 5; i++)
            {
                if (card_comb[i].get_value() == 0)
                {
                    card_comb[i].setValue(13);
                }
            }

            twopair = false;
            two_same1 = true;
            two_same2 = true;
            value1 = 0;
            value2 = 0;
            valuek = 0;

            for (int i = 0;i < 1; i++) if (card_comb[0].get_value() != card_comb[i+1].get_value()) two_same1 = false;
            for (int i = 2;i < 3; i++) if (card_comb[2].get_value() != card_comb[i+1].get_value()) two_same2 = false;

            if (two_same1 && two_same2)
            {
                twopair = true;
                valuek = card_comb[4].get_value();
                value1 = card_comb[0].get_value();
                value2 = card_comb[3].get_value();
            }


            if (twopair && value1 > board_comb.getmy_value())
            {
                setBoard_comb(2,value1,card_comb);
            }
            else if (twopair && value1 == board_comb.getmy_value() && value2 > board_comb.getCard_comb()[2].get_value())
            {
                setBoard_comb(2,value1,card_comb);
            }
            else if (twopair && value1 == board_comb.getmy_value() && value2 == board_comb.getCard_comb()[2].get_value() && valuek > board_comb.getCard_comb()[4].get_value())
            {
                setBoard_comb(2,value1,card_comb);
            }

        }
    }

    public void check_for_onePair()
    {
        boolean onepair = true;
        int value = 0;
        int valuek1, valuek2, valuek3;

        Card[] card_comb = new Card[5];
        for (int i = 0;i < 5; i++) card_comb[i] = new Card(0,0);

        for (int k = 0; k < num_of_combs; k++)
        {

            for (int i = 0;i < 5; i++)
            {
                card_comb[i].setValue(Str_to_Card(Stest[k][i]).get_value());
                card_comb[i].setColour(Str_to_Card(Stest[k][i]).get_colour());
            }

            for (int i = 0; i < 5; i++)
            {
                if (card_comb[i].get_value() == 0)
                {
                    card_comb[i].setValue(13);
                }
            }

            onepair = true;
            value = 0;
            valuek1 = 0;
            valuek2 = 0;
            valuek3 = 0;

            for (int i = 0;i < 1; i++) if (card_comb[0].get_value() != card_comb[i+1].get_value()) onepair = false;

            if (onepair)
            {
                valuek1 = card_comb[2].get_value();
                valuek2 = card_comb[3].get_value();
                valuek3 = card_comb[4].get_value();

                value = card_comb[0].get_value();
            }


            if (onepair && value > board_comb.getmy_value())
            {
                setBoard_comb(1,value,card_comb);
            }
            else if (onepair && value == board_comb.getmy_value() && valuek1 > board_comb.getCard_comb()[2].get_value())
            {
                setBoard_comb(1,value,card_comb);
            }
            else if (onepair && value == board_comb.getmy_value() && valuek1 == board_comb.getCard_comb()[2].get_value() && valuek2 > board_comb.getCard_comb()[3].get_value())
            {
                setBoard_comb(1,value,card_comb);
            }
            else if (onepair && value == board_comb.getmy_value() && valuek1 == board_comb.getCard_comb()[2].get_value() && valuek2 == board_comb.getCard_comb()[3].get_value() && valuek3 > board_comb.getCard_comb()[4].get_value())
            {
                setBoard_comb(1,value,card_comb);
            }

        }
    }

    public void check_for_highCard()
    {
        Integer[] value = new Integer[brd.size()];

        Card[] card_comb = new Card[brd.size()];
        for (int i = 0;i < brd.size(); i++) card_comb[i] = new Card(0,0);
        for (int i = 0;i < brd.size(); i++)
        {
            card_comb[i].setValue(Str_to_Card(Stest[0][i]).get_value());
            card_comb[i].setColour(Str_to_Card(Stest[0][i]).get_colour());
        }

        for (int i = 0; i < brd.size(); i++)
        {
            if (card_comb[i].get_value() == 0)
            {
                card_comb[i].setValue(13);
            }
        }


        for (int i = 0; i < brd.size(); i++) value[i] = card_comb[i].get_value();

        Arrays.sort(value, Collections.reverseOrder());

        Card[] sorted_card_comb = new Card[brd.size()];

        for (int i = 0; i < brd.size(); i++)
        {
            for (int j = 0; j < brd.size(); j++)
            {
                if (value[i] == card_comb[j].get_value()) sorted_card_comb[i] = new Card(card_comb[j].get_value(), card_comb[j].get_colour());
            }
        }

        setBoard_comb(0,value[0],sorted_card_comb);

    }


}





























