package hello.advanced.app.trace.startegy;

import hello.advanced.app.trace.startegy.code.strategy.ContextV1;
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
}
