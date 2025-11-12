package hello.advanced.trace.logtrace;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.TraceStatus;

class FieldLogTraceTest {

	FieldLogTrace trace = new FieldLogTrace();

	@Test
	void start_end_level3() {
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.begin("hello2");
		TraceStatus status3 = trace.begin("hello3");

		trace.end(status3);
		trace.end(status2);
		trace.end(status1);
	}

	@Test
	void start_exception_level3() {
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.begin("hello2");
		TraceStatus status3 = trace.begin("hello3");

		trace.exception(status3, new IllegalStateException());
		trace.exception(status2, new IllegalStateException());
		trace.exception(status1, new IllegalStateException());
	}

}