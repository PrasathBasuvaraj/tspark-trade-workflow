package org.techspark.trade.workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.techspark.trade.workflow.dto.TradeRequest;

import java.util.Map;

@Tag(name = "Trade Workflow", description = "Endpoints for managing trade approval workflow")
@AllArgsConstructor
@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final RuntimeService runtimeService;

    @Operation(
            summary = "Start Trade Approval Workflow",
            description = "Triggers the trade approval workflow process for SPY option trades."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Process started successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/start")
    public ResponseEntity<String> startTradeProcess(@RequestBody TradeRequest request) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                "tradeApprovalProcess",
                Map.of(
                        "symbol", request.getSymbol(),
                        "quantity", request.getQuantity(),
                        "orderType", request.getOrderType()
                )
        );
        return ResponseEntity.ok("Process started with ID: " + instance.getId());
    }

}
