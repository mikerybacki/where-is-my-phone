package com.letnight.whereismyphone.service;

import com.google.common.collect.Lists;
import com.letnight.whereismyphone.domain.PositionReport;
import com.letnight.whereismyphone.repo.PositionReportRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionReportRepository positionReportRepository;

    @Override
    public void putPositionReport(final PositionReport positionReport) {
        positionReportRepository.save(positionReport);
    }

    @Override
    public PositionReport getLatestPosition(final String name) {
        return positionReportRepository.findTopByNameOrderByLocationTimestampDesc(name);
    }

    @Override
    public List<PositionReport> getPositionReports() {
        return Lists.newArrayList(positionReportRepository.findAll());
    }
}
