package hello.advanced.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

	private final OrderServiceV1 orderServiceV1;
	private final HelloTraceV1 trace;

	/*
	* 아이템 주문
	*/
	@GetMapping("/v1/request")
	public String request(String itemId) {

		TraceStatus status = null;

		try {
			status = trace.begin("OrderController.request()");	// 트레이스 시작
			orderServiceV1.orderItem(itemId);
			trace.end(status);	// 트레이스 종료
			return "ok";
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}

}
