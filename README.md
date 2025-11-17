## 쓰레드 로컬

### 1. 동시성 문제

- 싱글톤은 객체의 인스턴스가 애플리케이션에 하나만 존재한다는 의미
- 하나만 있는 인스턴스에 여러 쓰레드가 접근하면 문제 발생

___

### 2. 동시성 문제 발견

**트랜잭션 관점에서 확인**
- DB 로그 체크
  - deadlock detected
  - lock wait timeout
- 두 트랜잭션이 동일한 데이터를 업데이트 하려고 하는 시점에 발생하는 트레이스 확인

**쓰레드 관점에서 확인**
- jstack 과 같은 실행 중인 쓰레드 상태를 덤프 떠서, 락 대기나 데드락 여부를 확인

___

### 3. ThreadLocal 이란 무엇인가

**해당 쓰레드만 접근 가능한 특별한 저장소**

- 은행 창구 직원과 같은 느낌
- 멀티쓰레드 환경에서도 구분이 됨

```java
private ThreadLocal<String> nameStore  = new ThreadLocal<>();
```

___

### 4. ThreadLocal 의 자료구조는? 그 이유는?

- 자료구조
  - 해시 테이블 기반

- 이유
  - 빠른 접근과 Key 충돌 최소화

___

### 5. ThreadLocal의 주의점

**상황:**
1. ThreadLocal을 remove 안 하고 종료
2. 쓰레드는 쓰레드풀로 돌아감
3. 다른 사용자가 요청하여 아까 돌아간 쓰레드가 사용됨
4. 기존에 데이터가 존재하는 보관소를 사용하게 됨

무조건 remove() 를 해주어야 한다.

___

## 템플릿 메서드

### 1. 왜 템플릿 메서드를 사용하는가?

**변하지 않는 로직을 보호하고, 변하는 부분만 열어두기 위해**

- 핵심 기능은 변하고
- 부가 기능은 변하지 않는다.

_어떤 효과가 존재하는가?_
- 중복을 줄이고 일관성을 유지할 수 있음
- 일관된 프로세스 보장
- OCP 준수

___

### 2. 어떻게 템플릿 메서드를 적용하는가?

1. 추상 템플릿을 생성한다.
2. 변하지 않는 로직을 넣어둔다.
3. 자식 클래스에서 상속과 오버라이딩 하여 실행시킨다.

- ex) 추상 템플릿
```java
@Sl4fj
public abstract class AbstractTemplate {
    
    public void execute() {
        long startTime = System.currentTimeMillis();
		// 비즈니스 로직 실행
        call(); // 상속
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
	protected abstract void call();
}
```













