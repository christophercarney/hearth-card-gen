package com.stazima.hearthgen; /**
 * Created by Chris on 5/28/2016.
 */
import rita.*;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Debug;

import java.io.*;

public class CardGenerator
{
    public String[] m_rawCardText;
    private Enums.Language m_currentLanguage;

    private RiMarkov m_textHero;
    private RiMarkov m_textMinion;
    private RiMarkov m_textWeapon;
    private RiMarkov m_textSpell;
    private RiMarkov m_name;
    private RiMarkov m_flavor;

    private int m_insanity;
    private Context m_context;

    public CardGenerator(Context context)
    {
        this.m_context = context;
        this.m_insanity = 3;
        this.m_currentLanguage = GetCurrentLanguage();
        this.InstantiateMarkovGenerators();
        this.LoadText();
    }

    private void InstantiateMarkovGenerators()
    {
        this.m_textHero = new RiMarkov(this.m_insanity, true, true);
        this.m_textMinion = new RiMarkov(this.m_insanity, true, true);
        this.m_textWeapon = new RiMarkov(this.m_insanity, true, true);
        this.m_textSpell = new RiMarkov(this.m_insanity, true, true);
        this.m_name = new RiMarkov(this.m_insanity, true, true);
        this.m_flavor = new RiMarkov(this.m_insanity, true, true);
    }

    private Enums.Language GetCurrentLanguage()
    {
        return Enums.Language.EN;
    }

    private void LoadText()
    {
        AssetManager am = this.m_context.getAssets();

        try
        {
            InputStream is = am.open("text/en/hero_power.txt");
            this.m_textHero.loadText(convertStreamToString(is));
            is.close();

            InputStream is1 = am.open("text/en/flavor.txt");
            this.m_flavor.loadText(convertStreamToString(is1));
            is1.close();

            InputStream is2 = am.open("text/en/minion.txt");
            this.m_textMinion.loadText(convertStreamToString(is2));
            is2.close();

            InputStream is3 = am.open("text/en/names.txt");
            this.m_name.loadText(convertStreamToString(is3));
            is3.close();

            InputStream is4 = am.open("text/en/spell.txt");
            this.m_textSpell.loadText(convertStreamToString(is4));
            is4.close();

            InputStream is5 = am.open("text/en/flavor.txt");
            this.m_textWeapon.loadText(convertStreamToString(is5));
            is5.close();
        } catch(Exception e) { System.out.println(e); }
    }

   public void SetInsanity(int newInsanity)
   {
       this.m_insanity = newInsanity;
       this.InstantiateMarkovGenerators();
       this.LoadText();
   }

    public Card GenerateCard(Enums.CardType cardType)
    {
        String rawCardText;
        String rawName;
        String rawFlavor;

        switch (cardType)
        {
            case MINION:
                this.m_rawCardText = this.m_textMinion.generateSentences(2);
                System.out.println(this.m_textMinion.print());
                break;
            case SPELL:
                rawCardText = this.m_textSpell.generateSentence();
                break;
            case HERO_POWER:
                rawCardText = this.m_textHero.generateSentence();
                break;
            case WEAPON:
                rawCardText = this.m_textWeapon.generateSentence();
                break;
            default:
                rawCardText = "";
        }

        rawName = this.m_name.generateSentence();
        rawFlavor = this.m_flavor.generateSentence();

        //Card newCard = new Card(cardType, rawCardText, rawName, rawFlavor);
        //newCard.GenerateImage();
        return null;                //return the whole card cause we wanna do cool shit with it later and it'll be easier this way
    }

    static String convertStreamToString(java.io.InputStream is)
    {
        java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
