package org.nacol.camundarestclient.process.restclient.process;

import com.alibaba.fastjson.JSONObject;
import commons.Page;
import commons.Result;
import commons.utils.VariableValueUtis;
import org.camunda.community.rest.client.api.*;
import org.camunda.community.rest.client.dto.*;
import org.camunda.community.rest.client.invoker.ApiClient;
import org.camunda.community.rest.client.invoker.ApiException;
import org.camunda.community.rest.client.springboot.CamundaHistoryApi;
import org.nacol.camundarestclient.process.dto.CmdHisTaskDto;
import org.nacol.camundarestclient.process.service.ProcessDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/process")
public class ProcessAPI {


    @Autowired
    private ProcessDefinitionApi processDefinitionApi;

    @Autowired
    TaskApi taskApi;

    @Autowired
    HistoricTaskInstanceApi historicTaskInstanceApi;

    @Autowired
    VariableInstanceApi variableInstanceApi;

    @PostMapping("/startProcess")
    public Result startProcess(@RequestBody JSONObject param) throws ApiException {
        String processKey = param.getString("processKey");
        Map<String, VariableValueDto> variables = VariableValueUtis.jsonToVars(param.getJSONObject("variables"));

        // start process instance
        ProcessInstanceWithVariablesDto processInstance = processDefinitionApi.startProcessInstanceByKey(
                processKey
                , new StartProcessInstanceDto().variables(variables)
        );

        return Result.buildResultSuccess("流程启动成功", processInstance.getId());
    }

    @PostMapping("/pageHisTask")
    public Result pageRtTask(@RequestBody Page<CmdHisTaskDto, CmdHisTaskDto> page) throws ApiException, ExecutionException, InterruptedException {
        CmdHisTaskDto filter = page.filter();

        CountResultDto countResultDto = historicTaskInstanceApi.getHistoricTaskInstancesCount(
                null, //String taskId
                null, //String taskParentTaskId
                null, //String processInstanceId
                null, //String processInstanceBusinessKey
                null, //String processInstanceBusinessKeyIn
                null, //String processInstanceBusinessKeyLike
                null, //String executionId
                null, //String processDefinitionId
                filter.getProcessDefinitionKey(), //String processDefinitionKey
                filter.getProcessDefinitionName(), //String processDefinitionName
                null, //String caseInstanceId
                null, //String caseExecutionId
                null, //String caseDefinitionId
                null, //String caseDefinitionKey
                null, //String caseDefinitionName
                null, //String activityInstanceIdIn
                null, //String taskName
                filter.getName(), //String taskNameLike
                null, //String taskDescription
                null, //String taskDescriptionLike
                null, //String taskDefinitionKey
                null, //String taskDefinitionKeyIn
                null, //String taskDeleteReason
                null, //String taskDeleteReasonLike
                null, //String taskAssignee
                filter.getAssignee(), //String taskAssigneeLike
                null, //String taskOwner
                null, //String taskOwnerLike
                null, //Integer taskPriority
                null, //Boolean assigned
                null, //Boolean unassigned
                null, //Boolean finished
                null, //Boolean unfinished
                null, //Boolean processFinished
                null, //Boolean processUnfinished
                null, //Date taskDueDate
                null, //Date taskDueDateBefore
                null, //Date taskDueDateAfter
                null, //Boolean withoutTaskDueDate
                null, //Date taskFollowUpDate
                null, //Date taskFollowUpDateBefore
                null, //Date taskFollowUpDateAfter
                null, //Date startedBefore
                null, //Date startedAfter
                null, //Date finishedBefore
                null, //Date finishedAfter
                null, //String tenantIdIn
                null, //Boolean withoutTenantId
                null, //String taskVariables
                null, //String processVariables
                null, //Boolean variableNamesIgnoreCase
                null, //Boolean variableValuesIgnoreCase
                null, //String taskInvolvedUser
                null, //String taskInvolvedGroup
                null, //String taskHadCandidateUser
                null, //String taskHadCandidateGroup
                null, //Boolean withCandidateGroups
                null//Boolean withoutCandidateGroups
        );

        List<HistoricTaskInstanceDto> hisTasks = historicTaskInstanceApi.getHistoricTaskInstances(
                null, //String taskId
                null, //String taskParentTaskId
                null, //String processInstanceId
                null, //String processInstanceBusinessKey
                null, //String processInstanceBusinessKeyIn
                null, //String processInstanceBusinessKeyLike
                null, //String executionId
                null, //String processDefinitionId
                filter.getProcessDefinitionKey(), //String processDefinitionKey
                filter.getProcessDefinitionName(), //String processDefinitionName
                null, //String caseInstanceId
                null, //String caseExecutionId
                null, //String caseDefinitionId
                null, //String caseDefinitionKey
                null, //String caseDefinitionName
                null, //String activityInstanceIdIn
                null, //String taskName
                filter.getName(), //String taskNameLike
                null, //String taskDescription
                null, //String taskDescriptionLike
                null, //String taskDefinitionKey
                null, //String taskDefinitionKeyIn
                null, //String taskDeleteReason
                null, //String taskDeleteReasonLike
                null, //String taskAssignee
                filter.getAssignee(), //String taskAssigneeLike
                null, //String taskOwner
                null, //String taskOwnerLike
                null, //Integer taskPriority
                null, //Boolean assigned
                null, //Boolean unassigned
                null, //Boolean finished
                null, //Boolean unfinished
                null, //Boolean processFinished
                null, //Boolean processUnfinished
                null, //Date taskDueDate
                null, //Date taskDueDateBefore
                null, //Date taskDueDateAfter
                null, //Boolean withoutTaskDueDate
                null, //Date taskFollowUpDate
                null, //Date taskFollowUpDateBefore
                null, //Date taskFollowUpDateAfter
                null, //Date startedBefore
                null, //Date startedAfter
                null, //Date finishedBefore
                null, //Date finishedAfter
                null, //String tenantIdIn
                null, //Boolean withoutTenantId
                null, //String taskVariables
                null, //String processVariables
                null, //Boolean variableNamesIgnoreCase
                null, //Boolean variableValuesIgnoreCase
                null, //String taskInvolvedUser
                null, //String taskInvolvedGroup
                null, //String taskHadCandidateUser
                null, //String taskHadCandidateGroup
                null, //Boolean withCandidateGroups
                null, //Boolean withoutCandidateGroups
                null, //String sortBy
                null, //String sortOrder
                page.getPageNumber(), //Integer firstResult
                page.getPageSize() //Intege maxResults
        );
        List<CmdHisTaskDto> list = hisTasks.stream().map(CmdHisTaskDto::converDto).collect(Collectors.toList());
        //STEP 封装流程定义相关数据
        for (CmdHisTaskDto o : list) {
            ProcessDefinitionDto processDefinition = processDefinitionApi.getProcessDefinition(o.getProcessDefinitionId());
            o.setProcessDefinitionKey(processDefinition.getKey());
            o.setProcessDefinitionName(processDefinition.getName())
                    .setVersion(processDefinition.getVersion());
        }

        page.setCount(countResultDto.getCount());
        page.setList(list);
        return Result.buildResultSuccess(page);
    }

