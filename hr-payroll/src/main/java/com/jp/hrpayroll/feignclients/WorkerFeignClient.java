package com.jp.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jp.hrpayroll.entity.Worker;

@Component
//name = application_name do recurso a ser acessado
@FeignClient(name = "hr-worker", url = "localhost:8001", path = "/workers")
public interface WorkerFeignClient {
	
	//esta interface tem que ser igual a interface do recurso que será chamado
	@GetMapping(value = "/{id}")
	ResponseEntity<Worker> getById(@PathVariable Long id);

}
