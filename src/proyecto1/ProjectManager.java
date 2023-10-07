package proyecto1;

public class ProjectManager extends Thread{
    int hoursWorked;
    int secondsPerDay;
    GameStudio studio;
    
    public ProjectManager(GameStudio studio) {
        this.studio = studio;
        hoursWorked = 0;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void run() {
        double milisecondsPerHalfHour = Math.ceil(secondsPerDay * 1000 /48);
       
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if(hoursWorked < 16){
                    // Watching Stream
                    studio.changePMActvity(true);
                    Thread.sleep((long)milisecondsPerHalfHour);
                    // Working
                    studio.changePMActvity(false);
                    Thread.sleep((long)milisecondsPerHalfHour);
                    hoursWorked++;
                } else {
                    // Work for 8 hours
                    Thread.sleep((long)(milisecondsPerHalfHour * 16));
                    studio.changeDeadline("reduce");
                    hoursWorked = 0;
                }
            }
        } catch(InterruptedException e){
            //Thread killed
        }
        
    }
}
