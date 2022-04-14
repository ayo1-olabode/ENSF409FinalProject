import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version 2.1
 * @since 1.0
 */

public class Hamper {
    private ArrayList<Food> log = new ArrayList<Food>();

    public Hamper(Food[] hamperItems) {
        Collections.addAll(log, hamperItems);
    }

    public Food getLine(int index) {
        return log.get(index);
    }

    public ArrayList<Food> getDataRecord() {
        return this.log;
    }
}
