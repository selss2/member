package com.abc.app.memberapp;

import java.util.List;

/**
 * Created by hb2016 on 2016-07-27.
 */
public interface MemberService {
    public String regist(MemberBean mem);
    public MemberBean findById(String findID);
    public void update(MemberBean stu2);
    public void delete(MemberBean member);
    public void logout(MemberBean member);
    public int count();
    public List<?> list();
    public MemberBean show();
    public List<?> findBy(String keyword);

}
