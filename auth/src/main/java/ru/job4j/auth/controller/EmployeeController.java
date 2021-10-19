package ru.job4j.auth.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.EmployeeRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;

    public EmployeeController(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return StreamSupport.stream(
                this.employeeRepository.findAll().spliterator(), false
        )
                .map(employee -> {
                    List<Person> people = restTemplate.exchange(
                            "http://localhost:8080/person/employeeId/" + employee.getId(),
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
                            }
                    ).getBody();
                    employee.setPeople(people);
                    return employee;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        var employee = this.employeeRepository.findById(id);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (employee.isPresent()) {
            List<Person> people = restTemplate.exchange(
                    "http://localhost:8080/person/employeeId/" + employee.get().getId(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
                    }
            ).getBody();
            employee.get().setPeople(people);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Employee>(employee.orElse(new Employee()), status);
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee result = employeeRepository.save(employee);
        CopyOnWriteArrayList<Person> people = new CopyOnWriteArrayList<>();
        for (Person person : employee.getPeople()) {
            person.setEmployeeId(result.getId());
            Person personFromRest = restTemplate.postForObject("http://localhost:8080/person/", person, Person.class);
            people.add(personFromRest);
        }
        result.setPeople(people);
        return new ResponseEntity<Employee>(result, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Employee employee) {
        Employee result = employeeRepository.save(employee);
        for (Person person : employee.getPeople()) {
            person.setEmployeeId(result.getId());
            Person personFromRest = restTemplate.postForObject("http://localhost:8080/person/", person, Person.class);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        this.employeeRepository.delete(employee);
        restTemplate.delete("http://localhost:8080/person/employeeId/{id}", id);
        return ResponseEntity.ok().build();
    }
}
