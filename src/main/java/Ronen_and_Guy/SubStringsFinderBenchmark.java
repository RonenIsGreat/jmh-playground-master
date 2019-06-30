package Ronen_and_Guy;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SubStringsFinderBenchmark {

    char[] longString;
    SubStringsFileCreator.SubStringsIterator iterator;

    @Setup(Level.Iteration)
    public void setup() {
        longString = GiantTextFileCreator.getLongStringFromFile();
        iterator = new SubStringsFileCreator.SubStringsIterator();
    }

    @Benchmark
    public void algorithm1Benchmark() {
        char[] subString;

        while ((subString = iterator.next()) != null){
            // TODO: write naive algorithm
        }
    }

    @Benchmark
    public void algorithm2Benchmark() {
        // TODO: write better algorithm
    }

    @Benchmark
    public void algorithm3Benchmark() {
        // TODO: write better algorithm
    }
}
