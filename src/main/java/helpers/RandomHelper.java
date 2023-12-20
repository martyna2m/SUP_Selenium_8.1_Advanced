package helpers;

import java.util.Random;

public class RandomHelper {

    public static int getRandomNumber(int minNumber, int maxNumber) {
        return new Random().nextInt(minNumber, maxNumber);
    }

    public static int getRandomNumber(int maxNumber) {
        return new Random().nextInt(maxNumber);
    }
}
