package com.ovi.abc;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonBean {

    private int value = 1;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
