package com.hbm.mapper;

import com.hbm.dto.CustomerPlanDto;
import com.hbm.model.PlanCustomer;

import java.time.LocalDate;

public class CustomerPlanMapper {
    public CustomerPlanDto mapEntityToDto(PlanCustomer planCustomer) {
        // I have an Entity given to me, and i need to convert it to dto record

        // Create an Object of Dto and pass the fields to its constructor
        CustomerPlanDto dto = new CustomerPlanDto(
                planCustomer.getCustomer().getId(),
                planCustomer.getCustomer().getName(),
                planCustomer.getCustomer().getEmail(),
                planCustomer.getPlan().getName(),
                LocalDate.parse(planCustomer.getStartDate().toString().split("T")[0]),
                LocalDate.parse(planCustomer.getDateEndDate().toString().split("T")[0])
        );
        return dto;
    }
}
