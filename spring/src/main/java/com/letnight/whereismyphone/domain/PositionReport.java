package com.letnight.whereismyphone.domain;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PositionReport {

    private Double longitude;
    private Double latitude;
    private LocalDateTime timestamp;

}
