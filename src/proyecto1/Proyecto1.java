package proyecto1;

public class Proyecto1 {

    public static void main(String[] args) {
        Configuration config1 = new Configuration(3, 7, 1, 2, 3, 3, 3, 2, 2);
        Configuration config2 = new Configuration(3, 7, 1, 2, 3, 3, 3, 2, 5);
        
        Specifications specsStudio1 = new Specifications(1, 2, 6, 5, 1, 3, 400000, 750000);
        Specifications specsStudio2 = new Specifications(2, 3, 4, 6, 5, 6, 450000, 900000);
        
        GameStudio studio1 = new GameStudio("Capcom", 2, specsStudio1, config1);
        GameStudio studio2 = new GameStudio("Bethesda", 5, specsStudio2, config2);
        
        studio1.start();
    }
}
