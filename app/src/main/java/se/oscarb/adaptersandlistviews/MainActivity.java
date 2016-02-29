package se.oscarb.adaptersandlistviews;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Instansvariabel
    ArrayList<String> carStrings;
    Context context;


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
                R.layout.my_text_view,
                carStrings);

        // Eller

        // Skapa anpassad adapter med egen layout
        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(this, R.layout.my_text_view, carStrings);


        // Skapa en anpassad adapter för en egen layout och en samling Cars
        final MyCarArrayAdapter myCarArrayAdapter = new MyCarArrayAdapter(this, android.R.layout.simple_list_item_1, carsFromGarage);

        // Använd vår adapter
        //myListView.setAdapter(myBuiltInArrayAdapter);
        //myListView.setAdapter(myCustomAdapter);
        myListView.setAdapter(myCarArrayAdapter);

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Position: " + position + " id: " + id, Toast.LENGTH_LONG).show();
                Car carToRemovee = myCarArrayAdapter.getItem(position);
                myCarArrayAdapter.remove(carToRemovee);
                return false;
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogAlertTest alert = new DialogAlertTest();
                alert.show(getFragmentManager(), "Será que funciona?");

            }
        });




//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "Position: " + position + " id: " + id, Toast.LENGTH_LONG).show();
//                Car carToRemovee = myCarArrayAdapter.getItem(position);
//                myCarArrayAdapter.remove(carToRemovee);
//            }
//        });


    }
}
