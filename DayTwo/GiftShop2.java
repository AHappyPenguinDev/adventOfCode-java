package codeoftheadvent.src.DayTwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiftShop2 {
    public static void main(String[] args) {
        String allIdRanges = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("codeoftheadvent/src/DayTwo/input.txt"))) {
            allIdRanges = reader.readLine();
            // System.out.println("ID ranges retrieved");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] idRanges = allIdRanges.split(",");

        int invalidIds=0;
        int midL; //Middle Left
                  
        String range = idRanges[0];
            String strLNum = range.substring(0, range.indexOf("-")); //before "-"
            String strRNum = range.substring(range.indexOf("-") + 1, range.length());// after "-"
            System.out.printf("\n\nLooking for invalid IDs in the range %s - %s...", strLNum, strRNum);
            
            int leftNumFirstHalf=0;
            int leftNumSecondHalf=0;
            long leftNum = Long.parseLong(strLNum);
            long rightNum = Long.parseLong(strRNum);

            int digitAmount=strLNum.length(); //number of digits 
            long buffer = leftNum;
            
            //Check if amount of digits is even, as odd numbers cannot be split in the middle
            do {
                System.out.printf("\n\nleftNum = %d, digit amount: %d",leftNum,digitAmount);
                //Gets amount of digits in number 
                while(true) {
                    System.out.printf("\nBuffer is: %d", buffer);
                    if(buffer > 0) {
                        buffer /= 10;
                        digitAmount++;
                        leftNum++;
                        System.out.printf("\nCurrent digit amount: %d",digitAmount);
                        continue;
                    }
                    break;
                }
                
                buffer=leftNum;
            } while(digitAmount % 2 != 0);

            System.out.printf("\n\nLeft Num = %d",leftNum); 
            strLNum = String.valueOf(leftNum);


            
            //Need to check for small numbers to not make the number 0
            if(strLNum.length() == 1 ) {
                System.out.printf("\n\tLength is 1, leftNumFirstHalf = %d", leftNumFirstHalf);
                leftNumFirstHalf = Integer.parseInt(strLNum);
            }
            
            
            //Split num in half to check for equal sequences later
            if(strLNum.length() > 1 ) {
            midL = (strLNum.length() - 1) /2; // find middle of num
            System.out.printf("\nMiddle of %d is index %s", leftNum, midL);
            
            leftNumFirstHalf = Integer.parseInt(strLNum.substring(0, midL + 1));
            leftNumSecondHalf = Integer.parseInt(strLNum.substring(midL + 1));
            System.out.printf("\n\tFIRST half = %d | SECOND half = %d", leftNumFirstHalf, leftNumSecondHalf);
            }

            
            //For each range, check if left side first = left side second and add 1 until it reaches max
            for (;leftNum <= rightNum; leftNum++) {
                
                // Need to figure out how to add numbers to the right side 
                    // while rhs > lhs, add 1
                    //     if rhs == math.pow(10,digitAmount/2) // if rhs reached max number in its decimal placing
                    //     make rhs == 0
                    //     and add 1 to lhs
                    // 3100
                    //

                //If rhs is greater than lhs, need to add 1 to rhs
                //until it is equal to its max value given its amount
                //of digits, max value for 5 would be 10, as it has 1 digit
                //this makes nooo sense
                while(leftNumSecondHalf > leftNumFirstHalf) {
                    leftNumSecondHalf++;
                    leftNum++;
                    System.out.printf("\n\tLeft num + 1 = %d", leftNum);
                    System.out.printf("\n\tIs %d = %.2f?",leftNumSecondHalf, Math.pow(10, digitAmount/2));
                    if(leftNumSecondHalf == Math.pow(10, digitAmount/2)) {
                        System.out.printf("\n%d is %.2f, executing %d = 0 and leftNumFirstHalf++ = %d",leftNumSecondHalf, Math.pow(10, digitAmount/2), leftNumSecondHalf, leftNumFirstHalf+1);
                        leftNumSecondHalf=0;
                        leftNumFirstHalf++; 
                    }
                }

                boolean isEqual = leftNumFirstHalf == leftNumSecondHalf;
                if(isEqual) 
                    invalidIds++;
                
                System.out.printf("\n%d %s %d, %s", leftNumFirstHalf, isEqual?"=":"!=", leftNumSecondHalf, isEqual?"Id is invalid, invalidIds = " + invalidIds : "Id is valid, skipping");
                leftNumSecondHalf++;
            }
        // }
        
    }
}
