package proyecto1;

public abstract class Employee extends Thread {
    int salaryPerHour;
    GameStudio studio;

    public Employee(int salaryPerHour, GameStudio studio) {
        this.salaryPerHour = salaryPerHour;
        this.studio = studio;
    }

    @Override
    public void run() {
       while (studio.simulationRunning()) {
            doWork(); // Realizar trabajo específico del empleado
        }
    }
    
    public abstract void doWork();
}
