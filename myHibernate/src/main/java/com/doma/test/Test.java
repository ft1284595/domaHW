package com.doma.test;

import com.doma.domain.Employee;
import com.doma.domain.Project;
import com.doma.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Employee employee1 = new Employee("Jack1", "jack1@gmail.com", 11);
        Employee employee2 = new Employee("Jack2", "jack2@gmail.com", 22);
        Employee employee3 = new Employee("Jack3", "jack3@gmail.com", 33);
//        Employee employee4 = new Employee("Jack4", "jack4@gmail.com", 44);
//        Employee employee5 = new Employee("Jack5", "jack5@gmail.com", 55);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee2);
        employeeList.add(employee3);

        Project project1 = new Project("project001");
        Project project2 = new Project("project002");
        Project project3 = new Project("project003");

        List<Project> projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);

//        project1.setEmployeeList(employeeList);
        employee1.setProjectList(projectList);



//        session.save(project1);


        session.save(employee1);

        Project project4 = new Project("project004");
        session.save(project4);
        Project project5 = new Project("project005");
        project5.setEmployeeList(employeeList);
        session.save(project5);

        tx.commit();

        testEhcache();
    }

    static void testEhcache(){
        System.out.println("start to test Ehcache =======================>");
        System.out.println("Temp Dir:" + System.getProperty("java.io.tmpdir"));

        //Initialize Sessions
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
        stats.setStatisticsEnabled(true);
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());

        Session session = sessionFactory.openSession();
        Session otherSession = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Transaction otherTransaction = otherSession.beginTransaction();

        printStats(stats, 0);

        Employee emp = (Employee) session.load(Employee.class, 1);
        printData(emp, stats, 1);

        emp = (Employee) session.load(Employee.class, 1);
        printData(emp, stats, 2);

        //clear first level cache, so that second level cache is used
        System.out.println("$$$$$$$$$$$$$$$$$$$$==>clean session's cache");
        session.evict(emp);
        emp = (Employee) session.load(Employee.class, 1);
        printData(emp, stats, 3);

//        emp = (Employee) session.load(Employee.class, 3L);
        emp = (Employee) session.load(Employee.class, 1);
        printData(emp, stats, 4);

        sessionFactory.getCache().evictAll();
        System.out.println("$$$$$$$$$$$$$$$$$$$$==>clean sessionFactory's cache");

        emp = (Employee) otherSession.load(Employee.class, 1);
        printData(emp, stats, 5);

        //Release resources
        transaction.commit();
        otherTransaction.commit();
        sessionFactory.close();
        System.out.println("end to test Ehcache =======================>");
    }

    private static void printStats(Statistics stats, int i) {
        System.out.println("***** " + i + " *****");
        System.out.println("Fetch Count="
                + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count="
                + stats.getSecondLevelCacheHitCount());
        System.out
                .println("Second Level Miss Count="
                        + stats
                        .getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count="
                + stats.getSecondLevelCachePutCount());
    }

    private static void printData(Employee emp, Statistics stats, int count) {
//        System.out.println(count + ":: Name=" + emp.getName() + ", Zipcode=" + emp.getAddress().getZipcode());
        System.out.println(count + ":: Employee=" + emp);
        printStats(stats, count);
    }
}
