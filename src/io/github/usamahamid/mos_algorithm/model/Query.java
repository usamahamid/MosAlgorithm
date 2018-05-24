package io.github.usamahamid.mos_algorithm.model;


/**
 * Query Data model, representing individual queries in Mo's Algorithm
 */
public class Query implements Comparable<Query> {
    /**
     * Left Index of the query
     */
    private int leftIndex;

    /**
     * Right Index of the query
     */
    private int rightIndex;

    /**
     * Sequence/order of the query
     */
    private int queryIndex;

    /**
     * Block size of query
     */
    private int blockSize;

    public Query(int leftIndex, int rightIndex, int queryIndex, int blockSize) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.queryIndex = queryIndex;
        this.blockSize = blockSize;
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(int leftIndex) {
        this.leftIndex = leftIndex;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }

    public int getQueryIndex() {
        return queryIndex;
    }

    public void setQueryIndex(int queryIndex) {
        this.queryIndex = queryIndex;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    @Override
    public int compareTo(Query o) {
        return compareByMos(o);
    }

    /**
     * Compares this query with the specified query for order. Returns a
     * negative integer, zero, or a positive integer as this query is less
     * than, equal to, or greater than the specified query, by <b>Mo’s order</b>.
     *
     * @see <a href="https://www.hackerearth.com/practice/notes/mos-algorithm/">Mo's Algorithm</a>
     */
    private int compareByMos(Query o) {
        if ((leftIndex - 1) / blockSize != (o.leftIndex - 1) / blockSize) {
            return (leftIndex - 1) / blockSize - (o.leftIndex - 1) / blockSize;
        }
        return rightIndex - o.rightIndex;
    }
}
