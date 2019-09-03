package Ronen_and_Guy;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class SubStringsFinderRunner {

    public static void main(String... args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                //.addProfiler(HotspotMemoryProfiler.class)
                //.addProfiler(StackProfiler.class)
                //.addProfiler(GCProfiler.class)
                .addProfiler(MaxMemoryProfiler.class)
                .timeout(TimeValue.minutes(20))
                .jvmArgs("-Xms1g", "-Xmx8g", "-Xmn800m", "-server")
                .include(SubStringsFinderBenchmark.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }

}
