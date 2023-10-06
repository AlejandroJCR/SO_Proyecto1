package proyecto1;

public class Integrator extends Employee{
    Drive drive;
    int daysPerNarrative;
    int secondsPerDay;
    
    public Integrator(GameStudio studio) {
        super(25, studio);
        this.drive = studio.getDrive();
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void doWork() {
        drive.getResources();
        System.out.println("Integration started");
        
        try {
            // Sleep while making a game
            Thread.sleep(secondsPerDay * 1000 * this.daysPerNarrative);
            drive.addGame();
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
        
    }
}
