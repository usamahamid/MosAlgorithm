package io.github.usamahamid.mos_algorithm.base;


import io.github.usamahamid.mos_algorithm.model.Query;

/**
 * Interface to be implement the business logic being utilized in Mo's Algorithm.
 */
public interface BusinessLogicInterface<E> {

    /**
     * Add new element into result, when query's range is inclusive.
     */
    void addIntoResult(E e);

    /**
     * Remove new element from result, when query's range is out of bounds.
     */
    void removeFromResult(E e);

    /**
     * Store the result, when the calculation for query range is complete.
     */
    void storeResultForQuery(Query query);
}
