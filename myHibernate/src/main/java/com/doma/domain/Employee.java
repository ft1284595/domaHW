package com.doma.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="EMPLOYEE")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_id")
    private int employee_id;
    @Column(name="emp_name", nullable = false)
    private String name;
    @Column(name="emp_email")
    private String email;
    @Column(name="emp_age")
    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="EMPLOYEE_PROJECT", joinColumns = {@JoinColumn(referencedColumnName = "emp_id", name="e_id")}
    , inverseJoinColumns = {@JoinColumn(referencedColumnName="pro_id", name="p_id")})
    private List<Project> projectList;

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Employee() {
    }

    public Employee(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Employee(String name) {
        this.name = name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
