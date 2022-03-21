package org.nacol.camundarestclient.process.restclient.manage;

import commons.Page;
import commons.Result;
import lombok.extern.log4j.Log4j2;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.api.ProcessInstanceApi;
import org.camunda.community.rest.client.dto.CountResultDto;
import org.camunda.community.rest.client.dto.ProcessDefinitionDiagramDto;
import org.camunda.community.rest.client.dto.ProcessDefinitionDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@Log4j2
@RestController
@RequestMapping("/manage/process-definition")
public class ManageProcessDefinitionApi {

    @Autowired
    private ProcessDefinitionApi processDefinitionApi;

    @Autowired
    ProcessInstanceApi processInstanceApi;

    @GetMapping("/getById/{processInstanceId}")
    public Result getById(@PathVariable String processInstanceId) throws ApiException, ExecutionException, InterruptedException {
        ProcessDefinitionDto processDefinition = processDefinitionApi.getProcessDefinition(processInstanceId);
        return Result.buildResultSuccess(processDefinition);
    }

    @PostMapping("page")
    public Result page(@RequestBody  Page<ProcessDefinitionDto, ProcessDefinitionDto> page) throws ApiException {
        ProcessDefinitionDto filter = page.filter();

        CountResultDto countResultDto = processDefinitionApi.getProcessDefinitionsCount(
                filter.getDeploymentId(), //String processDefinitionId
                null, //String processDefinitionIdIn
                null, //String name
                filter.getName(), //String nameLike
                null, //String deploymentId
                null, //Date deployedAfter
                null, //Date deployedAt
                filter.getKey(), //String key
                null, //String keysIn
                null, //String keyLike
                null, //String category
                null, //String categoryLike
                filter.getVersion(), //Integer version
                null, //Boolean latestVersion
                null, //String resourceName
                null, //String resourceNameLike
                null, //String startableBy
                null, //Boolean active
                null, //Boolean suspended
                null, //String incidentId
                null, //String incidentType
                null, //String incidentMessagenull,
                null, //String incidentMessageLike
                null, //String tenantIdIn
                null, //Boolean withoutTenantId
                null, //Boolean includeProcessDefinitionsWithoutTenantId
                null, //String versionTag
                null, //String versionTagLike
                null, //Boolean withoutVersionTag
                null, //Boolean startableInTasklist
                null, //Boolean notStartableInTasklist
                null //Boolean startablePermissionCheck
        );

        List<ProcessDefinitionDto> processDefinitionDtoList =  processDefinitionApi.getProcessDefinitions(
                filter.getDeploymentId(), //String processDefinitionId
                null, //String processDefinitionIdIn
                null, //String name
                filter.getName(), //String nameLike
                null, //String deploymentId
                null, //Date deployedAfter
                null, //Date deployedAt
                null, //String key
                null, //String keysIn
                filter.getKey(), //String keyLike
                null, //String category
                null, //String categoryLike
                filter.getVersion(), //Integer version
                null, //Boolean latestVersion
                null, //String resourceName
                null, //String resourceNameLike
                null, //String startableBy
                null, //Boolean active
                null, //Boolean suspended
                null, //String incidentId
                null, //String incidentType
                null, //String incidentMessagenull,
                null, //String incidentMessageLike
                null, //String tenantIdIn
                null, //Boolean withoutTenantId
                null, //Boolean includeProcessDefinitionsWithoutTenantId
                null, //String versionTag
                null, //String versionTagLike
                null, //Boolean withoutVersionTag
                null, //Boolean startableInTasklist
                null, //Boolean notStartableInTasklist
                null, //Boolean startablePermissionCheck
                null, //String sortBy
                null, //String sortOrder
                page.getPageNumber(), //Integer firstResult
                page.getPageSize()//Integer maxResults
        );
        page.setList(processDefinitionDtoList);
        page.setCount(countResultDto.getCount());
        return Result.buildResultSuccess(page);
    }

    @GetMapping("/bpmn20Xm/{processDefinitionId}")
    public Result bpmn20Xm(@PathVariable String processDefinitionId) throws ApiException {
        ProcessDefinitionDiagramDto processDefinitionBpmn20Xml = processDefinitionApi.getProcessDefinitionBpmn20Xml(processDefinitionId);
        return Result.buildResultSuccess("成功", processDefinitionBpmn20Xml.getBpmn20Xml());
    }
}
