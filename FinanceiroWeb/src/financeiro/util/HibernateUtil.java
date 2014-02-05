/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * 
 * @author TuLePe
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {

			AnnotationConfiguration cfg = new AnnotationConfiguration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();

		} catch (Throwable e) {
			System.out
					.println("Cria��o inicial do objeto SessionFactory falhou.\nErro: "
							+ e.getMessage());
			throw new ExceptionInInitializerError(e);
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}