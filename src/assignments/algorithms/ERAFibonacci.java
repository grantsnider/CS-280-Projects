package assignments.algorithms;

/**
 * Empiricial Runtime Analysis of several Fibonacci implementations.
 * 
 * Compared Dynamic programming, Recursive, and Binet approaches.
 */
public class ERAFibonacci {

    /**
    * Compute the nth Fibonacci number using dyanmic programming.
    * 
    * @param n the Fibonacci index
    * @return the nth Fibonacci number
    */
    public static int fibonacci(int n) {
        if (n <= 1)
            return 1;
        int last = 1;
        int nextToLast = 1;
        int answer = 1;

        for (int i = 2; i <= n; i++) {
            answer = last + nextToLast;
            nextToLast = last;
            last = answer;
        }

        return answer;
    }

    /**
    * Computes the nth Fibonacci number using recursion.
    * 
    * @param x the Fibonacci index
    * @return the nth Fibonacci number
    */
    public static long fibRec(int x) {
        if (x <= 1)
            return 1;
        else
           return fibRec(x - 1) + fibRec(x - 2);
    }

    /**
     * Computes Binets formula using the nth Fibonacci number.
     * 
     * @param n the Fibonacci index
     * @return the nth Fibonacci number.
     */
    public static long fibBinet(int n) {
        double phi = (1 + Math.sqrt(5)) / 2.0;
        double psi = (1 - Math.sqrt(5)) / 2.0;
        double results = (Math.pow(phi, n) - Math.pow(psi, n)) / Math.sqrt(5);

        return Math.round(results);
    }

    /**
     * Times Fibonacci computes for runtime analysis.
     * 
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("N\tTime");
        for (int n = 1000; n <= 100000; n += 1000) {
            long start = System.nanoTime();
            fibonacci(n);
            long end = System.nanoTime();
            double time = (end - start) / 1e9;
            System.out.println(n + "\t" + time);
    
        }
    }
}
