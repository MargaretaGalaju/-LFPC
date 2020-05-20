package com.company;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Chomsky chomsky = new Chomsky(new File("input.txt"));
            chomsky.to_chomsky();
        }
        catch (FileNotFoundException e){
            System.out.print(e.toString());
        }
    }
}
