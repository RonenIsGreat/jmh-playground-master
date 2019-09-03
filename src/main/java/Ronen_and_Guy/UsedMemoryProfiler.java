package Ronen_and_Guy;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.profile.InternalProfiler;
import org.openjdk.jmh.results.AggregationPolicy;
import org.openjdk.jmh.results.IterationResult;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.ScalarResult;

import java.util.ArrayList;
import java.util.Collection;

public class UsedMemoryProfiler implements InternalProfiler {

    @Override
    public String getDescription() {
        return "Used memory heap profiler";
    }

    @Override
    public void beforeIteration(BenchmarkParams benchmarkParams, IterationParams iterationParams) {

    }

    @Override
    public Collection<? extends Result> afterIteration(BenchmarkParams benchmarkParams, IterationParams iterationParams,
                                                       IterationResult result) {

        final long usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Collection<ScalarResult> results = new ArrayList<>();
        results.add(new ScalarResult("Minimum used memory", usedMem, "bytes", AggregationPolicy.MIN));
        results.add(new ScalarResult("Average used memory", usedMem, "bytes", AggregationPolicy.AVG));
        results.add(new ScalarResult("Maximum used memory", usedMem, "bytes", AggregationPolicy.MAX));

        return results;
    }
}