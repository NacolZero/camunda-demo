package org.nacol.camundaclient.config;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Nacol
 * @Date 2022/3/16
 * @Description 外部任务客户端
 */
@Log4j2
@Configuration
public class ExternalTaskClientConfig {

    @Autowired
    CamundaExternalClientConfig camundaExternalClientConfig;

    @Bean
    public ExternalTaskClient externalTaskClient() {
        log.info("engineUrl : {}", camundaExternalClientConfig.getEngineUrl());
        ExternalTaskClient client = ExternalTaskClient.create().baseUrl(camundaExternalClientConfig.getEngineUrl())
                .asyncResponseTimeout(10000)
                .build();
        return client;
    }

}
