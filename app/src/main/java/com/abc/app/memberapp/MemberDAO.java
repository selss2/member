package com.abc.app.memberapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 2016-07-27.
 */
public class MemberDAO extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "member";
    public static final String ID = "id";
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String SSN = "ssn";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PROFILE = "profile";

    public MemberDAO(Context context) {
        super(context, "hanbitdb", null, 1);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists "
                + TABLE_NAME
                + " ( "
                + ID + " text primary key, "
                + PW + " text, "
                + NAME + " text, "
                + SSN + " text, "
                + EMAIL + " text, "
                + PROFILE + " text, "
                + PHONE + " text );";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "drop table if exists "+TABLE_NAME;
        db.execSQL(sql);
        this.onCreate(db);
    }

    public int insert(MemberBean mem) {
        int result = 0;
        String sql = "insert into "+TABLE_NAME
                +String.format("(%s,%s,%s,%s,%s,%s,%s) ",ID,PW,NAME,SSN,EMAIL,PROFILE,PHONE)
                +String.format(" values('%s','%s','%s','%s','%s','%s','%s');"
                ,mem.getId()
                ,mem.getPw()
                ,mem.getName()
                ,mem.getSsn()
                ,mem.getEmail()
                ,mem.getProfile()
                ,mem.getPhone()
        );
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        return result;
    }

    public int update(MemberBean mem) {
        String sql = "update member"
                + " set pw = ? , email = ?"
                + " where id = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        int result = 0;
        return result;

    }

    // list
    public ArrayList<MemberBean> list() {
        String sql = "select "
                +String.format("%s,%s,%s,%s,%s,%s,%s ",ID,PW,NAME,SSN,EMAIL,PROFILE,PHONE)
                +" from "+TABLE_NAME+";";
        ArrayList<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery(sql,null);
        if(cursor != null){
            Log.d("DAO LIST","목록조회 성공 !!");
            cursor.moveToFirst();
        }
        do{
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setSsn(cursor.getString(3));
            temp.setEmail(cursor.getString(4));
            temp.setProfile(cursor.getString(5));
            temp.setPhone(cursor.getString(6));
            list.add(temp);
        }while(cursor.moveToNext());
        return list;
    }

    // findByPK
    public MemberBean findById(String pk) {

        String sql = "select "
                +String.format("%s,%s,%s,%s,%s,%s,%s ",ID,PW,NAME,SSN,EMAIL,PROFILE,PHONE)
                +String.format(" from "+TABLE_NAME+" where id = '%s' ;",pk);
        MemberBean temp = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            Log.d("DAO FIND_BY_ID ","ID 조회 성공 !!");
            if(cursor.moveToFirst()){
                temp = new MemberBean();
                temp.setId(cursor.getString(0));
                temp.setPw(cursor.getString(1));
                temp.setName(cursor.getString(2));
                temp.setSsn(cursor.getString(3));
                temp.setEmail(cursor.getString(4));
                temp.setProfile(cursor.getString(5));
                temp.setPhone(cursor.getString(6));

            }
        }


        return temp;
    }

    // findByNotPK
    public List<MemberBean> findByName(String name) {
        String sql = "select * from member where name =?";
        List<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(sql,null);
        return list;
    }

    // count
    public int count() {
        String sql = "select count(*) as count from member";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        int count = 0;
        return count;
    }

    public int delete(MemberBean member) {
        String sql = "delete from member where id=? and pw = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        int result = 0;
        return result;
    }

    public boolean login(MemberBean param) {
        boolean loginOk = false;
        String sql = "select "+PW+" from "+TABLE_NAME
                +String.format(" where id = '%s';",param.getId());

        //&& this.existId(param.getId())
        SQLiteDatabase db = this.getReadableDatabase();
        String pw="";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            pw = cursor.getString(0);
        }
        if(pw.equals("")){
            Log.d("DAO 로그인 결과 :","일치하는 ID가 없음");
            loginOk = false;
        }else{
            Log.d("DAO ID :",param.getId());
            Log.d("DAO PW :",pw);
            loginOk = (pw.equals(param.getPw()))?true:false;
        }

        System.out.println("LOGIN_OK ?" + loginOk);
        return loginOk;
    }

    public boolean existId(String id) {
        boolean existOK = false;
        String sql = "select count(*) as count from member where id = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        int result = 0;
        return existOK;
    }


}