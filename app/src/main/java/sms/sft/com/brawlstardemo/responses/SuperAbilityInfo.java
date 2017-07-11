package sms.sft.com.brawlstardemo.responses;

/**
 * Created by Admin on 7/8/2017.
 */

public class SuperAbilityInfo {

    private String typeAbility;
    private String range;
    private String damage;
    private String attackDeplay;

    public String getTypeAbility() {
        return typeAbility;
    }

    public void setTypeAbility(String typeAbility) {
        this.typeAbility = typeAbility;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getAttackDeplay() {
        return attackDeplay;
    }

    public void setAttackDeplay(String attackDeplay) {
        this.attackDeplay = attackDeplay;
    }

    public SuperAbilityInfo(String typeAbility, String range, String damage, String attackDeplay) {
        this.typeAbility = typeAbility;
        this.range = range;
        this.damage = damage;
        this.attackDeplay = attackDeplay;
    }
}
