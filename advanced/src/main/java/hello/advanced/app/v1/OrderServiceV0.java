package hello.advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
    private final OrderRepositoryV0 orderRepository;

    public void orderItem(String orderId) {
        orderRepository.save(orderId);
    }
}