    @PostMapping("/complete")
    public Result complete(@RequestBody JSONObject params) throws ApiException {
        String taskId = params.getString("taskId");
        Map<String, VariableValueDto> variables = VariableValueUtis.jsonToVars(params.getJSONObject("variables"));

        // start process instance
        Map<String, VariableValueDto> complete = taskApi.complete(
                taskId,
                new CompleteTaskDto().variables(variables));
        return Result.buildResultSuccess("任务执行成功");
    }

    @PostMapping("/receiveTask")
    public Result receiveTask (@RequestBody TaskDto taskDto) throws ApiException {
        UserIdDto userIdDto = new UserIdDto();
        userIdDto.setUserId(taskDto.getAssignee());
        taskApi.setAssignee(taskDto.getId(), userIdDto);
        return Result.buildResultSuccess("任务执行成功");
    }

    @GetMapping("/getTaskById/{taskId}")
    public Result getTaskById(@PathVariable String taskId) throws ApiException {
        TaskDto task = taskApi.getTask(taskId);
        List<VariableInstanceDto> vars = variableInstanceApi.getVariableInstances(
                null,//String variableName
                null,//String variableNameLike
                null,//String processInstanceIdIn
                null,//String executionIdIn
                null,//String caseInstanceIdIn
                null,//String caseExecutionIdIn
                taskId,//String taskIdIn
                null,//String batchIdIn
                null,//String activityInstanceIdIn
                null,//String tenantIdIn
                null,//String variableValues
                null,//Boolean variableNamesIgnoreCase
                null,//Boolean variableValuesIgnoreCase
                null,//String variableScopeIdIn
                null,//String sortBy
                null,//String sortOrder
                null,//Integer firstResult
                null,//Integer maxResults
                null//Boolean deserializeValues
        );

        JSONObject taskInfo = new JSONObject();
        taskInfo.put("task", task);
        taskInfo.put("variableList", vars);
        return Result.buildResultSuccess(taskInfo);
    }


}
