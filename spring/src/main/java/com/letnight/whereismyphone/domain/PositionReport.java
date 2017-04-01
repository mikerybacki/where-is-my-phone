package com.letnight.whereismyphone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Entity(name = "position_report")
@Data
@ToString
public class PositionReport {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Double longitude;

    private Double latitude;

    private Double accuracy;

    @Column(name = "location_timestamp")
    private Long locationTimestamp;

    @Column(name = "request_timestamp")
    private Long requestTimestamp;

    private String name;

}
