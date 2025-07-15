package org.techspark.trade.workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Workflow Utilities", description = "Endpoints for viewing and interacting with trade workflows")
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    @Operation(summary = "List active process instances")
    @GetMapping("/instances")
    public ResponseEntity<?> listActiveProcesses() {
        try {
            List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery()
                    .active()
                    .list();

            List<Map<String, Object>> result = new ArrayList<>();
            for (ProcessInstance pi : instances) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", pi.getId());
                map.put("name", pi.getName());
                map.put("definitionId", pi.getProcessDefinitionId());
                map.put("businessKey", pi.getBusinessKey());
                result.add(map);
            }

            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            log.error("Failed to fetch process instances", ex);
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Failed to fetch process instances",
                    "message", ex.getMessage()
            ));
        }
    }

    @Operation(summary = "List pending user tasks (optionally by assignee or group)")
    @GetMapping("/tasks")
    public List<Map<String, Object>> listTasks(
            @RequestParam(required = false) String assignee,
            @RequestParam(required = false) String candidateGroup) {

        List<Task> tasks;

        if (assignee != null) {
            tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        } else if (candidateGroup != null) {
            tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).list();
        } else {
            tasks = taskService.createTaskQuery().list();
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (Task task : tasks) {
            Map<String, Object> taskInfo = new HashMap<>();
            taskInfo.put("id", task.getId());
            taskInfo.put("name", task.getName());
            taskInfo.put("assignee", task.getAssignee());
            taskInfo.put("processInstanceId", task.getProcessInstanceId());
            result.add(taskInfo);
        }

        return result;
    }

    @Operation(summary = "Complete a user task by ID")
    @PostMapping("/tasks/{taskId}/complete")
    public ResponseEntity<?> completeTask(
            @PathVariable String taskId,
            @RequestBody(required = false) Map<String, Object> variables) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        if (task == null) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", "Task not found",
                    "taskId", taskId
            ));
        }

        try {
            if (variables != null && !variables.isEmpty()) {
                taskService.complete(taskId, variables);
            } else {
                taskService.complete(taskId);
            }
            return ResponseEntity.ok(Map.of(
                    "message", "Task completed successfully",
                    "taskId", taskId
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Task completion failed",
                    "message", e.getMessage()
            ));
        }
    }

}
