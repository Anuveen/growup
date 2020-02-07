package com.rock.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rock.controller.Details;
import com.rock.controller.Student;

@Service
public class AsyncService {


	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*@Async("asyncExecutor")
public CompletableFuture<List<Student>> getEmployeeName() throws InterruptedException
{
	List<Student> employeeNameData = restTemplate.getForObject("http://localhost:8080/name", List<Student>.class);
    Thread.sleep(1000L);    //Intentional delay
    return CompletableFuture.completedFuture(employeeNameData);
}*/

	@Async("asyncExecutor")
	public CompletableFuture<String> getEmployeeAddress() throws InterruptedException
	{
		//Map<String, Object> params = new HashMap<>();
		//params.put("name", "naveen");
		String employeeAddressData = restTemplate.getForObject("http://localhost:8080/get-pan/{name}", String.class, "naveen");
		Thread.sleep(10000L);    //Intentional delay
		return CompletableFuture.completedFuture(employeeAddressData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<Long> getEmployeePhone() throws InterruptedException
	{
	//Map<String, Object> params = new HashMap<>();
	//params.put("name", "naveen");
	Long employeePhoneData = restTemplate.getForObject("http://localhost:8080/get-id/{name}", Long.class, "naveen");
	Thread.sleep(10000L);    //Intentional delay
	return CompletableFuture.completedFuture(employeePhoneData);
	}

	@Async("asyncExecutor")
	public CompletableFuture<Details> getStudents() throws InterruptedException
	{
		Details employeePhoneData = restTemplate.getForObject("http://localhost:8080/get", Details.class);
		Thread.sleep(10000L);    //Intentional delay
		return CompletableFuture.completedFuture(employeePhoneData);
	}

}
