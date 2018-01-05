package sagar.cybernetics.com.technicalguide.Utils;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by sagar on 23/9/17.
 */

public class Animations {
    public  static void animator(RecyclerView.ViewHolder holder , boolean goesDown){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(holder.itemView,"translationY",goesDown==true?250:+250,0);
       // ObjectAnimator animatorTranslateX=ObjectAnimator.ofFloat(holder.itemView,"translationX",-25,25,-20,20,-15,15,-10,10,-5,5,0,0);
       // animatorTranslateX.setDuration(800);
        animatorTranslateY.setDuration(500);
        //animatorTranslateX.start();
        animatorTranslateY.start();



    }


    public  static void animatorRecycler(RecyclerView  holder ){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(holder,"translationY",-250,0);
        // ObjectAnimator animatorTranslateX=ObjectAnimator.ofFloat(holder.itemView,"translationX",-25,25,-20,20,-15,15,-10,10,-5,5,0,0);
        // animatorTranslateX.setDuration(800);
        animatorTranslateY.setDuration(500);
        //animatorTranslateX.start();
        animatorTranslateY.start();



    }





    public  static void animatorTwo(RecyclerView.ViewHolder holder , boolean goesDown){

        //ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(holder.itemView,"translationY",-20,20,-10,10,0,0);
        ObjectAnimator animatorTranslateX=ObjectAnimator.ofFloat(holder.itemView,"translationX",goesDown==true?150:+150,0);
        animatorTranslateX.setDuration(500);
        //animatorTranslateY.setDuration(800);
        animatorTranslateX.start();
        //animatorTranslateY.start();



    }

    public  static void animatorTest(RecyclerView.ViewHolder holder , boolean goesDown){

        //ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(holder.itemView,"translationY",-25,25,-15,15,-5,5,0,0);
        ObjectAnimator animatorTranslateX=ObjectAnimator.ofFloat(holder.itemView,"translationX",goesDown==true?150:-150,0);
        animatorTranslateX.setDuration(500);
        //animatorTranslateY.setDuration(800);
        animatorTranslateX.start();
//        animatorTranslateY.start();



    }


    public  static void animatorSlpash(ImageView imageView){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(imageView,"translationY",250,0);
        animatorTranslateY.setDuration(700);
        animatorTranslateY.start();




    }
   /* public  static void animatorSlpashRotate(ImageView imageView){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(imageView,"",250,0);
        animatorTranslateY.setDuration(700);
        animatorTranslateY.start();




    }
*/


    public  static void animatorTextProfile(TextView imageView){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(imageView,"translationX",250,0);
        animatorTranslateY.setDuration(800);
        animatorTranslateY.start();




    }



    public  static void animatorTextMCQ(RadioButton radioGroup){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(radioGroup,"translationX",250,0);
        animatorTranslateY.setDuration(400);
        animatorTranslateY.start();




    }


    public  static void animatorImageProfile(ImageView imageView){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(imageView,"translationX",250,0);
        animatorTranslateY.setDuration(800);
        animatorTranslateY.start();




    }


    public  static void animatorSlpash(TextView textView){

        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(textView,"translationY",350,0);

        // ObjectAnimator animatorTranslateX=ObjectAnimator.ofFloat(holder.itemView,"translationX",goesDown==true?250:-250,0);
        // animatorTranslateX.setDuration(800);
        animatorTranslateY.setDuration(700);
        //animatorTranslateX.start();
        animatorTranslateY.start();




    }



}
