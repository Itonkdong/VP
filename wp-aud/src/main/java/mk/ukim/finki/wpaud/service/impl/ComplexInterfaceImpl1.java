package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.service.ComplexInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class ComplexInterfaceImpl1 implements ComplexInterface
{
    @Override
    public void process()
    {
        System.out.println("===1===");
    }
}
