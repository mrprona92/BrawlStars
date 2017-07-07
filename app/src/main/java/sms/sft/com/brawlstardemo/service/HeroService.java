package sms.sft.com.brawlstardemo.service;

import android.content.Context;



import java.util.List;

import sms.sft.com.brawlstardemo.InitializingBean;
import sms.sft.com.brawlstardemo.dao.Hero;

/**
 * Created by ABadretdinov
 * 25.12.2014
 * 14:35
 */
public interface HeroService extends InitializingBean {

    List<Hero> getAllHeroes(Context context);

    Hero.List getFilteredHeroes(Context context, String filter);

    List<Hero> getHeroesByName(Context context, String name);

    Hero getExactHeroByName(Context context, String name);

    Hero getHeroById(Context context, long id);

    Hero getHeroWithStatsById(Context context, long id);

    void saveHero(Context context, Hero hero);

    String getAbilityPath(Context context, long id);

}
