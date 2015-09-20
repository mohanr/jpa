package com.jpa.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;


public class TestImpl implements Test {

	 @PersistenceContext(unitName="testingSetup")
	 private EntityManager entityManager;
	 
	 private static final Logger logger = LoggerFactory.getLogger("jpa");

	
	@Transactional
    public void roundTrip(){
		test();
		getAddress();
	}


	@Override
	@Transactional
	public void test() {
		logger.info(" TransactionSynchronizationManager.isActualTransactionActive()"
        +  TransactionSynchronizationManager.isActualTransactionActive() );
		INMEMORY_DB a = new INMEMORY_DB();
		a.setId("id");
		a.setStreet("Street");
		a.setArea("Area");
		a.setState("State");
		a.setCountry("LO");
		a.setPin(1);
		logger.info( a.toString() );
		entityManager.persist( a );
	}
	
	@Transactional
	public void getAddress(){
		logger.info("INMEMORY_DB:: "
			    + entityManager.createQuery("SELECT COUNT(*) FROM " + INMEMORY_DB.class.getName()).getSingleResult()
			        .toString());	
		TypedQuery<INMEMORY_DB> query =
				entityManager.createQuery("SELECT c FROM INMEMORY_DB c", INMEMORY_DB.class);
			  List<INMEMORY_DB> results = query.getResultList();		
		for( INMEMORY_DB memdb : results){
			logger.info( memdb.toString() );
		}
	}

}
