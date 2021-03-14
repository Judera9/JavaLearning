
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MatrixProduction {
    public int[][] readMatrix(String path) {
        try {
            File file = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader read = new BufferedReader(isr);

            ArrayList<String> bufferLines = new ArrayList<>();
            int rowCount = 0;
            int colCount = 0;
            while (true) {
                String line = read.readLine();
                if (line != null) {
                    bufferLines.add(line);
                    rowCount++;
                } else {
                    break;
                }
            }

            colCount = bufferLines.get(0).split(" ").length;
            int[][] matrix = new int[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                String[] rowDigits = bufferLines.get(i).split(" ");
                for (int j = 0; j < rowDigits.length; j++) {
                    matrix[i][j] = Integer.parseInt(rowDigits[j]);
                }
            }

            read.close();
            return matrix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveMatrix(int[][] matrix, String path) throws IOException {
        if (matrix == null) {
            return false;
        }

        File file = new File(path);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(osw);

        int rowLen = matrix[0].length;
        int colLen = matrix.length;
        for (int i = 0; i < colLen; i++) {
            for (int j = 0; j < rowLen - 1; j++) {
                writer.write(String.format("%d ", matrix[i][j]));
            }
            writer.write(String.format("%d\n", matrix[i][rowLen - 1]));
        }

        writer.close();
        return true;
    }

    public int[][] production(int[][] matrixA, int[][] matrixB) {
        int colCount = matrixB[0].length;
        int rowCount = matrixA.length;
        int[][] matrixC = new int[rowCount][colCount];

        if (matrixA[0].length != matrixB.length) {
            return null;
        }

        int middleCount = matrixB.length;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                for (int k = 0; k < middleCount; k++) {
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }

        }

        return matrixC;
    }

    public static void main(String[] args) throws IOException {
        String apath = "src/A.txt";
        String bpath = "src/B.txt";
        String savepath = "src/C.txt";
        MatrixProduction mp = new MatrixProduction();

        int[][] matrixA = mp.readMatrix(apath);
        int[][] matrixB = mp.readMatrix(bpath);
        int[][] matrixC = mp.production(matrixA, matrixB);

        mp.saveMatrix(matrixC, savepath);
        System.out.println(Arrays.deepToString(matrixC));
        //boolean status = mp.saveMatrix(savepath);
    }
}
