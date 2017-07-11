package sms.sft.com.brawlstardemo.infoparser;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import sms.sft.com.appbase.util.FileUtils;
import sms.sft.com.appbase.util.service.TaskRequest;
import sms.sft.com.brawlstardemo.BeanContainer;
import sms.sft.com.brawlstardemo.dao.Hero;
import sms.sft.com.brawlstardemo.responses.HeroInfo;
import sms.sft.com.brawlstardemo.responses.Role5Info;
import sms.sft.com.brawlstardemo.service.HeroService;
import sms.sft.com.brawlstardemo.util.Constants;

/**
 * Created by ABadretdinov
 * 23.06.2015
 * 16:07
 */
public class ResponseLoadRequest extends TaskRequest<String> {
    HeroService heroService = BeanContainer.getInstance().getHeroService();
    private Context mContext;

    public ResponseLoadRequest(Context context) {
        super(String.class);
        this.mContext = context;
    }

    @Override
    public String loadData() throws Exception {
        List<Hero> heroes = heroService.getAllHeroes(mContext);
        for (Hero hero : heroes) {
            List<HeroInfo> responses = loadHeroResponses(hero);
            FileUtils.saveJsonFile(Environment.getExternalStorageDirectory().getPath() + "/brawlstars/" + hero.getDotaId() + "/responses.json", responses);
            Log.d("BINH", Environment.getExternalStorageDirectory().getPath() + "/dota/" + hero.getDotaId() + "/responses.json" + "saved");
        }
        return "";
    }

    /*do not use /ru, otherwise you won't have items*/
    private List<HeroInfo> loadHeroResponses(Hero hero) throws Exception {
        String heroName = hero.getLocalizedName().replace("'", "%27").replace(' ', '_');

        String url = MessageFormat.format(Constants.Heroes.DOTA2_WIKI_RESPONSES_URL, heroName);

       /* if (hero.getId() == 114) {
            url = "http://dota2.gamepedia.com/Monkey_King/Responses";
        }
        System.out.println("hero url: " + url);*/
        Document doc = Jsoup.connect(url).get();

        List<HeroInfo> heroResponsesList = new ArrayList<>();

        HeroInfo heroInfo = new HeroInfo();

        heroInfo.setHero(hero);
        //Role 5 info
        Role5Info role5Info = new Role5Info();
        role5Info.setType(doc.select(".overview>p").get(0).textNodes().get(0).toString());
        role5Info.setRole(doc.select(".overview>p").get(1).textNodes().get(0).toString());
        role5Info.setSpeed(doc.select(".overview>p").get(2).textNodes().get(0).toString());
        role5Info.setHitpoints(doc.select(".overview>p").get(3).textNodes().get(0).toString());
        role5Info.setTier(doc.select(".overview>p").get(4).textNodes().get(0).toString());
        heroInfo.setRole5Info(role5Info);

        //Progressbar
        List<String> progressList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String progressValue = "";
            progressValue = method(doc.select("div.rating-progress").get(i).select("span").attr("style").split(":")[1]);
            progressList.add(progressValue);
            Log.d("BINH", "loadHeroResponses() called with: hero = [" + progressValue.substring(0, progressValue.length() - 1) + "]");
        }
        heroInfo.setProgress8Info(progressList);


        //modeRanking

        //Priority
        List<String> priorityList = new ArrayList<>();
        Elements elementsPriority = doc.select("ul.priority>li");
        for (Element element : elementsPriority) {
            priorityList.add(element.text());
        }
        heroInfo.setPriority(priorityList);

        //Favorable

        Elements listCharacter = doc.select("ul.character-list");
        int index = 0;
        for (Element element : listCharacter) {
            if (index == 0) {
                List<String> favorableList = new ArrayList<>();
                Elements elementsFavorable = element.getAllElements().select("div.title");
                for (Element elementFavor : elementsFavorable) {
                    favorableList.add(elementFavor.text());
                }
                heroInfo.setFavorableMatchups(favorableList);
            } else if (index == 1) {
                //difficultMatchups;
                List<String> difficultLists = new ArrayList<>();
                Elements elementsDifficults = element.getAllElements().select("div.title");
                for (Element elementDifficults : elementsDifficults) {
                    difficultLists.add(elementDifficults.text());
                }
                heroInfo.setDifficultMatchups(difficultLists);
            } else {
                //hardCounters;
                List<String> hardCountersLists = new ArrayList<>();
                Elements elementsHardCounters = element.getAllElements().select("div.title");
                for (Element elementCounters : elementsHardCounters) {
                    hardCountersLists.add(elementCounters.text());
                }
                heroInfo.setHardCounters(hardCountersLists);
            }
            index++;
        }


        //Strength
        List<String> strengthList = new ArrayList<>();
        Elements elementsStrength = doc.select("ul.strengths>li");
        for (Element element : elementsStrength) {
            strengthList.add(element.text());
        }
        heroInfo.setStrengths(strengthList);

        //Weaknesses
        List<String> weaknesssList = new ArrayList<>();
        Elements elementsWeaknesses = doc.select("ul.weaknesses>li");
        for (Element element : elementsWeaknesses) {
            weaknesssList.add(element.text());
        }
        heroInfo.setWeaknesses(weaknesssList);


        heroResponsesList.add(heroInfo);

        return heroResponsesList;
    }


    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

}
