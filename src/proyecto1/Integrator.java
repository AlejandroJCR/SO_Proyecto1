package proyecto1;

public class Integrator extends Employee{
    Drive drive;
    int daysPerNarrative;
    
    public Integrator(GameStudio studio) {
        super(25, studio);
        this.drive = studio.getDrive();
    }

    @Override
    public void doWork() {
        int secondsPerDay = 3;
        
        drive.getResources();
        System.out.println("Integration started");
        
        try {
            // Sleep while making a game
            Thread.sleep(secondsPerDay * 1000 * this.daysPerNarrative);
            System.out.println("Game ready");
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
        
    }
}
