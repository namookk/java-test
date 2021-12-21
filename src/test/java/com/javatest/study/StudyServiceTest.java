package com.javatest.study;

import com.javatest.domain.Member;
import com.javatest.domain.Study;
import com.javatest.member.MemberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Mock 어노테이션 사용시 있어야함
class StudyServiceTest {

//    @Mock MemberService memberService;
//
//    @Mock StudyRepository studyRepository;

    @Test
    @Disabled
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
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
    void stubExample(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
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