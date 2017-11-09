package com.company;

import java.util.*;

public class Knapsack {

    int W;//knapsack weight
    int weights[];//list of weights
    int benefits[];//list of benefits
    int n;//number of items
    int benefit = 0;//benefit sum
    int weight = 0;//weight sum


    //since the weight/benefit ratios have to be sorted we need a class to keep track of the original index
    private class wbRatio implements Comparable<wbRatio> {
        int index;
        int weight;
        int benefit;
        double ratio;

        private wbRatio(int index, int weight, int benefit){
            this.index = index;
            this.weight = weight;
            this.benefit = benefit;
            ratio = ((double)benefit)/weight;
        }


        @Override
        public int compareTo(wbRatio o) {
            if(this.ratio < o.ratio) return -1;
            else if(o.ratio < this.ratio) return 1;
            else return 0;
        }
    }

    private class maxBenefitSet{
        int setWeight;
        int setBenefit;
        ArrayList<Integer> indices;

        public maxBenefitSet(int weight, int benefit, ArrayList<Integer> indices){
            this.setWeight = weight;
            this.setBenefit = benefit;
            this.indices = indices;
        }
    }

    //constructor
    public Knapsack(int W, int[] weights, int[] benefits){
        if(weights.length == benefits.length) {
            this.W = W;
            this.weights = weights;
            this.benefits = benefits;
            this.n = weights.length - 1;
        }else{
            System.out.println("Bad input");
        }
    }



    public int[] generateSubset(int k, int n){
        //initialize the bit array
        int binary[] = new int[n];
        for(int i: binary){
            binary[i] = 0;
        }

        int index = binary.length - 1;
        while(k > 0 ){
            binary[index--] = k%2;
            k = k/2;
        }
        return binary;
    }

    public void BruteForceSolution(){
        int binary[];
        int maxBenefit = 0;
        int numSets = (int)Math.pow(2, n);
        ArrayList maxSets = new ArrayList<maxBenefitSet>();

        System.out.println("Brute Force Solutions:");
        //for loop checking each of the possible sets
        for(int i = 0; i < numSets; i++) {
            ArrayList packedItems = new ArrayList<Integer>();
            int setWeight = 0;
            int setBenefit = 0;
            binary = generateSubset(i, n);

            for(int j = 0; j < binary.length; j++){
                if(binary[j] == 1){
                    packedItems.add(j + 1);
                    setBenefit += benefits[j + 1];
                    setWeight += weights[j + 1];
                }
            }

            if(setWeight <= W) {
                //check the set
                if (setBenefit > maxBenefit) {
                    maxBenefit = setBenefit;
                    maxSets.clear();
                    maxSets.add(new maxBenefitSet(setWeight, setBenefit, packedItems));
                } else if (setBenefit == maxBenefit) {
                    maxSets.add(new maxBenefitSet(setWeight, setBenefit, packedItems));
                } else {
                    //move on
                }
            }

        }

        //checked every subset. max benefits are in maxSets
        for(int i = 0; i < maxSets.size(); i++){
            maxBenefitSet currSet = (maxBenefitSet) maxSets.get(i);
            System.out.print("Optimal Set = { ");
            for(int j = 0; j < currSet.indices.size(); j++){
                System.out.print(currSet.indices.get(j) + ",");
            }
            System.out.print("\b } Weight sum = " + currSet.setWeight + " benefit sum = " + currSet.setBenefit + "\n");
        }
        System.out.println();

    }


    public void DynamicProgrammingSolution(boolean printBmatrix){

        int B[][] = new int[n+1][W+1];
        int[] selected = new int[n + 1];

        for(int w = 0; w < W + 1; w++){
            B[0][w] = 0;
        }

        for(int k = 1; k <= n; k++){
            for(int w = 0; w <= W; w++){
                if (w < weights[k]){
                    B[k][w] = B[k-1][w];
                }else{
                    B[k][w] = Math.max(B[k-1][w], B[k-1][w - weights[k]] + benefits[k]);
                }
            }
        }


        //finding items

        //set up selected[]
        selected[0] = -1;
        for(int t = 1; t <= n; t++){
            selected[t] = 0;
        }

        //mark the items
        int w = W;
        int it = n;
        while(w > 0 && it > 0){
            if(B[it][w] != B[it-1][w]){
                selected[it] = 1;
                w = w - weights[it];
                it = it - 1;
            }else{
                it = it - 1;
            }
        }


        //Print solution
        System.out.println("\nDynamic Programming Solution:");
        //print matrix
        if(printBmatrix == true){
            System.out.println();
            for(int i=0; i <= n; i++){
                for(int j = 0; j <= W; j++){
                    System.out.format("%5d", B[i][j]);
                }
                System.out.println();
            }
            System.out.println("\n");
        }else{
            System.out.println("Dynamic Matrix Not Requested");
        }

        weight = 0;
        benefit = 0;
        System.out.print("Optimal Set = { ");
        for(int i = 1; i < selected.length; i++){
            if(selected[i] == 1){
                System.out.print(i + ",");
                weight += weights[i];
                benefit += benefits[i];
            }
        }
        System.out.print("\b } weight sum = " + weight + " benefit sum = " + benefit + "\n");
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
        Arrays.sort(weightBenefitRatio, 1, n, Collections.reverseOrder());

        //do the greedy choice
        //storing items we select
        List packedItems = new ArrayList<wbRatio>();
        for(int i = 1; weight <= W && i < n; i++){
            int availableSpace = W - weight;
            if(weightBenefitRatio[i].weight <= availableSpace){
                packedItems.add(weightBenefitRatio[i]);
                weight += weightBenefitRatio[i].weight;
                benefit += weightBenefitRatio[i].benefit;
            }
        }


        //print out the list of packed items
        System.out.println("Greedy Approximate Solution:");
        System.out.print("Greedy set = { ");
        for(int i = 0; i < packedItems.size(); i++){
            wbRatio currItem = (wbRatio) packedItems.get(i);
            System.out.print(currItem.index + ",");
        }
        System.out.print("\b } weight sum = " + weight + " benefit sum = " + benefit +"\n");


    }

}
