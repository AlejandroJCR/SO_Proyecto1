package proyecto1;

public class GameStudio extends Thread {
    String name;
    Specifications specs;
    Drive drive;
    
    int daysForNarrative, daysPerLevel, spritesPerDay, sistemsPerDay, daysPerDLC;
    int rawProfits, operativeCosts, utility;
            
    public GameStudio(String name, int carnetNumber, Specifications specs) {
        this.name = name;
        this.specs = specs;
        this.drive = new Drive();
        this.rawProfits = 0;
        this.operativeCosts = 0;
        this.utility = 0;
        
        // Set daysForNarrative, daysPerLevel and spritesPerDay
        if(carnetNumber >= 0 && carnetNumber < 3){
            daysForNarrative = 2;
            daysPerLevel = 2;
            spritesPerDay = 3;
        }else if (carnetNumber >= 3 && carnetNumber < 6){
            daysForNarrative = 3;  
            daysPerLevel = 3;
            spritesPerDay = 2;
        }else if (carnetNumber >= 6 && carnetNumber <= 9){
            daysForNarrative = 4;
            daysPerLevel = 4;
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
    }
    
    @Override
    public void run() {
       
    }
}
