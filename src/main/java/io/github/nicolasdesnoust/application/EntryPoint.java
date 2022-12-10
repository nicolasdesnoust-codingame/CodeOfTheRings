package io.github.nicolasdesnoust.application;


import io.github.nicolasdesnoust.domain.Actions;
import io.github.nicolasdesnoust.domain.Phrase;
import io.github.nicolasdesnoust.domain.Solver;

import java.util.Scanner;

public class EntryPoint {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Phrase magicPhrase = new Phrase(in.nextLine());

        Solver solver = new Solver();
        Actions actions = solver.solve(magicPhrase);

        System.out.println(actions.toSymbols());
    }
}
