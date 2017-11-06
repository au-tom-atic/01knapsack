import com.company.Knapsack;

public class KnapsackDriver {

    public static void main(String[] args) {
        test();
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

    public static void experiment(){

    }
}
