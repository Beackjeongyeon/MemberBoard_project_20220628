package com.memberboard.project.entity;

import com.memberboard.project.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@Table(name= "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 30)
    private String memberId;

    @Column(length = 30)
    private String memberPassword;

    @Column(length = 10)
    private String memberName;

    @Column(length = 50)
    private String memberEmail;

    @Column(length = 20)
    private String memberMobile;

    @Column
    private String memberProfileName;

    public static MemberEntity tosaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberProfileName(memberDTO.getMemberProfileName());
        return memberEntity;

    }

}