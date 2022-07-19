package com.hamitmizrak.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TodoDto {
    private Long todoId;
    private String todoName;

}
