package com.madroid.reporter.DataProvider;

public class ChartData {
    private String series;
    private String category;
    private double value;

    public ChartData(String series, String category, double value) {
        super();
        this.series = series;
        this.category = category;
        this.value = value;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
