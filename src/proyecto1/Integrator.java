package proyecto1;

public class Integrator extends Thread{
    Drive drive;
    int daysPerNarrative;
    int secondsPerDay;
    
    public Integrator(GameStudio studio) {
        this.drive = studio.getDrive();
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void run() {      
        try {
            while (!Thread.currentThread().isInterrupted()) {
                drive.getResources();
                // Sleep while making a game
                Thread.sleep(secondsPerDay * 1000 * this.daysPerNarrative);
                drive.addGame();
            }   
        } catch(InterruptedException e){
            //Thread killed
        }   
    }
}
