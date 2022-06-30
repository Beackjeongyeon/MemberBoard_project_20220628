package com.memberboard.project.service;

import com.memberboard.project.dto.MemberDTO;
import com.memberboard.project.entity.MemberEntity;
import com.memberboard.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signup(MemberDTO memberDTO) throws IOException {
        MultipartFile memberProfile = memberDTO.getMemberProfile();
        String memberProfileName = memberProfile.getOriginalFilename();
        memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
        String savePath = "D:\\springboot_img\\" + memberProfileName;
        if (!memberProfile.isEmpty()) {
            memberProfile.transferTo(new File(savePath));
            memberDTO.setMemberProfileName(memberProfileName);
            Long saveId = memberRepository.save(MemberEntity.tosaveEntity(memberDTO)).getId();
            return saveId;
        } else {
            return null;
        }
    }

    public String idCheck(String id) {
        Optional<MemberEntity> member = memberRepository.findByMemberId(id);
        if (member.isPresent()) {
            return "no";
        } else {
            return "ok";
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        MemberEntity member = loginCheck(memberDTO.getMemberId());
        if(member.getMemberPassword().equals(memberDTO.getMemberPassword())){
            return MemberDTO.toMemberDTO(member);
        }else{
            return null;
        }

    }

    public MemberEntity loginCheck(String memberId) {
        Optional<MemberEntity> member = memberRepository.findByMemberId(memberId);
        if(member.isPresent()){
            return member.get();
        }else {
            return null;
        }
    }
}