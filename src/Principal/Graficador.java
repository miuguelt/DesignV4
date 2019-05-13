package Principal;


import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import java.awt.Dimension;
import java.util.ArrayList;



public class Graficador extends ApplicationFrame {

    ChartPanel chartPanel;

    public Graficador(ArrayList<Double> x, ArrayList<Double> y, String titulo) {

        super("Graficador");        
        XYDataset paresDeDatos = PasarASerie(x, y);
        JFreeChart diagrama = crearDiagrama(paresDeDatos,titulo);
        chartPanel = new ChartPanel(diagrama);
        chartPanel.setPreferredSize(new Dimension(640,440));
    }

    private XYDataset PasarASerie(ArrayList<Double> x, ArrayList<Double> y) {

//le pasamos una funcion generadora f(x)

        XYSeries datos = new XYSeries("Gráfica");
        int n = x.size();
        for (int i = 0; i < n; i++) {
            datos.add(x.get(i),y.get(i));
           
        }
                    
        XYSeriesCollection conjuntoDatos = new XYSeriesCollection();
        conjuntoDatos.addSeries(datos);

        return conjuntoDatos;

    }

    private JFreeChart crearDiagrama(XYDataset conjuntoDatos, String titulo) {

        JFreeChart diag = ChartFactory.createXYLineChart(
                titulo, //Titulo Grafica
                "Ángulo [º]", // Leyenda eje X
                "", // Leyenda eje Y

                conjuntoDatos, // Los datos

                PlotOrientation.VERTICAL, //orientacion

                false, // ver titulo de linea
                false, //tooltips
                false //URL
                );
        diag.setBackgroundPaint(Color.LIGHT_GRAY);//Pintar el fondo
        diag.getPlot().setBackgroundPaint(Color.WHITE);
        diag.getPlot().setOutlinePaint(Color.GRAY);     
        return diag;

    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }
}