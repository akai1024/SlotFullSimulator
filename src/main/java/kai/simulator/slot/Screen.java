package kai.simulator.slot;

import kai.simulator.utils.JsonUtil;

import java.util.ArrayList;

/**
 * 盤面資訊
 */
public class Screen {

    /**
     * 是否滾動完所有組合
     */
    private boolean isComplete = false;
    private long rollTimes = 0;
    private ArrayList<Boolean> screen = new ArrayList<>();

    public void init(int symbolSize) {
        if (symbolSize > 0) {
            for (int i = 0; i < symbolSize; i++) {
                screen.add(false);
            }
        }
        isComplete = false;
        rollTimes = 0;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public long getRollTimes() {
        return rollTimes;
    }

    /**
     * 下一個盤面
     */
    public void next() {
        // 永遠都從第0位開始轉動
        rollToNext(0);
        rollTimes++;
    }

    /**
     * 轉動symbol
     */
    private void rollToNext(int index) {
        if (index >= screen.size()) {
            isComplete = true;
            return;
        }

        boolean symbol = !screen.get(index);
        screen.set(index, symbol);
        // 反向後為0代表進位
        if (!symbol) {
            rollToNext(index + 1);
        }
    }

    public ArrayList<Boolean> getScreen() {
        return screen;
    }

    public ArrayList<Integer> toIntegerList() {
        ArrayList<Integer> numList = new ArrayList<>();
        screen.forEach(bool -> numList.add(bool ? 1 : 0));
        return numList;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(toIntegerList());
    }

}
