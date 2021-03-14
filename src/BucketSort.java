import java.lang.reflect.Array;
import java.util.*;

public class BucketSort {

    public static void main(String[] args) {
        BucketSort bs = new BucketSort();
        int[] array = bs.getArray(100000, 100000);
        // bucketCount should be less than bound
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> sortedList = bs.bucketSort(array, 42, 100000);
        long endTime = System.currentTimeMillis();
        System.out.println(sortedList);
        System.out.println("runTime is: " + (endTime - startTime) + "ms");
    }

    // 创造一个长度为arraySize的随机数列
    public int[] getArray(int arraySize, int bound) {
        int[] array = new int[arraySize];
        Random r = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = r.nextInt(bound);
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    public ArrayList<Integer> bucketSort(int[] array, int bucketCount, int bound) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        float division = (float)bound / bucketCount;
        int bucketSize = (int)Math.ceil(division);
        ArrayList<Integer>[] bucket = new ArrayList[bucketCount];

        for (int value : array) {
            int bucketNumber = value / bucketSize;
            if (bucket[bucketNumber] == null) {
                bucket[bucketNumber] = new ArrayList<>();
            }
            bucket[bucketNumber].add(value);
        }

        for (int i = 0; i < bucketCount; i++) {
            if (bucket[i] != null) {
                Collections.sort(bucket[i]);
                sortedList.addAll(bucket[i]);
            }
        }

        return sortedList;
/*
        // find the max and min for the count of buckets
        int max = array[0], min = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
        } else if (value < min) {
            min = value;
        }
*/
    }
}