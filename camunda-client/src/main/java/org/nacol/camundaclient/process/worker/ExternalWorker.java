package org.nacol.camundaclient.process.worker;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @Author Nacol
 * @Date 2022/3/16
 * @Description 外部
 */
@Log4j2
@Component
public class ExternalWorker {

    @Autowired
    ExternalTaskClient externalTaskClient;

    @PostConstruct
    public void consumer() {
        log.info("ExternalWorker start consumer");
        externalTaskClient.subscribe("topic-nacol")
                .lockDuration(30000)
                .handler((externalTask, externalTaskService) -> {
                    // Get a process variable
//                    String item = (String) externalTask.getVariable("item");
//                    Long amount = (Long) externalTask.getVariable("amount");

                    // business logic




                    // Complete the task
                    log.info("---------------complete external task : {}", externalTask.getId());
                    externalTaskService.complete(externalTask);
                }).open();
    }

}
