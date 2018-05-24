package io.github.usamahamid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.github.usamahamid.logic.OddSumLogicInterface;
import io.github.usamahamid.mos_algorithm.MosAlgorithmDelegate;
import io.github.usamahamid.mos_algorithm.model.Query;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

    public static void main(String[] args) {
        // Get length of list from User.
        int length = scanner.nextInt();

        // Mo's Algorithm block size
        int blockSize = (int) Math.sqrt(length);

        List<Integer> integers = new ArrayList<Integer>();

        // Get list of integers from User.
        // Make the list 1-indexed, since query left and right indices will be entered as 1-indexed.
        // Reduces the calculation later, where it requires to subtract index by one.
        integers.add(0);
        for (int i = 1; i < (length + 1); i++) {
            integers.add(scanner.nextInt());
        }

        // Get total count of ranges and individual ranges from the User.
        int queryCount = scanner.nextInt();
        List<Query> queries = new ArrayList<Query>();
        for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
            int leftIndex = scanner.nextInt();
            int rightIndex = scanner.nextInt();
            // Create a list of Mo's Algorithm query from the ranges.
            queries.add(new Query(leftIndex, rightIndex, queryIndex, blockSize));
        }

        // Declare OddSumLogicInterface and use Mo's Algorithm to calculate the Sum of integers
        OddSumLogicInterface logicInterface = new OddSumLogicInterface(queries);
        MosAlgorithmDelegate delegate = new MosAlgorithmDelegate(logicInterface, queries);
        delegate.execute(integers);

        // Print the Sum of integers in list which has odd frequency each the given range (defined by query)
        List<Long> result = logicInterface.getResult();
        for (int i = 0; i < queryCount; i++) {
            System.out.println(result.get(i));
        }
    }
}
