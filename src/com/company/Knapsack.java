package com.company;

import java.util.*;

public class Knapsack {

    int W;
    int weights[];
    int benefits[];
    int n;
    wbRatio weightBenefitRatio[];
    int[][] B;
    int benefit = 0;
    int weight = 0;


    //since the weight/benefit ratios have to be sorted we need a class to keep track of the original index
    public class wbRatio implements Comparator<wbRatio> {
        int index;
        int weight;
        int benefit;
        double ratio;

        public wbRatio(int index, int weight, int benefit){
            this.index = index;
            this.weight = weight;
            this.benefit = benefit;
            ratio = ((double)weight)/benefit;
        }

        @Override
        public int compare(wbRatio o1, wbRatio o2) {
            if (o1.ratio < o2.ratio) return -1;
            if (o1.ratio > o2.ratio) return 1;
            return 0;
        }
    }

    public Knapsack(int W, int[] weights, int[] benefits){
        this.W = W;
        this.weights = weights;
        this.benefits = benefits;
        this.n = weights.length;
    }

    public int[] generateSubset(int k, int n){
        int[] binaryRep = new int[n];
        return binaryRep;
    }

    public void BruteForceSolution(){
        double twoToTheN = Math.pow(2.0, (double)n);
        int numSubSets = (int)twoToTheN - 1;
        int binaryRep[] = new int[n];

        for(int i = 1; i < numSubSets; i++){
            binaryRep = generateSubset(i,numSubSets);
        }

        for(int i = 0; i < n; i++){
            if(binaryRep[i] == 1){
                //add to item to knapsack
            }else if(binaryRep[i] == 0){
                //skip
            }else{
                //these should only be 1s and 0s you messed up
            }
        }

        //print out solutions


    }

    public void DynamicProgrammingSolution(boolean printBmatrix){

    }

    public void GreedyApproximateSolution(){
        //initialize weightBenefitRatio array
        weightBenefitRatio = new wbRatio[n];
        weightBenefitRatio[0] = new wbRatio(0, -1, -1);

        //storing items we select
        List packedItems = new ArrayList<wbRatio>();

        //calculate weight/benefit ratio for all items, add to array
        for(int i = 1; i < n; i++){
            wbRatio ratio = new wbRatio(i, weights[i], benefits[i]);
            weightBenefitRatio[i] = ratio;
        }

        //sort weight/benefit ratio array in descending order
        Arrays.sort(weightBenefitRatio, Collections.reverseOrder());

        //do the greedy choice
        for(int i = 0; weight <= W && i < n; i++){
            int availableSpace = W - weight;
            if(weightBenefitRatio[i].weight <= availableSpace){
                packedItems.add(weightBenefitRatio[i]);
                weight += weightBenefitRatio[i].weight;
                benefit += weightBenefitRatio[i].benefit;
            }
        }

        //print out the list of packed items



    }


}
