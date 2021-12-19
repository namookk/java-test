 JAVA TEST코드 작성
 ====

+ Junit5 를 이용한 테스트 코드 작성방법 공부

### 기본 Annotation
```java
@Test
@BeforeAll
@BeforeEach
@AfterAll
@AfterEach
@Disabled
```

### Assertion

| 기대한 값과 실제 값이 같은지 확인 | assertEquals(기대한 값, 실제 값)|
|---|---|
| 값이 null이 아닌지 확인 | assertNotNull(값) |
| 조건이 true인지 확인 | assertTrue(조건) |
| 모든 확인 구문을 확인 | assertAll(확인 구문) |
| 예외 발생 확인 | assertThrows(예외 타입, 예외 여부 ) |
| 특정 시간 안에 실행이 완료되는지 확인 | assertTimeout(시간, 실행구문 )|

### TEST 조건 주기 

`assumeThat` 사용  
`assumingThat` 사용  
`@EnabledOnOs`, `@EnabledOnJre`, `@EnabledIfEnvironmentVariable` 사용  

### Tags 활용

`@Tag` 를 활용하여 특정 태그가 있는 테스트케이스만 실행가능

### Annotaion 만들어서 사용
src/test/java/com/javatest/FastTest.java
src/test/java/com/javatest/SlowTest.java

처럼 만들 수 있다.

### 반복 테스트 방법

`@RepeatedTest`, `@ParameterizedTest` 사용

### ParameterizedTest 사용시 인자값을 받는 법 

`@ValueSource`, `@CsvSource` 사용

인자값을 특정 객체로 받아오고 싶은 경우엔 `@ConvertWith`, `@AggregateWith` 사용 


