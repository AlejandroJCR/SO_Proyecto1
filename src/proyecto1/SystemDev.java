package proyecto1;

public class SystemDev extends Employee {
    Drive drive;
    int systemsPerDay;
    int secondsPerDay;
    
    public SystemDev(int systemsPerDay, GameStudio studio) {
        super(20, studio);
        this.drive = studio.getDrive();
        this.systemsPerDay = systemsPerDay;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void doWork() {  
        try {
            // Sleep while producing a narrative
            Thread.sleep(secondsPerDay * 1000);
            drive.addSystems(systemsPerDay);
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }  
    }
}