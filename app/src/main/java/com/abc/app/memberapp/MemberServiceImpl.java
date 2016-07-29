package com.abc.app.memberapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 2016-07-27.
 */
public class MemberServiceImpl implements MemberService{

    MemberDAO dao;
    MemberBean session;
    public MemberServiceImpl(Context context) {

        dao = new MemberDAO(context);
    }

    @Override
    public void regist(MemberBean mem) {
        dao.insert(mem);
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
    public boolean login(MemberBean member) {
        return dao.login(member);
    }


    @Override
    public int count() {
        return dao.count();
    }


    @Override
    public MemberBean findById(String findID) {
        return dao.findById(findID);
    }


    @Override
    public ArrayList<MemberBean> list() {

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