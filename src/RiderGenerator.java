package src;

import java.util.Random;

public class RiderGenerator implements Runnable {
    private SemaphorData semaphorData;
    private float meanTime = 3f * 1000;
    public static Random random;

    public RiderGenerator(SemaphorData semaphorData) {
        this.semaphorData = semaphorData;
        random = new Random();
    }

    @Override
    public void run() {
        Integer riderId = 1;
        while (true) {
            new Thread(new Rider(semaphorData, riderId)).start();
            riderId+=1;
            try {
                float lambda = 1 / meanTime;
                Thread.sleep(Math.round(-Math.log(1 - random.nextFloat()) / lambda));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

