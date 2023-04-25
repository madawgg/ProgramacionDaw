
package addressapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateUtil {
    private static final String PATRO_DATA = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATEJADOR_DATA = DateTimeFormatter.ofPattern(PATRO_DATA);
    
    //torna la data en format de cadena o null en cas de no existir
    
    public static String format(LocalDate data){
        if(data == null){
            return null;
        }
        
        return FORMATEJADOR_DATA.format(data);
        
    }
    
    //torna la data en format de LocalData o null en cas de no existir
    
    public static LocalDate parse(String data){
        //en cas de que la variable no siga una data valida
        try{
            return FORMATEJADOR_DATA.parse(data, LocalDate::from);
        }catch(DateTimeParseException e){
            return null;
        }
    }
    
    //comproba que es valida la data
    
    public static boolean validDate(String data){
        //En cas de que la variable no siga una dta valida
        return DateUtil.parse(data) != null;
    }
}
