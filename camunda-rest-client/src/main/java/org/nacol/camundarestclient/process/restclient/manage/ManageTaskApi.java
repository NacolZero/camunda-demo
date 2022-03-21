package org.nacol.camundarestclient.process.restclient.manage;

import commons.Page;
import commons.Result;
import lombok.extern.log4j.Log4j2;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.api.TaskApi;
import org.camunda.community.rest.client.dto.CountResultDto;
import org.camunda.community.rest.client.dto.ProcessDefinitionDto;
import org.camunda.community.rest.client.dto.TaskDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.nacol.camundarestclient.process.dto.CmdPrsInsDto;
import org.nacol.camundarestclient.process.dto.CmdTaskDto;
import org.nacol.camundarestclient.process.service.ProcessDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CrossOrigin
@Log4j2
@RestController
@RequestMapping("/manage/task")
public class ManageTaskApi {

    @Autowired
    TaskApi taskApi;

    @Autowired
    ProcessDefinitionApi processDefinitionApi;

    @PostMapping("/page")
    public Result page(@RequestBody  Page<CmdTaskDto, CmdTaskDto> page) throws ApiException, ExecutionException, InterruptedException {
        CmdTaskDto filter = page.filter();

        CountResultDto countResultDto = taskApi.getTasksCount(
                null,//String taskId
                null,//String taskIdIn
                filter.getProcessInstanceId(),//String processInstanceId
                null,//String processInstanceIdIn
                null,//String processInstanceBusinessKey
                null,//String processInstanceBusinessKeyExpression
                null,//String processInstanceBusinessKeyIn
                null,//String processInstanceBusinessKeyLike
                null,//String processInstanceBusinessKeyLikeExpression
                filter.getProcessDefinitionId(),//String processDefinitionId
                null,//String processDefinitionKey
                null,//String processDefinitionKeyIn
                null,//String processDefinitionName
                null,//String processDefinitionNameLike
                null,//String executionId
                null,//String caseInstanceId
                null,//String caseInstanceBusinessKey
                null,//String caseInstanceBusinessKeyLike
                null,//String caseDefinitionId
                null,//String caseDefinitionKey
                null,//String caseDefinitionName
                null,//String caseDefinitionNameLike
                null,//String caseExecutionId
                null,//String activityInstanceIdIn
                null,//String tenantIdIn
                null,//Boolean withoutTenantId
                null,//String assignee
                null,//String assigneeExpression
                filter.getAssignee(),//String assigneeLike
                null,//String assigneeLikeExpression
                null,//String assigneeIn
                null,//String assigneeNotIn
                null,//String owner
                null,//String ownerExpression
                null,//String candidateGroup
                null,//String candidateGroupExpression
                null,//String candidateUser
                null,//String candidateUserExpression
                null,//Boolean includeAssignedTasks
                null,//String involvedUser
                null,//String involvedUserExpression
                null,//Boolean assigned
                null,//Boolean unassigned
                null,//String taskDefinitionKey
                null,//String taskDefinitionKeyIn
                null,//String taskDefinitionKeyLike
                null,//String name
                null,//String nameNotEqual
                filter.getName(),//String nameLike
                null,//String nameNotLike
                null,//String description
                null,//String descriptionLike
                null,//Integer priority
                null,//Integer maxPriority
                null,//Integer minPriority
                null,//String dueDate
                null,//String dueDateExpression
                null,//String dueAfter
                null,//String dueAfterExpression
                null,//String dueBefore
                null,//String dueBeforeExpression
                null,//Boolean withoutDueDate
                null,//String followUpDate
                null,//String followUpDateExpression
                null,//String followUpAfter
                null,//String followUpAfterExpression
                null,//String followUpBefore
                null,//String followUpBeforeExpression
                null,//String followUpBeforeOrNotExistent
                null,//String followUpBeforeOrNotExistentExpression
                null,//String createdOn
                null,//String createdOnExpression
                null,//String createdAfter
                null,//String createdAfterExpression
                null,//String createdBefore
                null,//String createdBeforeExpression
                null,//String delegationState
                null,//String candidateGroups
                null,//String candidateGroupsExpression
                null,//Boolean withCandidateGroups
                null,//Boolean withoutCandidateGroups
                null,//Boolean withCandidateUsers
                null,//Boolean withoutCandidateUsers
                false,//Boolean active
                null,//Boolean suspended
                null,//String taskVariables
                null,//String processVariables
                null,//String caseInstanceVariables
                null,//Boolean variableNamesIgnoreCase
                null,//Boolean variableValuesIgnoreCase
                null//String parentTaskId
        );

        List<TaskDto> taskDtoList = taskApi.getTasks(
                null,//String taskId
                null,//String taskIdIn
                filter.getProcessInstanceId(),//String processInstanceId
                null,//String processInstanceIdIn
                null,//String processInstanceBusinessKey
                null,//String processInstanceBusinessKeyExpression
                null,//String processInstanceBusinessKeyIn
                null,//String processInstanceBusinessKeyLike
                null,//String processInstanceBusinessKeyLikeExpression
                filter.getProcessDefinitionId(),//String processDefinitionId
                filter.getProcessDefinitionKey(),//String processDefinitionKey
                null,//String processDefinitionKeyIn
                null,//String processDefinitionName
                filter.getProcessDefinitionName(),//String processDefinitionNameLike
                null,//String executionId
                null,//String caseInstanceId
                null,//String caseInstanceBusinessKey
                null,//String caseInstanceBusinessKeyLike
                null,//String caseDefinitionId
                null,//String caseDefinitionKey
                null,//String caseDefinitionName
                null,//String caseDefinitionNameLike
                null,//String caseExecutionId
                null,//String activityInstanceIdIn
                null,//String tenantIdIn
                null,//Boolean withoutTenantId
                null,//String assignee
                null,//String assigneeExpression
                filter.getAssignee(),//String assigneeLike
                null,//String assigneeLikeExpression
                null,//String assigneeIn
                null,//String assigneeNotIn
                null,//String owner
                null,//String ownerExpression
                null,//String candidateGroup
                null,//String candidateGroupExpression
                null,//String candidateUser
                null,//String candidateUserExpression
                null,//Boolean includeAssignedTasks
                null,//String involvedUser
                null,//String involvedUserExpression
                null,//Boolean assigned
                null,//Boolean unassigned
                null,//String taskDefinitionKey
                null,//String taskDefinitionKeyIn
                null,//String taskDefinitionKeyLike
                null,//String name
                null,//String nameNotEqual
                filter.getName(),//String nameLike
                null,//String nameNotLike
                null,//String description
                null,//String descriptionLike
                null,//Integer priority
                null,//Integer maxPriority
                null,//Integer minPriority
                null,//String dueDate
                null,//String dueDateExpression
                null,//String dueAfter
                null,//String dueAfterExpression
                null,//String dueBefore
                null,//String dueBeforeExpression
                null,//Boolean withoutDueDate
                null,//String followUpDate
                null,//String followUpDateExpression
                null,//String followUpAfter
                null,//String followUpAfterExpression
                null,//String followUpBefore
                null,//String followUpBeforeExpression
                null,//String followUpBeforeOrNotExistent
                null,//String followUpBeforeOrNotExistentExpression
                null,//String createdOn
                null,//String createdOnExpression
                null,//String createdAfter
                null,//String createdAfterExpression
                null,//String createdBefore
                null,//String createdBeforeExpression
                null,//String delegationState
                null,//String candidateGroups
                null,//String candidateGroupsExpression
                null,//Boolean withCandidateGroups
                null,//Boolean withoutCandidateGroups
                null,//Boolean withCandidateUsers
                null,//Boolean withoutCandidateUsers
                null,//Boolean active
                null,//Boolean suspended
                null,//String taskVariables
                null,//String processVariables
                null,//String caseInstanceVariables
                null,//Boolean variableNamesIgnoreCase
                null,//Boolean variableValuesIgnoreCase
                null,//String parentTaskId
                null,//String sortBy
                null,//String sortOrder
                null,//Integer firstResult
                null//Integer maxResults
        );
        List<CmdTaskDto> list = taskDtoList.stream().map(CmdTaskDto::converDto).collect(Collectors.toList());

        //STEP 封装流程定义相关数据
        for (CmdTaskDto cmdTaskDto : list) {
            ProcessDefinitionDto processDefinition = processDefinitionApi.getProcessDefinition(cmdTaskDto.getProcessDefinitionId());
            cmdTaskDto.setProcessDefinitionKey(processDefinition.getKey())
                    .setProcessDefinitionName(processDefinition.getName())
                    .setVersion(processDefinition.getVersion());
        }

        page.setList(list);
        page.setCount(countResultDto.getCount());
        return Result.buildResultSuccess(page);
    }

}
