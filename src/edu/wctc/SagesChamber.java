package edu.wctc;

/**
 * A room containing a wise sage who provides knowledge about the upcoming boss.
 * The player can interact with the sage multiple times, each interaction providing
 * additional insight and increasing their chances of success in the final battle.
 * Implements Interactable interface.
 */
public class SagesChamber extends Room implements Interactable {
    private int interactionCount = 0;
    private final String[] dialogues = {
        "The creature ahead feeds on fear. Knowledge is your shield.\n" +
        "    Take time to prepare yourself mentally.",

        "I sense a weakness... its left flank is vulnerable.\n" +
        "    Strike there with conviction when the moment comes.",

        "You are as prepared as you can be. Go now, and reclaim your kingdom.\n" +
        "    May fortune favor the brave."
    };

    /**
     * Constructs a SagesChamber with the given name.
     * @param name the name of the room
     */
    public SagesChamber(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        String grid = layoutGrid != null ? renderGrid() : "[Map not loaded]";
        return "═══════════════════════════════════════\n" +
               "     SAGE'S CHAMBER - Ancient Library\n" +
               "═══════════════════════════════════════\n\n" +
               "An ancient library filled with dusty scrolls and crumbling tomes.\n" +
               "The walls are lined with shelves carved from dark wood, their\n" +
               "contents spanning centuries of accumulated knowledge.\n\n" +
               "In the center sits a robed figure on a stone bench, eyes closed\n" +
               "in deep meditation. The figure's presence radiates calm wisdom\n" +
               "and an aura of serene knowledge.\n\n" +
               "Room Layout:\n" +
               grid + "\n" +
               "═══════════════════════════════════════\n" +
               "Legend: w=wall, f=floor, n=NPC sage, p=player, d=door, @=you";
    }

    @Override
    public String interact(Player player) {
        if (interactionCount < dialogues.length) {
            String dialogue = dialogues[interactionCount];
            interactionCount++;
            player.addToScore(25);
            return "\n📜 Sage speaks:\n" +
                   "  \"" + dialogue + "\"\n" +
                   "  You gain 25 points.";
        }
        return "\n✗ The sage meditates silently. You've learned all they have to teach.";
    }

    /**
     * Gets the current interaction count.
     * Used for calculating success probability in the boss encounter.
     * @return the number of times the sage has been interacted with
     */
    public int getInteractionCount() {
        return interactionCount;
    }
}

