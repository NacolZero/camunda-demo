package org.nacol.camundarestclient.process.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.camunda.community.rest.client.dto.ProcessInstanceDto;
import org.camunda.community.rest.client.dto.TaskDto;

@Data
@Accessors(chain = true)
public class CmdPrsInsDto extends ProcessInstanceDto {

    private String processDefinitionKey;

    private String processDefinitionName;

    private Integer version;

    public static CmdPrsInsDto converDto(ProcessInstanceDto processInstanceDto){
        String taskJsonStr = JSONObject.toJSONString(processInstanceDto);
        return JSONObject.parseObject(taskJsonStr, CmdPrsInsDto.class);
    }
}
