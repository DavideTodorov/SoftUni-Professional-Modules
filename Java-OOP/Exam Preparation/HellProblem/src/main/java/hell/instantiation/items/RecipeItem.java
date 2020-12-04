package hell.instantiation.items;

import hell.interfaces.Recipe;

import java.util.List;

public class RecipeItem implements Recipe {
    private String name;
    private Integer strengthBonus;
    private Integer agilityBonus;
    private Integer intelligenceBonus;
    private Integer hitPointsBonus ;
    private Integer damageBonus;
    private List<String> requiredItems;

    public RecipeItem(String name, Integer strengthBonus, Integer agilityBonus,
                      Integer intelligenceBonus, Integer hitPointsBonus,
                      Integer damageBonus, List<String> requiredItems) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.agilityBonus = agilityBonus;
        this.intelligenceBonus = intelligenceBonus;
        this.hitPointsBonus = hitPointsBonus;
        this.damageBonus = damageBonus;
        this.requiredItems = requiredItems;
    }

    @Override
    public List<String> getRequiredItems() {
        return this.requiredItems;
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
