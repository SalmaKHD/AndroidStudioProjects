package onlineshopapp.domain;

public class PopularDomain {
    private String title;
    private String picUrl;
    private int review;
    private double score;
    private int numberInChart;
    private double price;

    public PopularDomain(String title, String picUrl, int review, double score, int numberInChart, double price) {
        this.title = title;
        this.picUrl = picUrl;
        this.review = review;
        this.score = score;
        this.numberInChart = numberInChart;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNumberInChart() {
        return numberInChart;
    }

    public void setNumberInChart(int numberInChart) {
        this.numberInChart = numberInChart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
