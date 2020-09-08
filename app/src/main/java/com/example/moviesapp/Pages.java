package com.example.moviesapp;

import java.util.List;

public class Pages {
int Page;
int total_results;
int total_pages;
List<Results> results;

    public int getPage() {
        return Page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Results> getResults() {
        return results;
    }
}
