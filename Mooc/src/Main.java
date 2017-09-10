import cn.gss.mooc.pojo.Message;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {
	private static final SessionFactory ourSessionFactory;

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();

			ourSessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return ourSessionFactory.openSession();
	}

	public static void main(final String[] args) throws Exception {
		final Session session = getSession();
		try {
			String hql = "From Message m , Count c WHERE m.msgid=c.msgid ORDER BY c.accesscount DESC";
			System.out.println(session.createQuery(hql,Message.class).list());
		} finally {
			session.close();
		}
	}
}