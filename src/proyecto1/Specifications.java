package proyecto1;

public class Specifications {
    int narratives;
    int levels;
    int sprites;
    int sistems;
    int gamesBeforeDlcs;
    int dlcs;
    int gameProfit;
    int gameWithDLCsProfit;
    
    public Specifications(int narratives, int levels, int sprites, int sistems, int gamesBeforeDlcs,
    int dlcs, int gameProfit, int gameWithDLCsProfit) {
        this.narratives = narratives;
        this.levels = levels;
        this.sprites = sprites;
        this.sistems = sistems;
        this.gamesBeforeDlcs = gamesBeforeDlcs;
        this.dlcs = dlcs;
        this.gameProfit = gameProfit;
        this.gameWithDLCsProfit = gameWithDLCsProfit;
    }
}
