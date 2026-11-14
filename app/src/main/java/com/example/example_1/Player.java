package com.example.example_1;


import android.nfc.FormatException;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Player {

    private int stack;
    private int BET;
    private int MIN_BET;
    private int MAX_BET;
    private int BET_TO_CALL;
    private int BB;
    private  int POT;
    private String Action_to_talk;
    private String Position;
    private String Action_str;
    private boolean acted;
    private boolean all_in;
    private boolean fold;
    private boolean HUMAN;
    private boolean GAVE_OVER;
    private boolean BLIND_SETTED;
    private String Player_name;
    private ArrayList<Card> Hole_Card;
    private ArrayList<Card> All_cards;
    private Combination combination;
    private Board player_board;
    private CountDownTimer Progress_timer;
    private CountDownTimer Wait_timer;
    private ProgressBar progress_time_remaining;
    private TextView Action_Textview;
    private TextView Stack_Textview;
    private TextView Position_Textview;
    private TextView pot_textview;
    private ImageView Card1_Imageview;
    private ImageView Card2_Imageview;

    //FOR HUMAN
    private Button FOLD_BUTTON, CHECK_BUTTON, CALL_BUTTON, BETR_BUTTON;
    private SeekBar BETR_SEEKBAR;
    private TextView BETR_TEXTVIEW, COMB_TEXTVIEW;
    //FOR HUMAN

    //FOR BOT
    private Handler Bot_action_handler;
    private Runnable Bot_Runnable;
    //FOR BOT

    public Player()
    {
        Hole_Card = new ArrayList<Card>(2);
        combination = new Combination();
        All_cards = new ArrayList<Card>();
        //player_board = new Board();
        BET = 0;
        POT = 0;
        BLIND_SETTED = false;
        if(!HUMAN) Bot_action_handler = new Handler();
    }

    // Getters and Setters

    public int getStack()
    {
        return  this.stack;
    }

    public int getBET()
    {
        return this.BET;
    }

    public String getPosition()
    {
        return this.Position;
    }

    public String getAction_str()
    {
        return this.Action_str;
    }

    public boolean isActed()
    {
        return this.acted;
    }

    public boolean isAll_in()
    {
        return this.all_in;
    }

    public boolean isHUMAN()
    {
        return this.HUMAN;
    }

    public boolean isFold() {
        return this.fold;
    }

    public boolean isGAVE_OVER() {
        return this.GAVE_OVER;
    }

    public boolean isBLIND_SETTED() {
        return BLIND_SETTED;
    }

    public String getPlayer_name()
    {
        return this.Player_name;
    }

    public ProgressBar getProgress_time_remaining()
    {
        return this.progress_time_remaining;
    }

    public TextView getAction_Textview()
    {
        return this.Action_Textview;
    }

    public TextView getStack_Textview() {
        return this.Stack_Textview;
    }

    public TextView getPosition_Textview()
    {
        return this.Position_Textview;
    }

    public TextView getPot_textview() {
        return this.pot_textview;
    }

    public TextView getCOMB_TEXTVIEW() {
        return this.COMB_TEXTVIEW;
    }

    public ImageView getCard1_Imageview() {
        return this.Card1_Imageview;
    }

    public ImageView getCard2_Imageview() {
        return this.Card2_Imageview;
    }

    public Button getFOLD_BUTTON() {
        return this.FOLD_BUTTON;
    }

    public Button getCHECK_BUTTON() {
        return this.CHECK_BUTTON;
    }

    public Button getCALL_BUTTON() {
        return this.CALL_BUTTON;
    }

    public Button getBETR_BUTTON() {
        return this.BETR_BUTTON;
    }

    public SeekBar getBETR_SEEKBAR() {
        return this.BETR_SEEKBAR;
    }

    public TextView getBETR_TEXTVIEW() {
        return this.BETR_TEXTVIEW;
    }

    public int getMIN_BET() {
        return this.MIN_BET;
    }

    public int getMAX_BET() {
        return this.MAX_BET;
    }

    public int getBET_TO_CALL() {
        return this.BET_TO_CALL;
    }

    public int getBB() {
        return this.BB;
    }

    public int getPOT() {
        return this.POT;
    }

    public String getAction_to_talk()
    {
        return this.Action_to_talk;
    }

    public ArrayList<Card> getHole_Card() {
        return this.Hole_Card;
    }

    public Combination getCombination() {
        return this.combination;
    }

    public Board getPlayer_board() {
        return this.player_board;
    }

    public void setStack(int s)
    {
        this.stack = s;
    }

    public void setBET(int b) {
        this.BET = b;
    }

    public void setPosition(String p)
    {
        this.Position = p;
    }

    public void setAction_str(String a)
    {
        this.Action_str = a;
    }

    public void setActed(boolean a)
    {
        this.acted = a;
    }

    public void setAll_in(boolean a)
    {
        this.all_in = a;
    }

    public void setFold(boolean f) {
        this.fold = f;
    }

    public void setHUMAN(boolean h)
    {
        this.HUMAN = h;
    }

    public void setGAVE_OVER(boolean g) {
        this.GAVE_OVER = g;
    }

    public void setBLIND_SETTED(boolean b) {
        this.BLIND_SETTED = b;
    }

    public void setPlayer_name(String n) {
        this.Player_name = n;
    }

    public void setProgress_time_remaining(ProgressBar p) {
        this.progress_time_remaining = p;
    }

    public void setAction_Textview(TextView a) {
        Action_Textview = a;
    }

    public void setStack_Textview(TextView s) {
        this.Stack_Textview = s;
    }

    public void setPosition_Textview(TextView p) {
        this.Position_Textview = p;
    }

    public void setPot_textview(TextView t) {
        this.pot_textview = t;
    }

    public void setCOMB_TEXTVIEW(TextView c) {
        this.COMB_TEXTVIEW = c;
    }

    public void setCard1_Imageview(ImageView i) {
        Card1_Imageview = i;
    }

    public void setCard2_Imageview(ImageView i) {
        Card2_Imageview = i;
    }

    public void setMIN_BET(int m) {
        this.MIN_BET = m;
    }

    public void setMAX_BET(int m) {
        this.MAX_BET = m;
    }

    public void setBET_TO_CALL(int c) {
        this.BET_TO_CALL = c;
    }

    public void setBB(int b) {
        this.BB = b;
    }

    public void setPOT(int p) {
        this.POT = p;
    }

    public void setAction_to_talk(String a) {
        Action_to_talk = a;
    }

    public void setHole_Card(ArrayList<Card> h) {
        this.Hole_Card.set(0, h.get(0));
        this.Hole_Card.set(1, h.get(1));
    }

    public void setCombination(Combination c) {
        this.combination = c;
    }

    public void setPlayer_board(Board p) {
        this.player_board = p;
    }

    public void setFOLD_BUTTON(Button b) {
        this.FOLD_BUTTON = b;
        FOLD_BUTTON.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FOLD_BTN_CLICKED();
            }
        });
    }

    public void setCHECK_BUTTON(Button b) {
        this.CHECK_BUTTON = b;
        CHECK_BUTTON.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                CHECK_BTN_CLICKED();
            }
        });
    }

    public void setCALL_BUTTON(Button b) {
        this.CALL_BUTTON = b;
        CALL_BUTTON.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                CALL_BTN_CLICKED();
            }
        });
    }

    public void setBETR_BUTTON(Button b) {
        this.BETR_BUTTON = b;
        BETR_BUTTON.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                BETR_BTN_CLICKED();
            }
        });
    }

    public void setBETR_TEXTVIEW(TextView t) {
        this.BETR_TEXTVIEW = t;
    }

    public void setBETR_SEEKBAR(SeekBar s) {

        this.BETR_SEEKBAR = s;
        BETR_SEEKBAR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int y;
                y = (stack - MIN_BET)*i/100 + MIN_BET;
                BETR_TEXTVIEW.setText(Integer.toString(y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void add_to_holeCard(Card c)
    {
        this.Hole_Card.add(new Card(c.get_value(), c.get_colour()));
        add_toAllCards(c);

    }

    public void add_toAllCards(Card c)
    {
        this.All_cards.add(new Card(c.get_value(), c.get_colour()));
        if (All_cards.size() >= 5)
        {
            ArrayList<String> s = new ArrayList<String>(All_cards.size());
            for (int i = 0; i < All_cards.size(); i++) s.add(All_cards.get(i).get_name());
            player_board = new Board(s);
            setCombination(player_board.board_comb);
        }
    }

    public void Clear_Cards()
    {
        this.Hole_Card.clear();
        this.All_cards.clear();
    }

    public int find_pot()
    {
        int r = 0;
        try {
            r = Integer.parseInt(pot_textview.getText().toString());
        }catch (Exception e){};

        return r;
    }


    // Getters and Setters


    //BUTTON LISTENERS
    public void FOLD_BTN_CLICKED()
    {
        setAction_str("FOLD");
        setFold(true);
        setBET(0);
        Action_Textview.setText(getAction_str());

        Progress_timer.cancel();
        progress_time_remaining.setProgress(100);

        if (HUMAN)
        {
            FOLD_BUTTON.setClickable(false);
            CHECK_BUTTON.setClickable(false);
            CALL_BUTTON.setClickable(false);
            BETR_BUTTON.setClickable(false);

            BETR_SEEKBAR.setClickable(false);
            BETR_SEEKBAR.setProgress(0);
            BETR_TEXTVIEW.setText("0");
        }
        if (!HUMAN) Bot_action_handler.removeCallbacks(Bot_Runnable);
    }
    public void CHECK_BTN_CLICKED()
    {
        setAction_str("CHECK");
        setBET(0);
        Action_Textview.setText(getAction_str());

        Progress_timer.cancel();
        progress_time_remaining.setProgress(100);

        if (HUMAN)
        {
            FOLD_BUTTON.setClickable(false);
            CHECK_BUTTON.setClickable(false);
            CALL_BUTTON.setClickable(false);
            BETR_BUTTON.setClickable(false);
            BETR_SEEKBAR.setClickable(false);
            BETR_SEEKBAR.setProgress(0);
            BETR_TEXTVIEW.setText("0");
        }
        if (!HUMAN) Bot_action_handler.removeCallbacks(Bot_Runnable);
    }
    public void CALL_BTN_CLICKED()
    {
        int temp = BET_TO_CALL;
        if (BET_TO_CALL - BET > stack) temp = stack + BET;

        setAction_str("CALL");

        int gap = temp - BET;
        BET = temp;

        Action_Textview.setText(getAction_str() + " " + Integer.toString(BET));

        setStack(stack - gap);
        POT = find_pot() + gap;
        pot_textview.setText(Integer.toString(POT));

        Stack_Textview.setText(Integer.toString(stack));
        if (stack == 0) all_in = true;


        Progress_timer.cancel();
        progress_time_remaining.setProgress(100);

        if (HUMAN)
        {
            FOLD_BUTTON.setClickable(false);
            CHECK_BUTTON.setClickable(false);
            CALL_BUTTON.setClickable(false);
            BETR_BUTTON.setClickable(false);

            BETR_SEEKBAR.setClickable(false);
            BETR_SEEKBAR.setProgress(0);
            BETR_TEXTVIEW.setText("0");
        }
        if (!HUMAN) Bot_action_handler.removeCallbacks(Bot_Runnable);
    }
    public void BETR_BTN_CLICKED()
    {
        if (BET_TO_CALL == 0) setAction_str("BET");
        else setAction_str("RAISE");

        int pre_bet = BET;
        BET = Integer.parseInt(BETR_TEXTVIEW.getText().toString());
        int gap = BET - pre_bet;
        stack = stack - gap;
        POT = find_pot() + gap;

        Action_Textview.setText(getAction_str() + " " + Integer.toString(BET));
        pot_textview.setText(Integer.toString(POT));
        Stack_Textview.setText(Integer.toString(stack));

        if (stack == 0) all_in = true;


        Progress_timer.cancel();
        progress_time_remaining.setProgress(100);

        if (HUMAN)
        {
            FOLD_BUTTON.setClickable(false);
            CHECK_BUTTON.setClickable(false);
            CALL_BUTTON.setClickable(false);
            BETR_BUTTON.setClickable(false);

            BETR_SEEKBAR.setClickable(false);
            BETR_SEEKBAR.setProgress(0);
            BETR_TEXTVIEW.setText("0");
        }
        if (!HUMAN) Bot_action_handler.removeCallbacks(Bot_Runnable);
    }
    //BUTTON LISTENERS



    public void Action()
    {
        if (isBLIND_SETTED() == false && (getPosition() == "SB" || getPosition() == "BB") ) blind_setting_action();
        else
        {
            Progress_timer = new CountDownTimer(15000,10) {
                @Override
                public void onTick(long l) {
                    int p = (int)l/150;
                    progress_time_remaining.setProgress(p);
                }

                @Override
                public void onFinish() {

                }
            }.start();

            if (HUMAN) Human_Action();
            else Bot_Action();

            Wait_for_Action();
        }
    }

    public void blind_setting_action()
    {
        if (getPosition() == "SB") blind_setting_action_do(BB/2);
        else blind_setting_action_do(BB);
    }

    public void blind_setting_action_do(int blind)
    {
        if (blind > stack) blind = stack;

        setAction_str("BET");
        setBET(blind);
        stack = stack - BET;
        POT = find_pot() + BET;

        Action_Textview.setText(Action_str + " " + Integer.toString(blind));
        getStack_Textview().setText(Integer.toString(stack));
        pot_textview.setText(Integer.toString(POT));

        if (stack == 0) all_in = true;
        setActed(true);
        setBLIND_SETTED(true);
    }

    public void Human_Action()
    {
        if (BET_TO_CALL <= BET)
        {
            FOLD_BUTTON.setClickable(false);
            CHECK_BUTTON.setClickable(true);
            CALL_BUTTON.setClickable(false);
            BETR_BUTTON.setClickable(true);

            MIN_BET = BB;
            BETR_TEXTVIEW.setText(Integer.toString(MIN_BET));
            BETR_SEEKBAR.setClickable(true);
        }
        else
        {
            FOLD_BUTTON.setClickable(true);
            CHECK_BUTTON.setClickable(false);
            CALL_BUTTON.setClickable(true);
            //BETR_BUTTON.setClickable(true);

            if (2*BET_TO_CALL <= stack)
            {
                BETR_BUTTON.setClickable(true);
                MIN_BET = 2*BET_TO_CALL;
                BETR_TEXTVIEW.setText(Integer.toString(MIN_BET));
                BETR_SEEKBAR.setClickable(true);
            }
            else if (BET_TO_CALL <= stack)
            {
                BETR_BUTTON.setClickable(true);
                MIN_BET = stack;
                BETR_TEXTVIEW.setText(Integer.toString(MIN_BET));
                BETR_SEEKBAR.setClickable(true);
            }
        }
    }

    public void Bot_Action()
    {
        Bot_Runnable = new Runnable() {
            @Override
            public void run() {
                Bot_Action_do();
            }
        };
        Bot_action_handler.postDelayed(Bot_Runnable, 5000);
    }

    public void Bot_Action_do()
    {
        if (BET_TO_CALL <= BET) CHECK_BTN_CLICKED();
        else CALL_BTN_CLICKED();
    }

    public void Wait_for_Action()
    {
        setActed(false);
        setAction_str("NONE");

        Wait_timer = new CountDownTimer(15000,50) {
            @Override
            public void onTick(long l) {
                if (getAction_str() != "NONE" && isActed() == false)
                {
                    setActed(true);
                    Player_Acted();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


    public void Player_Acted()
    {
        Wait_timer.cancel();
    }


}
