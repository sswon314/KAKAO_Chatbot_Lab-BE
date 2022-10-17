package com.example.goENC.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChoiceResponseDto {

    private Integer id;
    private Integer order;
    private String value;
    private Boolean isCheck;

    public ChoiceResponseDto(Integer id, Integer order, String value, Boolean isCheck) {
        this.id = id;
        this.order = order;
        this.value = value;
        this.isCheck = isCheck;
    }
}
