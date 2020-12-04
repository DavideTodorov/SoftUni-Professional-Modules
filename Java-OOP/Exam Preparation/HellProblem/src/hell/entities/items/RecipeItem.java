package hell.entities.items;

import hell.interfaces.Recipe;

import java.util.List;

public class RecipeItem extends CommonItem implements Recipe {
    private List<String> requiredItems;

    public RecipeItem(String name, Integer strengthBonus, Integer agilityBonus, Integer intelligenceBonus,
                      Integer hitPointsBonus, Integer damageBonus, List<String> requiredItems) {
        super(name, strengthBonus, agilityBonus,
                intelligenceBonus, hitPointsBonus, damageBonus);
        this.requiredItems = requiredItems;
    }

    @Override
    public List<String> getRequiredItems() {
        return this.requiredItems;
    }
}
