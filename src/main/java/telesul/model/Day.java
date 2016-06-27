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
public class Day {

    public static String getDay(int day) {
        return "Dia_" + StringUtils.leftPad(day + "", 2, '0');
    }
}
