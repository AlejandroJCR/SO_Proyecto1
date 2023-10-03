package proyecto1;

public abstract class Developer extends Thread {
    int salaryPerHour;

    public Developer(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @Override
    public void run() {
       
    }
}
