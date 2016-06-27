/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.model;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author lmohan
 */
public class DayMonth {

    private int day;
    private int month;

    public DayMonth(int day, int month) {
        this.day = day;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public static DayMonth valueOf(String str) {
        return new DayMonth(Integer.parseInt(str.substring(0, 2)), Integer.parseInt(str.substring(2, 4)));
    }

    @Override
    public String toString() {
        return "DayMonth{" + "day=" + day + ", month=" + month + '}';
    }

}
