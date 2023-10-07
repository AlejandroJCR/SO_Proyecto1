package proyecto1;

public class Director extends Thread{
    int secondsPerDay;
    GameStudio studio;
    
    public Director(GameStudio studio) {
        this.studio = studio;
        this.secondsPerDay = studio.config.secondsPerDay;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if(studio.deadlineZero()){
                    //Work 1 day to deliver games to the store
                    studio.changeDirectorActivity("enviando juegos");
                    Thread.sleep(secondsPerDay*1000);
                    studio.deliverGames();
                    studio.changeDeadline("reset");
                } else {
                    studio.changeDirectorActivity("haciendo labores administrativas");
                    int randomHour = (int)Math.floor(Math.random() * 24);
                    int restOfHours = 24 - randomHour;
                    Thread.sleep((secondsPerDay * 1000)/24 * randomHour);

                    //Starts watching the PM
                    DirectorCheck dc = new DirectorCheck(studio);
                    studio.changeDirectorActivity("vigilando al PM");
                    dc.start();
                    //Wait 25 minutes
                    Thread.sleep((secondsPerDay * 1000)/1440 * 25);
                    dc.interrupt();
                    Thread.sleep((secondsPerDay * 1000)/24 * restOfHours);
                }
            }   
        } catch(InterruptedException e){
            //Thread killed
        }
        
    }
}
