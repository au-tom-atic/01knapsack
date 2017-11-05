package com.company;

import java.util.*;

public class Knapsack {

    int W;
    int weights[];
    int benefits[];
    int n;

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

    public class maxBenefitSet{
        int setWeight;
        int setBenefit;
        ArrayList<Integer> setIndices;

        public maxBenefitSet(int weight, int benefit, ArrayList<Integer> indices){
            this.setWeight = weight;
            this.setBenefit = benefit;
            this.setIndices = indices;
        }
    }

    //constructor
    public Knapsack(int W, int[] weights, int[] benefits){
        if(weights.length == benefits.length) {
            this.W = W;
            this.weights = weights;
            this.benefits = benefits;
            this.n = weights.length;
        }else{
            System.out.println("Bad input");
        }
    }

    public int[] generateSubset(int k, int n){
        int[] binaryRep = new int[n];
        return binaryRep;
    }

    public void BruteForceSolution(){
        double twoToTheN = Math.pow(2.0, (double)n);
        int numSubSets = (int)twoToTheN - 1;
        int binaryRep[];
        int maxBenefit = 0;
        ArrayList maxSets = new ArrayList<maxBenefitSet>();

        System.out.println("Brute Force Solutions");
        //for loop checking each of the possible sets
        for(int i = 1; i < numSubSets; i++){
            ArrayList packedItems = new ArrayList<Integer>();
            int setWeight = 0;
            int setBenefit = 0;
            binaryRep = generateSubset(i,numSubSets);

            for(int j = 0; j < n; i++){
                if(binaryRep[j] == 1){
                    //add to item to knapsack
                    packedItems.add(j);
                    setWeight += weights[j];
                    setBenefit += benefits[j];
                }else if(binaryRep[i] == 0){
                    //skip
                }else{
                    //these should only be 1s and 0s you messed up
                }
            }

            //check if set is feasible
            if(setWeight <= W){
                //check if benefit is greater. clear current maxSets. add to maxSets
                if(setBenefit > maxBenefit){
                    maxBenefit = setBenefit;
                    maxBenefitSet newSet = new maxBenefitSet(setWeight, setBenefit, packedItems);
                    maxSets.clear();
                    maxSets.add(newSet);
                }else if(setBenefit == maxBenefit){//if benefit is equal add to list
                    maxBenefitSet newSet = new maxBenefitSet(setWeight, setBenefit, packedItems);
                    maxSets.add(newSet);
                }else{
                    //not enough benefit
                }
            }else{
                //weight was too much
            }
        }

        //print all maxSets
        for (int i = 0; i < maxSets.size(); i++){
            System.out.print("Optimal Set = { ");
            maxBenefitSet currSet = (maxBenefitSet) maxSets.get(i);
            for(int j = 0; j < currSet.setIndices.size(); j++){
                System.out.print(currSet.setIndices.get(j) + ",");
            }
            System.out.print("weight sum = " + currSet.setWeight + " benefit sum = " + currSet.setBenefit);
        }

    }

    public void DynamicProgrammingSolution(boolean printBmatrix){
        int B[][] = new int[W][n];


    }

    public void GreedyApproximateSolution(){
        //initialize weightBenefitRatio array
        wbRatio weightBenefitRatio[] = new wbRatio[n];
        weightBenefitRatio[0] = new wbRatio(0, -1, -1);


        //calculate weight/benefit ratio for all items, add to array
        for(int i = 1; i < n; i++){
            wbRatio ratio = new wbRatio(i, weights[i], benefits[i]);
            weightBenefitRatio[i] = ratio;
        }

        //sort weight/benefit ratio array in descending order
        Arrays.sort(weightBenefitRatio, Collections.reverseOrder());

        //do the greedy choice
        //storing items we select
        List packedItems = new ArrayList<wbRatio>();
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
