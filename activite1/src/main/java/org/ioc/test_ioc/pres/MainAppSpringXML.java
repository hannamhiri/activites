package org.ioc.test_ioc.pres;

import org.ioc.test_ioc.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAppSpringXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        IMetier metier = (IMetier) context.getBean("metier");

        System.out.println("Résultat = " + metier.calcul());
    }
}
