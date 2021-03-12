import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Scanner;

public class Hailstone {
    private int ReIndex = 0;

    public int nonreHailStone(int n) {
        if (n == 0){
            System.out.println("Wrong Input");
            return 0;
        }

        ArrayList<Integer> answers = new ArrayList<>();
        answers.add(n);

        while (n != 1) {
            System.out.printf("%d,", n);
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            answers.add(n);
        }

        System.out.printf("%d\n", 1);
        return answers.size();
    }

    public int reHailStone(int n) {
        if (n == 0){
            System.out.println("Wrong Input");
            return 0;
        }

        if (n == 1) {
            System.out.printf("%d\n", 1);
            this.ReIndex++;
            return ReIndex;
        }
        this.ReIndex++;
        System.out.printf("%d,", n);
        if (n % 2 == 0) {
            return reHailStone(n / 2);
        } else {
            return reHailStone(3 * n + 1);
        }
    }

    public static void main(String[] args) {
        Hailstone hs = new Hailstone();
        int n = 1;
        while (n > 0) {
            System.out.println("please input the number N of Hailstone(N):");
            Scanner inputScanner = new Scanner(System.in);
            n = inputScanner.nextInt();
            System.out.println("total numbers:" + hs.nonreHailStone(n));
            System.out.println("total numbers:" + hs.reHailStone(n));
        }
    }
}
