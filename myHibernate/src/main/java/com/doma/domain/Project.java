package com.doma.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PROJECT")
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY, region="employee")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pro_id")
    private int project_id;
    @Column(name="title", nullable = false)
    private String title;

//    @ManyToMany(mappedBy = "projectList")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="EMPLOYEE_PROJECT", joinColumns = {@JoinColumn(referencedColumnName = "pro_id", name = "p_id")}
            , inverseJoinColumns = {@JoinColumn(referencedColumnName="emp_id", name="e_id")})
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Project() {
    }

    public Project(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", title='" + title + '\'' +
                '}';
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
