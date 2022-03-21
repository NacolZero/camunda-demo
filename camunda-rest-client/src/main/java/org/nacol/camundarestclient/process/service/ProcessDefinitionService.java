package org.nacol.camundarestclient.process.service;

import com.sun.xml.internal.ws.util.CompletedFuture;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.dto.ProcessDefinitionDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.nacol.camundarestclient.process.dto.CmdHisTaskDto;
import org.nacol.camundarestclient.process.dto.CmdPrsInsDto;
import org.nacol.camundarestclient.process.dto.CmdTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.nacol.camundarestclient.config.thread.ProcessExecutorConfig.TASK_EXECUTOR;

@Service
public class ProcessDefinitionService {

    @Resource(name = TASK_EXECUTOR)
    Executor executor;

    @Autowired
    ProcessDefinitionApi processDefinitionApi;

    public void initDefinitionDataForPrs(List<CmdPrsInsDto> list) throws InterruptedException, ExecutionException {
        List<CompletableFuture<ProcessDefinitionDto>> futures = list.stream().map(o -> {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    ProcessDefinitionDto processDefinition = processDefinitionApi.getProcessDefinition(o.getDefinitionId());
                    o.setProcessDefinitionKey(processDefinition.getKey())
                            .setProcessDefinitionName(processDefinition.getName())
                            .setVersion(processDefinition.getVersion());
                    return processDefinition;
                } catch (ApiException e) {
                    e.printStackTrace();
                    return null;
                }
            }, executor);
        }).collect(Collectors.toList());
        for (CompletableFuture<ProcessDefinitionDto> future : futures) {
            future.get();
        }
    }

    public void initDefinitionDataForTask(List<CmdTaskDto> list) throws InterruptedException, ExecutionException {
        List<CompletableFuture<ProcessDefinitionDto>> futures = list.stream().map(o -> {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    ProcessDefinitionDto processDefinition = processDefinitionApi.getProcessDefinition(o.getProcessDefinitionId());
                    o.setProcessDefinitionKey(processDefinition.getKey())
                            .setProcessDefinitionName(processDefinition.getName())
                            .setVersion(processDefinition.getVersion());
                    return processDefinition;
                } catch (ApiException e) {
                    e.printStackTrace();
                    return null;
                }
            }, executor);
        }).collect(Collectors.toList());
        for (CompletableFuture<ProcessDefinitionDto> future : futures) {
            future.get();
        }
    }

    public void initDefinitionDataForHisTask(List<CmdHisTaskDto> list) throws InterruptedException, ExecutionException {
        List<CompletableFuture<ProcessDefinitionDto>> futures = list.stream().map(o -> {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    ProcessDefinitionDto processDefinition = processDefinitionApi.getProcessDefinition(o.getProcessDefinitionId());
                        o.setProcessDefinitionKey(processDefinition.getKey());
                        o.setProcessDefinitionName(processDefinition.getName())
                        .setVersion(processDefinition.getVersion());
                    return processDefinition;
                } catch (ApiException e) {
                    e.printStackTrace();
                    return null;
                }
            }, executor);
        }).collect(Collectors.toList());
        for (CompletableFuture<ProcessDefinitionDto> future : futures) {
            future.get();
        }
    }
}
