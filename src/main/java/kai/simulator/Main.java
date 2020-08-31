package kai.simulator;

import kai.simulator.slot.BingoCalculator;
import kai.simulator.slot.BingoLines;
import kai.simulator.slot.Screen;
import kai.simulator.slot.ScreenResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

        // 計算symbol總數與中獎線總數關係
        HashMap<Integer, Integer> symbolLineSize = new HashMap<>();

        // 轉動盤面
        while (!screen.isComplete()) {
            // 添加盤面計算結果
            ScreenResult result = new ScreenResult(
                    screen.getSymbolSize(),
                    screen.toIntegerList(),
                    calculator.getBingoIndexes(screen.getScreen()));
            results.add(result);

            if (result.isBingo()) {
                // 紅明顯
                System.err.println(result);

                // 累加中獎線數
                int sSize = result.getSymbolSize();
                int lineSize = symbolLineSize.getOrDefault(sSize, 0);
                symbolLineSize.put(sSize, lineSize + result.getBingoLineSize());
            } else {
                System.out.println(result);
            }

            // 處理最後轉動
            screen.next();
        }

        System.out.println("===============================");
        System.out.println("RollTimes: " + screen.getRollTimes());

        // 按照盤面symbol總數排序結果
        Collections.sort(results);

        // 處理result輸出檔案
        exportResults(results, symbolLineSize);
    }

    private static void exportResults(ArrayList<ScreenResult> results, HashMap<Integer, Integer> symbolLineSize) {
        // ...輸出檔案的部分
        try {

            String fileName = System.currentTimeMillis() + ".txt";
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter writer = new FileWriter(fileName);

            // symbol總數
            symbolLineSize.forEach((key, value) -> {
                try {
                    writer.write(String.format("Symbol總數: %d, 中獎線總數: %d", key, value));
                    writer.write("\n\r");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.write("=====================");
            writer.write("\n\r");

            // 盤面結果
            results.forEach(result -> {
                try {
                    writer.write(result.toString());
                    writer.write("\n\r");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
