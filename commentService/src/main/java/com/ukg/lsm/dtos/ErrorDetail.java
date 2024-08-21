package com.ukg.lsm.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetail {
    private String code;
    private String message;

    public ErrorDetail(String message)
    {
        this.message=message;
    }

}
