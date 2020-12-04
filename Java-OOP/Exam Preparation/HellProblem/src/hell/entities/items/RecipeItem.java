package hell.entities.items;

import hell.interfaces.Recipe;

import java.util.List;

public class RecipeItem extends CommonItem implements Recipe {
    private List<String> requiresItems;

    public RecipeItem(String name, Integer strengthBonus, Integer agilityBonus, Integer intelligenceBonus,
                      Integer hitPointsBonus, Integer damageBonus, List<String> requiresItems) {
        super(name, strengthBonus, agilityBonus,
                intelligenceBonus, hitPointsBonus, damageBonus);
        this.requiresItems = requiresItems;
    }

    @Override
    public List<String> getRequiredItems() {
        return this.requiresItems;
    }
}
