package com.maoxian.scheduler;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.StatsCmd;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.InvocationBuilder;
import com.maoxian.scheduler.pojo.Scheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Objects;

@SpringBootTest
public class SchedulerApplicationTest {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private DockerClient dockerClient;

    @Test
    @Scheduled
    public void testDockerConnection(){
        System.out.println("=============");
        List<Container> containers = dockerClient.listContainersCmd().exec();
        Container container = containers.get(0);
        System.out.println(container);
        StatsCmd statsCmd = dockerClient.statsCmd("5a71092de652dd861");
        statsCmd.withNoStream(true);
        statsCmd.exec(new InvocationBuilder.AsyncResultCallback<Statistics>(){

            @Override
            public void onNext(Statistics object) {
                super.onNext(object);
                CpuStatsConfig cpuStats = object.getCpuStats();
                Long totalUsage = Objects.requireNonNull(cpuStats.getCpuUsage()).getTotalUsage();
                Long systemCpuUsage = cpuStats.getSystemCpuUsage();
                System.out.println("cpu:"+totalUsage*1.0/systemCpuUsage*100);
                MemoryStatsConfig memoryStats = object.getMemoryStats();
                Long memoryStatsUsage = memoryStats.getUsage();
                Long limit = memoryStats.getLimit();
                System.out.println("mem:"+memoryStatsUsage*1.0/limit*100);
            }
        }).awaitResult();
//        statsCmd.exec(new ResultCallback<Statistics>() {
//            @Override
//            public void onStart(Closeable closeable) {
//                System.out.println("start");
//            }
//
//            @Override
//            public void onNext(Statistics object) {
//                CpuStatsConfig cpuStats = object.getCpuStats();
//                Long totalUsage = Objects.requireNonNull(cpuStats.getCpuUsage()).getTotalUsage();
//                Long systemCpuUsage = cpuStats.getSystemCpuUsage();
//                System.out.println("cpu:"+totalUsage*1.0/systemCpuUsage*100);
//                MemoryStatsConfig memoryStats = object.getMemoryStats();
//                Long memoryStatsUsage = memoryStats.getUsage();
//                Long limit = memoryStats.getLimit();
//                System.out.println("mem:"+memoryStatsUsage*1.0/limit*100);
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                System.out.println("error");
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("complete");
//            }
//
//            @Override
//            public void close() throws IOException {
//                System.out.println("close");
//            }
//        });
        while (true){
            int a=1;
        }
    }
}
