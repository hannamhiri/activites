package org.ioc.test_ioc.pres;

import org.ioc.test_ioc.dao.IDao;
import org.ioc.test_ioc.metier.MetierImpl;

public class MainAppDynamic {
    public static void main(String[] args) throws Exception {
        String daoClassName = "org.ioc.test_ioc.dao.DaoImpl";
        String metierClassName = "org.ioc.test_ioc.metier.MetierImpl";

       
        IDao dao = (IDao) Class.forName(daoClassName).getDeclaredConstructor().newInstance();
        MetierImpl metier = (MetierImpl) Class.forName(metierClassName).getDeclaredConstructor().newInstance();
        metier.setDao(dao);

        System.out.println("Résultat = " + metier.calcul());
    }
}

