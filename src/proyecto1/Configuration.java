package proyecto1;

public class Configuration {
    int secondsPerDay;
    int daysUntilDeadlineInit;
    int nNarrativeDevs;
    int nLevelDevs;
    int nSpriteDevs;
    int nSistemDevs;
    int nDLCDevs;
    int nIntegrators;
    int maxEmployees;
    int currentEmployees;
    
    public Configuration(int secondsPerDay, int daysUntilDeadlineInit, int nNarrativeDevs, int nLevelDevs, int nSpriteDevs,
        int nSistemDevs, int nDLCDevs, int nIntegrators, int carnetNumber){
        this.secondsPerDay = secondsPerDay;
        this.daysUntilDeadlineInit = daysUntilDeadlineInit;
        this.nNarrativeDevs = nNarrativeDevs;
        this.nLevelDevs = nLevelDevs;
        this.nSpriteDevs = nSpriteDevs;
        this.nSistemDevs = nSistemDevs;
        this.nDLCDevs = nDLCDevs;
        this.nIntegrators = nIntegrators;
        this.maxEmployees = carnetNumber + 10;
        this.currentEmployees =  nNarrativeDevs + nLevelDevs + nSpriteDevs + nSistemDevs + nDLCDevs + nIntegrators;
        System.out.println(this.currentEmployees);
    }
}
