package Ronen_and_Guy;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Stack;
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
        final String text = new String(this.longString);
        char[] subString;

        while ((subString = iterator.next()) != null){
            final String pattern = new String(subString);
            final int i = NaiveSearch.findIndexOf(text, pattern);

            if(i >= 0){
                // found the index of substring, at 'i'
            }
        }
    }

    @Benchmark
    public void JavaAlgorithmBenchmark() {
        final String myLongString = new String(this.longString);
        char[] subString;

        while ((subString = this.iterator.next()) != null){
            final String mySubString = new String(subString);
            final int i = myLongString.indexOf(mySubString);

            if(i >= 0){
                // found the index of substring, at 'i'
            }
        }
    }

    @Benchmark
    public void JavaWithThreadsAlgorithmBenchmark() {
        final String myLongString = new String(this.longString);
        char[] subString;

        while ((subString = this.iterator.next()) != null){
            final String mySubString = new String(subString);

            pool.execute(()->{
                final int i = myLongString.indexOf(mySubString);

                if(i >= 0){
                    // found the index of substring, at 'i'
                }
            });
        }

        // Wait for tasks to finish
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@Benchmark
    public void RabinKarpAlgorithmBenchmark() {
        final String text = new String(this.longString);
        char[] subString;

        while ((subString = iterator.next()) != null){
            final String pattern = new String(subString);
            final int i = Rabin_Karp.findIndexOf(text, pattern);

            if(i >= 0){
                // found the index of substring, at 'i'
            }
        }
    }

    @Benchmark
    public void RabinKarpWithThreadsAlgorithmBenchmark() {
        final String text = new String(this.longString);
        char[] subString;

        while ((subString = iterator.next()) != null){
            final String pattern = new String(subString);

            pool.execute(()->{
                final int i = Rabin_Karp.findIndexOf(text, pattern);

                if(i >= 0){
                    // found the index of substring, at 'i'
                }
            });
        }

        // Wait for tasks to finish
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
    @Benchmark
    public void RegexAlgorithmBenchmark() {
        final String myLongString = new String(this.longString);
        char[] subString;

        while ((subString = this.iterator.next()) != null){
            final String mySubString = new String(subString);
            final Pattern pattern = Pattern.compile(mySubString);
            final Matcher matcher = pattern.matcher(myLongString);

            if(matcher.find()){
                final int i = matcher.start();
                // found the index of substring, at 'i'
            }
        }
    }

    @Benchmark
    public void RegexWithThreadsAlgorithmBenchmark() {
        final String myLongString = new String(this.longString);
        char[] subString;

        while ((subString = this.iterator.next()) != null){
            final String mySubString = new String(subString);

            pool.execute(()->{
                final Pattern pattern = Pattern.compile(mySubString);
                final Matcher matcher = pattern.matcher(myLongString);

                if(matcher.find()){
                    final int i = matcher.start();
                    // found the index of substring, at 'i'
                }
            });
        }

        // Wait for tasks to finish
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@Benchmark
    public void RegexFindAllAtOnceAlgorithmBenchmark() {
        final String myLongString = new String(this.longString);
        StringBuilder patternBuilder = new StringBuilder();
        boolean first = true;
        char[] subString;

        // Build the pattern for all the sub-Strings
        while ((subString = this.iterator.next()) != null) {
            if (first)
                first = false;
            else
                patternBuilder.append('|');

            patternBuilder.append(subString);
        }

        final Pattern pattern = Pattern.compile(patternBuilder.toString());
        final Matcher matcher = pattern.matcher(myLongString);

        // find all the sub-Strings at once
        while(matcher.find())
        {
            final int i = matcher.start();
            // found the index of substring, at 'i'
        }
    }

    //@Benchmark
    public void RegexFindPartsAtOnceWithThreadsAlgorithmBenchmark() {
        final String myLongString = new String(this.longString);
        boolean doneFlag = false;

        while(!doneFlag){
            StringBuilder patternBuilder = new StringBuilder();
            boolean first = true;
            char[] subString;
            int numberOfSubStrings = 0;

            // Build the pattern for the sub-Strings
            while (numberOfSubStrings < 100 && !doneFlag) {
                subString = this.iterator.next();
                if(subString == null){
                    doneFlag = true;
                }else{
                    if (first)
                        first = false;
                    else
                        patternBuilder.append('|');

                    patternBuilder.append(subString);
                    numberOfSubStrings++;
                }
            }

            pool.execute(()->{
                final Pattern pattern = Pattern.compile(patternBuilder.toString());
                final Matcher matcher = pattern.matcher(myLongString);

                // find all the sub-Strings at once
                while(matcher.find())
                {
                    final int i = matcher.start();
                    // found the index of substring, at 'i'
                }
            });
        }

        // Wait for tasks to finish
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
*/
    //@Benchmark
    public void KMPAlgorithm() {
        final String text = new String(this.longString);
        char[] subString;

        while ((subString = iterator.next()) != null){
            final String pattern = new String(subString);
            final int i = KMPStringMatching.KMPSearch(pattern, text);

            if(i >= 0){
                // found the index of substring, at 'i'
            }
        }
    }

    @Benchmark
    public void KMPAlgorithmWithThreads() {
        final String text = new String(this.longString);
        char[] subString;

            while ((subString = this.iterator.next()) != null){
                final String pattern = new String(subString);
                //Threads Splitting.
                pool.execute(()->{
                    final int i = KMPStringMatching.KMPSearch(pattern, text);

                    if(i >= 0){
                        // found the index of substring, at 'i'
                    }
                });
            }

            // Wait for tasks to finish, if not finished within 20 minutes we terminate the operation.
            pool.shutdown();
            try {
                pool.awaitTermination(20, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    @Benchmark
    public void ImprovedMemoryKMPAlgorithmWithThreads() {
        final String text = new String(this.longString);
        char[] subString;

        while ((subString = this.iterator.next()) != null){
            final String pattern = new String(subString);
            //Threads Splitting.
            pool.execute(()->{
                final int i = KMPStringMatching.KMPSearchImprovedMemory(pattern, text);

                if(i >= 0){
                    // found the index of substring, at 'i'
                }
            });
        }

        // Wait for tasks to finish, if not finished within 20 minutes we terminate the operation.
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Benchmark
    public void ImprovedRunTimeKMPAlgorithmWithThreads() {
        final String text = new String(this.longString);
        char[] subString;;

        Stack<String> arrStr = new Stack<>();

        while ((subString = this.iterator.next()) != null) {
            arrStr.push((subString.toString()));
        }

        for (int r = 0; r < arrStr.size() ; r++) {
            //Threads Splitting.
            pool.execute(()->{
                final int i = KMPStringMatching.KMPSearchImprovedRunTime(arrStr.pop().toString(), text);
            });
        }

        // Wait for tasks to finish, if not finished within 20 minutes we terminate the operation.
        pool.shutdown();
        try {
            pool.awaitTermination(20, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
