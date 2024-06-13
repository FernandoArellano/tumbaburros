package com.example.tumbaburros.hackerrank;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuickTest {

    /*
    Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
    A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
     */
    public static int equalPairs(int[][] grid) {

        List<String> rowsList = new ArrayList<>();
        List<String> columnsList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int counter =0;

        for(int i=0; i<grid.length;i++){
            for(int j=0; j< grid.length;j++) {
                sb.append(grid[i][j]);
                if(j< grid.length-1){
                    sb.append(",");
                }
            }
            rowsList.add(sb.toString());
            sb = new StringBuilder();
        }

        for(int i=0; i<grid.length;i++){
            for(int j=0; j< grid.length;j++) {
                sb.append(grid[j][i]);
                if(j< grid.length-1){
                    sb.append(",");
                }
            }
            columnsList.add(sb.toString());
            sb = new StringBuilder();
        }

        for(String s: rowsList){
            if(columnsList.contains(s)){
                counter+=columnsList.stream().filter(c->s.equals(c)).count();
            }
        }


        return counter;

    }

    public static void main(String[] args) {
        System.out.println(equalPairs(new int[][]{{3,1,2,2},{1,4,4,4},{2,4,2,2},{2,5,2,2}}));
    }
}
