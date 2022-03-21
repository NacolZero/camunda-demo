package org.nacol.camundarestclient.process.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.camunda.community.rest.client.dto.HistoricTaskInstanceDto;

@Data
@Accessors(chain = true)
public class CmdHisTaskDto extends HistoricTaskInstanceDto {

    private String processDefinitionName;

    private Integer version;

    public static CmdHisTaskDto converDto(HistoricTaskInstanceDto taskDto){
        String taskJsonStr = JSONObject.toJSONString(taskDto);
        return JSONObject.parseObject(taskJsonStr, CmdHisTaskDto.class);
    }

}
