package com.jpa.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringConfiguration;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
@SpringConfiguration("applicationContext.xml")
public class ShrinkWrappedJPATest {

	private static Logger l = Logger.getLogger("jpa");
		
	@PersistenceContext(unitName="testingSetup")
	private EntityManager entityManager;

    @Deployment
    public static WebArchive createWebArchive() {
  
    	final WebArchive war=ShrinkWrap.create(WebArchive.class,"ShrinkWrapJPA.war");
    	  
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                				.addPackage("com.jpa.test");
 

        war.addAsLibrary(jar);
    	war.addAsResource("applicationContext.xml");
    	war.addAsResource("arquillian.xml");
    	war.addAsResource("log4j.xml");
    	war.addAsResource("schema.sql");
    	war.addAsResource("test-data.sql");
    	war.addAsResource("log4j.xml");
    	war.addAsResource("persistence.xml", "META-INF/persistence.xml");
    	loadDependencies( war );
 
    	l.info(war.toString(Formatters.VERBOSE));
    	return war;
    }

        
    
    
    private static void loadDependencies( final WebArchive war ){
    	
        File springorm = Maven.
				resolver().
					resolve("org.springframework:spring-orm:4.1.6.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springorm);

        File hibernate = Maven.
 				resolver().
 					resolve("org.hibernate:hibernate-core:4.1.7.FINAL")
 						.withoutTransitivity().asSingle(File.class);

         war.addAsLibraries(hibernate);

 
	    File hibernate1 = Maven.
					resolver().
						resolve("org.hibernate:hibernate-entitymanager:4.1.7.FINAL")
							.withoutTransitivity().asSingle(File.class);
	
	     war.addAsLibraries(hibernate1);

        File springexpression = Maven.
				resolver().
					resolve("org.springframework:spring-expression:4.2.0.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springexpression);

        File springweb = Maven.
				resolver().
					resolve("org.springframework:spring-web:4.2.0.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springweb);

        File springcore = Maven.
				resolver().
					resolve("org.springframework:spring-core:4.1.6.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springcore);
        
        File springcontext = Maven.
				resolver().
					resolve("org.springframework:spring-context:4.1.6.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springcontext);

        File springjdbc = Maven.
				resolver().
					resolve("org.springframework:spring-jdbc:4.1.6.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springjdbc);
        File springtx = Maven.
				resolver().
					resolve("org.springframework:spring-tx:4.1.6.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springtx);
        File hsqldb = Maven.
 				resolver().
 					resolve("org.hsqldb:hsqldb:2.3.1")
 						.withoutTransitivity().asSingle(File.class);

         war.addAsLibraries(hsqldb);
         File dbcp = Maven.
 				resolver().
 					resolve("commons-dbcp:commons-dbcp:1.4")
 						.withoutTransitivity().asSingle(File.class);

         war.addAsLibraries(dbcp);
       File aopalliance = Maven.
				resolver().
					resolve("aopalliance:aopalliance:1.0")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(aopalliance);
        File extensionspring = Maven.
				resolver().
					resolve("org.jboss.arquillian.extension:arquillian-service-deployer-spring-3:1.0.0.Beta3")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(extensionspring);


         File springbeans = Maven.
				resolver().
					resolve("org.springframework:spring-beans:4.1.6.RELEASE")
						.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springbeans);

        File springaop = Maven.
				resolver().
					resolve("org.springframework:spring-aop:4.2.0.RELEASE")
					.withoutTransitivity().asSingle(File.class);

        war.addAsLibraries(springaop);


         File transactionapi = Maven.
 				resolver().
 					resolve("org.jboss.arquillian.extension:arquillian-transaction-api:1.0.1.Final")
 					.withoutTransitivity().asSingle(File.class);

         war.addAsLibraries(transactionapi);
         File transactionimplbase = Maven.
 				resolver().
 					resolve("org.jboss.arquillian.extension:arquillian-transaction-impl-base:1.0.1.Final")
 					.withoutTransitivity().asSingle(File.class);

         war.addAsLibraries(transactionimplbase);

    }
    
    @Test
    @Transactional(manager="transactionManager")
    
	public void save() throws Exception, SystemException {
 

        INMEMORY_DB a = new INMEMORY_DB();
		a.setId("id");
		a.setStreet("Street");
		a.setArea("Area");
		a.setState("State");
		a.setCountry("LO");
		a.setPin(1);
		entityManager.persist( a );
  		assertEquals(getAddressCount(), 2);
	}

 
	public int getAddressCount(){
		TypedQuery<INMEMORY_DB> query =
				entityManager.createQuery("SELECT c FROM INMEMORY_DB c", INMEMORY_DB.class);
		List<INMEMORY_DB> results = query.getResultList();	
		return results.size();
	}

}