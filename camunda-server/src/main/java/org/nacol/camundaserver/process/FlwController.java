package org.nacol.camundaserver.process;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.nacol.camundaserver.config.Page;
import org.nacol.camundaserver.config.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/flw")
@RestController
public class FlwController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @PostMapping("/startProcess")
    public Result startProcess(@RequestBody JSONObject params) {
        String processKey = params.getString("processKey");

        //指派下个任务的处理人、处理角色
        Map<String, Object> vars = new HashMap<>();
        putVar(vars, params, "userId");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, vars);
        return Result.buildResultSuccess("流程开启成功", processInstance.getProcessInstanceId());
    }

    @PostMapping("/pageRtTask")
    public Result<Page<JSONObject, TaskDto>> pageRtTask(@RequestBody Page<JSONObject, TaskDto> page) {
        String userId = page.filter().getString("userId");
        if(StringUtils.isBlank(userId)) {
            return Result.buildResultError("请指定查询人或者组织。");
        }


        //STEP 获取列表数据
        List<TaskDto> tasks = initRtTaskQuery(userId)
                .listPage(page.getPageNumber(), page.getPageSize())
                .stream().map(TaskDto::fromEntity).collect(Collectors.toList());

        //STEP 获取总数
        long count = initRtTaskQuery(userId).count();

        page.setList(tasks);
        page.setCount(count);

        return Result.buildResultSuccess(page);
    }

    private TaskQuery initRtTaskQuery(String userId) {
        return taskService.createTaskQuery().taskAssignee(userId);
    }

    @PostMapping("/pageHisTask")
    public Result<Page<JSONObject, HistoricTaskInstance>> pageHisTask(@RequestBody Page<JSONObject, HistoricTaskInstance> page) {
        String userId = page.filter().getString("userId");
        if(StringUtils.isBlank(userId)) {
            return Result.buildResultError("请指定用户。");
        }

        //STEP 获取列表数据
        List<HistoricTaskInstance> tasks = initHisTaskQuery(userId).listPage(page.getPageNumber(), page.getPageSize());

        //STEP 获取总数
        long count = initHisTaskQuery(userId).count();

        page.setList(tasks);
        page.setCount(count);

        return Result.buildResultSuccess(page);
    }

    private HistoricTaskInstanceQuery initHisTaskQuery(String userId) {
        return historyService.createHistoricTaskInstanceQuery().taskAssignee(userId);
    }

    @PostMapping("/complete")
    public Result complete(@RequestBody JSONObject params) {
        String taskId = params.getString("taskId");

        String userId = params.getString("userId");
        if(StringUtils.isBlank(userId)) {
            return Result.buildResultError("请指定查询人或者组织。");
        }

        Map<String, Object> vars = new HashMap<>();
        putVar(vars, params, "age");
        //指派下个任务的处理人、处理角色
        putVar(vars, params, "userId");

        taskService.complete(taskId, vars);

        return Result.buildResultSuccess();
    }

    @PostMapping("/receiveTask")
    public Result receiveTask(@RequestBody JSONObject params) {
        String userId = params.getString("userId");
        String taskId = params.getString("taskId");

        if(StringUtils.isBlank(userId) || StringUtils.isBlank(taskId)) {
            return Result.buildResultError("参数错误");
        }

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        task.setAssignee(userId);
        taskService.saveTask(task);

        return Result.buildResultSuccess("接收成功");
    }


    private void putVar(Map<String, Object> vars, JSONObject params, String key) {
        Object val = params.get(key);
        if (val != null) {
            vars.put(key, val);
        }
    }

    private List<String> getList(JSONObject param, String key, Class<String> stringClass) {
        return Optional.ofNullable(param.getJSONArray(key)).map(o->o.toJavaList(String.class)).orElse(new ArrayList<>());
    }
}
