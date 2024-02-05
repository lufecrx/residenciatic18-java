package com.lufecrx.academico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Teste {

    public static void criar() {
        Estudante e1 = new Estudante("Lucas", "lucas@lucas", "123");
        Estudante e2 = new Estudante("Fernando", "fernando@fernando", "456");
        Estudante e3 = new Estudante("Gabriel", "gabriel@gabriel", "789");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public static void find() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
        EntityManager em = emf.createEntityManager();

        Estudante e = em.find(Estudante.class, 1);
        System.out.println(e);

        em.close();
        emf.close();
    }
    
    public static void main(String[] args) {
        // criar();
        find();
    }
}
