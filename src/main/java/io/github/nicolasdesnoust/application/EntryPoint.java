package application;

import domain.Actions;
import domain.Phrase;
import domain.Solver;

import java.util.Scanner;

public class EntryPoint {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Phrase magicPhrase = new Phrase(in.nextLine());

        Solver solver = new Solver();
        Actions actions = solver.solve(magicPhrase);

        System.out.println(actions.toSymbols());
    }
}
