package proyecto1;

import java.util.concurrent.Semaphore;

public class Drive {
    int id;
    Specifications specs;
    Proyecto1GUI GUI;
    
    Semaphore semDrive = new Semaphore(1);
    
    Semaphore semCurrentNarratives = new Semaphore(0);
    Semaphore semCurrentLevels = new Semaphore(0);
    Semaphore semCurrentSprites = new Semaphore(0);
    Semaphore semCurrentSistems = new Semaphore(0);
    Semaphore semCurrentDLCs = new Semaphore(0);

    Semaphore semMaxNarratives = new Semaphore(25);
    Semaphore semMaxLevels = new Semaphore(20);
    Semaphore semMaxSprites = new Semaphore(55);
    Semaphore semMaxSistems = new Semaphore(35);
    Semaphore semMaxDLCs = new Semaphore(10);
     
    int narratives, levels, sprites, systems, dlcs;
    int games, gamesWithDLC, currentGamesBeforeDLC;

    public Drive(int id, Specifications specs, Proyecto1GUI GUI) {
        this.id = id;
        this.specs = specs;
        this.GUI = GUI;
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
            GUI.modNarrativeAmount(id, narratives);
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
            GUI.modLevelsAmount(id, levels);
            semDrive.release();
            semCurrentLevels.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void addSprites(int spritesPerDay){
        try {
            semMaxSprites.acquire(spritesPerDay);
            semDrive.acquire();
            sprites += spritesPerDay;
            GUI.modSpritesAmount(id, sprites);
            semDrive.release();
            semCurrentSprites.release(spritesPerDay);
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void addSystems(int systemsPerDay){
        try {
            semMaxSistems.acquire(systemsPerDay);
            semDrive.acquire();
            systems += systemsPerDay;
            GUI.modSystemsAmount(id, systems);
            semDrive.release();
            semCurrentSistems.release(systemsPerDay);
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
            GUI.modDLCAmount(id, dlcs);
            semDrive.release();
            semCurrentDLCs.release();
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
    
    public void getResources(){
        try {
            semCurrentNarratives.acquire(specs.narratives);
            semCurrentLevels.acquire(specs.levels);
            semCurrentSprites.acquire(specs.sprites);
            semCurrentSistems.acquire(specs.systems);
            semDrive.acquire();
            narratives -= specs.narratives;
            levels -= specs.levels;
            sprites -= specs.sprites;
            systems -= specs.systems;
            GUI.modNarrativeAmount(id, narratives);
            GUI.modLevelsAmount(id, levels);
            GUI.modSpritesAmount(id, sprites);
            GUI.modSystemsAmount(id, systems);
            semDrive.release();
            
            semMaxNarratives.release(specs.narratives);
            semMaxLevels.release(specs.levels);  
            semMaxSprites.release(specs.sprites);   
            semMaxSistems.release(specs.systems);   
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
               semCurrentDLCs.acquire(specs.dlcs);
               
               // Adquire the drive lock again before modifying the values
               semDrive.acquire();
               gamesWithDLC++;
               dlcs -= specs.dlcs;
               currentGamesBeforeDLC = 0;
               
               semMaxDLCs.release(specs.dlcs);
               
               GUI.modGamesDLCAmount(id, gamesWithDLC);
               GUI.modDLCAmount(id, dlcs);
           } else {
               games++;
               currentGamesBeforeDLC++;
               GUI.modGamesAmount(id, games);
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
            GUI.modGamesAmount(id, 0);
            GUI.modGamesDLCAmount(id, 0);
            semDrive.release();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }  
        
        return localGames * specs.gameProfit + localGamesWithDLC * specs.gameWithDLCsProfit;
    }
}
