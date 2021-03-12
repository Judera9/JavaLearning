import java.io.*;

public class MatrixProduction {
    public int[][] readMatrix(String path) throws IOException {
        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(isr);
        String[] arr = reader.readLine().split(" ");

        int row = Integer.parseInt(arr[0]);
        int col = Integer.parseInt(arr[1]);
        int[][] matrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            String[] bufArr = reader.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                matrix[i][j] = Integer.parseInt(bufArr[j]);
            }
        }

        reader.close();
        return matrix;
    }

    public boolean calculateMatrix(int[][] Matrix1, int[][] Matrix2) {
        int row1 = Matrix1.length;
        int row2 = Matrix2.length;
        int col1 = Matrix1[0].length;
        int col2 = Matrix2[0].length;

        if (col1 == row2)
            return false;

        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < ; j++) {
                
            }
        }
    }

    public void writeMatrix(String path, int[][] matrix) throws IOException {
        File file = new File(path);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
        BufferedWriter writer = new BufferedWriter(osw);

        int row = matrix.length;
        int col = matrix[0].length;
        writer.write(String.format("%d %d", row, col));
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col - 1; j++) {
                writer.write(String.format("%d ", matrix[i][j]));
            }
            writer.write(String.format("%d", matrix[i][col - 1]));
        }

        writer.close();
    }
}
