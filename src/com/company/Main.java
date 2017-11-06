package com.company;

public class Main {

    public static void main(String[] args) {
        int W = 10;
        int[] weights = {1, 6, 5, 6, 5, 7, 7, 4};
        int[] benefits = {-1, 80, 95, 40, 95, 40, 40, 105};
	    Knapsack kp1 = new Knapsack(W, weights, benefits);
//	    kp1.GreedyApproximateSolution();
	    kp1.DynamicProgrammingSolution(true);
//	    kp1.BruteForceSolution();


    }
}
