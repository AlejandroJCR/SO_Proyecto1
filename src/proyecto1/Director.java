package proyecto1;

public class Director extends Employee{
    int hoursWorked;
    
    public Director(GameStudio studio) {
        super(30, studio);
        hoursWorked = 0;
    }

    @Override
    public void doWork() {
        int secondsPerDay = 3;
        
        try {
            if(studio.deadlineZero()){
                //Work 1 day to deliver games to the store
                Thread.sleep(secondsPerDay*1000);
                studio.deliverGames();
            } else {
                int randomHour = (int)Math.floor(Math.random() * 24);
                int restOfHours = 24 - randomHour;
                Thread.sleep((secondsPerDay * 1000)/24 * randomHour);
                
                //Starts watching the PM
                DirectorCheck dc = new DirectorCheck(studio);
                System.out.println("Director started watching");
                dc.start();
                //Wait 25 minutes
                Thread.sleep((secondsPerDay * 1000)/1440 * 25);
                dc.interrupt();
                Thread.sleep((secondsPerDay * 1000)/24 * restOfHours);
            }
           
                
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
        
    }
}
