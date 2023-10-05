package proyecto1;

import java.util.concurrent.Semaphore;

public class Drive {
    Specifications specs;
    
    static Semaphore semDrive = new Semaphore(1);
    
    static Semaphore semCurrentNarratives = new Semaphore(0);
    static Semaphore semCurrentLevels = new Semaphore(0);
    static Semaphore semCurrentSprites = new Semaphore(0);
    static Semaphore semCurrentSistems = new Semaphore(0);
    static Semaphore semCurrentDLCs = new Semaphore(0);
    
    
    static Semaphore semMaxNarratives = new Semaphore(25);
    static Semaphore semMaxLevels = new Semaphore(20);
    static Semaphore semMaxSprites = new Semaphore(55);
    static Semaphore semMaxSistems = new Semaphore(35);
    static Semaphore semMaxDLCs = new Semaphore(10);
     
    int narratives, levels, sprites, systems, dlcs;
    
    int games, gamesWithDLC, currentGamesBeforeDLC;

    public Drive(Specifications specs) {
        this.specs = specs;
        this.narratives = 0;
        this.levels = 0;
        this.sprites = 0;
        this.systems = 0;
        this.dlcs = 0;

        this.games = 0; 
        this.gamesWithDLC = 0;
        this.currentGamesBeforeDLC = 0;
    }
    
    public void addNarratives(){
        try {
            semMaxNarratives.acquire();
            semDrive.acquire();
            narratives++;
            System.out.println("Producer produced narrative : " + narratives + " " + levels);
            semDrive.release();
            semCurrentNarratives.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void addLevels(){
        try {
            semMaxLevels.acquire();
            semDrive.acquire();
            levels++;
            System.out.println("Producer produced level : " + narratives + " " + levels);
            semDrive.release();
            semCurrentLevels.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void addSprites(int spritesPerDay){
        try {
            for(int i=0; i < spritesPerDay; i++)
                semMaxSprites.acquire();
            semDrive.acquire();
            sprites += spritesPerDay;
            System.out.println("Producer produced sprites: " + sprites);
            semDrive.release();
            for(int i=0; i < spritesPerDay; i++)
                semCurrentSprites.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void addSystems(int systemsPerDay){
        try {
            for(int i=0; i < systemsPerDay; i++)
                semMaxSistems.acquire();
            semDrive.acquire();
            systems += systemsPerDay;
            System.out.println("Producer produced systems: " + systems);
            semDrive.release();
            for(int i=0; i < systemsPerDay; i++)
                semCurrentSistems.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
     
    public void addDLCs(){
        try {
            semMaxDLCs.acquire();
            semDrive.acquire();
            dlcs++;
            System.out.println("Producer produced DLC : " + dlcs);
            semDrive.release();
            semCurrentDLCs.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void getResources(){
        try {
            for(int i=0; i < specs.narratives; i++)
                semCurrentNarratives.acquire();
            for(int i=0; i < specs.levels; i++)
                semCurrentLevels.acquire();
            for(int i=0; i < specs.sprites; i++)
                semCurrentSprites.acquire();
            for(int i=0; i < specs.systems; i++)
                semCurrentSistems.acquire();
            System.out.println("Recursos listos");
            semDrive.acquire();
            narratives -= specs.narratives;
            levels -= specs.levels;
            sprites -= specs.sprites;
            systems -= specs.systems;
            System.out.println("Integrator is ready to make game: " + narratives + " " + levels + " " + sprites + " " + systems);
            semDrive.release();
            
            for(int i=0; i < specs.narratives; i++)
                semMaxNarratives.release();
            for(int i=0; i < specs.levels; i++)
                semMaxLevels.release();  
            for(int i=0; i < specs.sprites; i++)
                semMaxSprites.release();   
            for(int i=0; i < specs.systems; i++)
                semMaxSistems.release();   
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void addGame(){
        try {
           semDrive.acquire();
           if(currentGamesBeforeDLC != 0 && currentGamesBeforeDLC % specs.gamesBeforeDlcs == 0){
               // Temporaly release the drive semaphore to wait for the dlcs
               semDrive.release();
               for(int i=0; i < specs.dlcs; i++)
                    semCurrentDLCs.acquire();
               
               // Adquire the drive lock again before modifying the values
               semDrive.acquire();
               gamesWithDLC++;
               dlcs -= specs.dlcs;
               currentGamesBeforeDLC = 0;
               
               for(int i=0; i < specs.dlcs; i++)
                semMaxDLCs.release();
               
               System.out.println("Game with DLC ready! " + games + " " + gamesWithDLC);
           } else {
               games++;
               currentGamesBeforeDLC++;
               System.out.println("Game ready! " + games + " " + gamesWithDLC);
           }

           semDrive.release();
           }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }     
    }
    
    public int getGames(){
        int localGames = 0, localGamesWithDLC = 0;
        
        try{
            semDrive.acquire();
            localGames = games;
            localGamesWithDLC = gamesWithDLC;
            games = 0;
            gamesWithDLC = 0;        
            semDrive.release();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }  
        
        return localGames * specs.gameProfit + localGamesWithDLC * specs.gameWithDLCsProfit;
    }
}
