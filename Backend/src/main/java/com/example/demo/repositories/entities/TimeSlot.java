package com.example.demo.repositories.entities;

public enum TimeSlot{
	S0830(1),
    S0900(2),
    S0930(3),
    S1000(4),
    S1030(5),
    S1100(6),
    S1130(7),
    S1200(8),
    S1230(0),
    S1300(10),
    S1330(11),
    S1400(12),
    S1430(13),
    S1500(14),
    S1530(15),
    S1600(16);

    private final int timeValue;

    TimeSlot(int timeValue) {
        this.timeValue = timeValue;
    }

    public int getTimeValue() {
        return timeValue;
    }
}