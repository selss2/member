package com.abc.app.memberapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb2016 on 2016-07-27.
 */
public class MemberDAO extends SQLiteOpenHelper {

    public MemberDAO(Context context) {
        super(context, "", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int insert(MemberBean mem) {
        int result = 0;
        String sql = "insert into member(id,pw,name,reg_date,ssn,email,profile_img,phone)"
                + "values(?,?,?,?,?,?,?,?)";
        return result;
    }

    public int update(MemberBean mem) {
        String sql = "update member"
                + " set pw = ? , email = ?"
                + " where id = ?";
        int result = 0;

        return result;

    }

    public int exeUpdate(String sql) {
        int result = 0;

        return result;


    }

    // list
    public List<MemberBean> list() {
        String sql = "select * from member";
        List<MemberBean> list = new ArrayList<MemberBean>();

        return list;
    }

    // findByPK
    public MemberBean findById(String pk) {
        String sql = "select * from member where id = ?";
        MemberBean temp = null;

        return temp;
    }

    // findByNotPK
    public List<MemberBean> findByName(String name) {
        String sql = "select * from member where name =?";
        List<MemberBean> list = new ArrayList<MemberBean>();
        return list;
    }

    // count
    public int count() {
        String sql = "select count(*) as count from member";
        int count = 0;

        return count;
    }

    public int delete(MemberBean member) {
        String sql = "delete from member where id=? and pw = ?";
        int result = 0;

        return result;
    }

    public boolean login(MemberBean param) {
        boolean loginOk = false;
        if (param.getId() != null
                && param.getPw() != null
                && this.existId(param.getId())) {
            MemberBean member = this.findById(param.getId());
            if (member.getPw().equals(param.getPw())) {
                loginOk = true;
            }
        }
        System.out.println("LOGIN_OK ?" + loginOk);
        return loginOk;
    }

    public boolean existId(String id) {
        boolean existOK = false;
        String sql = "select count(*) as count from member where id = ?";
        int result = 0;

        return existOK;
    }


}

