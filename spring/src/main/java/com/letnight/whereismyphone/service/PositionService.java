package com.letnight.whereismyphone.service;

import com.letnight.whereismyphone.domain.PositionReport;
import java.util.List;

public interface PositionService {

    void putPositionReport(final PositionReport positionReport);

    PositionReport getLatestPosition(String name);

    List<PositionReport> getPositionReports();

}
