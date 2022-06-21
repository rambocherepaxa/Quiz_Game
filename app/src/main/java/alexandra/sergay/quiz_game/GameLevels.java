package alexandra.sergay.quiz_game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    MediaPlayer mediaPlayer; // This value will contain button click sound

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mediaPlayer = MediaPlayer.create(this,R.raw.button_pressed);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        // opening game on full cellphone screen

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.start(); // button click sound

               try { // returns player from game page to main page
                   Intent intent = new Intent(GameLevels.this, MainActivity.class);
                   startActivity(intent);finish();
               }
               catch (Exception e){
                    // no especial threat in error case (it's need only for keeping app work)
               }
            }
        });

        // Level 2 button
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mediaPlayer.start(); // button click sound

                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {
                    // no especial threat in error case (it's need only for keeping app work)
                }
            }
        });

        // Level 3 button
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mediaPlayer.start(); // button click sound

                    Intent intent = new Intent(GameLevels.this, Level3.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {
                    // no especial threat in error case (it's need only for keeping app work)
                }
            }
        });

        // Level 1 button
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mediaPlayer.start();

                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);finish();
                }
                catch (Exception e)
                {
                    // no especial threat in error case (it's need only for keeping app work)
                }
            }
        });

    }

    // back button
    @Override
    public void onBackPressed(){
        try {

            mediaPlayer = MediaPlayer.create(this,R.raw.button_pressed);
            mediaPlayer.start();

            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);finish();
        }
        catch (Exception e){
        // no especial threat in error case (it's need only for keeping app work)
        }
    }
}