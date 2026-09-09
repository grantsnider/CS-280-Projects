package assignments.algorithms;

import assignments.sorting.InsertionSort;

public class ERADemo {
    public static void main(String[] args) {
        for (int N = 100; N < 5000; N += 100){
            Integer [] array = randomArray(N);
            InsertionSort<Integer> sorter = new InsertionSort<>();
            Long start = System.nanoTime();
            sorter.sort(array);
            Long end = System.nanoTime();
            double duration = (end - start) / (1e9);
            System.out.println(N + "\t" + duration);

        }
    }
    private static Integer[] randomArray(int N) {
        Integer[] array = new Integer[N];
        for(int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * N);
        }
        return array;
    }
}


//java -cp lib/* -ea src/assignments/algorithms/ERADemo.java