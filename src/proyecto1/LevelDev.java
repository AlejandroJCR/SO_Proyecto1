package proyecto1;

public class LevelDev extends Thread {
    Drive drive;
    int daysPerLevel;
    int secondsPerDay;
    
    public LevelDev(int daysPerLevel, GameStudio studio) {
        this.drive = studio.getDrive();
        this.daysPerLevel = daysPerLevel;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Sleep while producing a narrative
                Thread.sleep(secondsPerDay * 1000 * this.daysPerLevel);
                drive.addLevels();
            }
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
    }
}