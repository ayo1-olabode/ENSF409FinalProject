/**
 * @author Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version 2.1
 * @since 1.0
 */

import java.util.*;
import java.io.*;

public class Client {
//    private int minIntake;
//    private int maxIntake;
    private String clientType;
    private int wholeGrains;
    private int fruitsVeggies;
    private int protein;
    private int other;
    private int calories;

    public Client(String clientType, int wholeGrains, int fruitsVeggies, int protein, int other, int calories) throws InvalidClientTypeException {
        if (Objects.equals(clientType, "Adult Male") || Objects.equals(clientType, "Adult Female")
        || Objects.equals(clientType, "Child over 8") || Objects.equals(clientType, "Child under 8")) {
            this.clientType = clientType;
        }
        else {
            throw new InvalidClientTypeException("Not a valid client type!");
        }
        this.wholeGrains = wholeGrains;
        this.fruitsVeggies = fruitsVeggies;
        this.protein = protein;
        this.other = other;
        this.calories = calories;
    }

    public String getClientType() {
        return clientType;
    }

    public int getWholeGrains() {
        return wholeGrains;
    }

    public int getFruitsVeggies() {
        return fruitsVeggies;
    }

    public int getProtein() {
        return protein;
    }

    public int getOther() {
        return other;
    }

    public int getCalories() {
        return calories;
    }

}
