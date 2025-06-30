package org.techspark.trade.workflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request object for submitting a trade")
public class TradeRequest {

    @Schema(description = "Symbol of the asset being traded", example = "SPY", requiredMode = Schema.RequiredMode.REQUIRED)
    private String symbol;

    @Schema(description = "Quantity of contracts", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private int quantity;

    @Schema(description = "Order type, e.g., PUT_CREDIT_SPREAD", example = "PUT_CREDIT_SPREAD", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderType;
}
