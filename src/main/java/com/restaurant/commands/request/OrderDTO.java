package com.restaurant.commands.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Getter
public class OrderDTO {

    @NotNull
    private Long reservationId;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long customerId;

    @NotNull
    private List<Long> dishesIds;

    @NotNull
    private String additionalRemarks;

    @JsonCreator
    public OrderDTO(@JsonProperty(value = "reservationId", required = true) Long reservationId,
                    @JsonProperty(value = "employeeId", required = true) Long employeeId,
                    @JsonProperty(value = "customerId", required = true) Long customerId,
                    @JsonProperty(value = "dishesIds", required = true) List<Long> dishesIds,
                    @JsonProperty(value = "additionalRemarks", required = true) String additionalRemarks) {
        this.reservationId = reservationId;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.dishesIds = dishesIds;
        this.additionalRemarks = additionalRemarks;
    }
}
