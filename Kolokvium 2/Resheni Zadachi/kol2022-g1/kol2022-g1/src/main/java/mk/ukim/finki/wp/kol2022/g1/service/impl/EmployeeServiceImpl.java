package mk.ukim.finki.wp.kol2022.g1.service.impl;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import mk.ukim.finki.wp.kol2022.g1.repository.SkillRepository;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import java.time.LocalDate;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService
{
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillRepository skillRepository)
    {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Employee> listAll()
    {
        return this.employeeRepository
                .findAll();
    }

    @Override
    public Employee findById(Long id)
    {
        return this.employeeRepository
                .findById(id)
                .orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate)
    {
        List<Skill> skills = this.skillRepository
                .findAllById(skillId);

        Employee employee = new Employee(name, email, password, type, skills, employmentDate);

        this.employeeRepository
                .save(employee);

        return employee;
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate)
    {

        Employee employee = this.employeeRepository
                .findById(id)
                .orElseThrow(InvalidEmployeeIdException::new);

        List<Skill> skills = this.skillRepository
                .findAllById(skillId);

        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setEmploymentDate(employmentDate);
        employee.setType(type);
        employee.setSkills(skills);

        this.employeeRepository.save(employee);

        return employee;
    }

    @Override
    public Employee delete(Long id)
    {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(InvalidEmployeeIdException::new);

        this.employeeRepository.delete(employee);

        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService)
    {
        Specification<Employee> specification = Specification.where(hasSkill(skillId))
                .and(hasYearOfService(yearsOfService));

        return this.employeeRepository
                .findAll(specification);
    }


    public static Specification<Employee> hasSkill(Long skillId)
    {
        if (skillId == null) return null;


        return (root, query, criteriaBuilder) -> {
            Join<Employee, Skill> skillsTable = root.join("skills");
            return criteriaBuilder.equal(skillsTable.get("id"), skillId);
        };

    }

    public static Specification<Employee> hasYearOfService(Integer yearOfService)
    {

        if (yearOfService == null) return null;

        LocalDate lessThenThis = LocalDate.now().minusYears(yearOfService);

        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("employmentDate"), lessThenThis);

    }






//    public static Specification<Employee> hasYearOfService(Integer yearOfService)
//    {
//        if (yearOfService == null) return null;
//
//        LocalDate lessThenThis = LocalDate.now().minusYears(yearOfService);
//
//        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("employmentDate"), lessThenThis);
//    }
//
//    public static Specification<Employee> hasSkill(Long skillId)
//    {
//        if (skillId == null) return null;
//
//
//        return (root, query, criteriaBuilder) -> {
//
//            Join<Employee, Skill> skillsJoin = root.join("skills");
//            return criteriaBuilder.equal(skillsJoin.get("id"), skillId);
//
//        };
//
//    }

}
