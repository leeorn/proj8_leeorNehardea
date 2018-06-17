package cs1c;
/**
 * Leeor N
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestFile
{
    private static final int MIN_ARRAY_SIZE = 20000;
    private static final int MAX_SIZE = 10000000;
    private static final int INCREMENT_AMOUNT = 500000;
    private static final int MAX_RECURSION = 300;
    private static final int MIN_RECURSION = 2;


    public static void main(String[] args)
    {
        int arraySize;
        long startTime, endTime, totalTime;
        String toPrintCSV = "Array,\tRecursion, \tTime " +
                "(millisec)\n";
        String fileName = "";

        for (arraySize = MIN_ARRAY_SIZE; arraySize <= MAX_SIZE;
             arraySize += INCREMENT_AMOUNT)
        {
            Integer originalArray[] = new Integer[arraySize];

            Random randomNum = new Random();
            for (int i = 0; i < arraySize; i++)
            {
                originalArray[i] = randomNum.nextInt(MAX_SIZE);
            }

            for (int i = MIN_RECURSION; i <= MAX_RECURSION; i+= 2)
            {
                Integer copiedArray[] = originalArray.clone();
                startTime = System.nanoTime();
                FHsort.setRecursionLimit(i);
                FHsort.quickSort(copiedArray);
                endTime = System.nanoTime();
                totalTime = endTime - startTime;

                System.out.println("Array size: " + originalArray.length +
                        ", Number of recursions: " + i + ", Time it takes: "
                        + TimeConverter.convertTimeToString(totalTime));

                toPrintCSV +=  originalArray.length + ",\t " + i + ",\t\t" +
                        totalTime + "\n";
            }
        }

        // Write to a file in CSV format
        try
        {
            FileWriter fw = new FileWriter("resources/valuesList.csv");
            BufferedWriter bw = new BufferedWriter(fw);

            toPrintCSV = toPrintCSV.substring(0, toPrintCSV.length() - 1);
            bw.write( toPrintCSV);
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Couldn't write to the file");
        }
    }
}
