package mmk.com.sg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mmk.com.sg.dto.model.EmployeeDTO;
import mmk.com.sg.dto.model.UserDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/employees" })
public class TestEmployeeController {


	private List<EmployeeDTO> employees = createList();

	@GetMapping(produces = "application/json")
	public List<EmployeeDTO> firstPage() {
		return employees;
	}

	@DeleteMapping(path = { "/{id}" })
	public EmployeeDTO delete(@PathVariable("id") int id) {
		EmployeeDTO deletedEmp = null;
		for (EmployeeDTO emp : employees) {
			if (emp.getEmpId().equals(id)) {
				employees.remove(emp);
				deletedEmp = emp;
				break;
			}
		}
		return deletedEmp;
	}

	@PostMapping
	public EmployeeDTO create(@RequestBody EmployeeDTO user) {
		employees.add(user);
		System.out.println(employees);
		return user;
	}

	private static List<EmployeeDTO> createList() {
		List<EmployeeDTO> tempEmployees = new ArrayList<>();
		EmployeeDTO emp1 = new EmployeeDTO();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setEmpId("1");
		emp1.setSalary(3000);

		EmployeeDTO emp2 = new EmployeeDTO();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setEmpId("2");
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}
	
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public UserDTO validateLogin() {
		return new UserDTO("User successfully authenticated");
	}

}
