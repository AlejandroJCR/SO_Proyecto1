package proyecto1;

public class NarrativeDev extends Thread {
    Drive drive;
    int daysPerNarrative;
    int secondsPerDay;
    
    public NarrativeDev(int daysPerNarrative, GameStudio studio) {
        this.drive = studio.getDrive();
        this.daysPerNarrative = daysPerNarrative;
        this.secondsPerDay = studio.config.secondsPerDay;
    }
    
    @Override
    public void run() {   
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Sleep while producing a narrative
                Thread.sleep(secondsPerDay * 1000 * this.daysPerNarrative);
                drive.addNarratives();
            }
        } catch (InterruptedException e) {
            //Thread killed
        }  
    }
}
