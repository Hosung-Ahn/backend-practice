package hello.advanced.app.trace.startegy;

import hello.advanced.app.trace.startegy.code.strategy.ContextV1;
import hello.advanced.app.trace.startegy.code.strategy.Strategy;
import hello.advanced.app.trace.startegy.code.strategy.StrategyLogic1;
import hello.advanced.app.trace.startegy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV11 = new ContextV1(strategyLogic1);
        contextV11.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV12 = new ContextV1(strategyLogic2);
        contextV12.execute();
    }

    @Test
    void strategyV2() {
        Strategy logic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(logic1);
        contextV1.execute();

        Strategy logic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        ContextV1 contextV2 = new ContextV1(logic2);
        contextV2.execute();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    public void strategyV3() {
        ContextV1 contextV1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        contextV2.execute();
    }
}
