package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class TesteMain {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        

        session.getTransaction().commit();
        session.close();

	}

}
