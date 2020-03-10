package com.rock.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rock.exception.PersonException;
import com.rock.repo.RockRepo;
import com.rock.util.AsyncService;
import com.rock.util.ErrorResponse;



@RestController
public class Controller {

	private static Logger log = LoggerFactory.getLogger(Controller.class);
	
	 @Autowired
	 private AsyncService service;
	 
	 @Autowired
	 @Qualifier(value = "repo-class-2")
	 private RockRepo rockRepoInterface;
	 
	 @RequestMapping("/get-intfc-auto-data")
	 public String getFromInterface() {
		 return rockRepoInterface.something();
	 }
	
	@RequestMapping("/test")
	public Resource<Student> getData() {
		Student s= new Student(1L,"naveen", "BAQ");
		Resource<Student> resource = new Resource<Student>(s);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).get());
	    resource.add(linkTo.withRel("details"));
		return resource;
	}
	
	@RequestMapping("/get")
	public Details get() {
		Details s= new Details("naveen", "asdf");
		return s;
	}
	
	@RequestMapping("/exception-check/{name}")
	public List<Student> getOther(@PathVariable String name) throws PersonException {
		if (name.equalsIgnoreCase("naveen")) {
		Student s= new Student(1L,"naveen", "BAQ");
		List<Student> resource = new ArrayList<Student>();
	    resource.add(s);
		return resource;
		} else {
			throw new PersonException("Invalid Peson");
		}
	}
	
	@RequestMapping("/get-pan/{name}")
	public String getOther2(@PathVariable String name) throws PersonException {
		List<Student> resource = getStudents();
		if (resource.stream().anyMatch(s -> s.getName().equalsIgnoreCase(name))) {
			String ss =  resource.stream().filter(n -> n.getName().equals(name)).findAny()
	                .orElse(null).getPassportNumber();
			return ss;
		} else {
			throw new PersonException("Invalid Peson");
		}
	}
	
	@RequestMapping("/get-id/{name}")
	public Long getOther3(@PathVariable String name) throws PersonException {
		List<Student> resource = getStudents();
		//if (resource.contains(name)) {
			Long ss =  resource.stream().filter(n -> n.getName().equals(name)).findAny()
	                .orElse(null).getId();
			return ss;
		//} else {
		//	throw new PersonException("Invalid Peson");
		//}
	}
	
	@RequestMapping("/security/check")
	public List<Student> getOtherForSecurity() throws PersonException {
		List<Student> resource = getStudents();
		return resource;
	}
	
	
	 @RequestMapping(value = "/test-async", method = RequestMethod.GET)
	    public void testingAsync() throws InterruptedException, ExecutionException
	    {
	        log.info("test-async Start");
	 
	        CompletableFuture<String> employeeAddress = service.getEmployeeAddress();
	        CompletableFuture<Details> employeeName = service.getStudents();
	        CompletableFuture<Long> employeePhone = service.getEmployeePhone();
	 
	        // Wait until they are all done
	        CompletableFuture.allOf(employeeAddress, employeeName, employeePhone).join();
	         
	        log.info("EmployeeAddress--> " + employeeAddress.get());
	        log.info("EmployeeName--> " + employeeName.get());
	        log.info("EmployeePhone--> " + employeePhone.get());
	    }

	private List<Student> getStudents() {
		List<Student> sl = new ArrayList<Student>();
		Student s= new Student(16L,"naveen", "BAQ");
		Student s1= new Student(178L,"Anu", "RTYU");
		Student s2= new Student(1456L,"Vnu", "FGHJ");
		Student s3= new Student(1243L,"some", "R&YTUI");
		Student s4= new Student(178L,"test", "FGHJ&");
		Student s5= new Student(15678L,"Anu Nav", "VBNM5678");
		sl.add(s);
		sl.add(s1);
		sl.add(s2);
		sl.add(s3);
		sl.add(s4);
		sl.add(s5);
		return sl;
	}

	@ExceptionHandler(PersonException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	
	
	
}
