package com.letnight.whereismyphone.controller;

import com.letnight.whereismyphone.domain.PositionReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class InputController {

    @PostMapping("/position")
    public void inputPosition(@RequestBody final PositionReport positionReport) {
        log.info("Received data into position endpoint: {}", positionReport);
    }

}
