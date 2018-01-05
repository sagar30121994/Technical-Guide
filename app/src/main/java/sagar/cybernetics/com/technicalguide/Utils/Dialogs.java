package sagar.cybernetics.com.technicalguide.Utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sagar.cybernetics.com.technicalguide.R;
import sagar.cybernetics.com.technicalguide.fragment.EditProfileFragment;

/**
 * Created by sagar on 23/9/17.
 */

public class Dialogs {


    public static void dataDialog(Context ctx){


        final Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.error_dialog);
        dialog.setTitle("");
        Button button = (Button) dialog.findViewById(R.id.declineButton);

        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }
        });



    }



    public static void networkDialog(Context ctx){

        final Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.connectin_dialog);
        dialog.setTitle("");
        Button button = (Button) dialog.findViewById(R.id.declineButton);

        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dialog.dismiss();



            }
        });



    }


    public static void accountDialog(final Context ctx){

        final Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.dialog_account_creation);
        dialog.setTitle("");
        Button button = (Button) dialog.findViewById(R.id.declineButton);

        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditProfileFragment editProfileFragment=new EditProfileFragment();
                ((AppCompatActivity) ctx).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, editProfileFragment)

                        .commit();




                dialog.dismiss();



            }
        });



    }





}
