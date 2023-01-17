package src;
import java.util.concurrent.Semaphore;

public class SemaphorData {
    public int waiting;
    public Semaphore busSemaphor;
    public Semaphore boardedSemaphor;
    public Semaphore mutex;
    public Bus bus;

    public SemaphorData() {
        this.waiting = 0;
        this.busSemaphor = new Semaphore(0);        //signal when bus arrive, wait on bus
        this.boardedSemaphor = new Semaphore(0);        //bus wait on till all aboard
        this.mutex = new Semaphore(1);          //rider count protect, avoid new riders when bus is at stop
    }

}
