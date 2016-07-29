package com.abc.app.memberapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 2016-07-27.
 */
public interface MemberService {
    public void regist(MemberBean mem);
    public MemberBean findById(String findID);
    public void update(MemberBean stu2);
    public void delete(MemberBean member);
    public boolean login(MemberBean member);
    public void logout(MemberBean member);
    public int count();
    public ArrayList<MemberBean> list();
    public MemberBean show();
    public List<?> findBy(String keyword);
}