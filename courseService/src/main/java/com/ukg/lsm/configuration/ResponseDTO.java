package com.ukg.lsm.configuration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor(staticName = "build")
public class ResponseDTO {
    private boolean success;
    private String message;
    private LocalDateTime completionTimeStamp;
    private Object result;
    private List<ErrorDetail> errorDetails;
}
