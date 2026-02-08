package edu.wctc;

/**
 * A room containing a weapon that the player can loot.
 * The weapon is critical for succeeding in the final boss encounter.
 * Implements Lootable interface.
 */
public class WeaponChamber extends Room implements Lootable {
    private boolean weaponLooted = false;

    /**
     * Constructs a WeaponChamber with the given name.
     * @param name the name of the room
     */
    public WeaponChamber(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        String grid = layoutGrid != null ? renderGrid() : "[Map not loaded]";
        return "═══════════════════════════════════════\n" +
               "      WEAPON CHAMBER - Armory\n" +
               "═══════════════════════════════════════\n\n" +
               "A dusty armory with scattered weapons on stone pedestals.\n" +
               "The air smells of rust and ancient metal. Sunlight filters\n" +
               "through cracks in the stone walls, illuminating countless\n" +
               "blades and helms covered in centuries of dust.\n\n" +
               "On a central pedestal, you notice a particularly well-preserved\n" +
               "longsword. Its blade still gleams faintly, and its grip looks\n" +
               "firm and reassuring.\n\n" +
               "Room Layout:\n" +
               grid + "\n" +
               "═══════════════════════════════════════\n" +
               "Legend: w=wall, f=floor, W=weapon, p=player, d=door, @=you";
    }

    @Override
    public String loot(Player player) {
        if (!weaponLooted) {
            player.addToInventory("Iron Longsword");
            player.addToScore(50);
            weaponLooted = true;
            return "\n★ You picked up the Iron Longsword!\n" +
                   "  The weapon is reassuringly heavy in your hands.\n" +
                   "  You gain 50 points.";
        }
        return "\n✗ You've already taken the weapon from this chamber.";
    }
}

