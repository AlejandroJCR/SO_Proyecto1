package proyecto1;

public class NarrativeDev extends Employee {
    Drive drive;
    int daysPerNarrative;
    
    public NarrativeDev(int daysPerNarrative, GameStudio studio) {
        super(10, studio);
        this.drive = studio.getDrive();
        this.daysPerNarrative = daysPerNarrative;
    }

    @Override
    public void doWork() {
        int secondsPerDay = 3;      
        try {
            // Sleep while producing a narrative
            Thread.sleep(secondsPerDay * 1000 * this.daysPerNarrative);
            drive.addNarratives();
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
    }
}
