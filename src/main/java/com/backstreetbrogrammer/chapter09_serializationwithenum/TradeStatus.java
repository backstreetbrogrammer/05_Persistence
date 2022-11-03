package com.backstreetbrogrammer.chapter09_serializationwithenum;

public enum TradeStatus {

    LIVE("Live"),
    STALE("Stale"),
    CLOSED("Closed"),
    UNKNOWN("Unknown status");

    private String desc;

    TradeStatus(final String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }

    @Override
    public String toString() {
        return "TradeStatus{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
