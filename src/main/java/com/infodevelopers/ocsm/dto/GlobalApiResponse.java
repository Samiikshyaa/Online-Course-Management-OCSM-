package com.infodevelopers.ocsm.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GlobalApiResponse {
    private boolean status;
    private String message;
    private Object data;

}
