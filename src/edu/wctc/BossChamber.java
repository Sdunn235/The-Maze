package edu.wctc;

import java.util.Random;

/**
 * The final room of the maze containing a powerful creature.
 * The player must exit this room to end the game. Success depends on preparation
 * (having the weapon and consulting the sage for increased success probability).
 * Implements Exitable interface.
 */
public class BossChamber extends Room implements Exitable {
    private SagesChamber sageReference;

    /**
     * Constructs a BossChamber with the given name.
     * @param name the name of the room
     */
    public BossChamber(String name) {
        super(name);
    }

    /**
     * Sets a reference to the sage's chamber for interaction count tracking.
     * @param sage the SagesChamber instance
     */
    public void setSageReference(SagesChamber sage) {
        this.sageReference = sage;
    }

    @Override
    public String getDescription() {
        String grid = layoutGrid != null ? renderGrid() : "[Map not loaded]";
        return "═══════════════════════════════════════\n" +
               "    BOSS CHAMBER - Obsidian Throne\n" +
               "═══════════════════════════════════════\n\n" +
               "A massive obsidian throne room stretches before you.\n" +
               "The walls gleam with an unnatural black sheen, reflecting\n" +
               "the faint red glow emanating from the far end of the chamber.\n\n" +
               "Atop a towering throne sits an ancient creature of immense\n" +
               "power. Its eyes burn with crimson fire, and an aura of\n" +
               "menacing darkness radiates from its form.\n\n" +
               "This is your final test. The creature turns to regard you,\n" +
               "as if acknowledging your arrival for the first time.\n\n" +
               "Room Layout:\n" +
               grid + "\n" +
               "═══════════════════════════════════════\n" +
               "Legend: w=wall, f=floor, b=boss, e=exit, d=door, p=player, @=you";
    }

    @Override
    public String exit(Player player) {
        int successChance = calculateSuccessChance(player);
        Random random = new Random();
        int roll = random.nextInt(100);

        if (roll < successChance) {
            player.addToScore(500);
            return "\n⚔️  VICTORY! ⚔️\n" +
                   "═══════════════════════════════════════\n" +
                   "You strike true with your blade!\n" +
                   "The creature falls, its form dissipating into shadow.\n\n" +
                   "The curse is broken. The kingdom is saved.\n" +
                   "Light floods back into the world as the darkness retreats.\n\n" +
                   "═══════════════════════════════════════\n" +
                   "FINAL SCORE: " + player.getScore() + " points\n" +
                   "═══════════════════════════════════════\n" +
                   "You have triumphed!";
        } else {
            return "\n💀 DEFEAT! 💀\n" +
                   "═══════════════════════════════════════\n" +
                   "The creature moves with terrible speed.\n" +
                   "You raise your blade, but it is no match for the\n" +
                   "ancient power before you.\n\n" +
                   "The creature overwhelms you.\n" +
                   "Darkness consumes all.\n\n" +
                   "═══════════════════════════════════════\n" +
                   "GAME OVER\n" +
                   "Final Score: " + player.getScore() + " points\n" +
                   "═══════════════════════════════════════\n" +
                   "You may try again...";
        }
    }

    /**
     * Calculates the success probability based on player preparation.
     * @param player the player attempting to exit
     * @return the success chance as a percentage (0-95)
     */
    private int calculateSuccessChance(Player player) {
        int chance = 0;

        // Base: 0% (automatic failure without weapon)
        if (!player.hasItem("Iron Longsword")) {
            return 0;
        }

        // With weapon: 50%
        chance = 50;

        // Each sage interaction adds 15% (max 3 interactions = +45%)
        if (sageReference != null) {
            int sageInteractions = sageReference.getInteractionCount();
            chance += (sageInteractions * 15);
        }

        // Cap at 95%
        return Math.min(chance, 95);
    }
}

