package hell.entities.items;

import hell.interfaces.Item;

public abstract class AbstractItem implements Item {
    private String name;
    private Integer strengthBonus;
    private Integer agilityBonus;
    private Integer intelligenceBonus;
    private Integer hitPointsBonus;
    private Integer damageBonus;

    public AbstractItem(String name, Integer strengthBonus, Integer agilityBonus,
                        Integer intelligenceBonus, Integer hitPointsBonus, Integer damageBonus) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.agilityBonus = agilityBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.hitPointsBonus = hitPointsBonus;
        this.damageBonus = damageBonus;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrengthBonus() {
        return this.strengthBonus;
    }

    @Override
    public int getAgilityBonus() {
        return this.agilityBonus;
    }

    @Override
    public int getIntelligenceBonus() {
        return this.intelligenceBonus;
    }

    @Override
    public int getHitPointsBonus() {
        return this.hitPointsBonus;
    }

    @Override
    public int getDamageBonus() {
        return this.damageBonus;
    }
}
