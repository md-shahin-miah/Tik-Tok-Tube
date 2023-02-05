package com.shahin.tiktoktube;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // boolean to help for game restart
    boolean game_active = true;

    // 0-youtube
    // 1-tiktok
    // 2-Null

    // Winning positions
    int[][] win_position={  {0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6} };
    // Initializing first player choice i.e. youtube
    int activeplayer = 0;
    // In starting all positions are null
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2 , 2, 2};


    public void Tap(View view) {

        // Get location of image tapped
        ImageView img = (ImageView) view;
        // Get Tag of that tapped image in int form e.g. 0,1,2... etc.
        int tapped_img = Integer.parseInt(img.getTag().toString());

        // Checking for reset
        if (!game_active) {
            resetGame(view);
            return;
        }


        // Checking if tapped position is null if null than provide active player
        if (gamestate[tapped_img] == 2) {
            gamestate[tapped_img] = activeplayer;
            // some effects
            img.setTranslationX(-1000f);
            // if active player is 0 means youtube than changing it to 1 means tiktok
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.o);
                activeplayer = 1;
                TextView text = (TextView) findViewById(R.id.status);
                text.setText("Cross's Turn - Tap to play");
                text.setTextColor(Color.parseColor("#032DFD"));

            // if active player is 1 means tiktok than changing it to 0 means youtube
            } else {
                img.setImageResource(R.drawable.close);
                activeplayer = 0;
                TextView text = (TextView) findViewById(R.id.status);
                text.setText("Zero's Turn - Tap to play");
                text.setTextColor(Color.parseColor("#FB0404"));
            }
            // Some Animation Effects
            img.animate()
                    .translationXBy(1000f)
                    .rotation(360)
                    .setDuration(500);
        }

        // Checking for winners
        for (int[] win_pos : win_position) {
            // Here from winner position we taking the positions and checking the set of position have same value (0 or 1) or not
            if (gamestate[win_pos[0]] == gamestate[win_pos[1]] && gamestate[win_pos[1]] == gamestate[win_pos[2]] && gamestate[win_pos[0]] != 2) {
                String winner;
                String color;

                // 0 is for youtube
                if (gamestate[win_pos[0]] == 0) {
                    winner = "Zero Won!";
                    color = "#FB0404";

                // 1 is for tiktok
                } else {
                    winner = "Cross Won!";
                    color = "#032DFD";
                }
                // for restart purpose
                game_active = false;
                TextView text = (TextView) findViewById(R.id.status);
                text.setText(winner);
                text.setTextColor(Color.parseColor(color));

            // Now for draw we have to check all places are filled and iff youtube has not won
            }else{

                // count for how many places are filled
                int draw_count = 0;
                for (int k : gamestate) {
                    // as 2 for null
                    if (k != 2) {
                        draw_count += 1;
                    }
                }
                // Now if all places are fillled
                if (draw_count == 9) {
                    // initially declaring draw if youtube has won than later change it
                    String winner = "Drawn!";
                    String color = "#070707";

                    // Check if youtube won or not
                    for (int[] k : win_position) {
                        if (gamestate[k[0]] == gamestate[k[1]] && gamestate[k[1]] == gamestate[k[2]] && gamestate[k[0]] != 2) {
                            winner = "Zero Won!";
                            color = "#FB0404";

                        }
                    }
                    game_active = false;
                    TextView text = (TextView) findViewById(R.id.status);
                    text.setText(winner);
                    text.setTextColor(Color.parseColor(color));
                }
            }


        }

    }

    // Reset function
    public void resetGame(View view){
        game_active = true;
        activeplayer = 0;
        // Again filing all nine two's in gamestate
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;

        }
        TextView text = (TextView) findViewById(R.id.status);
        text.setText("Zero's Turn - Tap to play");
        text.setTextColor(Color.parseColor("#FB0404"));

        // Deleting images from all places
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}