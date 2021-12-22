package com.javatest.study;

import com.javatest.domain.Member;
import com.javatest.domain.Study;
import com.javatest.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeList;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Mock 어노테이션 사용시 있어야함
@ActiveProfiles("test")
@Testcontainers
@Slf4j
class StudyServiceTest {

    @Mock MemberService memberService;
    @Autowired StudyRepository studyRepository;

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
            .withDatabaseName("studytest");

//    @Container
//    static GenericContainer postgreSQLContainer = new GenericContainer("postgres")
//            .withExposedPorts(5432) // 도커 안의 포트
//            .withEnv("POSTGRES_DB", "studytest");


    @BeforeEach
    void beforeEach(){
        studyRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {
        //Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
        //postgreSQLContainer.followOutput(logConsumer);
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @Test
    @Disabled
    void createStudyService() {
//        MemberService memberService = mock(MemberService.class);
//        StudyRepository studyRepository = mock(StudyRepository.class);
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "java");

        Member member = new Member();
        member.setId(1L);
        member.setEmail("papakang22@naver.com");

        //any -> argument matchers
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty())
        ;

        Optional<Member> byId = memberService.findById(1L);

        assertEquals("papakang22@naver.com", byId.get().getEmail());
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));

//        Optional<Member> memop = memberService.findById(1L);
//        assertEquals("papakang22@naver.com", memberService.findById(1L).get().getEmail());
//        assertEquals("papakang22@naver.com", memberService.findById(2L).get().getEmail());

//        doThrow(new IllegalArgumentException()).when(memberService).validate(1l);

//        studyService.createNewStudy(1L, study);

//        assertNotNull(studyService);
//        assertThrows(IllegalArgumentException.class, () -> {
//            memberService.validate(1L);
//        });
    }



    @Test
    @DisplayName("Mockito 테스트")
    void stubExample() {
        Study study = new Study(10, "테스트");
        Member member = new Member();
        member.setId(1L);
        member.setEmail("papakang22@naver.com");

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//        when(studyRepository.save(study)).thenReturn(study);

        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createNewStudy(1L, study);

        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());

        //호출된지 확인
//        verify(memberService, times(1)).notify(study);
        // ->
        then(memberService).should(times(1)).notify(study);

//        verifyNoInteractions(memberService); // 이후 memberService에서 호출되는게 없어야 함
        // ->
//        then(memberService).shouldHaveNoInteractions();

        verify(memberService, times(1)).notify(member);
        //호출안된지 확인
        verify(memberService, never()).validate(any());

        //순서 확인
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study); // 먼저
        inOrder.verify(memberService).notify(member); // 그다음

    }








}