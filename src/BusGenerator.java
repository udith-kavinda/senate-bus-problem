package src;

import java.util.Random;

public class BusGenerator implements Runnable {
    private SemaphorData semaphorData;
    private float meanTime = 2 * 60f * 1000;
    public static Random random;

    public BusGenerator(SemaphorData semaphorData) {
        this.semaphorData = semaphorData;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                float lambda = 1 / meanTime;
                Thread.sleep(Math.round(-Math.log(1 - random.nextFloat()) / lambda));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Bus(semaphorData)).start();
        }
    }
}

