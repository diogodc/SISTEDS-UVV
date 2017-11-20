/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 *
 * @author FELIPE DE OLIVEIRA VOGEL PENNA
 */
public class DataHora {
    
    public String formatoYMD(Date data){
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        return formato.format(data);
    }
    
    public String formatoDMY(Date data){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        return formato.format(data);
    }
    
    public String formatoSS(String data) throws ParseException{
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        return myFormat.format(fromUser.parse(data));        
    }

  
}
