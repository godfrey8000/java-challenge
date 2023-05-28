package jp.co.axa.apidemo.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    @ApiOperation(value = "Get all employees", notes = "Provide a token in the 'Authorization' header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access token", paramType = "header", dataTypeClass = String.class)
    })
    public List<Employee> getEmployees() {
        log.info("recieving get employees request");
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    @ApiOperation(value = "Get an employee by ID", notes = "Provide a token in the 'Authorization' header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access token", paramType = "header", dataTypeClass = String.class)
    })
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        log.info("recieving get employees request of id : {}", employeeId);
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    @ApiOperation(value = "Create a new employee", notes = "Provide a token in the 'Authorization' header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access token", paramType = "header", dataTypeClass = String.class)
    })
    public void saveEmployee(Employee employee){
        log.info("recieving save employees request of id : {}", employee.getId());
        employeeService.saveEmployee(employee);
        log.info("Employee : {} Saved Successfully" , employee.getId());
    }

    @DeleteMapping("/employees/{employeeId}")
    @ApiOperation(value = "Delete an employee", notes = "Provide a token in the 'Authorization' header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access token", paramType = "header", dataTypeClass = String.class)
    })
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        log.info("recieving delete employees request of id : {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        log.info("Employee : {} Deleted Successfully", employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    @ApiOperation(value = "Update an existing employee", notes = "Provide a token in the 'Authorization' header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access token", paramType = "header", dataTypeClass = String.class)
    })
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
