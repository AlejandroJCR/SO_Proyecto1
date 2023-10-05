package proyecto1;

public class GameStudio extends Thread {
    String name;
    
    Drive drive;
    LinkedList<Employee> employees;
    
    int employeesMax;
    int nNarrativeDevs, nLevelDevs, nIntegrators, nDLCDevs;
    int daysForNarrative, daysForLevel, spritesPerDay, sistemsPerDay, daysPerDLC;
    int rawProfits, operativeCosts, utility;
    
    int daysUntilDeadlineInit, currentDaysUntilDeadline;
    
    boolean isRunning;
            
    public GameStudio(String name, int carnetNumber, Specifications specs) {
        this.name = name;
        this.drive = new Drive(specs);
        
        this.employees = new LinkedList<>();
        this.employeesMax = carnetNumber + 10;
        this.nNarrativeDevs = 2;
        this.nLevelDevs = 3;
        this.nIntegrators = 2;
        this.nDLCDevs = 1;
        
        // Set daysForNarrative, daysPerLevel and spritesPerDay
        if(carnetNumber >= 0 && carnetNumber < 3){
            daysForNarrative = 2;
            daysForLevel = 2;
            spritesPerDay = 3;
        }else if (carnetNumber >= 3 && carnetNumber < 6){
            daysForNarrative = 3;  
            daysForLevel = 3;
            spritesPerDay = 2;
        }else if (carnetNumber >= 6 && carnetNumber <= 9){
            daysForNarrative = 4;
            daysForLevel = 4;
            spritesPerDay = 1;
        }
        
        // Set sistemsPerDay and daysPerDLC
        if(carnetNumber >= 0 && carnetNumber < 5){
            sistemsPerDay = 3;
            daysPerDLC = 3;
        }else if (carnetNumber >= 5 && carnetNumber < 6){
            sistemsPerDay = 5;  
            daysPerDLC = 2;
        }
       
        this.rawProfits = 0;
        this.operativeCosts = 0;
        this.utility = 0;
        
        daysUntilDeadlineInit = 7;
        currentDaysUntilDeadline = 7;
    }
    
    public boolean simulationRunning(){
        return isRunning;
    }
    
    public Drive getDrive(){
        return drive;
    }
    
    public void changeDeadline(String action){
        if(action.equals("reduce")){
            currentDaysUntilDeadline--;
        } else if(action.equals("reset")) {
            currentDaysUntilDeadline = daysUntilDeadlineInit;
        }
        System.out.println("Deadline " + currentDaysUntilDeadline);
    }
    
    @Override
    public void run() {
       isRunning = true;
       
       for(int i=0; i < nNarrativeDevs; i++)
           employees.append(new NarrativeDev(daysForNarrative, this));
       
       for(int i=0; i < nLevelDevs; i++)
           employees.append(new LevelDev(daysForLevel, this));
       
       for(int i=0; i < nDLCDevs; i++)
           employees.append(new DLCDev(daysPerDLC, this));
       
       for(int i=0; i < nIntegrators; i++)
           employees.append(new Integrator(this));
       
       employees.append(new ProjectManager(this));
       
       int n = employees.size();
       for(int i=0; i < n; i++){
           Employee e = employees.get(i);
           e.start();
       }
       
       try {
            Thread.sleep(3 * 1000 * 15);
            isRunning = false;
            
            for(int i=0; i < n; i++){
                Employee e = employees.get(i);
                e.join();
            }
            
            System.out.println("END OF SIMULATION");
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }
    }
}
