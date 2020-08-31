package kai.simulator.slot;

import kai.simulator.utils.JsonUtil;

import java.util.ArrayList;

public class ScreenResult implements Comparable<ScreenResult> {

    private final int symbolSize;
    private final ArrayList<Integer> screen;
    private final ArrayList<ArrayList<Integer>> bingoIndexes;

    public ScreenResult(int symbolSize, ArrayList<Integer> screen, ArrayList<ArrayList<Integer>> bingoIndexes) {
        this.symbolSize = symbolSize;
        this.screen = screen;
        this.bingoIndexes = bingoIndexes;
    }

    public boolean isBingo() {
        return getBingoLineSize() > 0;
    }

    public int getBingoLineSize() {
        return bingoIndexes != null ? bingoIndexes.size() : 0;
    }

    public int getSymbolSize() {
        return symbolSize;
    }

    public ArrayList<Integer> getScreen() {
        return screen;
    }

    public ArrayList<ArrayList<Integer>> getBingoIndexes() {
        return bingoIndexes;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

    @Override
    public int compareTo(ScreenResult screenResult) {
        return Integer.compare(symbolSize, screenResult.getSymbolSize());
    }
}
