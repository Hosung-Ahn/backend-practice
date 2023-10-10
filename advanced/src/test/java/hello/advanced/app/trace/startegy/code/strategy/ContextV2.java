package hello.advanced.app.trace.startegy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 전달 받는 방식
 */
@Slf4j
public class ContextV2 {

    // 변화하지 않는 로직
    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        // 위임
        strategy.call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("result time={}", resultTime);
    }
}
