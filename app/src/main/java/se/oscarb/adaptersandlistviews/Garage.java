package se.oscarb.adaptersandlistviews;


import java.util.ArrayList;

/*
    Klass för att skapa bil-objekt i
 */
public class Garage {
    // Instansvariabler
    ArrayList<Car> cars;

    // Konstruktor
    public Garage() {
        cars = new ArrayList<>();
        addCars();
    }

    // Instansmetoder

    public void addCars() {
        // Lägg till bilar
        cars.add(new Car("Bmw_720", R.drawable.bmw_720));
        cars.add(new Car("Cadillac", R.drawable.cadillac));
        cars.add(new Car("Camaro", R.drawable.camaro));
        cars.add(new Car("Chevrolet", R.drawable.chevrolet));
        cars.add(new Car("Chrysler", R.drawable.chrysler));
        cars.add(new Car("Dodge", R.drawable.dodge));
        cars.add(new Car("Ferrari", R.drawable.ferrari));
        cars.add(new Car("Fiat_spider", R.drawable.fiat_spider));
        cars.add(new Car("Honda", R.drawable.honda));
        cars.add(new Car("Jeep", R.drawable.jeep));
        cars.add(new Car("Lamborghini", R.drawable.lamborghini));
        cars.add(new Car("Mercedes", R.drawable.mercedes));
        cars.add(new Car("Nissan", R.drawable.nissan));
        cars.add(new Car("Peugeot", R.drawable.peugeot));
        cars.add(new Car("Renault", R.drawable.renault));
        cars.add(new Car("Saab", R.drawable.saab));
        cars.add(new Car("Toyota", R.drawable.toyota));
        cars.add(new Car("Volkswagen", R.drawable.volkswagen));
        cars.add(new Car("Volvo_xc90", R.drawable.volvo_xc90));
    }

    // Returnera alla bilar som en ArrayList<Car>
    public ArrayList<Car> getCars() {
        return cars;
    }



}
