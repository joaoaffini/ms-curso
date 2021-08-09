package com.jp.hrworker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jp.hrworker.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
