package sms.sft.com.brawlstardemo.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import sms.sft.com.brawlstardemo.BeanContainer;
import sms.sft.com.brawlstardemo.dao.DatabaseManager;
import sms.sft.com.brawlstardemo.dao.Hero;
import sms.sft.com.brawlstardemo.dao.HeroDao;

/**
 * Created by ABadretdinov
 * 25.12.2014
 * 14:35
 */
public class HeroServiceImpl implements HeroService {
    private HeroDao heroDao;


    @Override
    public void initialize() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        heroDao = beanContainer.getHeroDao();

    }

    @Override
    public List<Hero> getAllHeroes(Context context) {
        DatabaseManager manager = DatabaseManager.getInstance(context);
        SQLiteDatabase database = manager.openDatabase();
        try {
            return heroDao.getAllEntities(database);
        } finally {
            manager.closeDatabase();
        }
    }

    @Override
    public Hero.List getFilteredHeroes(Context context, String filter) {
        return null;
    }

    @Override
    public List<Hero> getHeroesByName(Context context, String name) {
        return null;
    }

    @Override
    public Hero getExactHeroByName(Context context, String name) {
        return null;
    }

    @Override
    public Hero getHeroById(Context context, long id) {
        return null;
    }

    @Override
    public Hero getHeroWithStatsById(Context context, long id) {
        return null;
    }

    @Override
    public void saveHero(Context context, Hero hero) {

    }

    @Override
    public String getAbilityPath(Context context, long id) {
        return null;
    }

}
