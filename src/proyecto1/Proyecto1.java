package proyecto1;

public class Proyecto1 {

    public static void main(String[] args) {
        Specifications specsStudio1 = new Specifications(1, 2, 6, 5, 3, 1, 400000, 750000);
        Specifications specsStudio2 = new Specifications(2, 3, 4, 6, 6, 5, 450000, 900000);
        
        GameStudio studio1 = new GameStudio("Capcom", 2, specsStudio1);
        GameStudio studio2 = new GameStudio("Bethesda", 5, specsStudio2);
        
        studio1.start();
    }
}
