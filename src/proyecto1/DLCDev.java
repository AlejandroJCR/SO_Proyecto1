package proyecto1;

public class DLCDev extends Employee {
    Drive drive;
    int daysPerDLC;
    int secondsPerDay;
    
    public DLCDev(int daysPerDLC, GameStudio studio) {
        super(17, studio);
        this.drive = studio.getDrive();
        this.daysPerDLC = daysPerDLC;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void doWork() {
        try {
            // Sleep while producing a narrative
            Thread.sleep(secondsPerDay * 1000 * this.daysPerDLC);
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
        drive.addDLCs();
    }
}
