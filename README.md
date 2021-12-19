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

