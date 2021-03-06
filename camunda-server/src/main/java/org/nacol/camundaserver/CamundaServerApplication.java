package org.nacol.camundaserver;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@SpringBootApplication
public class CamundaServerApplication {

    private static final String WORKER_ID = "externalWorkerId";

    public static void main(String[] args) {
        SpringApplication.run(CamundaServerApplication.class, args);
        cunsumer();
    }

    public static void cunsumer() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(10, WORKER_ID)
                .topic("topic-nacol", 60L * 1000L)
                .execute();

        for (LockedExternalTask task : tasks) {
            try {
                String topic = task.getTopicName();
                log.info("--------------task msg : {}", task.getId());

                // work on task for that topic
                boolean success = true;
                // if the work is successful, mark the task as completed
                if(success) {
                    Map<String, Object> variables = new HashMap<>();
                    externalTaskService.complete(task.getId(), WORKER_ID, variables);
                }
                else {
                    // if the work was not successful, mark it as failed
                    externalTaskService.handleFailure(
                            task.getId(),
                            "externalWorkerId",
                            "Address could not be validated: Address database not reachable",
                            1, 10L * 60L * 1000L);
                }
            }
            catch(Exception e) {
                //... handle exception
                e.printStackTrace();
            }
        }
    }
}
