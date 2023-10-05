package proyecto1;

public class DirectorCheck extends Thread{

    GameStudio studio;
    boolean caughtPM;
    
    public DirectorCheck(GameStudio studio) {
        this.studio = studio;
        this.caughtPM = false;
    }

    @Override
    public void run() {
        while (!isInterrupted() && !caughtPM) {
            if(studio.PMWatchingStreams){
                studio.pmFaults++;
                studio.operativeCosts -= 50;
                caughtPM = true;
                System.out.println("Caught PM!");
            }
        }
    }
}
