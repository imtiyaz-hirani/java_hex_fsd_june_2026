package com.springboot.ecom.mapper;

import com.springboot.ecom.dto.request.ExecutiveReqDto;
import com.springboot.ecom.dto.request.ExecutiveRespDto;
import com.springboot.ecom.dto.response.ExecutiveResDto;
import com.springboot.ecom.enums.JobTitle;
import com.springboot.ecom.model.Executive;
import org.springframework.stereotype.Component;

@Component
public class ExecutiveMapper {
    public static Executive convertDtoToEntity(ExecutiveReqDto dto){
        Executive executive = new Executive();
        executive.setName(dto.name());
        executive.setJobTitle(dto.jobTitle());
        return executive;
    }

    public static ExecutiveResDto convertEntityToDto(Executive executive){
        return new ExecutiveResDto(
                executive.getId(),
                executive.getName(),
                executive.getJobTitle()
        );

    }

    public static ExecutiveRespDto toDto(Executive executive){
         return   new ExecutiveRespDto(
                 executive.getId(),
                 executive.getName(),
                 executive.getJobTitle(),
                 executive.getUser().getUsername()
         );
    }
}
