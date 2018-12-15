package com.example.aomek.exitpoll.model;

import java.util.Locale;

public class PollItem {
    public final long _id;
    public final String score;
    public final String img;


    public PollItem(long id, String score, String img) {
        _id = id;
        this.score = score;
        this.img = img;
    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "%s (%s)",
                this.score,
                this.img
        );
        return msg;
    }
}
