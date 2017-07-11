package sms.sft.com.brawlstardemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import sms.sft.com.brawlstardemo.BeanContainer;

/**
 * User: ABadretdinov
 * Date: 29.08.13
 * Time: 11:07
 */
public class Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "brawlassistant.db";
    public static final int DATABASE_VERSION = 5;
    private Context mContext;

    /*public static final String CREATE_ITEMS_FROM="create table if not exists "+
            " items_from ( _id integer PRIMARY KEY AUTOINCREMENT, item_id integer not null, need_id integer not null);";*/

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        List<CreateTableDao> allDaos = BeanContainer.getInstance().getAllDaos();
        for (CreateTableDao dao : allDaos) {
            dao.onCreate(db);
        }
        db.execSQL("create table if not exists updated_version(version integer not null);");
        db.execSQL("insert into updated_version (version) values(0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < DATABASE_VERSION) {
            reinitHeroesAndItems(db);
        }
       /* List<CreateTableDao> allDaos = BeanContainer.getInstance().getAllDaos();
        for (CreateTableDao dao : allDaos) {
            dao.onUpgrade(db, oldVersion, newVersion);
        }*/
        onCreate(db);
    }

    private void reinitHeroesAndItems(SQLiteDatabase db) {
        db.execSQL("drop table " + HeroDao.TABLE_NAME);
        db.execSQL("update updated_version set version=0;");
    }

}