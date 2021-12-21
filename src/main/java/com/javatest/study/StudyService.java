package com.javatest.study;

import com.javatest.domain.Member;
import com.javatest.domain.Study;
import com.javatest.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'")));
        Study new_study = repository.save(study);
        memberService.notify(new_study);
        memberService.notify(member.get());
        return new_study;
    }

}
