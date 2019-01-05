package com.feedfacts.example.factfeeds.model;

import java.util.List;

/**
 * POJO class.
 *
 * @author Sneha Rakhonde
 */
public class FactFeed {
    private String title;
    private List<FactFeedRow> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FactFeedRow> getRows() {
        return rows;
    }

    public void setRows(List<FactFeedRow> rows) {
        this.rows = rows;
    }
}
