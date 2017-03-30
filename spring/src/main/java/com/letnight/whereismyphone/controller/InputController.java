package com.letnight.whereismyphone.controller;

import com.letnight.whereismyphone.domain.PositionReport;
import com.letnight.whereismyphone.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class InputController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/position")
    public String inputPosition(@RequestBody final PositionReport positionReport) {
        log.info("Received data into position endpoint: {}", positionReport);
        positionService.putPositionReport(positionReport);
        return "Added position endpoint: " + positionReport;
    }

}
