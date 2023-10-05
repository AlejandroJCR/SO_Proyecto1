package proyecto1;

public class ProjectManager extends Employee{
    int hoursWorked;
    
    public ProjectManager(GameStudio studio) {
        super(20, studio);
        hoursWorked = 0;
    }

    @Override
    public void doWork() {
        int secondsPerDay = 3;
        double milisecondsPerHalfHour = secondsPerDay * 1000 /48;
       
        try {
            if(hoursWorked < 16){
                // Watching Stream
                //System.out.println("PM is watching streams...");
                Thread.sleep((long)milisecondsPerHalfHour);
                // Working
                //System.out.println("PM is working!");
                Thread.sleep((long)milisecondsPerHalfHour);
                hoursWorked++;
                //System.out.println(hoursWorked);
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