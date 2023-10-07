package proyecto1;

public class DLCDev extends Thread {
    Drive drive;
    int daysPerDLC;
    int secondsPerDay;
    
    public DLCDev(int daysPerDLC, GameStudio studio) {
        this.drive = studio.getDrive();
        this.daysPerDLC = daysPerDLC;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Sleep while producing a narrative
                Thread.sleep(secondsPerDay * 1000 * this.daysPerDLC);
                drive.addDLCs();
            }
        } catch(InterruptedException e){
            //Thread killed
        }
    }
}
