import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to My 2-D ArrayList Processor!\n");

        //Create a Scanner object for standard input
        Scanner stdInput = new Scanner(System.in);

        //Prompt the user for which file to use
        System.out.print("\nEnter the file to process (v(dataValid.txt) or i(dataInvalid.txt): ");
        
        String inputType = stdInput.nextLine().toLowerCase();
        String inFileName, outFileName;
        char type = inputType.toLowerCase().charAt(0);

        //if 'v' use the valid data file, if i, use the invalid data file
        if (type == 'v'){
            inFileName = "dataValid.txt";
            outFileName = "dataValidOutput.txt";
        }
        else if (type == 'i'){
            inFileName = "dataInvalid.txt";
            outFileName = "dataInvalidOutput.txt";
        }
        else{
            System.out.println("Entry is invali");
            stdInput.close();
            return;
        }
        stdInput.close();

        //try is provided here for the Scanner input of a file
        String inputStr1, inputStr2;        
        try {
            File inFile = new File (inFileName);
            PrintWriter outFile = new PrintWriter(outFileName);
            Scanner input = new Scanner(inFile);

            while (input.hasNextLine()){
                //get 2 input lines
                //should probably check that the input has a nextLine before reading inLine2
                String inLine1 = input.nextLine();

                if (!input.hasNextLine())
                    break;

                String inLine2 = input.nextLine();
                
                //declare two 2-D ArrayLists
                ArrayList<ArrayList<Integer>> arr1 = new ArrayList<ArrayList<Integer>>();
                ArrayList<ArrayList<Integer>> arr2 = new ArrayList<ArrayList<Integer>>();

                outFile.println("\n\nPROCESSING A SET OF MATRICES");

                outFile.println("\n\tPROCESSING FIRST MATRIX");
                
                //verify 2-D Matrix
                //if valid, print & process the Matrix
                //if not, print a message
                int valid1 = ArrayFunctions.verifyArrayList(inLine1, arr1, outFile);

                if (valid1 == 1) {
                    ArrayFunctions.printArrayList(arr1, outFile);
                    ArrayFunctions.processArrayList(arr1, outFile);
                }

                outFile.println("\n\tPROCESSING SECOND MATRIX");
 
                //verify 2-D Matrix
                //if valid, print & process the Matrix
                //if not, print a message
                int valid2 = ArrayFunctions.verifyArrayList(inLine2, arr2, outFile);

                if (valid2 == 1) {
                    ArrayFunctions.printArrayList(arr2, outFile);
                    ArrayFunctions.processArrayList(arr2, outFile);
                }

                outFile.println("\n\tPROCESSING BOTH MATRICES");

                //if both are valid and the sizes are compatible, add the matrices
                //PART2: if both are valid and the sizes are compatible, multiply the matrices
                //if one of the matrices is not valid, print a message
                //if the sizes are not compatible, print a message
                if (valid1 == 1 && valid2 == 1) {
                    ArrayFunctions.addTwoMatrices(arr1, arr2, outFile);
                    ArrayFunctions.multMatrices(arr1, arr2, outFile);
                }
                else {
                    outFile.println("Unable to process matrices: Invalid matrix.");
                }
 
            }
           input.close();
           outFile.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found: " + inFileName);
            return;
        }

    }


}