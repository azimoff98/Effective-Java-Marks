package com.company.chapter1.item5;

import java.util.Collections;
import java.util.List;

public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValid(String word){
        return false;
    }

    public List<String> suggestions(String typo){
        return Collections.emptyList();
    }
}
