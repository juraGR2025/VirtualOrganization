package ru.virtual.experiment.virtualorganization.ComputingServices;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class poolComputingThreads {

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    for( int i = 0; i < 5; i++){
        executorService.submit(new Work(i));
    }

}
