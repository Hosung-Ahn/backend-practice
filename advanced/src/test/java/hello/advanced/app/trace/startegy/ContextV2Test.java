package hello.advanced.app.trace.startegy;

import hello.advanced.app.trace.startegy.code.strategy.ContextV2;
import hello.advanced.app.trace.startegy.code.strategy.Strategy;
import hello.advanced.app.trace.startegy.code.strategy.StrategyLogic1;
import hello.advanced.app.trace.startegy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    /**
     * 전략 패턴 적용
     */
    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직 1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직 2 실행");
            }
        });
    }

    /**
     * 전략 패턴 익명 내부 클래스, 람다 사용
     */
    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비지니스 로직 1 실행"));
        context.execute(() -> log.info("비지니스 로직 2 실행"));
    }
}
