package com.taintedmonk.kannurRealty.repository;

import com.taintedmonk.kannurRealty.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
