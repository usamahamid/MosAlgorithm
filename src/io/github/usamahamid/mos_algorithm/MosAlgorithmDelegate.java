package io.github.usamahamid.mos_algorithm;

import java.util.Collections;
import java.util.List;

import io.github.usamahamid.mos_algorithm.base.BusinessLogicInterface;
import io.github.usamahamid.mos_algorithm.model.Query;

/**
 * Mo's Algorithm delegate which utilizes the given {@link BusinessLogicInterface} to identify
 * result for List of {@link Query}
 */
public class MosAlgorithmDelegate {

    private final List<Query> queries;
    private final BusinessLogicInterface logicInterface;

    public MosAlgorithmDelegate(BusinessLogicInterface logicInterface, List<Query> queries) {
        this.logicInterface = logicInterface;
        this.queries = queries;
    }

    private void onPreExecute() {
        Collections.sort(queries);
    }

    public void execute(List values) {
        onPreExecute();
        int currentLeftIndex = 1, currentRightIndex = 0;
        for (Query query : queries) {
            int queryLeftIndex = query.getLeftIndex();
            int queryRightIndex = query.getRightIndex();

            while (currentRightIndex > queryRightIndex) {
                logicInterface.removeFromResult(values.get(currentRightIndex));
                currentRightIndex--;
            }

            while (currentRightIndex < queryRightIndex) {
                currentRightIndex++;
                logicInterface.addIntoResult(values.get(currentRightIndex));
            }

            while (currentLeftIndex < queryLeftIndex) {
                logicInterface.removeFromResult(values.get(currentLeftIndex));
                currentLeftIndex++;
            }

            while (currentLeftIndex > queryLeftIndex) {
                currentLeftIndex--;
                logicInterface.addIntoResult(values.get(currentLeftIndex));
            }

            logicInterface.storeResultForQuery(query);
        }
    }

}
