package com.example;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class Benchmarker {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmarker.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Random random;
        private ThreadLocalRandom threadLocalRandom;
        private SecureRandom secureRandom;
        private SplittableRandom splittableRandom;

        @Setup
        public void setup() throws NoSuchAlgorithmException {
            this.random = new Random();
            this.threadLocalRandom = ThreadLocalRandom.current();
            this.secureRandom = SecureRandom.getInstance("NativePRNGNonBlocking");
            this.splittableRandom = new SplittableRandom();
        }
    }

    @Benchmark
    public static void random(BenchmarkState state) {
        state.random.nextInt();
    }

    @Benchmark
    public static void threadLocalRandom(BenchmarkState state) {
        state.threadLocalRandom.nextInt();
    }

    @Benchmark
    public static void secureRandom(BenchmarkState state) {
        state.secureRandom.nextInt();
    }

    @Benchmark
    public static void splittableRandom(BenchmarkState state) {
        state.splittableRandom.nextInt();
    }
}
