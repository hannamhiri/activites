package org.ioc.test_ioc.pres;

import org.ioc.test_ioc.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class MainAppSpringAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println("Résultat = " + metier.calcul());
    }
}

@Configuration
@ComponentScan(basePackages = "org.ioc.test_ioc")
class AppConfig {
}
