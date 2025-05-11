/*
 * Name: Yasrib Yasir Farook
 * Date: 12/02/2024
 * Class: COSC-211
 * Description: 
 */
import java.io.File;
import java.util.Scanner;

public class FileSystemAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for directory name
        System.out.print("Enter the name of a directory: ");
        String dirName = scanner.nextLine();
        File directory = new File(dirName);

        if (directory.isDirectory()) {
            System.out.println("Number of files: " + countFiles(directory));
            System.out.println("Files in directory and subdirectories:");
            displayFiles(directory);
        } else {
            System.out.println(dirName + " is not a valid directory.");
        }

        // Prompt for file name
        System.out.print("Enter the name of a file to search for: ");
        String fileName = scanner.nextLine();
        File searchFile = new File(fileName);

        if (fileFound(directory, searchFile)) {
            System.out.println("File \"" + fileName + "\" was found in the directory.");
            System.out.println("Complete path: " + findFilePath(directory, searchFile));
        } else {
            System.out.println("File \"" + fileName + "\" was not found in the directory.");
        }

        scanner.close();
    }

    // Recursive method to count files
    public static int countFiles(File file) {
        int count = 0;

        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    count++;
                } else if (f.isDirectory()) {
                    count += countFiles(f); // Recursive call for subdirectory
                }
            }
        }
        return count;
    }

    // Recursive method to display files
    public static void displayFiles(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getAbsolutePath());
                if (f.isDirectory()) {
                    displayFiles(f); // Recursive call for subdirectory
                }
            }
        }
    }

    // Recursive method to check if file exists
    public static boolean fileFound(File file, File findFile) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile() && f.getName().equals(findFile.getName())) {
                    return true;
                } else if (f.isDirectory()) {
                    if (fileFound(f, findFile)) { // Recursive call for subdirectory
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Recursive method to find file path 
    public static String findFilePath(File file, File findFile) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile() && f.getName().equals(findFile.getName())) {
                    return f.getAbsolutePath();
                } else if (f.isDirectory()) {
                    String path = findFilePath(f, findFile); // Recursive call for subdirectory
                    if (path != null) {
                        return path;
                    }
                }
            }
        }
        return null;
    }
}
