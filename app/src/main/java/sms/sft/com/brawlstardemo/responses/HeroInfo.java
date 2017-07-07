package sms.sft.com.brawlstardemo.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 19.06.2015
 * 16:03
 */


public class HeroInfo implements Serializable {
    private String brawlID;
    private String id;
    private String img;
    private String dname;
    private String lore;


    private Role5Info role5Info;

    public List<String> getProgress8Info() {
        return progress8Info;
    }

    public void setProgress8Info(List<String> progress8Info) {
        this.progress8Info = progress8Info;
    }

    private List<String> progress8Info;
    private List<String> modeRanking;
    private List<String> priority;
    private List<String> favorableMatchups;
    private List<String> difficultMatchups;
    private List<String> hardCounters;
    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> tips;




    public String getBrawlID() {
        return brawlID;
    }

    public void setBrawlID(String brawlID) {
        this.brawlID = brawlID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getTips() {
        return tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }

    public List<String> getPriority() {
        return priority;
    }

    public void setPriority(List<String> priority) {
        this.priority = priority;
    }

    public List<String> getFavorableMatchups() {
        return favorableMatchups;
    }

    public void setFavorableMatchups(List<String> favorableMatchups) {
        this.favorableMatchups = favorableMatchups;
    }

    public List<String> getDifficultMatchups() {
        return difficultMatchups;
    }

    public void setDifficultMatchups(List<String> difficultMatchups) {
        this.difficultMatchups = difficultMatchups;
    }

    public List<String> getHardCounters() {
        return hardCounters;
    }

    public void setHardCounters(List<String> hardCounters) {
        this.hardCounters = hardCounters;
    }

    public List<String> getModeRanking() {
        return modeRanking;
    }

    public void setModeRanking(List<String> modeRanking) {
        this.modeRanking = modeRanking;
    }

    public Role5Info getRole5Info() {
        return role5Info;
    }

    public void setRole5Info(Role5Info role5Info) {
        this.role5Info = role5Info;
    }

    public HeroInfo() {
    }
}
