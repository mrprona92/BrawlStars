package sms.sft.com.brawlstardemo;


import java.util.ArrayList;
import java.util.List;

import sms.sft.com.brawlstardemo.dao.CreateTableDao;
import sms.sft.com.brawlstardemo.dao.HeroDao;
import sms.sft.com.brawlstardemo.service.HeroServiceImpl;

/**
 * User: ABadretdinov
 * Date: 02.04.14
 * Time: 10:51
 */
public class BeanContainer implements InitializingBean {
    private static final Object MONITOR = new Object();
    private static BeanContainer instance = null;


    private List<CreateTableDao> allDaos;
    private HeroDao heroDao;
    private HeroServiceImpl heroService;

    private LocalUpdateService localUpdateService;


    public BeanContainer() {
        allDaos = new ArrayList<>();
        heroDao = new HeroDao();
        allDaos.add(heroDao);
        heroService = new HeroServiceImpl();
        localUpdateService = new LocalUpdateService();
    }

    public HeroDao getHeroDao() {
        return heroDao;
    }

    public static BeanContainer getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (MONITOR) {
            if (instance == null) {
                instance = new BeanContainer();
            }
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void initialize() {
        heroService.initialize();
    }

    public HeroServiceImpl getHeroService() {
        return heroService;
    }

    public LocalUpdateService getLocalUpdateService() {
        return localUpdateService;
    }


    public List<CreateTableDao> getAllDaos() {
        return allDaos;
    }

}
