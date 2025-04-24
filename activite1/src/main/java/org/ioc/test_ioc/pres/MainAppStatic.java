package org.ioc.test_ioc.pres;

import org.ioc.test_ioc.dao.DaoImpl;
import org.ioc.test_ioc.metier.MetierImpl;

public class MainAppStatic {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao); 

        System.out.println("Résultat = " + metier.calcul());
    }
}
