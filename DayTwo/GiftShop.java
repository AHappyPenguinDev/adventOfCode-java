package codeoftheadvent.src.DayTwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiftShop {
    public static void main(String[] args) {
        //Start reading from file
        String allIdRanges = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("codeoftheadvent/src/DayTwo/input.txt"))) {
            allIdRanges = reader.readLine();
            // System.out.println("ID ranges retrieved");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] idRanges = allIdRanges.split(",");

        long invalidIds=0;
        int middle=0; //Middle Left
                  
        // Finished reading from file
        // Start separating range
        
rangeLoop:
        for(String range : idRanges) {
            String strLNum = range.substring(0, range.indexOf("-")); //before "-"
            String strRNum = range.substring(range.indexOf("-") + 1, range.length());// after "-"
            System.out.printf("\n%s\nLooking for invalid IDs in the range %s - %s...", 
                    "-------------------------------------------------------",strLNum, strRNum);
            
            int leftNumFirstHalf=0;
            int leftNumSecondHalf=0;
            long leftNum = Long.parseLong(strLNum);
            long rightNum = Long.parseLong(strRNum);

            System.out.printf("\n\nLeft Num before while = %d",leftNum); 
            strLNum = String.valueOf(leftNum);
            
            //Add amount required to make number have even amount of digits
            if(strLNum.length() % 2 != 0) {
                //Some ranges have max values with odd digits, in this case there is no possible invalid ID, so skip
                if(strRNum.length() % 2 != 0)  {
                    System.out.printf("\n%s No possible invalid ID for range %d-%d as both rightNum and leftNum have an odd amount of digits %s", 
                            "---", leftNum, rightNum, "---");
                    continue rangeLoop; // Continue outer loop
                }
                int digitAmount = String.valueOf(leftNum).length(); 
                System.out.printf("\n\tExecuting %d += %.0f - %d",leftNum, Math.pow(10,digitAmount), leftNum);
                leftNum += Math.pow(10,digitAmount) - leftNum;
                System.out.printf("\n\tLNum = %d", leftNum);
                strLNum= String.valueOf(leftNum);
            }
            
            //Split num in half to check for equal sequences later
            if(strLNum.length() > 1 ) {
                middle = (strLNum.length() - 1) /2; // find middle of num
                System.out.printf("\nMiddle of %d is index %s", leftNum, middle);
                
                leftNumFirstHalf = Integer.parseInt(strLNum.substring(0, middle + 1));
                leftNumSecondHalf = Integer.parseInt(strLNum.substring(middle + 1));
                System.out.printf("\n\tFIRST half = %d | SECOND half = %d", leftNumFirstHalf, leftNumSecondHalf);
            }
            
            //Find Invalid ids by checking if LHS == RHS
            while(leftNum <= rightNum) {
                if(leftNumFirstHalf == leftNumSecondHalf) {
                    String concatenatedHalfs = String.valueOf(leftNumFirstHalf) + String.valueOf(leftNumSecondHalf);
                    invalidIds += Long.parseLong(concatenatedHalfs);
                    System.out.printf("\n\t%d = %d, Invalid ID found!", leftNumFirstHalf,leftNumSecondHalf);
                    System.out.printf("\n\tThe concatenated value is %s", concatenatedHalfs);
                    System.out.println("\nInvalid IDs added up: " + invalidIds);
                }

                //Reset value of vars when RHS > LHS, would have problems otherwise
                if(leftNumFirstHalf < leftNumSecondHalf) {
                    System.out.printf("\n\tLFH < LSH -> %d < %d",leftNumFirstHalf,leftNumSecondHalf);
                        
                    int secondHalfDigitAmount = String.valueOf(leftNumSecondHalf).length(); 
                    System.out.printf("\n\tExecuting %d += %.0f - %d",leftNum, Math.pow(10,secondHalfDigitAmount), leftNumSecondHalf);
                    leftNum += Math.pow(10,secondHalfDigitAmount) - leftNumSecondHalf;
                    System.out.printf("\n\tLNum = %d", leftNum);
                    
                    strLNum= String.valueOf(leftNum);
                    leftNumFirstHalf = Integer.parseInt(strLNum.substring(0, middle + 1));
                    leftNumSecondHalf = Integer.parseInt(strLNum.substring(middle + 1));
                    if(strLNum.length() % 2 != 0) {
                        System.out.printf("\n\ts < %s | Continuing range loop", leftNumFirstHalf, leftNumSecondHalf);
                        continue rangeLoop;
                    }
                }
                // System.out.printf("\n\tSecond half -> %d | Left num -> %d", leftNumSecondHalf+1, leftNum + 1); //Uncomment this to see each adding operation being done
                leftNumSecondHalf++;
                leftNum++;
            }
            System.out.println();
        }
        System.out.println("--- Invalid IDs added up: " + invalidIds + " ---");
        System.out.println();
    }
}
