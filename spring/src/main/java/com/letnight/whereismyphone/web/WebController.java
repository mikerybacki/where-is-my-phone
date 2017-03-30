package com.letnight.whereismyphone.web;

import com.letnight.whereismyphone.domain.PositionReport;
import com.letnight.whereismyphone.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WebController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public String homePage(final Model model) {
        positionService.getPositionReports();
        final PositionReport latestPosition = positionService.getLatestPosition("Tomten");
        model.addAttribute("position", latestPosition);
        return "index";
    }
}
