package org.nacol.camundarestclient.process.restclient.manage;

import commons.Page;
import commons.Result;
import lombok.extern.log4j.Log4j2;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.api.ProcessInstanceApi;
import org.camunda.community.rest.client.api.TaskApi;
import org.camunda.community.rest.client.dto.CountResultDto;
import org.camunda.community.rest.client.dto.ProcessDefinitionDto;
import org.camunda.community.rest.client.dto.ProcessInstanceDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.nacol.camundarestclient.process.dto.CmdPrsInsDto;
import org.nacol.camundarestclient.process.service.ProcessDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CrossOrigin
@Log4j2
@RestController
@RequestMapping("/manage/process-instance")
public class ManageProcessInstanceApi {


    @Autowired
    ProcessInstanceApi processInstanceApi;

    @Autowired
    ProcessDefinitionApi processDefinitionApi;

    @PostMapping("/page")
    public Result page(@RequestBody  Page<CmdPrsInsDto, CmdPrsInsDto> page) throws ApiException, ExecutionException, InterruptedException {
        CmdPrsInsDto filter = page.filter();

        CountResultDto countResultDto = processInstanceApi.getProcessInstancesCount(
                filter.getId(),//String processInstanceIds
                null,//String businessKey
                filter.getBusinessKey(),//String businessKeyLike
                null,//String caseInstanceId
                null,//String processDefinitionId
                filter.getProcessDefinitionKey(),//String processDefinitionKey
                null,//String processDefinitionKeyIn
                null,//String processDefinitionKeyNotIn
                null,//String deploymentId
                null,//String superProcessInstance
                null,//String subProcessInstance
                null,//String superCaseInstance
                null,//String subCaseInstance
                null,//Boolean active
                null,//Boolean suspended
                null,//Boolean withIncident
                null,//String incidentId
                null,//String incidentType
                null,//String incidentMessage
                null,//String incidentMessageLike
                null,//String tenantIdIn
                null,//Boolean withoutTenantId
                null,//Boolean processDefinitionWithoutTenantId
                null,//String activityIdIn
                null,//Boolean rootProcessInstances
                null,//Boolean leafProcessInstances
                null,//String variables
                null,//Boolean variableNamesIgnoreCase
                null//Boolean variableValuesIgnoreCase
        );

        List<ProcessInstanceDto> processInstanceDtoList =  processInstanceApi.getProcessInstances(
                null,//String sortBy
                null,//String sortOrder
                page.getPageNumber(),//Integer firstResult
                page.getPageSize(),//Integer maxResults
                filter.getId(),//String processInstanceIds
                null,//String businessKey
                filter.getBusinessKey(),//String businessKeyLike
                null,//String caseInstanceId
                null,//String processDefinitionId
                filter.getProcessDefinitionKey(),//String processDefinitionKey
                null,//String processDefinitionKeyIn
                null,//String processDefinitionKeyNotIn
                null,//String deploymentId
                null,//String superProcessInstance
                null,//String subProcessInstance
                null,//String superCaseInstance
                null,//String subCaseInstance
                null,//Boolean active
                null,//Boolean suspended
                null,//Boolean withIncident
                null,//String incidentId
                null,//String incidentType
                null,//String incidentMessage
                null,//String incidentMessageLike
                null,//String tenantIdIn
                null,//Boolean withoutTenantId
                null,//Boolean processDefinitionWithoutTenantId
                null,//String activityIdIn
                null,//Boolean rootProcessInstances
                null,//Boolean leafProcessInstances
                null,//String variables
                null,//Boolean variableNamesIgnoreCase
                null//Boolean variableValuesIgnoreCase
        );

        List<CmdPrsInsDto> list = processInstanceDtoList.stream().map(CmdPrsInsDto::converDto).collect(Collectors.toList());

        //STEP 封装流程定义相关属性
        for (CmdPrsInsDto cmdPrsInsDto : list) {
            ProcessDefinitionDto processDefinition = processDefinitionApi.getProcessDefinition(cmdPrsInsDto.getDefinitionId());
            cmdPrsInsDto.setProcessDefinitionKey(processDefinition.getKey())
                    .setProcessDefinitionName(processDefinition.getName())
                    .setVersion(processDefinition.getVersion());
        }

        page.setList(list);
        page.setCount(countResultDto.getCount());
        return Result.buildResultSuccess(page);
    }

}
