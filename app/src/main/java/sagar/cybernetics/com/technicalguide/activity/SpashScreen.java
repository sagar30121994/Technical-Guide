package sagar.cybernetics.com.technicalguide.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;

import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.Utils.Animations;

public class SpashScreen extends AppCompatActivity {

    private GoogleApiClient mGoogleApiClient;
    TextView textView,copy;
    ImageView text,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);



        getSupportActionBar().hide();
        text=(ImageView)findViewById(R.id.text);
        image=(ImageView)findViewById(R.id.imageView);

        textView=(TextView)findViewById(R.id.ver);

        copy=(TextView)findViewById(R.id.copy);




        Animations.animatorSlpash(text);
        RotateAnimation rotate = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF,
                0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(700);
        image.startAnimation(rotate);



        Animations.animatorTextProfile(textView);
        Animations.animatorTextProfile(copy);

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(1200);
                    Intent StartMainScreen = new Intent(SpashScreen.this,Home.class);
                    startActivity(StartMainScreen);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();



    }


    }

