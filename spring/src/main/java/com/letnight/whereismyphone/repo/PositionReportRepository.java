package com.letnight.whereismyphone.repo;

import com.letnight.whereismyphone.domain.PositionReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionReportRepository extends CrudRepository<PositionReport, Long> {

    PositionReport findTopByNameOrderByLocationTimestampDesc(final String name);

}
