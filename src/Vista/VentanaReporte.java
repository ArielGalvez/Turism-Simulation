/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author Ariel
 */
import java.awt.*;
import javax.swing.*;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

class VentanaReporte extends JFrame
{
	private	JTabbedPane tabbedPane;
	private	JPanel panel1;
	private	JPanel panel2;
	
        //PanelDibujo padre;
        double costoPaq1, costoPaq2, costoPaq3, costoPaq4, costoTotalPaq;
	int num1, num2, num3, num4, sumaClientes;
        int paq1, paq2, paq3, paq4, sumaPaquetes;
        int qt1, qt2, qt3, qt4, qtTotales;
	public VentanaReporte(PanelDibujo padre)
	{
            //this.padre=padre;
            costoPaq1=padre.main.getDatosVariables().getCostoPaquetes()[0];
            costoPaq2=padre.main.getDatosVariables().getCostoPaquetes()[1];
            costoPaq3=padre.main.getDatosVariables().getCostoPaquetes()[2];
            costoPaq4=padre.main.getDatosVariables().getCostoPaquetes()[3];
            costoTotalPaq=costoPaq1+costoPaq2+costoPaq3+costoPaq4;
            /**/
            num1=padre.numParticulares;
        num2=padre.numEstudiantes;
        num3=padre.numEsquiadores;
        num4=padre.numEsqExp;
        sumaClientes=num1+num2+num3+num4;
        /********/
        paq1=padre.paqNormal;
        paq2=padre.paqTuristico;
        paq3=padre.paqPremium;
        paq4=padre.paqVip;
        sumaPaquetes=paq1+paq2+paq3+paq4;
        /********/
        qt1=padre.nuQueParticulares;
        qt2=padre.nuQueEstudiantes;
        qt3=padre.nuQueEsquiadores;
        qt4=padre.nuQueEsqExp;
        qtTotales=qt1+qt2+qt3+qt4;
            /**fin*/	
		setTitle("Ventana Reportes Estadisticos");
		setSize( 640, 400 );
                setIconImage(new ImageIcon("src/Images/fondo/icono/iconN.png").getImage());
		setLocationRelativeTo(null); 
		setBackground(Color.gray );

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		createPage1();
		createPage2();

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Grafico Barras", panel1 );
		tabbedPane.addTab( "Grafico Torta", panel2 );
		topPanel.add( tabbedPane, BorderLayout.CENTER );
	}

	private void createPage1()
	{
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,1) );
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		float num=1.0f;
		dataset.setValue(costoPaq1*paq1,"Paquete Normal "+costoPaq1*paq1, "Bs");
		dataset.setValue(costoPaq2*paq2,"Paquete Turistico "+costoPaq2*paq2, "Bs");
                dataset.setValue(costoPaq3*paq3,"Paquete Premium "+costoPaq3*paq3, "Bs");
                dataset.setValue(costoPaq4*paq4,"Paquete VIP "+costoPaq4*paq4, "Bs");
		
		JFreeChart chart1 = ChartFactory.createBarChart3D ("Grafica de Paquetes Vendidos",
				"Tipos de paquetes vendidos",
				"Costo en Bs",
			dataset,
			PlotOrientation.VERTICAL,
		    true, 
		    false,
		    false);        
		    panel1.add(new ChartPanel(chart1));
	}

	private void createPage2()
	{
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1) );
		DefaultPieDataset dataset1 = new DefaultPieDataset();
		float x=1.0f;	
        	dataset1.setValue("Particular ="+((num1*x)/sumaClientes)*100+"%",num1);
        	dataset1.setValue("Estudiante ="+((num2*x)/sumaClientes)*100+"%",num2);
        	dataset1.setValue("Esquiador ="+((num3*x)/sumaClientes)*100+"%",num3);
        	dataset1.setValue("Esquiador Exp. ="+((num4*x)/sumaClientes)*100+"%",num4);
        	
        JFreeChart chart2 = ChartFactory.createPieChart3D("Promedios de Tipo de Clientes", dataset1, true, false, false);
        panel2.add(new ChartPanel(chart2));
	}

}
