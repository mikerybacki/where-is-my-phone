package com.letnight.whereismyphone.service;

import com.letnight.whereismyphone.domain.PositionReport;
import com.letnight.whereismyphone.repo.PositionReportRepository;
import java.util.ArrayList;
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
    public List<PositionReport> getPositionReports() {
        final Iterable<PositionReport> positionReports = positionReportRepository.findAll();
        final List<PositionReport> positionReportList = new ArrayList<>();
        positionReports.forEach(positionReportList::add);
        return positionReportList;
    }

}
