package sms.sft.com.brawlstardemo.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 19.06.2015
 * 16:03
 */


public class Role5Info implements Serializable {
    private String type;
    private String role;
    private String speed;
    private String hitpoints;
    private String tier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(String hitpoints) {
        this.hitpoints = hitpoints;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Role5Info(String type, String role, String speed, String hitpoints, String tier) {
        this.type = type;
        this.role = role;
        this.speed = speed;
        this.hitpoints = hitpoints;
        this.tier = tier;
    }

    public Role5Info() {
    }
}
