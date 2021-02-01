package com.project.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPassRequest {
    /**
     * Nguyen Van Linh made it
     */
    private String password;
    private String code;
}
