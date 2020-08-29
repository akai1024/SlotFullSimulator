package kai.simulator.slot;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 中獎計算機（只適用矩形盤面）
 */
public class BingoCalculator {

    /**
     * 是否雙向中獎計算
     */
    private final boolean isTwoWay;

    /**
     * 總輪數
     */
    private final int wheelSize;

    /**
     * 每輪symbol數
     */
    private final int symbolPerWheelSize;

    /**
     * 最小中獎數
     */
    private final int minBingoSize;

    /**
     * 中獎線位置
     * 例如3x5盤面：
     * 0, 1, 2, 3, 4
     * 5, 6, 7, 8, 9
     * 10, 11, 12, 13, 14
     */
    private final ArrayList<ArrayList<Integer>> bingoLineIndex;

    public BingoCalculator(boolean isTwoWay, int wheelSize, int symbolPerWheelSize, int minBingoSize, ArrayList<ArrayList<Integer>> bingoLineIndex) {
        this.isTwoWay = isTwoWay;
        this.wheelSize = wheelSize;
        this.symbolPerWheelSize = symbolPerWheelSize;
        this.minBingoSize = minBingoSize;
        this.bingoLineIndex = bingoLineIndex;
    }

    public ArrayList<ArrayList<Integer>> getBingoIndexes(ArrayList<Boolean> screen) {
        ArrayList<ArrayList<Integer>> bingoIndexes = new ArrayList<>();
        // 錯誤的盤面資訊
        if (screen == null || screen.size() < wheelSize * symbolPerWheelSize) {
            return bingoIndexes;
        }

        // 對每條線
        bingoLineIndex.forEach(indexes -> {
            if (indexes.size() < minBingoSize) {
                // 這條線不可能達成最小連線
                return;
            }

            // 正向中獎
            ArrayList<Integer> normalBingoIdx = isBingo(indexes, screen);
            if (normalBingoIdx != null) {
                bingoIndexes.add(normalBingoIdx);
            }

            // 反向也要計算（正向連線數小於總輪數時算，最大連線只會被計算一次）
            if (isTwoWay && (normalBingoIdx == null || normalBingoIdx.size() < wheelSize)) {
                ArrayList<Integer> reverseIndexes = new ArrayList<>(indexes);
                Collections.reverse(reverseIndexes);
                ArrayList<Integer> reversBingoIdx = isBingo(reverseIndexes, screen);
                if (reversBingoIdx != null) {
                    bingoIndexes.add(reversBingoIdx);
                }
            }

        });
        return bingoIndexes;
    }

    private ArrayList<Integer> isBingo(ArrayList<Integer> indexes, ArrayList<Boolean> screen) {
        ArrayList<Integer> bingoIdx = new ArrayList<>();
        // 從左而右判斷
        for (int pointer = 0; pointer < indexes.size(); pointer++) {
            int index = indexes.get(pointer);
            if (screen.get(index)) {
                bingoIdx.add(index);
            } else {
                break;
            }
        }
        // 超過最小中獎數
        return (bingoIdx.size() >= minBingoSize) ? bingoIdx : null;
    }

}
