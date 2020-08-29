package kai.simulator;

import kai.simulator.slot.BingoCalculator;
import kai.simulator.slot.BingoLines;
import kai.simulator.slot.Screen;
import kai.simulator.slot.ScreenResult;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int wheelSize = 5;
        int symbolPerWheelSize = 3;
        int symbolSize = wheelSize * symbolPerWheelSize;
        int minBingoSize = 3;

        // 初始化盤面
        Screen screen = new Screen();
        screen.init(symbolSize);

        // 初始化中獎計算
        BingoCalculator calculator = new BingoCalculator(
                true,
                wheelSize,
                symbolPerWheelSize,
                minBingoSize,
                BingoLines.getBingoLines()
        );

        // 盤面結果
        ArrayList<ScreenResult> results = new ArrayList<>();

        // 轉動盤面
        while (!screen.isComplete()) {
            // 添加盤面計算結果
            ScreenResult result = new ScreenResult(screen.toIntegerList(), calculator.getBingoIndexes(screen.getScreen()));
            results.add(result);

            if (result.isBingo()) {
                // 紅明顯
                System.err.println(result);
            } else {
                System.out.println(result);
            }

            // 處理最後轉動
            screen.next();
        }

        System.out.println("===============================");
        System.out.println("RollTimes: " + screen.getRollTimes());

        // 處理result輸出檔案
        exportResults(results);
    }

    private static void exportResults(ArrayList<ScreenResult> results) {
        // ...輸出檔案的部分
    }

}
