package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.helloTrace.HelloTraceV2;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace trace;
    public void save(String itemId) {
        TraceStatus status = trace.begin("OrderRepository.save()");
        try {
            if (itemId.equals("ex")) {
                throw new IllegalStateException ("예외 발생");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
        // 저장 로직

    }
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
