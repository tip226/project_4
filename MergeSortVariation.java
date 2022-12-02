import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class MergeSortVariation {
    public static int getIterations = 0;
    public static void externalMergeSort(String filename){
        while(!split(filename, "tempFile1.txt", "tempFile2.txt")){
            getIterations++;
            merge("tempFile1.txt", "tempFile2.txt", filename);
        }
        File tempFile1 = new File("tempFile1.txt");
        File tempFile2 = new File("tempFile2.txt");
        tempFile1.delete();
        tempFile2.delete();
    }
    
    // splitting the input file into two partially
    // sorted temporary files
    public static boolean split(String filename, String file1, String file2) {
        File file = new File(filename);
        File tempFile1 = new File(file1);
        File tempFile2 = new File(file2);
        try{
            Scanner read = new Scanner(file);
            PrintWriter file1Writer = new PrintWriter(tempFile1);
            PrintWriter file2Writer = new PrintWriter(tempFile2);
            PrintWriter writeFile = file1Writer;
            int previous = read.nextInt();
            int current = read.nextInt();
            writeFile.println(previous);
            while(read.hasNextInt()){
                if(current >= previous){
                    writeFile.println(current);
                } else {
                    if(writeFile == file1Writer){
                        writeFile = file2Writer;
                    } else {
                        writeFile = file1Writer;
                    }
                    writeFile.println(current);
                }
                previous = current;
                current = read.nextInt();
            }
            read.close();
            file1Writer.close();
            file2Writer.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0);
        }
        if(tempFile2.length() == 0L){
                return true;
            } else {
                return false;
            }
    }

    // Merging the two temporary files into the input file
    public static void merge(String file1, String file2, String filename){
        File file = new File(filename);
        try{
            PrintWriter writeFile = new PrintWriter(file);
            File tempFile1 = new File(file1);
            File tempFile2 = new File(file2);
            Scanner read1 = new Scanner(tempFile1);
            Scanner read2 = new Scanner(tempFile2);
            int item1 = read1.nextInt();
            int item2 = read2.nextInt();
            while(read1.hasNextInt() && read2.hasNextInt()){
                if(item1 < item2){
                    writeFile.println(item1);
                    item1 = read1.nextInt();
                }
                else{
                    writeFile.println(item2);
                    item2 = read2.nextInt();
                }
            }
            // process any remaining items from file1 or file2
            while(read1.hasNextInt()){
                int item = read1.nextInt();
                writeFile.println(item);
            }
            while(read2.hasNextInt()){
                int item = read2.nextInt();
                writeFile.println(item);
            }
            read1.close();
            read2.close();
            writeFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot merge files.");
            System.exit(0);
        }
    }
}
