import java.util.Scanner;

public class PeakColumns {
    public static int[][] populateMatrix(Scanner scanner, int cols, int rows){
        int[][] matrix = new int[rows][cols];
        // Fill the matrix with user input, validating to ensure no spaces
        System.out.println("Enter the matrix values (one number per line):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (true) {
                    System.out.print("Element at (" + (i + 1) + "," + (j + 1) + "): ");
                    String input = scanner.nextLine();
                    if (input.matches("-?\\d+")) { // Only single integer input per line
                        matrix[i][j] = Integer.parseInt(input);
                        break;
                    } else {
                        System.out.println("Invalid input. Enter a single integer without spaces.");
                    }
                }
            }
        }

        System.out.println("\nMatrix:");
        displayMatrix(matrix, rows, cols);
        return matrix;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get matrix dimensions with validation
        int rows = 0, cols = 0;
        while (true) {
            System.out.print("Enter number of rows: ");
            String inputRows = scanner.nextLine();
            System.out.print("Enter number of columns: ");
            String inputCols = scanner.nextLine();

            if (inputRows.matches("\\d+") && inputCols.matches("\\d+")) {
                rows = Integer.parseInt(inputRows);
                cols = Integer.parseInt(inputCols);
                if (rows > 0 && cols > 0) break;
            }
            System.out.println("Invalid input. Enter positive integers for rows and columns.");
        }

        int[][] matrix = populateMatrix(scanner, cols, rows);;

//
        System.out.println("\nMatrix:");
        displayMatrix(matrix, rows, cols);

        // Find and display peak-columns
        System.out.println("\nPeak-Columns Found:");
        boolean peakFound = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isPeakColumn(matrix, i, j, rows, cols)) {
                    System.out.printf("(%d, %d) = %d%n", i + 1, j + 1, matrix[i][j]);
                    peakFound = true;
                }
            }
        }

        if (!peakFound) {
            System.out.println("No peak-columns found.");
        }
    }

    // Helper method to display the matrix
    private static void displayMatrix(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%4d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // Method to check if an element is a peak-column
    private static boolean isPeakColumn(int[][] matrix, int row, int col, int rows, int cols) {
        int value = matrix[row][col];

        // Check if it's the maximum in its row
        for (int j = 0; j < cols; j++) {
            if (matrix[row][j] > value) {
                return false;
            }
        }

        // Check if it's the minimum in its column
        for (int i = 0; i < rows; i++) {
            if (matrix[i][col] < value) {
                return false;
            }
        }

        return true;
    }
}