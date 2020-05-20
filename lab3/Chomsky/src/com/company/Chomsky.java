package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class Chomsky {

    private Grammar grammar;

    Chomsky(File grammar_in) throws FileNotFoundException {
        this.grammar = new Grammar(grammar_in);
    }

    void to_chomsky(){
        this.grammar.print();
        this.remove_nonproductive();
        this.remove_inaccessible();
        this.remove_eps();
        this.add_rules_for_terminals();
        this.check_s();
        this.remove_more_then_three();
        this.remove_less_then_two();
        this.grammar.merge();
        System.out.println("\nFinal");
        this.grammar.print();
    }

    private void remove_nonproductive(){
        List<String> nonproductive = new ArrayList<>();
        for (String nonterminal: this.grammar.getNonterminals()){
            if (!this.grammar.getLeft().contains(nonterminal)){
                nonproductive.add(nonterminal);
            }
        }
        int i = 0;
        for (List<String> words: this.grammar.getRight()){
            i += 1;
            List<String> remove = new ArrayList<>(); // word with nonproductive nonterminal of some terminal
            for (String word: words){
                for (char c: word.toCharArray()){
                    if (nonproductive.contains(String.valueOf(c))){
                        remove.add(word);
                        break;
                    }
                }
            }
            this.grammar.getRight().get(i - 1).removeAll(remove);
        }
        List<Integer> remove = new ArrayList<>();
        int k = 0;
        for (String left: this.grammar.getLeft()){ // we might remove all word for some nonterminal and now can remove this nonterminal too
            if (this.grammar.rules(left).size() == 0){
                remove.add(k);
            }
            k += 1;
        }
        k = 0;
        for (int j: remove){
            this.grammar.getNonterminals().remove(this.grammar.getLeft().get(j - k));
            this.grammar.getRight().remove(j - k);
            this.grammar.getLeft().remove(j - k);
            k += 1;
        }
        if (nonproductive.size() != 0){
            this.grammar.getNonterminals().removeAll(nonproductive);
            System.out.print("\nNonproductive nonterminals: ");
            for (String s: nonproductive){
                System.out.print(s + " ");
            }
            System.out.println();
            this.grammar.print();
        }
        else{
            System.out.println("\nNo nonproductive nonterminals");
        }
    }

    private void remove_inaccessible(){
        List<String> inaccessible = new ArrayList<>();
        for (String nonterminal: this.grammar.getLeft()){
            if (nonterminal.equals("S")) continue;
            boolean finded = false;
            for (List<String> words: this.grammar.getRight()){
                for (String word: words){
                    if (word.contains(nonterminal)){
                        finded = true;
                        break;
                    }
                }
                if (finded) break;
            }
            if (!finded && !inaccessible.contains(nonterminal)){
                inaccessible.add(nonterminal);
            }
        }
        if (inaccessible.size() != 0){
            this.grammar.getNonterminals().removeAll(inaccessible);
            System.out.print("\nInaccessible nonterminals: ");
            for (String s: inaccessible){
                System.out.print(s + " ");
            }
            System.out.println();
            int i = 0, removed = 0;
            for (String left: this.grammar.getLeft()){
                if (inaccessible.contains(left)){
                    this.grammar.getRight().remove(i - removed);
                    removed += 1;
                }
                i += 1;
            }
            this.grammar.getLeft().removeAll(inaccessible);
            this.grammar.print();
        }
        else {
            System.out.println("\nNo inaccessible nonterminals");
        }
    }

    private void remove_eps(){
        if (!this.grammar.contains_eps){
            System.out.println("No eps nonterminals");
            return;
        }
        List<String> eps = this.grammar.eps_nonterminals();
        System.out.print("\nEps nonterminals: ");
        for (String s: eps){
            System.out.print(s + " ");
        }
        System.out.println();
        for (int i = 0; i < this.grammar.getLeft().size(); i++){
            for (int j = 0; j < this.grammar.getRight().get(i).size(); j++){
                String word = this.grammar.getRight().get(i).get(j);
                for (char c: word.toCharArray()){
                    if (eps.contains(String.valueOf(c))){
                        if (word.length() == 1){
                            this.grammar.getRight().get(i).add("0");
                        }
                        else{
                            this.grammar.getRight().get(i).add(word.replaceFirst(String.valueOf(c), ""));
                        }
                        break;
                    }
                }
            }
        }
        List<String> remove = new ArrayList<>();
        for (String left: this.grammar.getLeft()){
            if (left.equals("S")) continue;
            this.grammar.rules(left).remove("0");
            if (this.grammar.rules(left).size() == 0){
                remove.add(left);
            }

        }
        for (String left: remove){
            this.grammar.getNonterminals().remove(left);
            this.grammar.getRight().remove(this.grammar.getLeft().indexOf(left));
            this.grammar.getLeft().remove(left);
        }
        this.grammar.print();
    }

    private void add_rules_for_terminals(){
        for (String left: this.grammar.getLeft()) {
            List<String> l = new ArrayList<>();
            for (String rule : this.grammar.rules(left)) {
                StringBuilder new_word = new StringBuilder("");
                for (char c : rule.toCharArray()) {
                    if (!String.valueOf(c).toUpperCase().equals(String.valueOf(c))) {
                        new_word.append(String.valueOf(c).toUpperCase());
                    } else {
                        new_word.append(c);
                    }
                }
                l.add(String.valueOf(new_word));
            }
            this.grammar.change_rules(left, l);
        }
        for (String terminal: this.grammar.getTerminals()){
            String nonterminal = terminal.toUpperCase();
            if (!this.grammar.getNonterminals().contains(nonterminal)){
                this.grammar.getNonterminals().add(nonterminal);
            }
            List<String> l = new ArrayList<>();
            l.add(terminal);
            this.grammar.add_rule(nonterminal, l);
            this.grammar.getNonterminals().add(nonterminal);
        }

        System.out.println("\nAdded new rules for terminals");
        this.grammar.print();
    }

    private void check_s(){
        boolean need_to_add = false;
        for (List<String> rules: this.grammar.getRight()) {
            for (String rule : rules) {
                if (rule.contains("S")) {
                    need_to_add = true;
                    break;
                }
            }
            if (need_to_add){
                break;
            }
        }
        if (need_to_add){
            this.grammar.getNonterminals().add("S0");
            System.out.println("\nNew start axiom - S0");
            this.grammar.change_left("S", "S0");
            this.grammar.add_rule("S", this.grammar.rules("S0"));
            this.grammar.print();
        }
    }

    private void remove_more_then_three(){
        List<String> r = new ArrayList<>(this.grammar.getLeft());
        for (String left: r){
            List<String> new_r = new ArrayList<>();
            for (String rule: this.grammar.rules(left)){
                if (rule.length() > 2){
                    while (rule.length() > 2){
                        String w = rule.substring(0, 2);
                        rule = rule.substring(2);
                        List<String> l = new ArrayList<>();
                        l.add(w);
                        String w_ = this.grammar.get_free();
                        this.grammar.getNonterminals().add(w_);
                        this.grammar.add_rule(w_, l);
                        rule = w_ + rule;
                    }
                    new_r.add(rule);
                }
                else {
                    new_r.add(rule);
                }
            }
            this.grammar.change_rules(left, new_r);
        }
        System.out.println("\nRemoving rules with length more then 2");
        this.grammar.print();
    }

    private void remove_less_then_two(){
        for (String left: this.grammar.getLeft()){
            List<String> new_r = new ArrayList<>();
            for (String rule: this.grammar.rules(left)){
                if (rule.length() < 2 && rule.toUpperCase().equals(rule)){
                    List<String> r = this.grammar.rules(rule);
                    for (String r_: r){
                        if (r_.length() == 1 && r_.toLowerCase().equals(r_)){
                            new_r.add(r_);
                        }
                        if (r_.length() == 2){
                            new_r.add(r_);
                        }
                    }
                }
                else{
                    new_r.add(rule);
                }
            }
            this.grammar.change_rules(left, new_r);
        }
        System.out.println("\nRemoving rules with one nonterminal in the right side");
        this.grammar.print();
    }
}