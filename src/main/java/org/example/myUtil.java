package org.example;

public class myUtil {
    static int[] pair;

    public static int[] indexToCoordinate(int i, int j) {
        // i -> y
        // j -> x
        pair = new int[2];
        pair[1] = i * 50;
        pair[0] = j * 50;
        return pair;
    }

    public static int[] coordinateToIndex(int x, int y) {
        // x -> j
        // y -> i
        pair = new int[2];
        pair[1] = x/50;
        pair[0] = y/50;
        return pair;
    }

}
