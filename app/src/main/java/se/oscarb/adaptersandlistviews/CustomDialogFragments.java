package se.oscarb.adaptersandlistviews;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by PauloRogerio on 2016-03-01.
 */
public class CustomDialogFragments extends DialogFragment {

    // Instansvariabler
    // Instansen av klassen MainActivity hamnar här
    // Vi undersöker senare om den implementerar vårt interface nedan
    // Om den gör det så kan vi säga att kod för knapptryck finns
    // definierad i klassen activity pekar på (i vårt fall MainActivity)
    CustomDialogClickListener activity;


    /*
        Ett fragment är som en "activity inuti en activity"
        När man "placerar ut" ett fragment "attachar" man det på en activity
        I det här fallet
        Fragment: CustomDialogFragment
        Activity: MainActivity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Verifiera att vår Activity implementerar vårt interface
        // som heter CustomDialogClickListener
        this.activity = (CustomDialogClickListener) activity;

    }

    //Interface med metoder som behöver implementeras
    public interface CustomDialogClickListener {
        public void onCancelClick(DialogFragment dialog);
        public void onDeleteClick(DialogFragment dialog);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Do you want to delete the car?");
        alertDialogBuilder.setTitle("Alert Dialog..");
        alertDialogBuilder.setIcon(R.drawable.ic_favorite_outline_24dp);
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"Cancel Button pressed!", Toast.LENGTH_SHORT).show();
                activity.onCancelClick(CustomDialogFragments.this);
            }
        });

        alertDialogBuilder.setPositiveButton("Delete away!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"Delete Button pressed!", Toast.LENGTH_SHORT).show();
                activity.onDeleteClick(CustomDialogFragments.this);
            }
        });


        //alertDialogBuilder.show();

        return alertDialogBuilder.create();
    }
}
