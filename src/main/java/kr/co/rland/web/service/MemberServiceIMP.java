package kr.co.rland.web.service;

import kr.co.rland.web.entity.Member;
import kr.co.rland.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceIMP implements MemberService{
    @Autowired
    MemberRepository repository;

    @Override
    public boolean validdate(String username, String password) {


        Member member = repository.findByUserName(username);

        if(member == null)
            return false;
        if(!member.getPwd().equals(password))
            return false;
        return true;
    }
}
