package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Member;

public interface MemberMapper {
    Member getMemberByTelephone(String telephone);

    void addMember(Member member);

    long getCountByMonth(String month);
}
