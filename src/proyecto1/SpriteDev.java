package proyecto1;

public class SpriteDev extends Employee {
    Drive drive;
    int spritesPerDay;
    
    public SpriteDev(int spritesPerDay, GameStudio studio) {
        super(20, studio);
        this.drive = studio.getDrive();
        this.spritesPerDay = spritesPerDay;
    }

    @Override
    public void doWork() {
        int secondsPerDay = 3;      
        try {
            // Sleep while producing a narrative
            Thread.sleep(secondsPerDay * 1000);
            drive.addSprites(spritesPerDay);
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }  
    }
}

