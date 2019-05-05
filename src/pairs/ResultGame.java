package pairs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ResultGame{
    WHITE_WINS,
    BLACK_WINS,
    DRAW,
    MINUS_PLUS,
    PLUS_MINUS,
    MINUS_MINUS,
    ZERO_ZERO,
    PLUS;

    private static final List<ResultGame> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size() - 5;
    private static final Random RANDOM = new Random();

    public static ResultGame randomResult()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    public static ResultGame loadFromBD(String result)  {
        if (result.equals("1:0")) {
            return WHITE_WINS;
        }
        else if (result.equals("0:1")) {
            return BLACK_WINS;
        }
        else if (result.equals("=:=")) {
            return DRAW;
        }
        else if (result.equals("+:-")) {
            return PLUS_MINUS;
        }
        else if (result.equals("-:+")) {
            return MINUS_PLUS;
        }
        else if (result.equals("-:-")) {
            return BLACK_WINS;
        }
        else if (result.equals("+")) {
            return BLACK_WINS;
        }
        else {
            return PLUS;
        }
    }
}