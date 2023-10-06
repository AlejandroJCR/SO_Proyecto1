package proyecto1;

public class ProjectManager extends Employee{
    int hoursWorked;
    int secondsPerDay;
    
    public ProjectManager(GameStudio studio) {
        super(20, studio);
        hoursWorked = 0;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void doWork() {
        double milisecondsPerHalfHour = Math.ceil(secondsPerDay * 1000 /48);
       
        try {
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
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
        
    }
}
