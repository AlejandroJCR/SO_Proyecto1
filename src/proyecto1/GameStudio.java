package proyecto1;

public class GameStudio extends Thread {
    int id;
    Drive drive;
    LinkedList<Employee> employees;
    Configuration config;
    Proyecto1GUI GUI;
    
    int daysForNarrative, daysForLevel, spritesPerDay, systemsPerDay, daysPerDLC;
    int rawProfits, operativeCosts, utility, deductedFromPM;
    int pmFaults;
    
    int currentDaysUntilDeadline;
    
    boolean isRunning;
    boolean PMWatchingStreams;
            
    public GameStudio(int id, int carnetNumber, Specifications specs, Configuration config, Proyecto1GUI GUI) {
        this.id = id;
        this.drive = new Drive(id, specs, GUI);
        this.employees = new LinkedList<>();
        this.config = config;
        this.GUI = GUI;
        
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
            systemsPerDay = 3;
            daysPerDLC = 3;
        }else if (carnetNumber >= 5 && carnetNumber < 6){
            systemsPerDay = 5;  
            daysPerDLC = 2;
        }
       
        rawProfits = 0;
        operativeCosts = 0;
        utility = 0;
        
        currentDaysUntilDeadline = config.daysUntilDeadlineInit;
    }
    
    public boolean simulationRunning(){
        return isRunning;
    }
    
    public Drive getDrive(){
        return drive;
    }
    
    public boolean deadlineZero(){
         return currentDaysUntilDeadline == 0;
    }
    
    public void changeDeadline(String action){
        if(action.equals("reduce")){
            if(currentDaysUntilDeadline > 0)
                currentDaysUntilDeadline--;
        } else if(action.equals("reset")) {
            currentDaysUntilDeadline = config.daysUntilDeadlineInit;
        }
        System.out.println("Deadline " + currentDaysUntilDeadline);
        GUI.modDeadline(id, currentDaysUntilDeadline);
    }
    
    public void changePMActvity(boolean isWatchingStreams){
        PMWatchingStreams = isWatchingStreams;
        if(isWatchingStreams){
            GUI.modPmActivity(id, "viendo streams");
        } else{
            GUI.modPmActivity(id, "trabajando");
        }
    }
    
    public void caughtPM(){
        pmFaults++;
        deductedFromPM += 50;
        operativeCosts -= 50;
        
        GUI.modPmFaults(id, pmFaults, deductedFromPM);
    }
    
    public void deliverGames(){
        rawProfits += drive.getGames();
        System.out.println("PROFITS ! " + rawProfits);
    }
    
    public void changeDirectorActivity(String activity){
        GUI.modDirectorActivity(id,activity);
    }
        
    @Override
    public void run() {
       isRunning = true;
       
       for(int i=0; i < config.nNarrativeDevs; i++)
           employees.append(new NarrativeDev(daysForNarrative, this));
       
       for(int i=0; i < config.nLevelDevs; i++)
           employees.append(new LevelDev(daysForLevel, this));
       
       for(int i=0; i < config.nSpriteDevs; i++)
           employees.append(new SpriteDev(spritesPerDay, this));
       
       for(int i=0; i < config.nSistemDevs; i++)
           employees.append(new SystemDev(systemsPerDay, this));
       
       for(int i=0; i < config.nDLCDevs; i++)
           employees.append(new DLCDev(daysPerDLC, this));
       
       for(int i=0; i < config.nIntegrators; i++)
           employees.append(new Integrator(this));
       
       employees.append(new ProjectManager(this));
       
       employees.append(new Director(this));
       
       int n = employees.size();
       for(int i=0; i < n; i++){
           Employee e = employees.get(i);
           e.start();
       }
       
       /*try {
            Thread.sleep(3 * 1000 * 24);
            isRunning = false;
            
            for(int i=0; i < n; i++){
                Employee e = employees.get(i);
                e.join();
            }
            
            System.out.println("END OF SIMULATION");
        } catch(InterruptedException e){
             // this part is executed when an exception (in this example InterruptedException) occurs
        }*/
    }
}
