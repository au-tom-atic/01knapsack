import com.company.Knapsack;

import java.util.Arrays;
import java.util.Random;

public class KnapsackDriver {

    public static void main(String[] args) {
        test();
        //experiment1();
        //experiment2();
    }

    public static void test(){
        //Testcase #1
        int n = 7;
        int W = 100;
        int[] weights = {-1, 60, 50, 60, 50, 70, 70, 45};
        int[] benefits = {-1, 180, 95, 40, 95, 40, 40, 105};
        Knapsack test1 = new Knapsack(W, weights, benefits);
        System.out.format("%30s", "-----Test 1-----\n");
        test1.BruteForceSolution();
        test1.GreedyApproximateSolution();
        test1.DynamicProgrammingSolution(false);

        //Testcase #2
        int n2 = 18;
        int W2 = 39;
        int[] weights2 = {-1,25,4,2,5,6,2,7,8,2,1,1,3,5,8,9,6,3,2};
        int[] benefits2 = {-1,75,7,4,3,2,6,8,7,9,6,5,4,8,10,8,1,2,2};
        System.out.format("%30s", "-----Test 2-----\n");
        Knapsack test2 = new Knapsack(W2, weights2, benefits2);
        test2.BruteForceSolution();
        test2.GreedyApproximateSolution();
        test2.DynamicProgrammingSolution(false);

        //Testcase #3
        int n3 = 20;
        int W3 = 29;
        int[] weights3 =  {-1, 10,14,35,12,16,20,13,7,2,4,3,10,5,6,17,7,9,3,4,3};
        int[] benefits3 =  {-1, 2,13,41,1,12, 5,31,2,41,16,2,12,1,13,4, 51,6,12,1,9};
        System.out.format("%30s", "-----Test 2-----\n");
        Knapsack test3 = new Knapsack(W3, weights3, benefits3);
        test3.BruteForceSolution();
        test3.GreedyApproximateSolution();
        test3.DynamicProgrammingSolution(false);

        //Testcase #4
        int n4 = 7;
        int W4 = 10;
        int[] weights4 = {-1,2,5,3,2,5,3,7};
        int[]benefits4 = {-1, 5,10,5,20,15,5,10};
        System.out.format("%30s", "-----Test 4----\n");
        Knapsack test4 = new Knapsack(W4, weights4, benefits4);
        test4.BruteForceSolution();
        test4.GreedyApproximateSolution();
        test4.DynamicProgrammingSolution(true);

    }

    public static void experiment1(){
        //experiment to determine the runtime of the greedy solution
        final int[] arraySizes = {10, 100, 1000, 10000, 100000, 1000000, 2000000};
        int W;
        int weights[];
        int benefits[];
        long start = 0;
        long finish = 0;
        double avg =0;
        double avgOverLog;
        Knapsack exp;

        System.out.format("%10s%30s%30s", "n", "mean", "mean/(n*log(n))");
        System.out.println();
        for(int s = 0; s < arraySizes.length; s++){

            long sum = 0;
            W = arraySizes[s];

            for(int i = 0; i < 20; i++){
                weights = randomArray(arraySizes[s]);
                benefits = randomArray(arraySizes[s]);

                exp = new Knapsack(W, weights, benefits);

                start = System.nanoTime();
                exp.GreedyApproximateSolution();
                finish = System.nanoTime() - start;
                sum += finish;
            }

            avg = sum / 20.0;
            double nlog2n = (arraySizes[s] * (Math.log(arraySizes[s]) / Math.log(2)));
            avgOverLog = avg / nlog2n;

            System.out.format("%10s%30s%30s",
                    arraySizes[s], avg, avgOverLog);
            System.out.println();

        }

    }

    //expirement to test how often the greedy solution finds the optimal solution
    //modified GreedyApproximateSolution and DynammicProgrammingSolution
    // to return the benefit for comparison
    public static void experiment2(){
        int sumOptimal = 0;
        int weights[];
        int benefits[];
        int W = 100;
        int offBySum = 0;
        int offCounter = 0;
        Knapsack exp;

        for(int i = 0; i < 10000; i++){
            int greedyBenefit = 0;
            int dynamicBenefit = 0;
            weights = randomArray(20);
            benefits = randomArray(20);

            exp = new Knapsack(W, weights, benefits);

            //greedyBenefit = exp.GreedyApproximateSolution();
            //dynamicBenefit = exp.DynamicProgrammingSolution(false);

            if(greedyBenefit == dynamicBenefit){
                sumOptimal++;
            }else{
                offBySum += Math.abs(greedyBenefit - dynamicBenefit);
                offCounter++;
            }
        }

        double percent = (sumOptimal / 10000.0) * 100;
        double offByAverage = (double) offBySum / offCounter;

        System.out.println("Greedy Approximate Solution finds Optimal Solution " + percent + "% of the time");
        System.out.println("The Greedy Solution was off by an average of " + offByAverage);
    }

    private static int[] randomArray(int size){
        Random rand = new Random();
        int range = 100 - 1 + 1;
        int[] r = new int[size];
        r[0] = -1;
        for(int i = 1; i< r.length;i++){
            r[i] = rand.nextInt(range) + 1;
        }
        return r;
    }
}
