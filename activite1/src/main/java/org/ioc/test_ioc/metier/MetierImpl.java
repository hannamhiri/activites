package org.ioc.test_ioc.metier;

import org.ioc.test_ioc.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("metier")	
public class MetierImpl implements IMetier {
    private IDao dao; // Couplage faible via interface

    // Injection via setter
    @Autowired  // Important : permet à Spring de faire l’injection
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double data = dao.getData();
        return data * 2; // Exemple de traitement
    }
}