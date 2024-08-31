package com.hibernate.example.demohibernate.utils;

import java.util.function.Consumer;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class TransactionUtil {

    private TransactionUtil(){}

    public static <T> T inTransactionWithReturn(EntityManagerFactory em, Function<EntityManager, T> accion) {

        EntityManager entity = em.createEntityManager();
        EntityTransaction transaction = entity.getTransaction();        
        T resultadoFuncion;
        try {
            transaction.begin();
            resultadoFuncion = accion.apply(entity);
            transaction.commit();

            return resultadoFuncion;

        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();

            throw e;
        } finally{
            entity.close();
        }
    }

    public static void inTransaction(EntityManagerFactory em, Consumer<EntityManager> accion) {

        EntityManager entity = em.createEntityManager();
        EntityTransaction transaction = entity.getTransaction();        
        try {
            transaction.begin();
            accion.accept(entity);
            transaction.commit();

        } catch (Exception e) {
            if(transaction.isActive())
                transaction.rollback();

            throw e;
        } finally{
            entity.close();
        }
    }

}
