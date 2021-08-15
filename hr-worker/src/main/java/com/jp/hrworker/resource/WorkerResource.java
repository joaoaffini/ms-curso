package com.jp.hrworker.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.hrworker.entity.Worker;
import com.jp.hrworker.service.WorkerService;

@RefreshScope
@RestController
@RequestMapping(path = "/workers")
public class WorkerResource {
	
	private static Logger LOGGER = LoggerFactory.getLogger(WorkerResource.class);
	
	//test.config = esta la no repositorio do git = https://github.com/joaoaffini/ms-curso-configs/blob/main/hr-worker.properties
//	@Value("${test.config}")
//	private String testConfig;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerService service;
	
	@GetMapping(path = "/configs")
	public ResponseEntity<Void> getConfigs() {
		
//		LOGGER.info("Config = " + testConfig);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> list = service.findAll();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> getById(@PathVariable Long id) {
		
//		try {
//			Thread.sleep(3000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		LOGGER.info("PORT = " + env.getProperty("local.server.port"));
		
		Worker worker = service.findById(id);
		
		return ResponseEntity.ok(worker);
	}

}
