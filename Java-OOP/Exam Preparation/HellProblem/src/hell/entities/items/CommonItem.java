package hell.entities.items;

public class CommonItem extends AbstractItem {
    public CommonItem(String name, Integer strengthBonus, Integer agilityBonus,
                      Integer intelligenceBonus, Integer hitPointsBonus, Integer damageBonus) {
        super(name, strengthBonus, agilityBonus,
                intelligenceBonus, hitPointsBonus, damageBonus);
    }
}
