package com.letnight.whereismyphone.controller;

import com.letnight.whereismyphone.domain.PositionReport;
import com.letnight.whereismyphone.service.PositionServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OutputController {

    @Autowired
    private PositionServiceImpl positionService;

    @GetMapping("/positions")
    public List<PositionReport> inputPosition() {
        return positionService.getPositionReports();
    }
}
