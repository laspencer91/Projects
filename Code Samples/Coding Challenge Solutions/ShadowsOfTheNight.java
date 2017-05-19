import java.util.*;
import java.io.*;
import java.math.*;

/**
 * This is a solution for the shadows of the night episode 1 challenge
 * at    https://www.codingame.com/ide/puzzle/shadows-of-the-knight-episode-1
 * It uses Binary Search on two axis to find the target position in the least
 * amount of steps possible.
 **/
class ShadowsOfTheNight 
{
    public static void main(String args[]) 
    {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X = in.nextInt(); // Starting X position of search
        int Y = in.nextInt(); // Starting Y position of search
        
        int[][] windows = new int[W][H]; // Matrix with all possible positions X and Y
        // game loop
        
        int min_y = 0;
        int min_x = 0;
        int max_y = H;
        int max_x = W;
        
        while (true) 
        {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            switch (bombDir)
            {
                case "U":
                    max_x = X;
                    max_y = Y;
                    break;
                case "UR":
                    min_x = X;
                    max_y = Y;
                    break;
                case "R":
                    min_x = X;
                    min_y = Y; max_y = Y;
                    break;
                case "DR":
                    min_x = X;
                    min_y = Y;
                    break;
                case "D":
                    max_x = X; min_x = X;
                    min_y = Y;
                    break;
                case "DL":
                    max_x = X;
                    min_y = Y;
                    break;
                case "L":
                    min_y = Y; max_y = Y;
                    max_x = X;
                    break;
                case "UL":
                    max_x = X;
                    max_y = Y;
                    break;
            }
            

            int next_x = (max_x + min_x) / 2;
            int next_y = (max_y + min_y) / 2;
            X = next_x;
            Y = next_y;
            
            // the location of the next window Batman should jump to.
            System.out.println(next_x + " " + next_y);
        }
    }
}