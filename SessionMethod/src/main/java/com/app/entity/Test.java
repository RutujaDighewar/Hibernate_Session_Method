/**
 * 
 */
package com.app.entity;

import org.hibernate.Session;

/**
 * @author Rutuja
 *
 */
public class Test {

	public void getTest() {
		Session session = HibernateUtility.getSession().openSession();
		Employee emp = (Employee) session.get(Employee.class, 2);
		System.out.println(emp);
		Employee emp2 = (Employee) session.get(Employee.class, 2); // hit only once
		System.out.println(emp2);
	}

	public void loadTest() {
		Session session = HibernateUtility.getSession().openSession();
		Employee emp = (Employee) session.load(Employee.class, 2);
		// System.out.println(emp);
		System.out.println(emp.getId());
		System.out.println(emp.getName());
		System.out.println(emp.getMobile());
		Employee emp2 = (Employee) session.get(Employee.class, 2);
		System.out.println(emp2);
		session.flush();
	}

	public void saveAndPersist() {
		Session session = HibernateUtility.getSession().openSession();
		Employee emp = new Employee();
		emp.setName("Sukhi");
		emp.setMobile("342683789");
		emp.setSalary(50000d);
		emp.setAge(29);

		/*
		 * Integer maxid=(Integer)session.save(emp); System.out.println(maxid);
		 */

		// session.save(emp);

		session.persist(emp);
		session.beginTransaction().commit();

	}

	public void saveOrUpdate() {
		Session session = HibernateUtility.getSession().openSession();
		Employee emp = new Employee();
		emp.setId(6);
		emp.setName("Puja");
		emp.setMobile("876532562");
		emp.setSalary(80000d); // without id - save
		emp.setAge(37); // with id - update
		session.saveOrUpdate(emp);
		session.beginTransaction().commit();
	}

	public void mergeTest() {
		Session session = HibernateUtility.getSession().openSession();
		Employee emp = (Employee) session.get(Employee.class, 5);
		emp.setName("Dipali");
		session.close();

		Session session1 = HibernateUtility.getSession().openSession();
		Employee emp2 = (Employee) session1.get(Employee.class, 5);
		emp.setAge(45);
		session1.close();

		Session session2 = HibernateUtility.getSession().openSession();
		Employee emp3 = (Employee) session2.get(Employee.class, 5);
		emp.setSalary(9000d);

		session2.merge(emp);
		session2.beginTransaction().commit();

	}

	public static void main(String[] args) {
		Test t = new Test();
		// t.getTest();
		// t.loadTest();
		// t.saveAndPersist();
		// t.saveOrUpdate();
		t.mergeTest();
	}

}
