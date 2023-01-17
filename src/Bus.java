package src;

public class Bus implements Runnable {
    private SemaphorData semaphorData;
    public int loaded=0;

    public Bus(SemaphorData semaphorData) {
        this.semaphorData = semaphorData;
    }

    private void depart() {
        System.out.println("Bus loaded with " + loaded + " riders and " + semaphorData.waiting + " riders are left");
        System.out.println("======== BUS DEPARTED========= \n");
    }

    @Override
    public void run() {
        try {
            semaphorData.mutex.acquire();              //avoid new riders board to the bus when bus is at stop
                System.out.println("BUS ARRIVED !!! \n");
                System.out.println(semaphorData.waiting + " Riders waiting for Bus");
                System.out.println(Math.min(semaphorData.waiting,50) + " Riders can board to Bus");
                if (semaphorData.waiting > 0) {
                    semaphorData.bus=this;
                    semaphorData.busSemaphor.release();    //Signal riders that bus has arrived
                    semaphorData.boardedSemaphor.acquire();    //Wait untill less than or equal 50 riders board to bus
                }
            depart();
            semaphorData.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
