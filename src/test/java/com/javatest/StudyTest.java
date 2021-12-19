package com.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//DisplayNameGenerator.ReplaceUnderscores ( _ 를 공백 )
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
//    @EnabledOnOs({OS.MAC, OS.LINUX})
//    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_12})
    @EnabledIfEnvironmentVariable(named="TEST_ENV", matches = "namookk")
    void create_new_study() {
//
//        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
//
//        assumingThat("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
//            System.out.println("LOCAL");
//        });

        IllegalArgumentException exeption = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exeption.getMessage();
        Study study = new Study(10);

        assertEquals(message, "limit은 0보다 커야함");

        //assertj
//        assertThat(study.getLimit()).isGreaterThan(0);

        //기대하는 시간초가 끝나면 바로 끝남
//        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });
        //Preemptively Tread를 사용하는 구문은 사용하면 위험함

//        assertAll( // 모두 실행함
//                () -> assertNotNull(study),
//                // 기대하는 값, 실제 값, 메세지
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
//                // 메세지의 문자열 연산이 많으면 람다식으로 사용
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
//                        @Override
//                        public String get() {
//                            return "스터디를 처음 만들면 상태값이 DRAFT여야 한다.";
//                        }
//                    }),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 함")
//        );

        System.out.println("create");
    }

    @Test
    @DisplayName("스터디 다시 만들기")
    @EnabledIfEnvironmentVariable(named="TEST_ENV", matches = "LOCAL")
    void create_new_study_again() {
        System.out.println("create2");
    }

    @BeforeAll
    static void beforeAlll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}