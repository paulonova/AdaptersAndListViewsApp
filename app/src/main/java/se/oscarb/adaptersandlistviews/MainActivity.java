package se.oscarb.adaptersandlistviews;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomDialogFragments.CustomDialogClickListener  {

    // Instansvariabel
    ArrayList<String> carStrings;
    MyCarArrayAdapter myCarArrayAdapter;
    private int lastClickPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initiera vår ArrayList carStrings med strängar
        carStrings = new ArrayList<>();
        carStrings.add("Volvo"); // Notera att Volvo är en String, inte ett Car-objekt...


        // En bättre lista över bilar (med objekt av typen Car)
        Garage myGarage = new Garage();
        ArrayList<Car> carsFromGarage = myGarage.getCars();


        // Hämta vår ListView
        final ListView myListView = (ListView) findViewById(R.id.my_list_view);

        // Skapa adapter med egen TextView
        ArrayAdapter<String> myBuiltInArrayAdapter = new ArrayAdapter<>(this,
                R.layout.my_text_view, carStrings);

        // Eller

        // Skapa anpassad adapter med egen layout
        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(this, R.layout.my_text_view, carStrings);


        // Skapa en anpassad adapter för en egen layout och en samling Cars
        myCarArrayAdapter = new MyCarArrayAdapter(this, android.R.layout.simple_list_item_1, carsFromGarage);

        // Använd vår adapter
        //myListView.setAdapter(myBuiltInArrayAdapter);
        //myListView.setAdapter(myCustomAdapter);
        myListView.setAdapter(myCarArrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: Skapa en Dialogrutan med bilens logotyp eller bild!
                Toast.makeText(MainActivity.this, "Position: " + position + " id: " + id, Toast.LENGTH_LONG).show();

                // Bilen som ska visas
                Car carToDisplay = myCarArrayAdapter.getItem(position);

                // Hämta storleken på skärmen
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);

                // Vad ska hända när man klickar kort på knappen?
                // Skapa en dialogruta med bilens logotyp i!
                AlertDialog.Builder logoDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                // Skapa en View som du sedan byter ut bilden i viewn så den
                // är bilens logotyp

                View logotypeView = getLayoutInflater().inflate(R.layout.car_logotype, null, false);
                ImageView logotypeImage = (ImageView) logotypeView.findViewById(R.id.logotype_image);
                Bitmap optimizedLogo = ResizeImage.getOptimizedBitmap(MainActivity.this, carToDisplay.getLogotypeResourceId(), metrics.widthPixels, metrics.heightPixels);

                logotypeImage.setImageBitmap(optimizedLogo);


                logoDialogBuilder.setView(logotypeView);

                logoDialogBuilder.create();
                logoDialogBuilder.show();

            }
        });



        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CustomDialogFragments dialog = new CustomDialogFragments();
                setLastClickPosition(position);

                // Vilken bil ska tas bort?
                Car carToRemove = myCarArrayAdapter.getItem(getLastClickPosition());

                // Skicka med vilken bil som ska tas bort till dialogrutan
                dialog.setCar(carToRemove);
                dialog.show(getSupportFragmentManager(), "CustomDialogFragments");

                //returnera true så att LongClick inte också räknas som click
                return true;
            }
        });

    }

    @Override
    public void onCancelClick(DialogFragment dialog) {
        // Kod som ska köras när man clickar på Cancel
        Toast.makeText(this, "No car deleted!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDeleteClick(DialogFragment dialog) {
        Toast.makeText(this, "Car deleted!", Toast.LENGTH_LONG).show();
        Car carToRemove = myCarArrayAdapter.getItem(getLastClickPosition());
        myCarArrayAdapter.remove(carToRemove);
    }

    // Getters and Setters
    public int getLastClickPosition() {
        return lastClickPosition;
    }

    public void setLastClickPosition(int lastClickPosition) {
        this.lastClickPosition = lastClickPosition;
    }
}
