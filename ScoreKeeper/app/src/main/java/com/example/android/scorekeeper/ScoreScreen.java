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

import static com.example.android.scorekeeper.R.drawable.ball;
import static com.example.android.scorekeeper.R.id.sound;
import static com.example.android.scorekeeper.R.string.goals;

public class ScoreScreen extends AppCompatActivity {


    private int team1_score = 0;
    private int team2_score = 0;
    private int team1_fouls = 0;
    private int team2_fouls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
    }

    public void reset(View view) {

        TextView team1_goals = (TextView) findViewById(R.id.team1_score);
        TextView team2_goals = (TextView) findViewById(R.id.team2_score);
        TextView team1_fouls = (TextView) findViewById(R.id.team1_fouls);
        TextView team2_fouls = (TextView) findViewById(R.id.team2_fouls);

        team1_goals.setText("0");
        team2_goals.setText("0");
        team1_fouls.setText("0");
        team2_fouls.setText("0");


    }

    public void soundStatus(View view) {

        CheckBox sound = (CheckBox) findViewById(R.id.sound);

        if (sound.isChecked())

            sound.setText(R.string.sound_on);
        else
            sound.setText(R.string.sound_off);


    }

    public void imageStatus(View view) {

        CheckBox image = (CheckBox) findViewById(R.id.images);

        if (image.isChecked())
            image.setText(R.string.images_on);

        else
            image.setText(R.string.images_off);
    }


    public void goal(View view) {

        CheckBox image = (CheckBox) findViewById(R.id.images);
        CheckBox sound = (CheckBox) findViewById(R.id.sound);

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

            TextView score = (TextView) findViewById(R.id.team1_score);
            score.setText(String.valueOf(team1_score));
        } else {

            team2_score++;
            TextView score = (TextView) findViewById(R.id.team2_score);
            score.setText(String.valueOf(team2_score));
        }


    }

    public void voidGoal(View view) {

        int team = view.getId();


        if (team == R.id.team1_void_goal) {

            if (team1_score > 0)
                team1_score--;

            TextView score = (TextView) findViewById(R.id.team1_score);
            score.setText(String.valueOf(team1_score));
        } else {

            if (team2_score > 0)
                team2_score--;
            TextView score = (TextView) findViewById(R.id.team2_score);
            score.setText(String.valueOf(team2_score));
        }

    }

    public void foul(View view) {


        CheckBox image = (CheckBox) findViewById(R.id.images);

        CheckBox sound = (CheckBox) findViewById(R.id.sound);

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

            TextView value = (TextView) findViewById(R.id.team1_fouls);
            team1_fouls++;
            value.setText(String.valueOf(team1_fouls));

        } else {

            TextView value = (TextView) findViewById(R.id.team2_fouls);
            team2_fouls++;
            value.setText(String.valueOf(team2_fouls));

        }
    }
}
