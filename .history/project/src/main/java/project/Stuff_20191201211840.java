package project;

public class Stuff {
    public void run() {
        System.out.println("I AM DOING SOME STUFF...");
    }

    /**
     * Returns an integer square root of x (discarding the fractional parts)
     */
     public int isqrt(int x) {
	     int guess = 1;
	     while (guess * guess < x) {
	     	guess++;
     }
     return guess;
     }
}