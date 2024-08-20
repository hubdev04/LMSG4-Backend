package com.ukg.authService.configuration;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ErrorDetail {
    private String code;
    private String message;

    public ErrorDetail(String number, String s) {
        this.code = number;
        this.message = s;
    }
}
