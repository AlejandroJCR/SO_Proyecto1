package proyecto1;

public class LevelDev extends Employee {
    Drive drive;
    int daysPerLevel;
    
    public LevelDev(int daysPerLevel, GameStudio studio) {
        super(10, studio);
        this.drive = studio.getDrive();
        this.daysPerLevel = daysPerLevel;
    }

    @Override
    public void doWork() {
        int secondsPerDay = 3;
        System.out.println("Level dev started");
        
        try {
            // Sleep while producing a narrative
            Thread.sleep(secondsPerDay * 1000 * this.daysPerLevel);
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
        drive.writeLevel();
    }
}