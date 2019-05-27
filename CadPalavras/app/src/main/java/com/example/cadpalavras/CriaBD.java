package com.example.cadpalavras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper {

    private static final String NOME_BD = "MinhaBib";
    private static final int VERSAO_BD = 1;

    public CriaBD(Context ctx){
        super(ctx, NOME_BD,null,VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table tblivro(_id integer primary key autoincrement," +
                " titulo text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table tblivro;");
        onCreate(bd);

    }
}