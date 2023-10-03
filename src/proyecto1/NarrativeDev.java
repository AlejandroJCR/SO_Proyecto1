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
        System.out.println("Narrative dev started");
        
        try {
            // Sleep while producing a narrative
            Thread.sleep(secondsPerDay * 1000 * this.daysPerNarrative);
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
        drive.writeNarrative();
    }
}
