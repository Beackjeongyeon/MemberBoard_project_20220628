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

    public Long signup(MemberDTO memberDTO)throws IOException {
        MultipartFile memberProfile = memberDTO.getMemberProfile();
        String memberProfileName = memberProfile.getOriginalFilename();
        memberProfileName = System.currentTimeMillis()+ "_" + memberProfileName;
        String savePath = "D:\\springboot_img\\" + memberProfileName;
        if(!memberProfile.isEmpty()){
            memberProfile.transferTo(new File(savePath));
        }
        memberDTO.setMemberProfileName(memberProfileName);
        // tosaveEntity 에도 보내줘야함
        Optional<MemberEntity> optionalMemberEntity =
                memberRepository.findByMemberPassword(memberDTO.getMemberPassword());
                if(optionalMemberEntity.isPresent()){
                    MemberEntity memberEntity = optionalMemberEntity.get();
                    Long saveId = memberRepository.save(MemberEntity.tosaveEntity(memberDTO, memberEntity).getId());
                    return saveId;
                }else{
                    return null;
                }

    }


}
