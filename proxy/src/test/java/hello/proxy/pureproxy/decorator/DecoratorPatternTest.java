package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {
    @Test
    void decorator() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();

    }

    @Test
    void decorator1() {
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2() {
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        Component TimeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(TimeDecorator);
        client.execute();
    }
}
