package proyecto1;

public class LevelDev extends Employee {
    Drive drive;
    int daysPerLevel;
    int secondsPerDay;
    
    public LevelDev(int daysPerLevel, GameStudio studio) {
        super(10, studio);
        this.drive = studio.getDrive();
        this.daysPerLevel = daysPerLevel;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void doWork() {
        try {
            // Sleep while producing a narrative
            Thread.sleep(secondsPerDay * 1000 * this.daysPerLevel);
            drive.addLevels();
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
    }
}