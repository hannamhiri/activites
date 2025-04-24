package org.ioc.test_ioc.dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao{
	@Override
    public double getData() {
       
        return 7.5;
}
}
