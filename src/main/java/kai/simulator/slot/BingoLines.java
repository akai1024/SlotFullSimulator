package kai.simulator.slot;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 中獎線位置
 */
public enum BingoLines {

    LINE1(new Integer[]{5, 6, 7, 8, 9}),
    LINE2(new Integer[]{0, 1, 2, 3, 4}),
    LINE3(new Integer[]{10, 11, 12, 13, 14}),
    LINE4(new Integer[]{0, 6, 12, 8, 4}),
    LINE5(new Integer[]{10, 6, 2, 8, 14}),
    LINE6(new Integer[]{5, 1, 2, 3, 9}),
    LINE7(new Integer[]{5, 11, 12, 13, 9}),
    LINE8(new Integer[]{0, 1, 7, 13, 14}),
    LINE9(new Integer[]{10, 11, 7, 3, 4}),

    ;

    private final ArrayList<Integer> indexes;

    BingoLines(Integer[] idx) {
        this.indexes = new ArrayList<>(Arrays.asList(idx));
    }

    public ArrayList<Integer> getIndexes() {
        return indexes;
    }

    public static ArrayList<ArrayList<Integer>> getBingoLines() {
        ArrayList<ArrayList<Integer>> bingoLines = new ArrayList<>();
        for (BingoLines line : BingoLines.values()) {
            bingoLines.add(line.getIndexes());
        }
        return bingoLines;
    }

}
