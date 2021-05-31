package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.ContactData;
import ru.stqa.pft.mantis.model.Contacts;


import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public ContactData contactWithId(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactData contact = (ContactData) session.createQuery( "from ContactData where id =" + id).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return contact;
  }

}
