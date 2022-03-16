package org.nacol.camundaclient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2022/3/16
 * @Description Camunda 外部客户端配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "camunda")
public class CamundaExternalClientConfig {

    private String engineUrl;

}
