package com.assessment.nkart.models;

import java.util.List;

public class DashboardData {

    private List<TopImage> topImages = null;
    private List<Products> newCollection = null;
    private List<Products> bestSelling = null;
    private List<Products> recentView = null;
    private List<Products> dealOfTheDay = null;

    public List<TopImage> getTopImages() {
        return topImages;
    }

    public void setTopImages(List<TopImage> topImages) {
        this.topImages = topImages;
    }

    public List<Products> getNewCollection() {
        return newCollection;
    }

    public void setNewCollection(List<Products> newCollection) {
        this.newCollection = newCollection;
    }

    public List<Products> getBestSelling() {
        return bestSelling;
    }

    public void setBestSelling(List<Products> bestSelling) {
        this.bestSelling = bestSelling;
    }

    public List<Products> getRecentView() {
        return recentView;
    }

    public void setRecentView(List<Products> recentView) {
        this.recentView = recentView;
    }

    public List<Products> getDealOfTheDay() {
        return dealOfTheDay;
    }

    public void setDealOfTheDay(List<Products> dealOfTheDay) {
        this.dealOfTheDay = dealOfTheDay;
    }
}
