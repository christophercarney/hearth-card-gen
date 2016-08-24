package com.stazima.hearthgen;

import android.content.res.AssetManager;
import android.media.Image;
import android.media.ImageReader;

import java.util.Random;

public class Card
{
    private int m_cost;
    private int m_attack;
    private int m_durability;

    public String m_name;
    private String m_text;
    private Enums.Rarity m_rarity;
    public String m_flavorText;
    //private Enums.CardSet m_cardSet;
    private Enums.Class m_class;
    private Enums.CardType m_type;
    //private Enums.Race m_race;

    private Image m_image;
    public String m_rawText;

    public Card(Enums.CardType cardType, String rawText, String rawName, String rawFlavor)
    {
        this.m_type = cardType;
        this.m_name = rawName;
        this.m_flavorText = rawFlavor;
        this.m_image = this.FindImage();
        this.m_rawText = rawText;
        //this.ProcessRawText(rawText);
    }

    private void ProcessRawText(String rawText)
    {
        this.GleanStats(rawText);
        //this.GleanText(rawText);
    }

    private void GleanStats(String rawText)
    {
        this.m_rarity = this.FindRarity(rawText);
        this.m_cost = this.FindCost(rawText);
        this.m_class = this.FindClass(rawText);
        if (this.m_type != Enums.CardType.SPELL || this.m_type != Enums.CardType.HERO_POWER)
        {
            this.m_attack = this.FindAttack(rawText);
            this.m_durability = this.FindDurability(rawText);
        }
    }

    private Enums.Rarity FindRarity(String rawText)
    {
        if (rawText.contains("RARITY:"))
        {
            String rarity = rawText.substring(rawText.lastIndexOf("RARITY:"), 1);
            switch(rarity.toLowerCase())        //take the first letter to figure out
            {
                case "c":
                    return Enums.Rarity.COMMON;
                case "r":
                    return Enums.Rarity.RARE;
                case "e":
                    return Enums.Rarity.EPIC;
                case "l":
                    return Enums.Rarity.LEGENDARY;
                default:
                    return Enums.Rarity.BASIC;
            }
        }
        else
        {
            return Enums.Rarity.RandomRarity();
        }
    }

    private int FindCost(String rawText)
    {
        if (rawText.contains("COST:"))
        {
            String cost = rawText.substring(rawText.lastIndexOf("COST:"), 1);
            return Integer.parseInt(cost);
        }
        else
        {
            Random rand = new Random();
            return rand.nextInt(11);
        }
    }

    private int FindAttack(String rawText)
    {
        if (rawText.contains("ATT:"))
        {
            String att = rawText.substring(rawText.lastIndexOf("ATT:"), 1);
            return Integer.parseInt(att);
        }
        else
        {
            Random rand = new Random();
            if (rand.nextInt(100) == 99)
                return 30;
            return rand.nextInt(13);
        }
    }

    private int FindDurability(String rawText)
    {
        if (rawText.contains("DUR:"))
        {
            String cost = rawText.substring(rawText.lastIndexOf("DUR:"), 1);
            return Integer.parseInt(cost);
        }
        else
        {
            Random rand = new Random();
            if (this.m_type == Enums.CardType.MINION)
            {
                if (rand.nextInt(100) == 99)
                    return 30;
                return rand.nextInt(12) + 1;
            }
            else
                return rand.nextInt(8) + 1;
        }
    }

    private Enums.Class FindClass(String rawText)
    {
        if (rawText.contains("CLASS:"))
        {
            String cardClass = rawText.substring(rawText.lastIndexOf("CLASS:"), 4);
            switch(cardClass.toLowerCase())        //take the first letter to figure out
            {
                case "warr":
                    return Enums.Class.WARRIOR;
                case "sham":
                    return Enums.Class.SHAMAN;
                case "mage":
                    return Enums.Class.MAGE;
                case "rogu":
                    return Enums.Class.ROGUE;
                case "prie":
                    return Enums.Class.PRIEST;
                case "hunt":
                    return Enums.Class.HUNTER;
                case "warl":
                    return Enums.Class.WARLOCK;
                case "pala":
                    return Enums.Class.PALADIN;
                case "drui":
                    return Enums.Class.DRUID;
                default:
                    return Enums.Class.NEUTRAL;
            }
        }
        else
        {
            return Enums.Class.RandomClass();
        }
    }

    private Image FindImage()
    {
        AssetManager am =  MainActivity.GetAppContext().getAssets();
        //Image im = ImageReader()
        return null;
    }

    public String GetText()
    {
        return this.m_text;
    }

    public String GetFlavorText()
    {
        return this.m_flavorText;
    }
}
