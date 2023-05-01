package utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {
    
    public static float formataValor(float valor, int casasDecimaisMinimas, int casasDecimaisMaxima, RoundingMode estiloFormatacao){

        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(casasDecimaisMinimas);
		formatter.setMinimumFractionDigits(casasDecimaisMaxima);
		formatter.setRoundingMode(estiloFormatacao); 
		Float valorFormatado = Float.valueOf(formatter.format(valor));

        return valorFormatado;

    }

}
