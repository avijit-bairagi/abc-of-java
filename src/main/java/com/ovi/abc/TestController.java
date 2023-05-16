package com.ovi.abc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SingletonBean singletonBean1;

    @Autowired
    private SingletonBean singletonBean2;

    @Autowired
    private PrototypeBean prototypeBean1;

    @Autowired
    private PrototypeBean prototypeBean2;

    @GetMapping
    public void test() {

        System.out.println(singletonBean1.getValue());

        singletonBean1.setValue(3);

        System.out.println(singletonBean2.getValue());


        System.out.println(prototypeBean1.getValue());

        prototypeBean1.setValue(3);

        System.out.println(prototypeBean2.getValue());
    }
}
