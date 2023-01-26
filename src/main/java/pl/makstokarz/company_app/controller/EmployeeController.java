package pl.makstokarz.company_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.makstokarz.company_app.model.Employee;
import pl.makstokarz.company_app.repository.EmployeeRepo;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("employees")
    public ResponseEntity<List<Employee>> saveEmployee(@RequestBody List<Employee> empList){
        return new ResponseEntity<>(employeeRepo.saveAll(empList),HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id){
        return employeeRepo.findById(id)
                .map(employee -> new ResponseEntity<>(employee,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee empData, @PathVariable Long id){
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(empData.getName());
                    employee.setSurname(empData.getSurname());
                    employee.setPosition(empData.getPosition());
                    return employee;
                })
                .map(employeeRepo::save)
                .map(employee -> new ResponseEntity<>(employee,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id){
        return employeeRepo.findById(id)
                .map(employee -> {
                    employeeRepo.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
