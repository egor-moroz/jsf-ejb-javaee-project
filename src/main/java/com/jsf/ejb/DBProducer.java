package com.jsf.ejb;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DBProducer {

  @Produces
  @PersistenceContext(unitName = "bookUN")
  private EntityManager em;
}
