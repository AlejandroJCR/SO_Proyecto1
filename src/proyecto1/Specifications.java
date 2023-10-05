package proyecto1;

public class Specifications {
    int narratives;
    int levels;
    int sprites;
    int systems;
    int dlcs;
    int gamesBeforeDlcs;
    int gameProfit;
    int gameWithDLCsProfit;
    
    public Specifications(int narratives, int levels, int sprites, int systems, int dlcs, int gamesBeforeDlcs, int gameProfit, int gameWithDLCsProfit) {
        this.narratives = narratives;
        this.levels = levels;
        this.sprites = sprites;
        this.systems = systems;
        this.gamesBeforeDlcs = gamesBeforeDlcs;
        this.dlcs = dlcs;
        this.gameProfit = gameProfit;
        this.gameWithDLCsProfit = gameWithDLCsProfit;
    }
}
