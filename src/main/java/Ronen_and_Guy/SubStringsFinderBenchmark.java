package Ronen_and_Guy;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SubStringsFinderBenchmark {

    char[] longString;
    SubStringsFileCreator.SubStringsIterator iterator;

    @Setup(Level.Iteration)
    public void setup() {
        longString = GiantTextFileCreator.getLongStringFromFile();
        iterator = new SubStringsFileCreator.SubStringsIterator();
    }

    // this takes a lot of time...
    //@Benchmark
    public void NaiveAlgorithmBenchmark() {
        char[] subString;

        while ((subString = iterator.next()) != null){
            for (int i = 0; i < this.longString.length - subString.length; i++){
                char[] checkedPartOfLongString = Arrays.copyOfRange(this.longString, i, i + subString.length);
                if(Arrays.equals(checkedPartOfLongString, subString)){
                    // found the index of substring, at 'i'
                    break;
                }
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
    public void algorithm2Benchmark() {
        // TODO: write better algorithm
    }

    @Benchmark
    public void algorithm3Benchmark() {
        // TODO: write better algorithm
    }
}
