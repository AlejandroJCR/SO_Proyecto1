package proyecto1;

public class GameStudio extends Thread {
    int id;
    Drive drive;
    
    LinkedList<NarrativeDev> narrativesDevs = new LinkedList<>();;
    LinkedList<LevelDev> levelsDevs = new LinkedList<>();;
    LinkedList<SpriteDev> spritesDevs = new LinkedList<>();;
    LinkedList<SystemDev> systemsDevs = new LinkedList<>();;
    LinkedList<DLCDev> dlcDevs = new LinkedList<>();;
    LinkedList<Integrator> integrators = new LinkedList<>();
    ProjectManager PM;
    Director director;
    
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
    
    public void addNarrativeDev(){
        if(isRunning && config.currentEmployees + 1 <= config.maxEmployees){
            NarrativeDev newDev = new NarrativeDev(daysForNarrative, this);
            narrativesDevs.append(newDev);
            newDev.start();
            config.nNarrativeDevs++;
            config.currentEmployees++;
            GUI.modNarrativesDev(id, config.nNarrativeDevs);
        }
    }
    
    public void removeNarrativeDev(){
        if(isRunning && config.nNarrativeDevs != 1){
            NarrativeDev dev = narrativesDevs.pop();
            System.out.println(dev);
            dev.interrupt(); 
            config.nNarrativeDevs--;
            config.currentEmployees--;
            GUI.modNarrativesDev(id, config.nNarrativeDevs);
        }
    }
    
    public void addLevelDev(){
        if(isRunning && config.currentEmployees + 1 <= config.maxEmployees){
            LevelDev newDev = new LevelDev(daysForLevel, this);
            levelsDevs.append(newDev);
            newDev.start();
            config.nLevelDevs++;
            config.currentEmployees++;
            GUI.modLevelDev(id, config.nLevelDevs);
        }
    }
    
    public void removeLevelDev(){
        if(isRunning && config.nLevelDevs != 1){
            LevelDev dev = levelsDevs.pop();
            dev.interrupt();            
            config.nLevelDevs--;
            config.currentEmployees--;
            GUI.modLevelDev(id, config.nLevelDevs);
        }
    }
    
    
    public void addSpritesDev(){
        if(isRunning && config.currentEmployees + 1 <= config.maxEmployees){
            SpriteDev newDev = new SpriteDev(spritesPerDay, this);
            spritesDevs.append(newDev);
            newDev.start();
            config.nSpriteDevs++;
            config.currentEmployees++;
            GUI.modSpritesDev(id, config.nSpriteDevs);
        }
    }
    
    public void removeSpritesDev(){
        if(isRunning && config.nSpriteDevs != 1){
            SpriteDev dev = spritesDevs.pop();
            dev.interrupt();
            config.nSpriteDevs--;
            config.currentEmployees--;
            GUI.modSpritesDev(id, config.nSpriteDevs);
        }
    }
    
    public void addSystemDev(){
        if(isRunning && config.currentEmployees + 1 <= config.maxEmployees){
            SystemDev newDev = new SystemDev(systemsPerDay, this);
            systemsDevs.append(newDev);
            newDev.start();
            config.nSistemDevs++;
            config.currentEmployees++;
            GUI.modSystemDev(id, config.nSistemDevs);
        }
    }
    
    public void removeSystemDev(){
        if(isRunning && config.nSistemDevs != 1){
            SystemDev dev = systemsDevs.pop();
            dev.interrupt();
            config.nSistemDevs--;
            config.currentEmployees--;
            GUI.modSystemDev(id, config.nSistemDevs);
        }
    }
    
    public void addDLCDev(){
        if(isRunning && config.currentEmployees + 1 <= config.maxEmployees){
            DLCDev newDev = new DLCDev(daysPerDLC, this);
            dlcDevs.append(newDev);
            newDev.start();
            config.nDLCDevs++;
            config.currentEmployees++;
            GUI.modDLCDev(id, config.nDLCDevs);
        }
    }
    
    public void removeDLCDev(){
        if(isRunning && config.nDLCDevs != 1){
            DLCDev dev = dlcDevs.pop();
            dev.interrupt();
            config.nDLCDevs--;
            config.currentEmployees--;
            GUI.modDLCDev(id, config.nDLCDevs);
        }
    }
    
    public void addIntegrator(){
        System.out.println(config.currentEmployees);
        if(isRunning && config.currentEmployees + 1 <= config.maxEmployees){
            Integrator newIntegrator = new Integrator(this);
            integrators.append(newIntegrator);
            newIntegrator.start();
            config.nIntegrators++;
            config.currentEmployees++;
            GUI.modIntegrators(id, config.nIntegrators);
        }
    }
    
    public void removeIntegrator(){
        if(isRunning && config.nIntegrators != 1){
            Integrator integrator = integrators.pop();
            integrator.interrupt();
            config.nIntegrators--;
            config.currentEmployees--;
            GUI.modIntegrators(id, config.nIntegrators);
        }
    }
    
    @Override
    public void run() {
       isRunning = true;
       
       for(int i=0; i < config.nNarrativeDevs; i++)
           narrativesDevs.append(new NarrativeDev(daysForNarrative, this));
       
       for(int i=0; i < config.nLevelDevs; i++)
           levelsDevs.append(new LevelDev(daysForLevel, this));
       
       for(int i=0; i < config.nSpriteDevs; i++)
           spritesDevs.append(new SpriteDev(spritesPerDay, this));
       
       for(int i=0; i < config.nSistemDevs; i++)
           systemsDevs.append(new SystemDev(systemsPerDay, this));
       
       for(int i=0; i < config.nDLCDevs; i++)
           dlcDevs.append(new DLCDev(daysPerDLC, this));
       
       for(int i=0; i < config.nIntegrators; i++)
           integrators.append(new Integrator(this));
       
       PM = new ProjectManager(this);
       director = new Director(this);
       
       for(int i=0; i < config.nNarrativeDevs; i++)
           narrativesDevs.get(i).start();
       
       for(int i=0; i < config.nLevelDevs; i++)
           levelsDevs.get(i).start();
       
       for(int i=0; i < config.nSpriteDevs; i++)
           spritesDevs.get(i).start();
       
       for(int i=0; i < config.nSistemDevs; i++)
           systemsDevs.get(i).start();
       
       for(int i=0; i < config.nDLCDevs; i++)
           dlcDevs.get(i).start();
       
       for(int i=0; i < config.nIntegrators; i++)
           integrators.get(i).start();
     
        PM.start();
        director.start();
        
        GUI.initEmployeesPanel();
                
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
