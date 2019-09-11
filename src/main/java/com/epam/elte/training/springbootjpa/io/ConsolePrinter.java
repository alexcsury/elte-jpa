package com.epam.elte.training.springbootjpa.io;

import java.util.Scanner;

public abstract class ConsolePrinter {

    protected final Scanner scanner = new Scanner(System.in);

    protected boolean shouldContinue(String choice, String end) {
        return !choice.equals(end.toLowerCase()) && !choice.equals(end.toUpperCase());
    }

    public abstract void showOptions();

}
