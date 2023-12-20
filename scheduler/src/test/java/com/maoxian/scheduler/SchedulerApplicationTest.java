package com.maoxian.scheduler;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.StatsCmd;
import com.github.dockerjava.api.model.*;
import com.maoxian.scheduler.config.SchedulerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class SchedulerApplicationTest {
    @Autowired
    private SchedulerConfig schedulerConfig;

    @Autowired
    private DockerClient dockerClient;

    @Test
    public void testDockerConnection(){
        System.out.println("=============");
        List<Container> containers = dockerClient.listContainersCmd().exec();
        Container container = containers.get(0);
        System.out.println(container);
        StatsCmd statsCmd = dockerClient.statsCmd(container.getId());
        statsCmd.exec(new ResultCallback<Statistics>() {
            @Override
            public void onStart(Closeable closeable) {
                System.out.println("start");
            }

            @Override
            public void onNext(Statistics object) {
                CpuStatsConfig cpuStats = object.getCpuStats();
                Long totalUsage = Objects.requireNonNull(cpuStats.getCpuUsage()).getTotalUsage();
                Long systemCpuUsage = cpuStats.getSystemCpuUsage();
                System.out.println("cpu:"+totalUsage*1.0/systemCpuUsage*100);
                MemoryStatsConfig memoryStats = object.getMemoryStats();
                Long memoryStatsUsage = memoryStats.getUsage();
                Long limit = memoryStats.getLimit();
                System.out.println("mem:"+memoryStatsUsage*1.0/limit*100);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }

            @Override
            public void close() throws IOException {
                System.out.println("close");
            }
        });
        while (true){
            int a=1;
        }
    }
}
