package Utils;

public class Probabilidad {
    public static boolean sorteo (int probabilidad) {
        return (Math.random()*100 < probabilidad);
    }
}
