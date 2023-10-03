package proyecto1;

public class GameStudio extends Thread {
    String name;
    Specifications specs;
    Drive drive;
    LinkedList<Employee> employees;
    
    int employeesMax;
    int nNarrativeDevs, nLevelDevs;
    int daysForNarrative, daysForLevel, spritesPerDay, sistemsPerDay, daysPerDLC;
    int rawProfits, operativeCosts, utility;
    
    boolean isRunning;
            
    public GameStudio(String name, int carnetNumber, Specifications specs) {
        this.name = name;
        this.specs = specs;
        this.drive = new Drive();
        
        this.employees = new LinkedList<>();
        this.employeesMax = carnetNumber + 10;
        this.nNarrativeDevs = 2;
        this.nLevelDevs = 3;
        
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
    }
    
    public boolean simulationRunning(){
        return isRunning;
    }
    
    public Drive getDrive(){
        return drive;
    }
    
    @Override
    public void run() {
       isRunning = true;
       
       for(int i=0; i < nNarrativeDevs; i++){
           employees.append(new NarrativeDev(daysForNarrative, this));
       }
       
       for(int i=0; i < nLevelDevs; i++){
           employees.append(new LevelDev(daysForLevel, this));
       }
       
       int n = employees.size();
       for(int i=0; i < n; i++){
           Employee e = employees.get(i);
           e.start();
       }
    }
}
