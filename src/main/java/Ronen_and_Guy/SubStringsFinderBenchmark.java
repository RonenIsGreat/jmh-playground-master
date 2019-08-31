package Ronen_and_Guy;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SubStringsFinderBenchmark {

    char[] longString;
    SubStringsFileCreator.SubStringsIterator iterator;
    ExecutorService pool;

    @Setup(Level.Iteration)
    public void setup() {
        longString = GiantTextFileCreator.getLongStringFromFile();
        iterator = new SubStringsFileCreator.SubStringsIterator();
        pool = Executors.newCachedThreadPool();
    }

    // this takes a lot of time...
    //@Benchmark
    public void NaiveAlgorithmBenchmark() {
        char[] subString;

        while ((subString = iterator.next()) != null){
            int i = NaiveSearch.findIndexOf(this.longString, subString);

            if(i >= 0){
                // found the index of substring, at 'i'
            }
        }
    }

    @Benchmark
    public void JavaAlgorithmBenchmark() {
        String myLongString = new String(this.longString);
        char[] subString;

        while ((subString = this.iterator.next()) != null){
            String mySubString = new String(subString);
            int i = myLongString.indexOf(mySubString);

            if(i >= 0){
                // found the index of substring, at 'i'
            }
        }
    }

    @Benchmark
    public void JavaWithThreadsAlgorithmBenchmark() {
        String myLongString = new String(this.longString);
        char[] subString;

        while ((subString = this.iterator.next()) != null){
            final char[] threadSubString = subString;

            pool.execute(()->{
                String mySubString = new String(threadSubString);
                int i = myLongString.indexOf(mySubString);

                if(i >= 0){
                    // found the index of substring, at 'i'
                }
            });
        }

        // Wait for tasks to finish
        pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@Benchmark
    public void RabinKarpAlgorithmBenchmark() {
        char[] subString;

        while ((subString = iterator.next()) != null){
            int i = Rabin_Karp.findIndexOf(this.longString, subString);

            if(i >= 0){
                // found the index of substring, at 'i'
            }
        }
    }
}
