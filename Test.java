import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args){
        writeToFile("data.txt", 0, 10000, 10);
        MergeSortVariation.externalMergeSort("data.txt");
        File file = new File("data.txt");
        //file.delete();

        System.out.println("Testing input files of different sizes");
        System.out.printf("%-10s\t%-10s\n", "Size", "Iterations");
        for(int i = 10000; i <= 100000; i = i + 10000){
            File newFile = new File("testFile.txt");
            try {
                newFile.createNewFile();
                writeToFile("testFile.txt", 0, 10000, i);
                MergeSortVariation.externalMergeSort("testFile.txt");
                newFile.delete();
                int iterations = MergeSortVariation.getIterations;
                System.out.printf("%-10d\t%-10d\n", i, iterations);
            }
            catch(IOException e){
                System.out.println("Cannot make new file.");
            }
        }
    }

    public static void writeToFile(String filename, int min, int max, int size){
        File file = new File(filename);
        int range = max - min + 1;
        try{
            PrintWriter writeFile = new PrintWriter(file);
            for(int i=0; i<size; i++){
                writeFile.print((int)(Math.random()*range) + min);
                if(i<size-1){
                    writeFile.println();
                }
            }
            writeFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot write to " + filename);
        }
    }
}
