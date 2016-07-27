package com.abc.app.memberapp;

import java.util.List;

/**
 * Created by hb2016 on 2016-07-27.
 */
public class MemberServiceImpl implements MemberService{
    private MemberDAO dao = MemberDAO.getInstance();
    private MemberBean session;

    private static MemberServiceImpl instance = new MemberServiceImpl();

    public static MemberServiceImpl getInstance() {
        return instance;
    }


    private MemberServiceImpl() {
        session = new MemberBean();
    }

    @Override
    public String regist(MemberBean mem) {
        String msg = "";
        MemberBean temp = this.findById(mem.getId());
        if (temp == null) {
            System.out.println(mem.getId()+"가 존재하지 않음,가입 가능한 ID");
            int result = dao.insert(mem);
            if (result==1) {
                msg = "success";
            } else {
                msg = "fail";
            }
        } else {
            System.out.println(mem.getId()+"가 존재함,가입 불가능한 ID");
            msg = "fail";
        }

        return msg;
    }


    @Override
    public void update(MemberBean mem) {
        int result = dao.update(mem);
        if (result == 1) {
            System.out.println("서비스 수정결과 성공");
        }else{
            System.out.println("서비스 수정결과 실패");
        }
    }
    @Override
    public MemberBean show() {
        return session;
    }
    @Override
    public void delete(MemberBean member) {
        dao.delete(member);
    }


    @Override
    public int count() {
        // TODO Auto-generated method stub
        return dao.count();
    }


    @Override
    public MemberBean findById(String findID) {
        return dao.findById(findID);
    }


    @Override
    public List<?> list() {

        return dao.list();
    }
    @Override
    public List<?> findBy(String keyword) {
        return dao.findByName(keyword);
    }
    @Override
    public void logout(MemberBean member) {
        if (member.getId().equals(session.getId())
                && member.getPw().equals(session.getPw())) {
            session = null;
        }

    }
}
