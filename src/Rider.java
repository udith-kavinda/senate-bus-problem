package src;

public class Rider implements Runnable {
    private SemaphorData semaphorData;
    private Integer riderId;

    public Rider(SemaphorData semaphorData, Integer riderId) {
        this.semaphorData = semaphorData;
        this.riderId = riderId;
    }

    private void board() {
        System.out.println("Rider " + this.riderId + " board to bus.");
    }

    @Override
    public void run() {
        try {

            semaphorData.mutex.acquire();      //protect rider count by avoiding new riders when bus is at stop
                semaphorData.waiting += 1;
                System.out.println("Rider " + this.riderId + " on waiting");
            semaphorData.mutex.release();

            semaphorData.busSemaphor.acquire();    //Waiting for bus, lock when boarding so only 1 board at same time

            board();

            semaphorData.bus.loaded+=1;
            if(semaphorData.bus.loaded==50 || semaphorData.bus.loaded==semaphorData.waiting){
                semaphorData.waiting=Math.max(semaphorData.waiting - 50, 0);
                semaphorData.boardedSemaphor.release();
            }else {
                semaphorData.busSemaphor.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

