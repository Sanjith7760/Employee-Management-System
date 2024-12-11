// EMPLOYEE MANAGEMENT SYSTEM : PROJECT
package com.Project.ems.EmployeeManagementSystem;

import java.util.Scanner;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class App {
	// Create a scanner class to get an input
	private static Scanner sc = new Scanner(System.in);
	// Loading the Configuration
	// Create SessionFactory
	private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	// Opening Session
	private static Session session = null;
	// Begin Transaction
	private static Transaction transaction = null;
	
    public static void main( String[] args ) {
    	System.out.println("EMPLOYEE  MANAGEMENT  SYSTEM");
    	while(true) {
    		System.out.println("Press 1    ----->    READ EMPLOYEE");
    		System.out.println("Press 2    ----->    ADD EMPLOYEE");
    		System.out.println("Press 3    ----->    REMOVE EMPLOYEE");
    		System.out.println("Press 4    ----->    UPDATE EMPLOYEE");
    		System.out.println("PRESS ANY OTHER NUMBER TO STOP");
    		
    		// Create choice for CRUD Operation
    		int choice = sc.nextInt();
    		switch(choice) {
    		case 1 -> readEmployee();
    		case 2 -> addEmployee();
    		case 3 -> removeEmployee();
    		case 4 -> updateEmployee();
    		default -> {
    			// Print the message and stop the program using return
    			System.out.println("THANK   YOU  FOR  USING  EMPLOYEE  MANAGEMENT SYSTEM");
    			System.out.println("HAVE A WONDERFULL DAY!");
    			return;
    		  }
    		}
    	}
    }
    
    public static void readEmployee() {
    	// Fetch the Employee Details
    	System.out.println("Enter  Id to fetch  the Employee Details: ");
    	int id = sc.nextInt();
    	session = sessionFactory.openSession();
    	transaction = session.beginTransaction();
    	Employee emp = session.get(Employee.class, id);
    	
    	if(emp!=null) {
    		System.out.println("Employee Id : "+emp.getId());
    		System.out.println("Employee Name : "+emp.getName());
    		System.out.println("Employee Salary : "+emp.getSalary());
    		System.out.println("Employee Phone : "+emp.getPhone());
    		System.out.println("Employee Email : "+emp.getEmail());
    		System.out.println("Employee Type : "+emp.getType());
    	}
    	else {
    		System.out.println("EMPLOYEE WITH ID "+id+" NOT FOUND");
    	}
    	transaction.commit();
    	session.close();
    }
    
    public static void addEmployee() {
    	// Create and Persist the Employee Details
    	System.out.println("Enter -> Id, Name, Salary, Phone, Email, Type");
    	Employee emp = new Employee();
    	System.out.println("Employee Name: ");
    	emp.setName(sc.next());
    	System.out.println("Employee Salary: ");
    	emp.setSalary(sc.nextInt());
    	System.out.println("Employee Phone: ");
    	emp.setPhone(sc.nextInt());
    	System.out.println("Employee Email: ");
    	emp.setEmail(sc.next());
    	System.out.println("Employee Type: ");
    	emp.setType(sc.next());
    	
    	session = sessionFactory.openSession();
    	transaction = session.beginTransaction();
    	session.persist(emp);
    	transaction.commit();
    	session.close();
    }
    
    public static void removeEmployee() {
    	// Delete the Employee Details
    	System.out.println("Enter  Id to Delete Employee Details");
    	int id = sc.nextInt();
    	session = sessionFactory.openSession();
    	transaction = session.beginTransaction();
    	Employee emp = session.get(Employee.class, id);
    	
    	if(emp!= null) {
    		session.remove(emp);
    	}
    	else {
    		System.out.println("EMPLOYEE WITH ID "+id+" NOT FOUND");
    	}
    	transaction.commit();
    	session.close();
    }
    
    public static void updateEmployee() {
    	// Update the Employee Details
    	
    	System.out.println("Enter Id to Update Employee Details:");
    	int id = sc.nextInt();
    	session = sessionFactory.openSession();
    	transaction = session.beginTransaction();
    	Employee emp = session.get(Employee.class, id);
    	if(emp!=null) {
    		
    		System.out.println("CHOOSE TO UPDATE");
    		System.out.println("1 ----> Name");
    		System.out.println("2 ----> Salary");
    		System.out.println("3 ----> Phone");
    		System.out.println("4 ----> Email");
    		System.out.println("5 ----> Type");
    		int option = sc.nextInt();
    		switch(option) {
    		case 1: System.out.println("Enter New Name");
    		String name = sc.next();
    		emp.setName(name);
    		break;
    		
    		case 2: System.out.println("Enter New Salary");
    		int salary = sc.nextInt();
    		emp.setSalary(salary);
    		break;
    		
    		case 3: System.out.println("Enter New Phone");
    		int phone = sc.nextInt();
    		emp.setPhone(phone);
    		break;
    		
    		case 4: System.out.println("Enter New Email");
    		String email = sc.next();
    		emp.setEmail(email);
    		break;
    		
    		case 5: System.out.println("Enter New Type");
    		String type = sc.next();
    		emp.setType(type);
    		
    		default :  System.out.println("Enter New Salary");
    		  int salary1 = sc.nextInt();
    		emp.setSalary(salary1);
    		}
    		
    		session.update(emp);
    		transaction.commit();
    		session.close();
    	}
    	else {
    		System.out.println("EMPLOYEE WITH ID "+id+" NOT FOUND");
    	}
    }
}
