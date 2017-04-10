package com.example.android.scorekeeper;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.value;
import static com.example.android.scorekeeper.R.dimen.score;
import static com.example.android.scorekeeper.R.drawable.ball;
import static com.example.android.scorekeeper.R.id.sound;
import static com.example.android.scorekeeper.R.id.team1_fouls;
import static com.example.android.scorekeeper.R.string.goals;

public class ScoreScreen extends AppCompatActivity {


    private TextView team1_score_text = null;
    private TextView team2_score_text = null;
    private TextView team1_fouls_text = null;
    private TextView team2_fouls_text = null;
    private CheckBox sound = null;
    private CheckBox image = null;

    private int team1_score = 0;
    private int team2_score = 0;
    private int team1_fouls = 0;
    private int team2_fouls = 0;

    static final String SCORE_1 = "team 1 score";
    static final String SCORE_2 = "team 2 score";
    static final String FOULS_1 = "team 1 fouls";
    static final String FOULS_2 = "team 2 fouls";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        team1_score_text = (TextView) findViewById(R.id.team1_score);
        team2_score_text = (TextView) findViewById(R.id.team2_score);
        team1_fouls_text = (TextView) findViewById(R.id.team1_fouls);
        team2_fouls_text = (TextView) findViewById(R.id.team2_fouls);
        sound = (CheckBox) findViewById(R.id.sound);
        image = (CheckBox) findViewById(R.id.images);

        if (savedInstanceState != null) {

            // Restore value of members from saved state
            team1_score = savedInstanceState.getInt(SCORE_1);
            team2_score = savedInstanceState.getInt(SCORE_2);
            team1_fouls = savedInstanceState.getInt(FOULS_1);
            team2_fouls = savedInstanceState.getInt(FOULS_2);

            team1_score_text.setText(String.valueOf(team1_score));
            team2_score_text.setText(String.valueOf(team2_score));
            team1_fouls_text.setText(String.valueOf(team1_fouls));
            team2_fouls_text.setText(String.valueOf(team2_fouls));
        } else

        {

            team1_score_text.setText("0");
            team2_score_text.setText("0");
            team1_fouls_text.setText("0");
            team2_fouls_text.setText("0");

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(SCORE_1, team1_score);
        savedInstanceState.putInt(SCORE_2, team2_score);
        savedInstanceState.putInt(FOULS_1, team1_fouls);
        savedInstanceState.putInt(FOULS_2, team2_fouls);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void reset(View view) {


        team1_score_text.setText("0");
        team2_score_text.setText("0");
        team1_fouls_text.setText("0");
        team2_fouls_text.setText("0");

        team1_score = 0;
        team2_score = 0;
        team1_fouls = 0;
        team2_fouls = 0;


    }

    public void soundStatus(View view) {

        if (sound.isChecked())

            sound.setText(R.string.sound_on);
        else
            sound.setText(R.string.sound_off);


    }

    public void imageStatus(View view) {


        if (image.isChecked())
            image.setText(R.string.images_on);

        else
            image.setText(R.string.images_off);
    }


    public void goal(View view) {

        if (image.isChecked()) {
            ImageView ball = new ImageView(this);

            ball.setImageResource(R.drawable.ball);

            Toast toast = new Toast(this);

            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(ball);

            toast.show();

        }

        int team = view.getId();

        if (sound.isChecked()) {

            MediaPlayer goal = MediaPlayer.create(this, R.raw.goal);
            goal.start();
        }

        if (team == R.id.team1_goal) {

            team1_score++;

            team1_score_text.setText(String.valueOf(team1_score));

        } else {

            team2_score++;

            team2_score_text.setText(String.valueOf(team2_score));
        }


    }

    public void voidGoal(View view) {

        int team = view.getId();


        if (team == R.id.team1_void_goal) {

            if (team1_score > 0)
                team1_score--;

            team1_score_text.setText(String.valueOf(team1_score));

        } else {

            if (team2_score > 0)
                team2_score--;

            team2_score_text.setText(String.valueOf(team2_score));
        }

    }

    public void foul(View view) {

        if (image.isChecked()) {

            ImageView img_whistle = new ImageView(this);

            img_whistle.setImageResource(R.drawable.whistle_img);

            Toast toast = new Toast(this);

            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(img_whistle);

            toast.show();

        }

        if (sound.isChecked()) {

            MediaPlayer whistle = MediaPlayer.create(this, R.raw.whistle);

            whistle.start();
        }

        int team = view.getId();

        if (team == R.id.team1_foul) {

            team1_fouls++;
            team1_fouls_text.setText(String.valueOf(team1_fouls));

        } else {

            team2_fouls++;
            team2_fouls_text.setText(String.valueOf(team2_fouls));

        }
    }
}
