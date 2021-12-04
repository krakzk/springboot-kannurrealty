package com.taintedmonk.kannurRealty.repository;

import com.taintedmonk.kannurRealty.entity.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationInfoRepository extends JpaRepository<LocationInfo, Long> {
}
