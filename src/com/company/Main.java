package com.company;

public class Main {

    public static void main(String[] args) {
        int W = 100;
        int[] weights = {-1, 60, 50, 60, 50, 70, 70, 45};
        int[] benefits = {-1, 180, 95, 40, 95, 40, 40, 105};
	    Knapsack kp1 = new Knapsack(W, weights, benefits);
	    kp1.GreedyApproximateSolution();
	    //kp1.BruteForceSolution();
    }
}
