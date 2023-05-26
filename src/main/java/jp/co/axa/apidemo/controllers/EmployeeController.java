package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        log.info("recieving get employees request");
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")

    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        log.info("recieving get employees request of id : {}", employeeId);
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    public void saveEmployee(Employee employee){
        log.info("recieving save employees request of id : {}", employee.getId());
        employeeService.saveEmployee(employee);
        log.info("Employee : {} Saved Successfully" , employee.getId());
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        log.info("recieving delete employees request of id : {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        log.info("Employee : {} Deleted Successfully", employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        log.info("recieving update employees request of id : {}", employeeId);
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
            log.info("Employee : {} updated Successfully", employeeId);
        }else{
            log.info("Employee : {} Not Found", employeeId);
        }

    }

}
