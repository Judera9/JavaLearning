//package Ass1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MaxProfit {
    public static void main(String[] args) throws IOException {
        MaxProfit mp = new MaxProfit();
        for (String arg : args) {
            ArrayList<int[]> testList = mp.readTests(arg);
            int[] results = new int[testList.size()];
            for (int j = 0; j < testList.size(); j++)
                results[j] = mp.maxProfit(testList.get(j));
            mp.printResults(results);
            if (args.length > 1 && !arg.equals(args[args.length - 1])) {
                System.out.println();
            }
        }
    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

    public ArrayList<int[]> readTests(String path) throws IOException {
        ArrayList<int[]> OneTestList = new ArrayList<int[]>();

        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader reader = new BufferedReader(isr);

        String readInStringLine = reader.readLine();
        int TestRowNumber = 0;
        while (readInStringLine != null) {
            String[] TestStringList = readInStringLine.split(" ");
            OneTestList.add(new int[TestStringList.length]);
            for (int i = 0; i < TestStringList.length; i++) {
                OneTestList.get(TestRowNumber)[i] = Integer.parseInt(TestStringList[i]);
            }
            TestRowNumber++;
            readInStringLine = reader.readLine();
        }

        reader.close();
        return OneTestList;
    }

    public boolean saveResults(int[] results, String path) throws IOException {
        File file = new File(path);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(osw);

        for (int result : results) {
            writer.write(String.format("%d\n", result));
        }

        writer.close();
        return true;
    }

    public void printResults(int[] results) {
        for (int i = 0; i < results.length - 1; i++) {
            int result = results[i];
            System.out.print(String.format("%d ", result));
        }
        System.out.printf("%d", results[results.length - 1]);
    }
}
