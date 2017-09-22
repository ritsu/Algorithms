package net.qiguang.algorithms.C2_Sorting.S1_ElementarySorts;

/**
 * 2.1.13 Deck sort.
 * Explain how you would put a deck of cards in order by suit (in the order spades, hearts, clubs, diamonds)
 * and by rank within each suit, with the restriction that the cards must be laid out face down in a row,
 * and the only allowed operations are to check the values of two cards and to exchange two cards
 * (keeping them face down).
 *
 * Answer:
 * Assign a value of (s*13 + r) to each card, where:
 *    s = 0 if spades
 *        1 if hearts
 *        2 if clubs
 *        3 if diamonds
 *    r = rank of card (1 to 13)
 * Then sort as usual.
 */
public class Exercise_2_1_13 {
    // Nothing to see here.
}
