package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.model.LeaveApplication;
@Repository
public class LeaveApplicationNativeSqlRepo {
    @PersistenceContext
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<LeaveApplication> getAllLeavesOnStatus(StringBuffer whereQuery) {

	Query query = entityManager.createNativeQuery("select * from leave_details where " + whereQuery,
		LeaveApplication.class);
	
	return query.getResultList();
    }
}
