package com.company;

public class Main {

    public static void main(String[] args) {
        int W = 10;
        int[] weights = {-1, 5, 3, 2, 1, 7};
        int[] benefits = {-1, 2, 4, 6, 8, 3};
	    Knapsack kp1 = new Knapsack(W, weights, benefits);
	    kp1.GreedyApproximateSolution();
	    kp1.BruteForceSolution();
    }
}
