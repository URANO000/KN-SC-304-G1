package org.example.Mod5;

import javax.swing.*;
import java.math.BigDecimal;
import org.example.Mod5.IndicadorEco;

public class BCCR {
    //Se llama al web services para implementar en el menu
    public void getIndicadores() throws Exception {
        WebService webService = new WebService();

        //Lleno de informacion
        IndicadorEco indicadorEco = webService.getIndicador("318", "15/04/2025", "15/04/2025", "Ratman",
                "N", "asevilla40838@ufide.ac.cr", "3E5D58A0A0");

        BigDecimal tipoCambio = tipoCambio(indicadorEco);
        JOptionPane.showMessageDialog(null,
                "-----------------------------------\n" +
                        "Tipo de cambio: " + tipoCambio.toString() + "\n" +
                        "-----------------------------------");

    }

    public static BigDecimal tipoCambio(IndicadorEco indicadorEco) {
        BigDecimal resultado = null;

        //Verificar que el resultado NO sea nulo....oouuugh
        if (indicadorEco != null && indicadorEco.getDiffgram() != null){
            IndicadorEco.Diffgram diffgram = indicadorEco.getDiffgram();

            if (diffgram.getDatosDeIndicadores() != null) {
                IndicadorEco.DatosDeIndicadores datos = diffgram.getDatosDeIndicadores();

                if (datos.getIndicadores() != null && !datos.getIndicadores().isEmpty()){
                    for (IndicadorEco.Indicador indicador : datos.getIndicadores()){

                        //Se imprimen los datos del indicador
                        JOptionPane.showMessageDialog(null,
                                "--------------------------------\n" +
                                        "-----Datos del Indicador---\n" +
                                        "Código: " + indicador.getCodigo() + "\n" +
                                        "Fecha: " + indicador.getFecha() + "\n" +
                                        "Valor: " + indicador.getValor() + "\n");

                        //Si existe como BigDecimal, se extrae intCompact
                        if (indicador.getValor() != null) {
                            long intCompact = indicador.getValor().unscaledValue().longValue();
                            System.out.println("\tintCompact: " + intCompact);
                        }
                        return indicador.getValor();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"No hay indicadores en la respuesta");
                }
            }else {
                JOptionPane.showMessageDialog(null, "No existen datos para los indicadores");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El objeto de indicador económico está vacío");
        }
        return resultado;

    }
}
