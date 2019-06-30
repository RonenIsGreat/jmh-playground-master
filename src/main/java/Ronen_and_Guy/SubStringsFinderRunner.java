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
                .measurementIterations(15)
                .warmupIterations(10)
                .forks(3)
                .jvmArgs("-Xms1g", "-Xmx1g", "-Xmn800m", "-server")
                .include(SubStringsFinderBenchmark.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }

}
