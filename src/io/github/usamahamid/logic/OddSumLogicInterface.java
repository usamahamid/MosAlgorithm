package io.github.usamahamid.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.usamahamid.mos_algorithm.base.BusinessLogicInterface;
import io.github.usamahamid.mos_algorithm.model.Query;

/**
 * The business logic implementation being utilized in Mo's Algorithm.
 * Provides Sum of integers in a list which has odd frequency with the given range.
 */
public class OddSumLogicInterface implements BusinessLogicInterface<Integer> {

    /**
     * Stores the frequency of an integer.
     */
    private final Map<Integer, Integer> frequencyMap;

    /**
     * Stores the sum of integers with odd frequency with each range.
     */
    private final List<Long> result;

    /**
     * Stores transient/temporary result when adding or removing a integer's sum.
     */
    private long currentResult;

    public OddSumLogicInterface(List<Query> queries) {
        frequencyMap = new HashMap<Integer, Integer>();

        // Initialize the result array
        result = new ArrayList<Long>(queries.size());
        for (int i = 0; i < queries.size(); i++) {
            result.add(0L);
        }
    }

    public List<Long> getResult() {
        return result;
    }

    @Override
    public void addIntoResult(Integer value) {
        updateCurrentResult(value, true);
    }

    @Override
    public void removeFromResult(Integer value) {
        updateCurrentResult(value, false);
    }

    @Override
    public void storeResultForQuery(Query query) {
        result.set(query.getQueryIndex(), currentResult);
    }

    /**
     * Update the current result with the
     */
    private void updateCurrentResult(Integer value, boolean isWithinCurrentRange) {
        int freq = getFreq(value);
        boolean isOdd = (freq % 2) != 0;
        if (isOdd) {
            // Update the current result by removing Sum of integers if the frequency is odd,
            // before the increasing or decreasing the frequency.
            currentResult -= (freq * value);
        }

        // Update the frequency of integer within the current range.
        if (isWithinCurrentRange) {
            frequencyMap.put(value, ++freq);
        } else {
            frequencyMap.put(value, --freq);
        }

        // The frequency will be odd now after adding or subtracting, if wasn't odd before.
        if (!isOdd) {
            // Update the current result with Sum of integers if the frequency is odd.
            currentResult += (freq * value);
        }
    }

    /**
     * Get the frequency for given integer
     */
    private int getFreq(Integer value) {
        Integer freq = frequencyMap.get(value);
        if (freq == null) {
            frequencyMap.put(value, 0);
            freq = 0;
        }
        return freq;
    }
}
