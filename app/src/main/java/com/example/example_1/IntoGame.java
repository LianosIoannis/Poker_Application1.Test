package com.example.example_1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntoGame extends AppCompatActivity {


    Card[] deck;
    Card[] shuffled_deck;
    int dealing_step;
    int dealing_count;
    int num_of_players;
    int starting_chips;
    int[] my_brd_counter;
    int[] op_brd_counter;
    String Pos_of_player1;
    String Pos_of_player2;
    int stack1;
    int stack2;
    String player1_str_action;
    String player2_str_action;
    int p1_bet;
    int p2_bet;
    int the_Pot;
    int pot_winner;
    int hand_counter;
    int BB;
    int SB;
    int Min_Bet;




    Handler handler = new Handler();
    Runnable opRun;
    Handler game_over_handler = new Handler();
    Runnable game_over_run;


    CountDownTimer timer_progress1;
    CountDownTimer timer_progress2;

    CountDownTimer timer_wait1;
    CountDownTimer timer_wait2;

    boolean acted_player2 = false;
    boolean acted_player1 = false;
    boolean all_in;




    ImageView flop1_img ;
    ImageView flop2_img ;
    ImageView flop3_img ;
    ImageView turn_img ;
    ImageView river_img ;
    ImageView mycard1_img ;
    ImageView mycard2_img ;
    ImageView oppcard1_img ;
    ImageView oppcard2_img ;


    TextView result;
    TextView pot;
    TextView my_stack;
    TextView game_info;
    TextView opp1_stack;
    TextView player_1_pos;
    TextView player_2_pos;
    TextView p1_action;
    TextView p2_action;
    TextView action_betSize;

    Button new_game_btn;
    Button fold_btn;
    Button check_btn;
    Button call_btn;
    Button raise_btn;

    ProgressBar my_progress_bar;
    ProgressBar op_progress_bar;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into_game);


        flop1_img = (ImageView) findViewById(R.id.flop1_imageview);
        flop2_img = (ImageView) findViewById(R.id.flop2_imageview);
        flop3_img = (ImageView) findViewById(R.id.flop3_imageview);
        turn_img = (ImageView) findViewById(R.id.turn_imageview);
        river_img = (ImageView) findViewById(R.id.river_imageview);
        mycard1_img = (ImageView) findViewById(R.id.mycard1_image);
        mycard2_img = (ImageView) findViewById(R.id.mycard2_image);
        oppcard1_img = (ImageView) findViewById(R.id.oppcard1_image);
        oppcard2_img = (ImageView) findViewById(R.id.oppcard2_image);

        result = (TextView) findViewById(R.id.HandWinner_txt);
        pot = (TextView) findViewById(R.id.pot_textview);
        my_stack = (TextView) findViewById(R.id.stack_txt);
        game_info = (TextView) findViewById(R.id.GameInfo_textview);
        opp1_stack = (TextView) findViewById(R.id.opp1stack_txt);
        player_1_pos = (TextView) findViewById(R.id.player_pos_1);
        player_2_pos = (TextView) findViewById(R.id.player_pos_2);
        p1_action = (TextView) findViewById(R.id.action_1);
        p2_action = (TextView) findViewById(R.id.action_2);
        action_betSize = (TextView) findViewById(R.id.bet_size_textview);

        my_progress_bar = (ProgressBar) findViewById(R.id.myprogressBar);
        op_progress_bar = (ProgressBar) findViewById(R.id.opprogressbar);


        //Initialize seekBar====================================================
        seekBar = (SeekBar) findViewById(R.id.seekbar_bet);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int y;
                y = (stack1 - Min_Bet)*i/100 + Min_Bet;
                action_betSize.setText(Integer.toString(y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Initialize seekBar====================================================


        my_brd_counter = new int[7];
        op_brd_counter = new int[7];

        deck = new Card[52];
        shuffled_deck = new Card[52];
        for (int i = 0; i < 52; i++)
        {
            deck[i] = new Card(0,0);
            shuffled_deck[i] = new Card(0,0);
        }



        //Initialize Buttons====================================================

        new_game_btn = (Button) findViewById(R.id.newgame_button);
        fold_btn = (Button) findViewById(R.id.fold_button);
        check_btn = (Button) findViewById(R.id.check_button);
        call_btn = (Button) findViewById(R.id.call_button);
        raise_btn = (Button) findViewById(R.id.betr_button);


        new_game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                New_Game();
            }
        });

        fold_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fold_btn_Clicked();
            }
        });

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_btn_Clicked();
            }
        });

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_btn_Clicked();
            }
        });

        raise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                raise_btn_Clicked();
            }
        });

        //Initialize Buttons====================================================

        //New_Game();


    }


    public void New_Game()
    {

        dealing_step = 0;
        dealing_count = 0;
        num_of_players = 2;
        starting_chips = 100;
        stack1 = starting_chips;
        stack2 = starting_chips;
        Pos_of_player1 = "SB";
        Pos_of_player2 = "BB";
        player1_str_action = "NONE";
        player2_str_action = "NONE";
        hand_counter = 0;
        all_in = false;
        BB = 2;
        SB = BB/2;
        Min_Bet = BB;


        for (int i = 0; i < 7; i++)
        {
            my_brd_counter[i] = -1;
            op_brd_counter[i] = -1;
        }

        if (timer_progress1 != null) timer_progress1.cancel();
        if (timer_progress2 != null) timer_progress2.cancel();
        if (timer_wait1 != null) timer_wait1.cancel();
        if (timer_wait2 != null )timer_wait2.cancel();


        //Set TextViews to default
        pot.setText("0");
        my_stack.setText(Integer.toString(starting_chips));
        opp1_stack.setText(Integer.toString(starting_chips));
        result.setText("HAND WINNER");
        game_info.setText("GOOD LUCK !!!");
        player_1_pos.setText("Player_1/" + Pos_of_player1);
        player_2_pos.setText("Player_2/" + Pos_of_player2);
        p1_action.setText("ACTION !!");
        p2_action.setText("ACTION !!");
        action_betSize.setText("0");
        //Set TextViews to default



        //Set seekBar to default

        //Set seekBar to default



        //Set ImageViews to default
        flop1_img.setImageResource(R.drawable.back);
        flop2_img.setImageResource(R.drawable.back);
        flop3_img.setImageResource(R.drawable.back);
        turn_img.setImageResource(R.drawable.back);
        river_img.setImageResource(R.drawable.back);
        mycard1_img.setImageResource(R.drawable.back);
        mycard2_img.setImageResource(R.drawable.back);
        oppcard1_img.setImageResource(R.drawable.back);
        oppcard2_img.setImageResource(R.drawable.back);
        //Set ImageViews to default


        //Create_and_shuffle_deck();
        New_Hand();
    }

    public void Create_and_shuffle_deck()
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

        deck_to_shuffle.toArray(shuffled_deck);
    }


    public void Deal_Next()
    {
        Card[] my_board;
        Card[] op_board;


        if (dealing_step == 0)//Deal cards to players
        {
            int Dealer = 0;
            if(Pos_of_player1 == "SB") Dealer = 1;
            else Dealer = 2;

            if (Dealer == 1)
            {
                //oppcard1_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//1
                op_brd_counter[0] = dealing_count;
                dealing_count++;

                mycard1_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//0
                my_brd_counter[0] = dealing_count;
                dealing_count++;

                //oppcard2_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//3
                op_brd_counter[1] = dealing_count;
                dealing_count++;

                mycard2_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//2
                my_brd_counter[1] = dealing_count;
                dealing_count++;
            }
            else
            {
                mycard1_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//0
                my_brd_counter[0] = dealing_count;
                dealing_count++;

                //oppcard1_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//1
                op_brd_counter[0] = dealing_count;
                dealing_count++;

                mycard2_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//2
                my_brd_counter[1] = dealing_count;
                dealing_count++;

                //oppcard2_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//3
                op_brd_counter[1] = dealing_count;
                dealing_count++;
            }

            dealing_step++;
        }
        else if (dealing_step == 1)//Deal flop
        {
            dealing_count++;

            flop1_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//5
            my_brd_counter[2] = dealing_count;
            op_brd_counter[2] = dealing_count;
            dealing_count++;

            flop2_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//6
            my_brd_counter[3] = dealing_count;
            op_brd_counter[3] = dealing_count;
            dealing_count++;

            flop3_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//7
            my_brd_counter[4] = dealing_count;
            op_brd_counter[4] = dealing_count;
            dealing_count++;

            my_board = new Card[5];
            op_board = new Card[5];

            for (int i = 0; i < 5; i++)
            {
                my_board[i] = new Card(shuffled_deck[my_brd_counter[i]].get_value(), shuffled_deck[my_brd_counter[i]].get_colour());
                op_board[i] = new Card(shuffled_deck[op_brd_counter[i]].get_value(), shuffled_deck[op_brd_counter[i]].get_colour());
            }

            show_combination(my_board,op_board);

            dealing_step++;
        }
        else if (dealing_step == 2)//Deal turn
        {
            dealing_count++;
            turn_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));//7
            my_brd_counter[5] = dealing_count;
            op_brd_counter[5] = dealing_count;
            dealing_count++;

            my_board = new Card[6];
            op_board = new Card[6];

            for (int i = 0; i < 6; i++)
            {
                my_board[i] = new Card(shuffled_deck[my_brd_counter[i]].get_value(), shuffled_deck[my_brd_counter[i]].get_colour());
                op_board[i] = new Card(shuffled_deck[op_brd_counter[i]].get_value(), shuffled_deck[op_brd_counter[i]].get_colour());
            }

            show_combination(my_board,op_board);

            dealing_step++;
        }
        else if (dealing_step == 3)//Deal river
        {
            dealing_count++;
            river_img.setImageResource(card_to_SrcInt(shuffled_deck[dealing_count]));
            my_brd_counter[6] = dealing_count;
            op_brd_counter[6] = dealing_count;

            my_board = new Card[7];
            op_board = new Card[7];

            for (int i = 0; i < 7; i++)
            {
                my_board[i] = new Card(shuffled_deck[my_brd_counter[i]].get_value(), shuffled_deck[my_brd_counter[i]].get_colour());
                op_board[i] = new Card(shuffled_deck[op_brd_counter[i]].get_value(), shuffled_deck[op_brd_counter[i]].get_colour());
            }

            show_combination(my_board,op_board);

            dealing_step++;
        }

    }

    public void fold_btn_Clicked()
    {
        player1_str_action = "FOLD";
        p1_bet = 0;
        p1_action.setText(player1_str_action);


        fold_btn.setClickable(false);
        check_btn.setClickable(false);
        call_btn.setClickable(false);
        raise_btn.setClickable(false);
        seekBar.setClickable(false);



        timer_progress1.cancel();
        my_progress_bar.setProgress(100);
    }

    public void check_btn_Clicked()
    {
        player1_str_action = "CHECK";
        p1_bet = 0;
        p1_action.setText(player1_str_action);

        fold_btn.setClickable(false);
        check_btn.setClickable(false);
        call_btn.setClickable(false);
        raise_btn.setClickable(false);
        seekBar.setClickable(false);



        timer_progress1.cancel();
        my_progress_bar.setProgress(100);

    }


    public void call_btn_Clicked()
    {
        player1_str_action = "CALL";
        int bet_gap = p2_bet - p1_bet;
        p1_bet = p2_bet;

        stack1 = stack1 - bet_gap;
        my_stack.setText(Integer.toString(stack1));

        the_Pot = the_Pot + bet_gap;
        pot.setText(Integer.toString(the_Pot));
        if (stack1 == 0) all_in = true;

        p1_action.setText(player1_str_action + " " + Integer.toString(p1_bet));
        fold_btn.setClickable(false);
        check_btn.setClickable(false);
        call_btn.setClickable(false);
        raise_btn.setClickable(false);
        seekBar.setClickable(false);



        timer_progress1.cancel();
        my_progress_bar.setProgress(100);
    }

    public void raise_btn_Clicked()
    {
        if (player2_str_action == "BET") Raise_do("RAISE");
        else Raise_do("BET");

    }

    public void Raise_do(String action)
    {
        player1_str_action = action;
        int prev_bet = p1_bet;
        p1_bet = Integer.parseInt(action_betSize.getText().toString());

        p1_action.setText(player1_str_action + " " + Integer.toString(p1_bet));
        stack1 = stack1 - (p1_bet - prev_bet);
        my_stack.setText(Integer.toString(stack1));

        the_Pot = the_Pot + (p1_bet - prev_bet);
        pot.setText(Integer.toString(the_Pot));
        if (stack1 == 0) all_in = true;

        fold_btn.setClickable(false);
        check_btn.setClickable(false);
        call_btn.setClickable(false);
        raise_btn.setClickable(false);
        seekBar.setClickable(false);


        timer_progress1.cancel();
        my_progress_bar.setProgress(100);
    }


    public void New_Hand()
    {

        Create_and_shuffle_deck();
        player1_str_action = "NONE";
        player2_str_action = "NONE";
        dealing_step  = 0;
        dealing_count = 0;
        the_Pot = 0;
        all_in = false;

        if(hand_counter != 0) swap_positions();
        hand_counter++;

        //Set ImageViews to default
        flop1_img.setImageResource(R.drawable.back);
        flop2_img.setImageResource(R.drawable.back);
        flop3_img.setImageResource(R.drawable.back);
        turn_img.setImageResource(R.drawable.back);
        river_img.setImageResource(R.drawable.back);
        mycard1_img.setImageResource(R.drawable.back);
        mycard2_img.setImageResource(R.drawable.back);
        oppcard1_img.setImageResource(R.drawable.back);
        oppcard2_img.setImageResource(R.drawable.back);
        //Set ImageViews to default


        Deal_Wait_for_actions();


    }


    public void Deal_Wait_for_actions()
    {

        if (dealing_step < 4)
        {
            if (all_in == false)
            {
                Deal_Next();
                if (dealing_step == 1)
                {
                    if (talk_first() == 1)
                    {
                        player1_str_action = "BET";
                        p1_bet = SB;
                        p1_action.setText(player1_str_action + " " + Integer.toString(p1_bet));
                        stack1 = stack1 - p1_bet;
                        my_stack.setText(Integer.toString(stack1));

                        the_Pot = the_Pot + p1_bet;
                        pot.setText(Integer.toString(the_Pot));
                        if (stack1 == 0) all_in = true;

                        player2_str_action = "RAISE";
                        p2_bet = BB;
                        p2_action.setText(player2_str_action + " " + Integer.toString(p2_bet));
                        stack2 = stack2 - p2_bet;
                        opp1_stack.setText(Integer.toString(stack2));

                        the_Pot = the_Pot + p2_bet;
                        pot.setText(Integer.toString(the_Pot));
                        if (stack2 == 0) all_in = true;

                        player1_talks();
                    }
                    else
                    {
                        player2_str_action = "BET";
                        p2_bet = SB;
                        p2_action.setText(player2_str_action + " " + Integer.toString(p2_bet));
                        stack2 = stack2 - p2_bet;
                        opp1_stack.setText(Integer.toString(stack2));

                        the_Pot = the_Pot + p2_bet;
                        pot.setText(Integer.toString(the_Pot));
                        if (stack2 == 0) all_in = true;

                        player1_str_action = "RAISE";
                        p1_bet = BB;
                        p1_action.setText(player1_str_action + " " + Integer.toString(p1_bet));
                        stack1 = stack1 - p1_bet;
                        my_stack.setText(Integer.toString(stack1));

                        the_Pot = the_Pot + p1_bet;
                        pot.setText(Integer.toString(the_Pot));
                        if (stack1 == 0) all_in = true;

                        player2_talks();
                    }
                }
                else
                {
                    player1_str_action = "NONE";
                    player2_str_action = "NONE";
                    p1_bet = 0;
                    p2_bet = 0;
                    if (talk_first() == 1) player1_talks();
                    if (talk_first() == 2) player2_talks();
                }
            }
            else Check_gameOver();
        }
        else Check_gameOver();
    }

    public void Check_gameOver()
    {
        oppcard1_img.setImageResource(card_to_SrcInt(shuffled_deck[op_brd_counter[0]]));
        oppcard2_img.setImageResource(card_to_SrcInt(shuffled_deck[op_brd_counter[1]]));
        for (int i = dealing_step; i < 4; i++) Deal_Next();

        game_over_run = new Runnable() {
            @Override
            public void run() {
                Check_gameOver_do();
            }
        };
        game_over_handler.postDelayed(game_over_run, 2500);

    }

    public void Check_gameOver_do()
    {

        if (pot_winner == 1)
        {
            stack1 += the_Pot;
            my_stack.setText(Integer.toString(stack1));
        }
        else if (pot_winner == 2)
        {
            stack2 += the_Pot;
            opp1_stack.setText(Integer.toString(stack2));
        }
        else
        {
            stack1 += the_Pot/2;
            my_stack.setText(Integer.toString(stack1));
            stack2 += the_Pot/2;
            opp1_stack.setText(Integer.toString(stack2));
        }

        if (stack1 != 0 && stack2 != 0) New_Hand();
        else if (stack2 == 0)
        {
            Toast.makeText(this, "!!!  YOU WON  !!!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "!!!  YOU LOST  !!!", Toast.LENGTH_LONG).show();
        }

        game_over_handler.removeCallbacks(game_over_run);

    }



    public int talk_first()
    {
        int r = 0;
        if (dealing_step == 1)
        {
            if (Pos_of_player1 == "SB") r = 1;
            else r = 2;
        }
        else
        {
            if (Pos_of_player1 == "SB") r = 2;
            else r = 1;
        }
        return r;
    }


    public void player1_talks()
    {
        player1_action();
        Wait_for_player1();
    }

    public void Wait_for_player1()
    {
        p1_action.setText("ACTION...");
        if (player2_str_action == "BET" || player2_str_action == "RAISE")
        {
            call_btn.setText("CALL " + Integer.toString(p2_bet - p1_bet));
        }

        player1_str_action = "NONE";
        timer_wait1 = new CountDownTimer(15000, 100) {
            @Override
            public void onTick(long l) {
                if (player1_str_action != "NONE" && acted_player1 == false)
                {
                    acted_player1 = true;
                    player1_acted();
                }
            }
            @Override
            public void onFinish() { }
        }.start();
    }

    public void player1_acted()
    {
        timer_wait1.cancel();
        acted_player1 = false;

        if (player1_str_action == "BET") player2_talks();
        else if (player1_str_action == "CALL" && dealing_step != 1) Deal_Wait_for_actions();
        else if (player1_str_action == "CALL" && dealing_step == 1 && the_Pot > 2*BB) Deal_Wait_for_actions();
        else if (player1_str_action == "CALL" && dealing_step == 1) player2_talks();
        else if (player1_str_action == "FOLD") New_Hand();
        else if (talk_first() == 1 && player1_str_action == "CHECK") player2_talks();
        else if (talk_first() == 2 && player1_str_action == "CHECK") Deal_Wait_for_actions();

        if (player2_str_action == "BET" || player2_str_action == "RAISE")
        {
            if (p2_bet > p1_bet)
            {
                the_Pot = the_Pot - (p2_bet - p1_bet);
                pot.setText(Integer.toString(the_Pot));
                stack2+= p2_bet - p1_bet;
            }
        }

        seekBar.setProgress(0);
        call_btn.setText("CALL");
    }


    public void player2_talks()
    {
        player2_action();
        Wait_for_player2();
    }

    public void Wait_for_player2()
    {
        p2_action.setText("ACTION...");

        player2_str_action = "NONE";
        timer_wait2 = new CountDownTimer(15000, 100) {
            @Override
            public void onTick(long l) {
                if (player2_str_action != "NONE" && acted_player2 == false)
                {
                    acted_player2 = true;
                    player2_acted();
                }
            }
            @Override
            public void onFinish() { }
        }.start();
    }

    public void player2_acted()
    {
        timer_wait2.cancel();
        acted_player2 = false;

        if (player2_str_action == "BET") player1_talks();
        else if (player2_str_action == "CALL" && dealing_step != 1) Deal_Wait_for_actions();
        else if (player2_str_action == "CALL" && dealing_step == 1 && the_Pot > 2*BB) Deal_Wait_for_actions();
        else if (player2_str_action == "CALL" && dealing_step == 1) player1_talks();
        else if (player2_str_action == "FOLD") New_Hand();
        else if (talk_first() == 2 && player2_str_action == "CHECK") player1_talks();
        else if (talk_first() == 1 && player2_str_action == "CHECK") Deal_Wait_for_actions();

        if (player1_str_action == "BET" || player1_str_action == "RAISE")
        {
            if (p1_bet > p2_bet)
            {
                the_Pot = the_Pot - (p1_bet - p2_bet);
                pot.setText(Integer.toString(the_Pot));
                stack1+= p1_bet - p2_bet;
            }
        }

    }



    public void player1_action()
    {
        if (player2_str_action == "NONE" || player2_str_action == "CHECK" || player2_str_action == "CALL")
        {
            fold_btn.setClickable(false);
            check_btn.setClickable(true);
            call_btn.setClickable(false);
            raise_btn.setClickable(true);

            seekBar.setClickable(true);
            Min_Bet = BB;
            action_betSize.setText(Integer.toString(Min_Bet));
        }
        else if (player2_str_action == "BET" || player2_str_action == "RAISE")
        {
            fold_btn.setClickable(true);
            check_btn.setClickable(false);
            call_btn.setClickable(true);
            //call_btn.setText("CALL " + Integer.toString(p2_bet));

            if (p2_bet <= stack1)
            {
                raise_btn.setClickable(true);
                seekBar.setClickable(true);
                Min_Bet = 2*p2_bet;
                action_betSize.setText(Integer.toString(Min_Bet));
            }
            else
            {
                raise_btn.setClickable(false);
                seekBar.setClickable(false);
            }
        }


        timer_progress1 = new CountDownTimer(15000, 10) {
            @Override
            public void onTick(long l) {
                int p = (int)l/150;
                my_progress_bar.setProgress(p);
            }

            @Override
            public void onFinish() {
                fold_btn.setClickable(false);
                check_btn.setClickable(false);
                call_btn.setClickable(false);
                raise_btn.setClickable(false);
            }
            }.start();

    }

    public void player2_action()
    {

        timer_progress2 = new CountDownTimer(15000, 10) {
            @Override
            public void onTick(long l) {
                int p = (int)l/150;
                op_progress_bar.setProgress(p);
            }

            @Override
            public void onFinish() { }
        }.start();


        opRun = new Runnable() {
            @Override
            public void run() {
                player2_action_do();
            }
        };
        handler.postDelayed(opRun, 5000);
    }

    public void player2_action_do()
    {
        if (player1_str_action == "CHECK" || player1_str_action == "NONE" || player1_str_action == "CALL")
        {
            player2_str_action = "CHECK";
            p2_action.setText(player2_str_action);
            p2_bet = 0;
        }
        else if (player1_str_action == "BET" || player1_str_action == "RAISE")
        {
            int temp_bet1 = p1_bet;
            if (p1_bet - p2_bet > stack2) temp_bet1 = stack2 + p2_bet;

            player2_str_action = "CALL";
            int bet_gap = temp_bet1 - p2_bet;
            p2_bet = temp_bet1;
            p2_action.setText(player2_str_action + " " + Integer.toString(p2_bet));

            the_Pot = the_Pot + bet_gap;
            pot.setText(Integer.toString(the_Pot));

            stack2 = stack2 - bet_gap;
            opp1_stack.setText(Integer.toString(stack2));
            if (stack2 == 0) all_in = true;
        }

        op_progress_bar.setProgress(100);
        timer_progress2.cancel();
        handler.removeCallbacks(opRun);

    }


    public void swap_positions()
    {
        String temp = Pos_of_player1;
        Pos_of_player1 = Pos_of_player2;
        Pos_of_player2 = temp;
        player_1_pos.setText("Player_1/" + Pos_of_player1);
        player_2_pos.setText("Player_2/" + Pos_of_player2);
    }


    private void show_combination(Card[] my_board, Card[] op_board)
    {
        String[] my_str = new String[my_board.length];
        String[] op_str = new String[my_board.length];

        for (int i = 0; i < my_board.length; i++)
        {
            my_str[i] = my_board[i].get_name();
            op_str[i] = op_board[i].get_name();
        }

        ArrayList<String> my_board_str = new ArrayList<String>(Arrays.asList(my_str));
        ArrayList<String> op_board_str = new ArrayList<String>(Arrays.asList(op_str));



        Board b1 = new Board(my_board_str);
        Board b2 = new Board(op_board_str);


        String txt1 = "PLAYER 1 :" + b1.Brd_Text_Combination();
        String txt2 = "PLAYER 2 :" + b2.Brd_Text_Combination();

        int winner = compare_combinations(b1.board_comb, b2.board_comb);
        pot_winner = winner;


        String comb_str = txt1 + System.getProperty("line.separator") + txt2;
        game_info.setText(comb_str);
        Print_results(comb_str,winner);
    }

    public void Print_results(String res, int winner)
    {
        if (winner != 0) result.setText("HAND WINNER :" + " PLAYER " + Integer.toString(winner));
        else result.setText("HAND WINNER :" + " !! TIE !! ");
        game_info.setText(res);
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


}
