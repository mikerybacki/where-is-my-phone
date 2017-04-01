package com.letnight.whereismyphone.controller;

import com.letnight.whereismyphone.domain.PositionReport;
import com.letnight.whereismyphone.service.PositionService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/position/{name}")
    public PositionReport getLatestPosition(@RequestParam final String name) {
        return positionService.getLatestPosition(name);
    }

    @GetMapping("/positions")
    public List<PositionReport> getAllPositions() {
        return positionService.getPositionReports();
    }

    @PostMapping("/position")
    public String insertPosition(@RequestBody final PositionReport positionReport) {
        log.info("Received data into position endpoint: {}", positionReport);
        positionService.putPositionReport(positionReport);
        return "Added position endpoint: " + positionReport;
    }
}
