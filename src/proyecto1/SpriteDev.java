package proyecto1;

public class SpriteDev extends Thread {
    Drive drive;
    int spritesPerDay;
    int secondsPerDay;
    
    public SpriteDev(int spritesPerDay, GameStudio studio) {
        this.drive = studio.getDrive();
        this.spritesPerDay = spritesPerDay;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void run() { 
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Sleep while producing a narrative
                Thread.sleep(secondsPerDay * 1000);
                drive.addSprites(spritesPerDay);
            }
        } catch(InterruptedException e){
            //Thread killed
        }  
    }
}

