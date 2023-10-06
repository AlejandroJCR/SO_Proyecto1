package proyecto1;

public class SystemDev extends Thread {
    Drive drive;
    int systemsPerDay;
    int secondsPerDay;
    
    public SystemDev(int systemsPerDay, GameStudio studio) {
        this.drive = studio.getDrive();
        this.systemsPerDay = systemsPerDay;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void run() {  
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Sleep while producing a narrative
                Thread.sleep(secondsPerDay * 1000);
                drive.addSystems(systemsPerDay);
            }
        } catch(InterruptedException e){
            System.out.println("KILLED !!");
        }  
    }
}