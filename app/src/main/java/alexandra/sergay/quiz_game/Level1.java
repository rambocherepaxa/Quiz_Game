package alexandra.sergay.quiz_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

// Those values will contain sounds of game accompaniment
    MediaPlayer mediaPlayer;
    MediaPlayer error_sound;
    MediaPlayer right_sound;
    MediaPlayer victory_sound;

    Dialog dialog; // This value will contain page of tutorial dialog before start
    Dialog dialogEnd; // This value will contain page of victory and reference to next level

//Those values will contain a number value that will be shown on the pictures
    public int numLeft;
    public int numRight;

    Array array = new Array(); // This array will contain list of pictures
    Random random = new Random(); // This value will mark which picture to show from array
    public int count = 0; // This counter will save the number of right answered questions


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Assigning sounds to each variable separately for its sound
        mediaPlayer = MediaPlayer.create(this,R.raw.button_pressed);
        error_sound = MediaPlayer.create(this,R.raw.incorrect_answer);
        right_sound = MediaPlayer.create(this,R.raw.right_answer);
        victory_sound = MediaPlayer.create(this,R.raw.level_finish);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        // setting level text marker
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        // uploading right and left images
        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        // uploading text under right and left picture
        final TextView text_left = findViewById(R.id.text_left);

        final TextView text_right = findViewById(R.id.text_right);

        // opening game page on full cellphone screen
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mediaPlayer.start();

                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();
            }
        });
        Button btncontinue = (Button) dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                dialog.dismiss();
            }
        });
        dialog.show();

        //___________________________________________________
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    mediaPlayer.start();

                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();
            }
        });
        Button btncontinue2 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    mediaPlayer.start();

                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
            }
        });

        //___________________________________________________

        Button btn_back = (Button) findViewById((R.id.button_back1));
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    mediaPlayer.start();

                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });

        final int[] progress = {
                R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,R.id.point6,
                R.id.point7,R.id.point8,R.id.point9,R.id.point10,
        };

        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]);
        text_left.setText(array.texts1[numLeft]);

        numRight = random.nextInt(10);

        while (numRight == numLeft) {
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images1[numRight]);
        text_right.setText(array.texts1[numRight]);

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    img_right.setEnabled(false);
                    if (numLeft > numRight){
                        right_sound.start();
                        img_left.setImageResource(R.drawable.img_true);
                    }
                    else{
                        error_sound.start();
                        img_left.setImageResource(R.drawable.image_false);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(numLeft>numRight){
                        if(count<10){
                            count += 1;
                        }

                        for(int i = 0; i < 10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    else{
                        if(count>0){
                            if(count == 1) {
                                count = 0;
                            }
                            else{
                                count -= 2;

                                for(int i = 0; i < 9; i++){
                                    TextView tv = findViewById(progress[i]);
                                    tv.setBackgroundResource(R.drawable.style_points);
                                }
                                for(int i = 0; i < count; i++){
                                    TextView tv = findViewById(progress[i]);
                                    tv.setBackgroundResource(R.drawable.style_points_green);
                                }
                            }
                        }
                    }
                    if(count == 10){

                        victory_sound.start();
                        dialogEnd.show();
                    }
                    else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        numRight = random.nextInt(10);

                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });
                //Finished left card operation


        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    img_left.setEnabled(false);
                    if (numLeft < numRight){
                        right_sound.start();
                        img_right.setImageResource(R.drawable.img_true);
                    }
                    else{
                        error_sound.start();
                        img_right.setImageResource(R.drawable.image_false);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(numLeft<numRight){
                        if(count<10){
                            count += 1;
                        }

                        for(int i = 0; i < 10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    else{
                        if(count>0){
                            if(count == 1) {
                                count = 0;
                            }
                            else{
                                count -= 2;

                                for(int i = 0; i < 9; i++){
                                    TextView tv = findViewById(progress[i]);
                                    tv.setBackgroundResource(R.drawable.style_points);
                                }
                                for(int i = 0; i < count; i++){
                                    TextView tv = findViewById(progress[i]);
                                    tv.setBackgroundResource(R.drawable.style_points_green);
                                }
                            }
                        }
                    }
                    if(count == 10){

                        victory_sound.start();
                        dialogEnd.show();
                    }
                    else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        numRight = random.nextInt(10);

                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });

        //Finished right card action
    }

    @Override
    public void onBackPressed()
    {
        try {
            mediaPlayer.start();
            Intent intent = new Intent(Level1.this,GameLevels.class);
            startActivity(intent);
            finish();
        }
        catch(Exception e){}
    }
}