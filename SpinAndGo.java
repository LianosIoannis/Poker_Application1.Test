package com.example.example_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SpinAndGo extends AppCompatActivity {


    private int dealing_step;
    private int dealing_count;
    private int Dealer;
    private int number_of_players;
    private String action_to_pass = "NONE";
    private int bet_to_pass = 0;
    private int beeting_loop_counter;
    private int last_to_talk;
    private int first_to_talk;
    private int hand_number;

    CountDownTimer Waiting_Timer;


    Card[] deck;
    Card[] shuffled_deck;
    ArrayList<Integer> POT_WINNER;

    Player[] player;
    Button New_Game_Button;
    ImageView flop1_imageview;
    ImageView flop2_imageview;
    ImageView flop3_imageview;
    ImageView turn_imageview;
    ImageView river_imageview;
    TextView pot_textview;
    TextView gameiInfo_textview;
    TextView combination_textview;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_and_go);

        Create_Deck();
        POT_WINNER = new ArrayList<Integer>();

        flop1_imageview = (ImageView)findViewById(R.id.flop1_imageview);
        flop2_imageview = (ImageView)findViewById(R.id.flop2_imageview);
        flop3_imageview = (ImageView)findViewById(R.id.flop3_imageview);
        turn_imageview = (ImageView)findViewById(R.id.turn_imageview);
        river_imageview = (ImageView)findViewById(R.id.river_imageview);
        pot_textview = (TextView)findViewById(R.id.pot_textview);
        gameiInfo_textview = (TextView)findViewById(R.id.GameInfo_textview);
        combination_textview = (TextView)findViewById(R.id.combination_textview);

        gameiInfo_textview.setMovementMethod(new ScrollingMovementMethod());



        New_Game_Button = (Button) findViewById(R.id.newgame_button);
        New_Game_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                New_Game();
            }
        });
    }

    public void Create_Deck()
    {
        deck = new Card[52];
        shuffled_deck = new Card[52];
        for (int i = 0; i < 52; i++)
        {
            deck[i] = new Card(0,0);
            //shuffled_deck[i] = new Card(0,0);
        }
    }

    public void New_Game()
    {
        number_of_players = 3;
        hand_number = 0;
        Dealer = 0;

        player = new Player[number_of_players];
        for (int i = 0; i < number_of_players; i++) player[i] = new Player();
        Initialize_players_Data();


        New_Hand();
    }

    public void find_Dealer()
    {
        for (int i = 0; i < 3; i++)
        {
            if (player[i].getPosition() == "D")
            {
                Dealer = i;
                break;
            }
        }
    }

    public void Initialize_players_Data()
    {
        player[0].setPosition("D");
        player[1].setPosition("SB");
        player[2].setPosition("BB");

        for (int i = 0; i < 3; i++) player[i].setPlayer_name("Player_" + Integer.toString(i+1));

        player[0].setHUMAN(true);
        player[1].setHUMAN(false);
        player[2].setHUMAN(false);

        player[0].setStack(100);
        player[1].setStack(100);
        player[2].setStack(100);

        player[0].setBB(2);
        player[1].setBB(2);
        player[2].setBB(2);

        player[0].setCard1_Imageview((ImageView)findViewById(R.id.card1_imageview));
        player[0].setCard2_Imageview((ImageView)findViewById(R.id.card2_imageview));
        player[1].setCard1_Imageview((ImageView)findViewById(R.id.card3_imageview));
        player[1].setCard2_Imageview((ImageView)findViewById(R.id.card4_imageview));
        player[2].setCard1_Imageview((ImageView)findViewById(R.id.card5_imageview));
        player[2].setCard2_Imageview((ImageView)findViewById(R.id.card6_imageview));

        player[0].setPosition_Textview((TextView)findViewById(R.id.position1_textview));
        player[1].setPosition_Textview((TextView)findViewById(R.id.position2_textview));
        player[2].setPosition_Textview((TextView)findViewById(R.id.position3_textview));

        player[0].setStack_Textview((TextView)findViewById(R.id.stack1_textview));
        player[1].setStack_Textview((TextView)findViewById(R.id.stack2_textview));
        player[2].setStack_Textview((TextView)findViewById(R.id.stack3_textview));

        player[0].setAction_Textview((TextView)findViewById(R.id.action1_textview));
        player[1].setAction_Textview((TextView)findViewById(R.id.action2_textview));
        player[2].setAction_Textview((TextView)findViewById(R.id.action3_textview));

        player[0].setPot_textview((TextView)findViewById(R.id.pot_textview));
        player[1].setPot_textview((TextView)findViewById(R.id.pot_textview));
        player[2].setPot_textview((TextView)findViewById(R.id.pot_textview));

        player[0].setProgress_time_remaining((ProgressBar)findViewById(R.id.progressbar1));
        player[1].setProgress_time_remaining((ProgressBar)findViewById(R.id.progressbar2));
        player[2].setProgress_time_remaining((ProgressBar)findViewById(R.id.progressbar3));

        player[0].setFOLD_BUTTON((Button)findViewById(R.id.fold_button));
        player[0].setCHECK_BUTTON((Button)findViewById(R.id.check_button));
        player[0].setCALL_BUTTON((Button)findViewById(R.id.call_button));
        player[0].setBETR_BUTTON((Button)findViewById(R.id.betr_button));
        player[0].setBETR_SEEKBAR((SeekBar)findViewById(R.id.seekbar_bet));
        player[0].setBETR_TEXTVIEW((TextView)findViewById(R.id.bet_size_textview));
        player[0].setCOMB_TEXTVIEW((TextView)findViewById(R.id.combination_textview));

        for (int i = 0; i < 3; i++) player[i].getPosition_Textview().setText(player[i].getPlayer_name() + "/" + player[i].getPosition());

    }

    public void Shuffle_deck()
    {
        int k = 0;
        for (int i = 0; i < 13; i ++)
        {
            for (int j = 0; j < 4; j++)
            {
                deck[k].setValue(i);
                deck[k].setColour(j);
                k++;
            }
        }

        List<Card> deck_to_shuffle = Arrays.asList(deck);
        Collections.shuffle(deck_to_shuffle);

        for (int i = 0; i < 52; i++) shuffled_deck[i] = new Card(deck_to_shuffle.get(i).get_value(), deck_to_shuffle.get(i).get_colour());
    }

    public void Deal_Next()
    {
        if (dealing_step == 0) Deal_toPlayers();
        else if (dealing_step == 1) Deal_Flop();
        else if (dealing_step == 2) Deal_Turn();
        else if (dealing_step == 3) Deal_River();
    }

    public void Deal_toPlayers()
    {
        int[] next = new int[number_of_players];
        int k = Dealer;
        for (int i = 0; i < number_of_players; i++)
        {
            k++;
            if (k == number_of_players) k = 0;
            next[i] = k;
        }


        for (int i = 0; i < number_of_players; i++)
        {
            player[next[i]].getCard1_Imageview().setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
            player[next[i]].add_to_holeCard(shuffled_deck[dealing_count]);
            dealing_count++;
        }
        for (int i = 0; i < number_of_players; i++)
        {
            player[next[i]].getCard2_Imageview().setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
            player[next[i]].add_to_holeCard(shuffled_deck[dealing_count]);
            dealing_count++;
        }

        dealing_step++;
    }

    public void Deal_Flop()
    {
        dealing_count++;
        flop1_imageview.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
        for (int i = 0; i < 3; i++) player[i].add_toAllCards(shuffled_deck[dealing_count]);

        dealing_count++;
        flop2_imageview.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
        for (int i = 0; i < 3; i++) player[i].add_toAllCards(shuffled_deck[dealing_count]);

        dealing_count++;
        flop3_imageview.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
        for (int i = 0; i < 3; i++) player[i].add_toAllCards(shuffled_deck[dealing_count]);

        dealing_count++;

        dealing_step++;
    }

    public void Deal_Turn()
    {
        dealing_count++;
        turn_imageview.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
        for (int i = 0; i < 3; i++) player[i].add_toAllCards(shuffled_deck[dealing_count]);

        dealing_count++;

        dealing_step++;
    }

    public void Deal_River()
    {
        dealing_count++;
        river_imageview.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
        for (int i = 0; i < 3; i++) player[i].add_toAllCards(shuffled_deck[dealing_count]);

        dealing_step++;
    }

    public int compare_combinations(Combination comb1, Combination comb2)
    {
        int winner = 0;

        if(comb1.getValue_of_comb() > comb2.getValue_of_comb()) winner  = 1;
        else if (comb1.getValue_of_comb() < comb2.getValue_of_comb()) winner = 2;
        else if (comb1.getmy_value() > comb2.getmy_value()) winner = 1;
        else if (comb1.getmy_value() < comb2.getmy_value()) winner  =2;
        else // Check Kicker
        {
            for (int i = 0; i < comb1.getCard_comb().length; i++)
            {
                if (comb1.getCard_comb()[i].get_value() > comb2.getCard_comb()[i].get_value())
                {
                    winner = 1;
                    break;
                }
                else if (comb1.getCard_comb()[i].get_value() < comb2.getCard_comb()[i].get_value())
                {
                    winner = 2;
                    break;
                }
            }
        }

        return winner;
    }

    public void Sort_POT_WINNER(ArrayList<Combination> c)
    {

        for (int i = 0; i < c.size(); i++)
        {
            for (int j = i; j < c.size(); j++)
            {
                if ( compare_combinations(c.get(i), c.get(j)) == 2 )
                {
                    Collections.swap(POT_WINNER, i, j);
                }
            }
        }
    }

    public int find_pot()
    {
        int r = 0;
        try {
            r = Integer.parseInt(pot_textview.getText().toString());
        }catch (Exception e){};

        return r;
    }

    public void Print_Combinations()
    {
        String result = "";
        for (int i = 0; i < 3; i++) result+=player[i].getCombination().get_name() + System.getProperty("line.separator");
        gameiInfo_textview.setText(result);
    }

    public void update_GameInfo(int i)
    {
        String r1 = gameiInfo_textview.getText().toString() + System.getProperty("line.separator");
        String r2 = "";

        if (player[i].getAction_str() == "FOLD") r2 = player[i].getPlayer_name() + " FOLDS";
        else if (player[i].getAction_str() == "CHECK") r2 = player[i].getPlayer_name() + " CHECKS";
        else if (player[i].getAction_str() == "CALL") r2 = player[i].getPlayer_name() + " CALLS " + Integer.toString(player[i].getBET());
        else if (player[i].getAction_str() == "BET") r2 = player[i].getPlayer_name() + " BETS " + Integer.toString(player[i].getBET());
        else if (player[i].getAction_str() == "RAISE") r2 = player[i].getPlayer_name() + " RAISE TO " + Integer.toString(player[i].getBET());

        gameiInfo_textview.setText(r1+r2);
    }

    public int card_to_SrcInt(Card card)
    {
        int[] card_v = new int[2];
        card_v[0] = card.get_value();
        card_v[1] = card.get_colour();

        int src = 0;

        if (card_v[0] == 0 && card_v[1] == 0) src = R.drawable.s01;
        else if (card_v[0] == 1 && card_v[1] == 0) src = R.drawable.s02;
        else if (card_v[0] == 2 && card_v[1] == 0) src = R.drawable.s03;
        else if (card_v[0] == 3 && card_v[1] == 0) src = R.drawable.s04;
        else if (card_v[0] == 4 && card_v[1] == 0) src = R.drawable.s05;
        else if (card_v[0] == 5 && card_v[1] == 0) src = R.drawable.s06;
        else if (card_v[0] == 6 && card_v[1] == 0) src = R.drawable.s07;
        else if (card_v[0] == 7 && card_v[1] == 0) src = R.drawable.s08;
        else if (card_v[0] == 8 && card_v[1] == 0) src = R.drawable.s09;
        else if (card_v[0] == 9 && card_v[1] == 0) src = R.drawable.s10;
        else if (card_v[0] == 10 && card_v[1] == 0) src = R.drawable.s11;
        else if (card_v[0] == 11 && card_v[1] == 0) src = R.drawable.s12;
        else if (card_v[0] == 12 && card_v[1] == 0) src = R.drawable.s13;
        else if (card_v[0] == 0 && card_v[1] == 1) src = R.drawable.d01;
        else if (card_v[0] == 1 && card_v[1] == 1) src = R.drawable.d02;
        else if (card_v[0] == 2 && card_v[1] == 1) src = R.drawable.d03;
        else if (card_v[0] == 3 && card_v[1] == 1) src = R.drawable.d04;
        else if (card_v[0] == 4 && card_v[1] == 1) src = R.drawable.d05;
        else if (card_v[0] == 5 && card_v[1] == 1) src = R.drawable.d06;
        else if (card_v[0] == 6 && card_v[1] == 1) src = R.drawable.d07;
        else if (card_v[0] == 7 && card_v[1] == 1) src = R.drawable.d08;
        else if (card_v[0] == 8 && card_v[1] == 1) src = R.drawable.d09;
        else if (card_v[0] == 9 && card_v[1] == 1) src = R.drawable.d10;
        else if (card_v[0] == 10 && card_v[1] == 1) src = R.drawable.d11;
        else if (card_v[0] == 11 && card_v[1] == 1) src = R.drawable.d12;
        else if (card_v[0] == 12 && card_v[1] == 1) src = R.drawable.d13;
        else if (card_v[0] == 0 && card_v[1] == 2) src = R.drawable.h01;
        else if (card_v[0] == 1 && card_v[1] == 2) src = R.drawable.h02;
        else if (card_v[0] == 2 && card_v[1] == 2) src = R.drawable.h03;
        else if (card_v[0] == 3 && card_v[1] == 2) src = R.drawable.h04;
        else if (card_v[0] == 4 && card_v[1] == 2) src = R.drawable.h05;
        else if (card_v[0] == 5 && card_v[1] == 2) src = R.drawable.h06;
        else if (card_v[0] == 6 && card_v[1] == 2) src = R.drawable.h07;
        else if (card_v[0] == 7 && card_v[1] == 2) src = R.drawable.h08;
        else if (card_v[0] == 8 && card_v[1] == 2) src = R.drawable.h09;
        else if (card_v[0] == 9 && card_v[1] == 2) src = R.drawable.h10;
        else if (card_v[0] == 10 && card_v[1] == 2) src = R.drawable.h11;
        else if (card_v[0] == 11 && card_v[1] == 2) src = R.drawable.h12;
        else if (card_v[0] == 12 && card_v[1] == 2) src = R.drawable.h13;
        else if (card_v[0] == 0 && card_v[1] == 3) src = R.drawable.c01;
        else if (card_v[0] == 1 && card_v[1] == 3) src = R.drawable.c02;
        else if (card_v[0] == 2 && card_v[1] == 3) src = R.drawable.c03;
        else if (card_v[0] == 3 && card_v[1] == 3) src = R.drawable.c04;
        else if (card_v[0] == 4 && card_v[1] == 3) src = R.drawable.c05;
        else if (card_v[0] == 5 && card_v[1] == 3) src = R.drawable.c06;
        else if (card_v[0] == 6 && card_v[1] == 3) src = R.drawable.c07;
        else if (card_v[0] == 7 && card_v[1] == 3) src = R.drawable.c08;
        else if (card_v[0] == 8 && card_v[1] == 3) src = R.drawable.c09;
        else if (card_v[0] == 9 && card_v[1] == 3) src = R.drawable.c10;
        else if (card_v[0] == 10 && card_v[1] == 3) src = R.drawable.c11;
        else if (card_v[0] == 11 && card_v[1] == 3) src = R.drawable.c12;
        else if (card_v[0] == 12 && card_v[1] == 3) src = R.drawable.c13;

        return src;

    }

    public int find_next_of(int p)
    {
        int r = 0;
        int k = p;
        for (int i = 0; i < number_of_players - 1; i++)
        {
            k++;
            if (k == number_of_players) k = 0;
            if (player[k].isFold() == false)
            {
                r = k;
                break;
            }
        }

        return r;
    }

    public int find_previous_of(int p)
    {
        int r = 0;
        int k = p;
        for (int i = 0; i < number_of_players - 1; i++)
        {
            k--;
            if (k == -1) k = number_of_players - 1;
            if (player[k].isFold() == false)
            {
                r = k;
                break;
            }
        }

        return r;
    }

    public int all_inCounter()
    {
        int r = 0;
        for (int i = 0; i < number_of_players; i++)
        {
            if (player[i].isAll_in()) r++;
        }
        return r;
    }

    public int fold_Counter()
    {
        int r = 0;
        for (int i = 0; i < number_of_players; i++)
        {
            if (player[i].isFold()) r++;
        }
        return r;
    }

    public int active_playersCounter()
    {
        int r = 0;
        for (int i = 0; i < number_of_players; i++)
        {
            if (player[i].isGAVE_OVER() == false) r++;
        }
        return r;
    }

    public int players_inHand()
    {
        return number_of_players - fold_Counter();
    }

    public int find_SB()
    {
        return find_next_of(Dealer);
    }

    public int find_BB()
    {
        return find_next_of(find_SB());
    }

    public void clear_TempData()
    {
        for (int i = 0; i < 3; i++)
        {
            player[i].setAction_to_talk("NONE");
            player[i].setBET_TO_CALL(0);
            player[i].setAction_str("NONE");
            player[i].setBET(0);
            if (dealing_step == 0) player[i].Clear_Cards();
        }
    }

    public void change_players_position()
    {
        int k = Dealer;
        player[k].setPosition("D");
        k = find_next_of(k);
        player[k].setPosition("SB");
        k = find_next_of(k);
        player[k].setPosition("BB");

       for (int i = 0; i < 3; i++)
       {
           if (player[i].getStack() != 0)
           {
               player[i].getPosition_Textview().setText(player[i].getPlayer_name() + "/" + player[i].getPosition());
               player[i].setBLIND_SETTED(false);
               player[i].setFold(false);
           }
       }

    }

    public void change_views_toDeafault()
    {
        for (int i = 0; i < 3; i++)
        {
            player[i].getCard1_Imageview().setImageResource(R.drawable.back);
            player[i].getCard2_Imageview().setImageResource(R.drawable.back);
        }
        flop1_imageview.setImageResource(R.drawable.back);
        flop2_imageview.setImageResource(R.drawable.back);
        flop3_imageview.setImageResource(R.drawable.back);
        turn_imageview.setImageResource(R.drawable.back);
        river_imageview.setImageResource(R.drawable.back);
        pot_textview.setText("0");
        gameiInfo_textview.setText("GOOD LUCK !" + System.getProperty("line.separator") + "HAND: " + Integer.toString(hand_number+1));
    }

    public void clear_action_textviews()
    {
        for (int i = 0; i < 3; i++) player[i].getAction_Textview().setText("");
    }


    public void New_Hand()
    {

        if (hand_number != 0)
        {
            Dealer = find_next_of(Dealer);
            change_players_position();
        }

        change_views_toDeafault();

        hand_number++;

        dealing_step = 0;
        dealing_count = 0;

        pot_textview.setText("0");

        Shuffle_deck();
        clear_TempData();

        Deal_Wait_forActions();

    }

    public void Deal_Wait_forActions()
    {
        if (dealing_step < 4)
        {
            Deal_Next();
            if (dealing_step > 1) player[0].getCOMB_TEXTVIEW().setText(player[0].getCombination().get_name());
            beeting_loop_counter = 0;
            last_to_talk = Dealer;
            first_to_talk = find_next_of(Dealer);
            action_to_pass = "NONE";
            bet_to_pass = 0;
            clear_action_textviews();
            clear_TempData();
            if (dealing_step == 1 && beeting_loop_counter == 0) Blind_setter();
            else Betting_Loop(first_to_talk);
        }
        else
        {
            find_Winner();
            New_Hand();
        }
    }

    public void find_Winner()
    {
        if (POT_WINNER != null) POT_WINNER.clear();
        ArrayList<Combination> c = new ArrayList<Combination>();

        for (int i = 0; i < number_of_players; i++)
        {
            if (player[i].isFold() == false)
            {
                POT_WINNER.add(i);
                c.add(player[i].getCombination());
            }
        }

        Sort_POT_WINNER(c);

        int split = 1;

        for (int i = 1; i < POT_WINNER.size(); i++) if (player[POT_WINNER.get(0)].getCombination().get_name() == player[POT_WINNER.get(i)].getCombination().get_name()) split++;

        for (int i = 0; i < split; i++)
        {
            player[POT_WINNER.get(i)].setStack(player[POT_WINNER.get(i)].getStack() + (int)find_pot()/split);
            player[POT_WINNER.get(i)].getStack_Textview().setText(Integer.toString(player[POT_WINNER.get(i)].getStack()));
        }
    }

    public void Blind_setter()
    {
        player[find_SB()].Action();
        update_GameInfo(find_SB());

        player[find_BB()].Action();
        update_GameInfo(find_BB());

        bet_to_pass = player[find_BB()].getBET();
        last_to_talk = find_BB();

        Betting_Loop(find_next_of(find_BB()));
    }


    public void Betting_Loop(final int i)
    {
        if (Waiting_Timer != null) Waiting_Timer.cancel();
        player[i].getAction_Textview().setText("ACTION...");
        player[i].setBET_TO_CALL(bet_to_pass);
        player[i].Action();

        Waiting_Timer = new CountDownTimer(15000, 50) {
            @Override
            public void onTick(long l)
            {
                if (player[i].isActed())
                {
                    if (player[i].getBET() > bet_to_pass)
                    {
                        bet_to_pass = player[i].getBET();
                        last_to_talk = find_previous_of(i);
                    }

                    update_GameInfo(i);

                    if (last_to_talk != i)
                    {
                        Betting_Loop(find_next_of(i));
                        beeting_loop_counter++;
                    }
                    else Deal_Wait_forActions();
                }
            }

            @Override
            public void onFinish() {
            }
        }.start();

    }



}

