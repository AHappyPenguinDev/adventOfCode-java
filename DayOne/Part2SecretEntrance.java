package codeoftheadvent.src.DayOne;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2SecretEntrance {
    public static void main(String[] args) {
        int[] dial = new int[100];
        int max = 100;
        List<String> movesList = new ArrayList<String>();

        System.out.println("Initializing Dial values...");
        for (int i = 0; i < max; i++) {
            dial[i] = i;
            System.out.printf("Dial[%d] = %d%n", i, i);
        }
        System.out.println();
        // Need to get input from file
        try (BufferedReader reader = new BufferedReader(new FileReader("codeoftheadvent/src/DayOne/input.txt"));) {
            int counter = 0; // Keep track of how many moves for formatting
            String move;

            // This is just for cool formatting
            System.out.println("Adding moves: ");
            int hundredsCounter = 0;
            while ((move = reader.readLine()) != null) {
                movesList.add(move);
                counter++;
                if (counter % 100 == 0 || hundredsCounter == 0) { // show range from n - 100 to n every 100 lines
                    hundredsCounter++;
                    System.out.printf("%n%nMoves %d - %d: ", hundredsCounter * 100 - 100, hundredsCounter * 100);
                }
                System.out.printf("%s, ", move);
            }
            System.out.println();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Need to do the moves
        // Dial starts pointing at 50
        int pointer = 50;
        int zeroAmount = 0;
        int counter = 0;
        for (String move : movesList) {
            // Get multiplier (amount of moves) of move by selecting everything after the
            // first index
            String strMoveAmount = move.substring(1);
            int moveAmount = Integer.parseInt(strMoveAmount);
            // if (move.charAt(0) == 'R') {
            // System.out.printf("[R] Current Position: %d\n\tExecuting pointer = %d + %d",
            // pointer, pointer,
            // moveAmount);
            // pointer = pointer + moveAmount;
            // System.out.printf("\tPointer: %d\n", pointer);
            // if (pointer > 99) {
            // System.out.printf("Pointer is greater than 99: %d\n\tExecuting pointer = %d -
            // %d", pointer, pointer, 100 * (pointer / 100));
            // // 100 means a full rotation
            // pointer = pointer - 100 * (pointer / 100); // pointer - 100 by the amount of
            // System.out.printf("\tPointer: %d\n", pointer);
            // }
            //
            // if (pointer == 0) {
            // zeroAmount++;
            // System.out.printf("Zero found, amount of zeroes = %d\n", zeroAmount);
            // }
            // System.out.println();
            // System.out.println();
            // }

            if (move.charAt(0) == 'L') {
                
                if (moveAmount < 100) {
                    System.out.printf("[L] Current Position: %d\n\tExecuting pointer = %d - %d",pointer, pointer,moveAmount);
                    pointer = pointer - moveAmount;
                }
                
                if (moveAmount > 100) {
                    int diff = pointer - moveAmount % 100; // The remainder of moveAmount/100 = how much it needs to move after x full rotations
                    zeroAmount += pointer - moveAmount/100; // how many rotations are in move amount 
                    System.out.printf("[L] Current Position: %d", pointer);
                    System.out.printf("\n\tMove amount is greater than 100: %d\n\tExecuting pointer = %d - (%d %%100) = %d",moveAmount, pointer, moveAmount, diff);
                    pointer = diff;
                }
                

                // If it's less than 0, 100 + pointer = new position (or pointer+100)
                if (pointer < 0) {
                    System.out.printf("\nPointer is less than 0: %d\n\tExecuting pointer = %d +100", pointer, pointer);
                    pointer = pointer + 100;
                }

                System.out.printf("\n\tPointer: %d\n\tMove amount: %d", pointer, moveAmount);
                
                if (pointer == 0) {
                    zeroAmount++;
                    System.out.printf("Zero found, amount of zeroes = %d\n", zeroAmount);
                }
                System.out.println();
                System.out.println();
            }
        }
        System.out.printf("\n----- Total zeroes: %d -----\n", zeroAmount);

    }
}
