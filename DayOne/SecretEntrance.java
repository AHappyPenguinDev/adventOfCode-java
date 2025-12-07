package codeoftheadvent.src.DayOne;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecretEntrance {
    public static void main(String[] args) {
        List<String> movesList = new ArrayList<String>();

        System.out.println();
        // Need to get input from file
        try (BufferedReader reader = new BufferedReader(new FileReader("codeoftheadvent/src/input.txt"));) {
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
        for (String move : movesList) {
            // Get multiplier (amount of moves) of move by selecting everything after the
            // first index
            String strMoveAmount = move.substring(1);
            int moveAmount = Integer.parseInt(strMoveAmount);
            if (move.charAt(0) == 'R') {
                // If going to the RIGHT, middle = 0, since 99 + n = 0 +n, so ADD to 0
                // 90 + 20 = 110, 110 - 99 = position
                // The difference between the sum of the current position with the move and 99
                // gives the new position {
                System.out.printf("[R] Current Position: %d\n\tExecuting pointer = %d + %d",
                        pointer, pointer,
                        moveAmount);
                pointer = pointer + moveAmount;
                System.out.printf("\tPointer: %d\n", pointer);
                if (pointer > 99) {
                    System.out.printf("Pointer is greater than 99: %d\n\tExecuting pointer = %d - %d", pointer, pointer,
                            100 * (pointer / 100));
                    // 100 means a full rotation
                    pointer = pointer - 100 * (pointer / 100); // pointer - 100 by the amount of
                    // 100s in pointer
                    System.out.printf("\tPointer: %d\n", pointer);
                }
                if (pointer == 0) {
                    zeroAmount++;
                    System.out.printf("Zero found, amount of zeroes = %d\n", zeroAmount);
                }
                System.out.println();
                System.out.println();
                // }
            }

            if (move.charAt(0) == 'L') {
                if (moveAmount < 100) {
                    System.out.printf("[L] Current Position: %d\n\tExecuting pointer = %d - %d", pointer, pointer,
                            moveAmount);
                    pointer = pointer - moveAmount;
                }

                // p = 50 - 110
                // p = -60

                if (moveAmount > 100) {
                    int diff = pointer - moveAmount % 100;
                    System.out.printf("[L] Current Position: %d", pointer);
                    System.out.printf(
                            "\n\tMove amount is greater than 100: %d\n\tExecuting pointer = %d - (%d %% 100) = %d",
                            moveAmount, pointer, moveAmount, diff);
                    pointer = diff;
                }

                if (pointer < 0) {
                    System.out.printf("\nPointer is less than 0: %d\n\tExecuting pointer = %d + 100", pointer, pointer);
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
