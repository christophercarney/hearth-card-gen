//Aiden Hall, HearthJson, card asset guy, rita, blizz

package com.stazima.hearthgen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Enums
{
    public enum Rarity
    {
        BASIC, COMMON, RARE, EPIC, LEGENDARY;

        private static final List<Rarity> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Rarity RandomRarity()
        {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public enum Class
    {
        WARRIOR, SHAMAN, MAGE, ROGUE, PRIEST, HUNTER, WARLOCK, PALADIN, DRUID, NEUTRAL;

        private static final List<Class> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Enums.Class RandomClass()
        {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public enum CardType
    {
        MINION, SPELL, WEAPON, HERO_POWER
    }

    public enum Language
    {
        EN, DE, ES, DR, IT, JA, KO, PL, PT, RU, CN
    }

    public enum Race
    {
        MECH, BEAST, MURLOC;
    }

    public enum CardSet
    {
        BASIC, CLASSIC, NAXX, GVG, BRM, TGT, LOE, WOG;
    }

    public enum Mechanic
    {
        DEATHRATTLE, INSPIRE, BATTLECRY, TAUNT, CHARGE, IMMUNE;
    }
}
