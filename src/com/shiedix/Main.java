package com.shiedix;
import java.io.IOException;

/**
 *
 * Ueber diese Java-Datei startet man alles.
 *
 * @version 1.0 from 03/29/2021
 * @author JFK_Bruechner
 */

class Main
{
    public static MainMenu menu;

    public static void main(String[] args) throws IOException {
        menu = new MainMenu("Snake");
    }
}