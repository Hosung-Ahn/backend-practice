package hello.advanced.app.trace.startegy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {
    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    // 변화하지 않는 로직
    public void execute() {
        long startTime = System.currentTimeMillis();

        // 위임
        strategy.call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("result time={}", resultTime);
    }
}
