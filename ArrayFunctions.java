import java.io.PrintWriter;
import java.util.ArrayList;

public class ArrayFunctions{
    //PRE:  accepts a string
    //POST: determines that each character in the string is a digit
    //      returns true if so, false if not
    public static boolean isDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i))))
                return false;
        }
        return true;
    }

    //PRE: accepts a string
    //POST: verifies that the string only contains 0s and 1s
    //      returns true if so, false if not
    public static boolean isBinary(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0' || str.charAt(i) == '1')
                continue;
            else
                return false;
        }
        return true;
    }

    //PRE: This function will accept the input string, an empty 2-D ArrayList, and the output file
    //POST: if ‘valid’ return 1, if not print a message and return -1
    //      if ‘valid’ load the ArrayList with the values in the input string and return 1
    public static int verifyArrayList(String inputLine, 
                                      ArrayList<ArrayList<Integer>> arr1, 
                                      PrintWriter outFile) {
        String[] tokens = inputLine.trim().split(" ");

        //test to make sure there are at least 2 tokens for row & col
        if (tokens.length < 2) {
            outFile.println("\tMatrix contains invalid row/col values.");
            return -1;
        }
        
        //use position [0] for number of rows and position[1] for number of cols
        int row, col;
        try {
            row = Integer.parseInt(tokens[0]);
            col = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            outFile.println("\tMatrix contains invalid row/col values.");
            return -1;
        }

        //PART 2:  check the row & col values are between 1 and 5 (inclusive) 
        //PART 2:  check that tokens.length has enough data for the matrix
        if (row < 1 || row > 5 || col < 1 || col > 5) {
            outFile.println("\tMatrix contains invalid row/col values.");
            return -1;
        }

        if (tokens.length < row * col + 2) {
            outFile.println("\tMatrix does not contain enough values.");
            return -1;
        }

        //starting at position 2 in tokens, read remaining characters
        //see lecture example for loading individual entries in tokens into the ArrayList
        //PART 2:  verfiy that each value in token is isBinary
        int position = 2;

        for (int i = 0; i < row; i++) {
            ArrayList<Integer> tempRow = new ArrayList<Integer>();

            for (int j = 0; j < col; j++) {
                if (!isBinary(tokens[position])) {
                    outFile.println("\tMatrix contains an invalid value.");
                    return -1;
                }

                tempRow.add(Integer.parseInt(tokens[position]));
                position++;
            }

            arr1.add(tempRow);
        }

        return 1;
    }


    //PRE: accepts a 2-D ArrayList that has been loaded with proper values and the output file
    //POST: prints the 2-D ArrayList to the output file
    public static void printArrayList(ArrayList<ArrayList<Integer>> arr1, 
                                      PrintWriter outFile) {
        //print 2-D ArrayList
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr1.get(i).size(); j++) {
                outFile.print(arr1.get(i).get(j) + " ");
            }
            outFile.println();
        }
    }

    
    //PRE:  Accepts one 2 dimensional ArrayList that has been loaded with proper values
    //      and the output file
    //POST: Deterimines if the number of rows = number of columns for this ArrayList
    //      if not, prints the message: "Cannot determine reflexive/symmetric: Invalid dimensions"
    //      if so, determines if the ArrayList is reflexive or symmetric and prints out a message for each  
    public static void processArrayList(ArrayList<ArrayList<Integer>> arr1, 
                                        PrintWriter outFile) {
   
        //If the number of rows != number of columns, print message & return
        if (arr1.size() != arr1.get(0).size()) {
            outFile.println("Cannot determine reflexive/symmetric: Invalid dimensions");
            return;
        }

        //Check for Reflexive
        boolean reflexive = true;

        for (int i = 0; i < arr1.size(); i++) {
            if (arr1.get(i).get(i) != 1) {
                reflexive = false;
            }
        }
        
        //Check for Symmetric
        boolean symmetric = true;

        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr1.get(i).size(); j++) {
                if (!arr1.get(i).get(j).equals(arr1.get(j).get(i))) {
                    symmetric = false;
                }
            }
        }
        
        //Print results
        if (reflexive)
            outFile.println("Matrix is reflexive.");
        else
            outFile.println("Matrix is not reflexive.");

        if (symmetric)
            outFile.println("Matrix is symmetric.");
        else
            outFile.println("Matrix is not symmetric.");
    }

    //PRE:  Accepts two 2 dimensional ArrayLists that have been loaded with proper values
    //      and the output file
    //POST: Determine if the the ArrayLists are the same size
    //       if not, print the message: "Unable to add Matrices: size mismatch." to the output file & return
    //       if so, add the corresponding elements (row,col) from each ArrayList and print to output file 
    //
    public static void addTwoMatrices(ArrayList<ArrayList<Integer>> arr1, 
                                      ArrayList<ArrayList<Integer>> arr2,
                                      PrintWriter outFile) {
        //verify if matrices can be added 
        //If the matrix sizes do not match print message & return
        if (arr1.size() != arr2.size() ||
            arr1.get(0).size() != arr2.get(0).size()) {
            outFile.println("Unable to add Matrices: size mismatch.");
            return;
        }

        //add corresponding elements & prints the result to the output file
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr1.get(i).size(); j++) {
                outFile.print((arr1.get(i).get(j) + arr2.get(i).get(j)) + " ");
            }
            outFile.println();
        }
    }

    //PRE: Accepts two valid 2 dimensional ArrayLists and the output file
    //POST: Multiplies the matrices if their dimensions are compatible
    //      otherwise prints a size mismatch message
    public static void multMatrices(ArrayList<ArrayList<Integer>> arr1,
                                    ArrayList<ArrayList<Integer>> arr2,
                                    PrintWriter outFile) {

        if (arr1.get(0).size() != arr2.size()) {
            outFile.println("Unable to multiply Matrices: size mismatch.");
            return;
        }

        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr2.get(0).size(); j++) {
                int total = 0;

                for (int k = 0; k < arr1.get(0).size(); k++) {
                    total = total + arr1.get(i).get(k) * arr2.get(k).get(j);
                }

                outFile.print(total + " ");
            }
            outFile.println();
        }
    }
}