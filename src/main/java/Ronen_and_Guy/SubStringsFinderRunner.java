package Ronen_and_Guy;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class SubStringsFinderRunner {

    // Ronen Rossin 206756280
    // Guy Ohayon 301851713
    public static void main(String... args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                //.addProfiler(HotspotMemoryProfiler.class)
                //.addProfiler(StackProfiler.class)
                //.addProfiler(GCProfiler.class)
                .addProfiler(UsedMemoryProfiler.class)
                .timeout(TimeValue.minutes(20))
                .jvmArgs("-Xms2g", "-Xmx2g", "-Xmn800m", "-server")
                .include(SubStringsFinderBenchmark.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }

}
