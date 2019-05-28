/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author jayhuynh
 */
public class DateValidation {
    private String[] SAMPLE_MONTH = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
    
    public boolean checkDate(String inputDate)
    {
        if(inputDate.isEmpty())
        {
            return false;
        }
        if(inputDate.length() != 8)
        {
            return false;
        }
        if(!this.checkInt(inputDate))
        {
            return false;
        }
        if(!this.checkValidDate(inputDate))
        {
            return false;
        }
        return true;
    }
    
    public String convertDate(String inputDate)
    {
        String result = "";
        String day = inputDate.substring(0, 2);
        String month = SAMPLE_MONTH[Integer.parseInt(inputDate.substring(3, 5))-1];
        String year = inputDate.substring(6, 8);
        result = day + "-" + month + "-" + year;       
        return result;
    }
    
    private boolean checkValidDate(String input)
    {
        if(Integer.parseInt(input.substring(0, 2)) < 1 || Integer.parseInt(input.substring(0, 2)) > 31)
        {
            return false;
        }
        if(Integer.parseInt(input.substring(3, 5)) < 1 || Integer.parseInt(input.substring(3, 5)) > 12)
        {
            return false;
        }
        if(Integer.parseInt(input.substring(6, 8)) < 1)
        {
            return false;
        }
        return true;
    }
    
    private boolean checkInt(String input)
    {
        if(input.charAt(0) < 48 || input.charAt(0) > 57)
        {
            return false;
        }
        if(input.charAt(1) < 48 || input.charAt(1) > 57)
        {
            return false;
        }
        if(input.charAt(3) < 48 || input.charAt(3) > 57)
        {
            return false;
        }
        if(input.charAt(4) < 48 || input.charAt(4) > 57)
        {
            return false;
        }
        if(input.charAt(6) < 48 || input.charAt(6) > 57)
        {
            return false;
        }
        if(input.charAt(7) < 48 || input.charAt(7) > 57)
        {
            return false;
        }
        return true;
    }
}
