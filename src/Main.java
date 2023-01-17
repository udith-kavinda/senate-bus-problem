package src;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("=====================Program started==================");

        SemaphorData semaphorData = new SemaphorData();

        Thread busGenerator = new Thread(new BusGenerator(semaphorData));
        Thread riderGenerator = new Thread(new RiderGenerator(semaphorData));

        busGenerator.start();
        riderGenerator.start();

        try {
            busGenerator.join();
            riderGenerator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

