package com.jp.hrworker.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.hrworker.entity.Worker;
import com.jp.hrworker.repository.WorkerRepository;

@Service
public class WorkerService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private WorkerRepository repository;
	
	public List<Worker> findAll() {
		return repository.findAll();
	}

	public Worker findById(Long id) {
		Optional<Worker> worker = repository.findById(id);
		return worker.get();
	}

}
