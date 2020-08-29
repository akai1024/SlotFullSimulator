package kai.simulator.slot;

import kai.simulator.utils.JsonUtil;

import java.util.ArrayList;

public class ScreenResult {

    private final ArrayList<Integer> screen;
    private final ArrayList<ArrayList<Integer>> bingoIndexes;

    public ScreenResult(ArrayList<Integer> screen, ArrayList<ArrayList<Integer>> bingoIndexes) {
        this.screen = screen;
        this.bingoIndexes = bingoIndexes;
    }

    public boolean isBingo() {
        return bingoIndexes != null && bingoIndexes.size() > 0;
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
}
