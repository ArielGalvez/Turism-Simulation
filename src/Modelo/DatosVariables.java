/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Ariel
 */
public class DatosVariables {
    Random rdm = new Random();
    private ArrayList<DatoTriangular> datosTriangulares;
    private double[] probTipoClientes;
    private double[] probPaquetes; private double[] costoPaquetes;
    private EmpleadosPorHorario[] empleadosPorHorario;
    public DatosVariables(){
        restaurarDatosTriangulares();
        asignarProbClientes();
        asignarCantEmpleados();
        asignarProbPaquetes();
    }
    public void restaurarDatosTriangulares(){
        datosTriangulares= new ArrayList<>();
        //demora que tardan en firmar el contrato los tipos de clientes
        DatoTriangular demoraParticular=new DatoTriangular("Particular", 120, 60);
        datosTriangulares.add(demoraParticular);
        DatoTriangular demoraEstudiante=new DatoTriangular("Estudiante", 100, 50);
        datosTriangulares.add(demoraEstudiante);
        DatoTriangular demoraEsquiador=new DatoTriangular("Esquiador", 150, 75);
        datosTriangulares.add(demoraEsquiador);
        DatoTriangular demoraEsqExperto=new DatoTriangular("Esq. Experto", 120, 60);
        datosTriangulares.add(demoraEsqExperto);
        //otros datos
        DatoTriangular ingresarNuevo= new DatoTriangular("Ingreso Nuevo Cliente", 60, 10);
        datosTriangulares.add(ingresarNuevo);
        DatoTriangular demoraEmpleadoInformar=new DatoTriangular("Demora Emp. Informar", 280, 60);//cambia 480 120
        datosTriangulares.add(demoraEmpleadoInformar);
        DatoTriangular llamadaTelefonica=new DatoTriangular("Llamada Telefonica", 600, 300);//10 5
        datosTriangulares.add(llamadaTelefonica);
        DatoTriangular interrumpirAtencion=new DatoTriangular("Interrumpir Atencion", 90, 30);
        datosTriangulares.add(interrumpirAtencion);
        DatoTriangular otrasActividades=new DatoTriangular("Otras Actividades", 1200, 300);//20 5
        datosTriangulares.add(otrasActividades);
        DatoTriangular demoraCajero=new DatoTriangular("Demora Cajero", 180, 30);
        datosTriangulares.add(demoraCajero);
        DatoTriangular demoraVerificarContrato=new DatoTriangular("Demora Fila Contrato", 300, 75);
        datosTriangulares.add(demoraVerificarContrato);
        
    }
    public int sizeDatos(){ return datosTriangulares.size();}
    /*metodo para editar datos el id representa la posicion de arriba
    * inicia en 0
    */
    public void setDatoTriangular(int id, int valor, int maxMin){
        datosTriangulares.get(id).setDatoTriangular(valor, maxMin);
    }
    public DatoTriangular getDatoTriangular(int id){return datosTriangulares.get(id);}
    
    public void asignarProbClientes(){
        probTipoClientes= new double[4];
        probTipoClientes[0]=0.3;
        probTipoClientes[1]=0.4;
        probTipoClientes[2]=0.2;
        probTipoClientes[3]=0.1;
    }
    public void asignarProbPaquetes(){
        probPaquetes= new double[4];
        probPaquetes[0]=0.40;
        probPaquetes[1]=0.30;
        probPaquetes[2]=0.22;
        probPaquetes[3]=0.8;
        /******/
        costoPaquetes= new double[4];
        costoPaquetes[0]=120.50;
        costoPaquetes[1]=180.50;
        costoPaquetes[2]=240.0;
        costoPaquetes[3]=300.0;
    }
    public double[] getProbPaquetes(){return probPaquetes;}
    public double[] getCostoPaquetes(){return costoPaquetes;}
    public double[] getProbTipoClientes(){return probTipoClientes;}
    public double getProbById(int id){return probTipoClientes[id];}
    public void asignarCantEmpleados(){
        empleadosPorHorario= new EmpleadosPorHorario[4];
        empleadosPorHorario[0]=new EmpleadosPorHorario("09:00", "11:00", 3);
        empleadosPorHorario[1]=new EmpleadosPorHorario("11:00", "12:00", 4);
        empleadosPorHorario[2]=new EmpleadosPorHorario("12:00", "13:00", 6);
        empleadosPorHorario[3]=new EmpleadosPorHorario("13:00", "19:00", 4);
    }
    public EmpleadosPorHorario[] getEmpleadosPorHorario(){return empleadosPorHorario;}
    
    public short generarTipoCliente(){
        double num=rdm.nextDouble(); //genera de 0 a 100;
        double lim=0;
        lim=lim+probTipoClientes[0];
        double lim1=lim;
        lim=lim+probTipoClientes[1];
        double lim2=lim;
        lim=lim+probTipoClientes[2];
        double lim3=lim;
        if(num<=lim1)        return 1;
        else if(num<=lim2)   return 2;
        else if(num<=lim3)   return 3;
        else return 4;
    }
    public short generarTipoPaquete(){
        double num=rdm.nextDouble(); //genera de 0 a 100;
        double lim=0;
        lim=lim+probPaquetes[0];
        double lim1=lim;
        lim=lim+probPaquetes[1];
        double lim2=lim;
        lim=lim+probPaquetes[2];
        double lim3=lim;
        if(num<=lim1)        return 1;
        else if(num<=lim2)   return 2;
        else if(num<=lim3)   return 3;
        else return 4;
    }
    public boolean clienteSeRetira(){
        boolean res=false;
        double num=rdm.nextDouble();
        if(num<=0.7) res=true;//si la prob sale menor a 70% se queda, si no se retira
        return res;
    }
}
