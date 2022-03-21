package org.nacol.camundarestclient.process.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.camunda.community.rest.client.dto.TaskDto;

@Data
@Accessors(chain = true)
public class CmdTaskDto extends TaskDto {

    private String processDefinitionKey;

    private String processDefinitionName;

    private Integer version;

    public static CmdTaskDto converDto(TaskDto taskDto){
        String taskJsonStr = JSONObject.toJSONString(taskDto);
        return JSONObject.parseObject(taskJsonStr, CmdTaskDto.class);
    }
}
