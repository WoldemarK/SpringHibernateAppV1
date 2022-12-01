package com.example.springhibernateappv1.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional(readOnly = true)
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person p = session.get(Person.class, id);
        p.setName(updatedPerson.getName);
        p.setAge(updatedPerson.getAge);
        p.setEmail(updatedPerson.getEmail);


    }

    @Transactional(readOnly = true)
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
