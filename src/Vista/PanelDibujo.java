/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;
import Modelo.CustomFont;
import Modelo.DistribucionTriangular;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Point;
import java.util.ArrayList;
/**
 *
 * @author Ariel
 */

/**
 * esta clase tb hace referencia al monitor
 */
public class PanelDibujo extends JPanel{
    DistribucionTriangular ditribTriangular=new DistribucionTriangular();
    Main main;
    //imagenes a usar
    Image fondo;
    Image informesH, informesM, informesMEsp;
    Image ordenaza, contratosH, contratosM;
    Image cajero, cajera;
    Image aux;
    ClienteImagen particular, estudiante, esquiador, esqExperto;
    //Timer hilo;
    Timer hora;
    int posX, posY;
    int nuevo;
    JLabel tiempo;
    int h=8, m=59, s=0;
    int VELOCIDAD=20;
    int LIMITE=500;
    //
    ArrayList<Cliente> listaClientes=new ArrayList<>();
    final Point entrada=new Point(395, 510);
    final Point doblar1=new Point(395, 330);
    final Point punto1=new Point(304, 330);
    final Point punto2=new Point(196, 330);
    final Point punto3=new Point(88, 330);
    final Point punto1Fin=new Point(304, 392);
    final Point punto2Fin=new Point(196, 392);
    final Point punto3Fin=new Point(88, 392);
    //variables importantes
    public Empleado informador1, informador2, informador3;
    public Empleado informador4, informador5, informador6;
    public Empleado contratador, cobranza;
    int lol=0;
    //para Reportes
    int numParticulares=0, numEstudiantes=0, numEsquiadores=0, numEsqExp=0;
    int paqNormal=0, paqTuristico=0, paqPremium=0, paqVip=0;
    int nuQueParticulares=0, nuQueEstudiantes=0, nuQueEsquiadores=0, nuQueEsqExp=0;
    public Main getMain(){return main;}
    /**
     * constructor de la clase
     */
    public PanelDibujo(Main main){
        this.main=main;
        //creamos datos de INFORMACIONES
        informador1= new Empleado();
        informador1.addPunto(304, 330);
        informador1.addPunto(304, 400);
        
        informador2= new Empleado();
        informador2.addPunto(196, 330);
        informador2.addPunto(196, 400);
        
        informador3= new Empleado();
        informador3.addPunto(88, 330);
        informador3.addPunto(88, 400);
        //desde aqui esta trabajando
        informador4= new Empleado();
        informador4.addPunto(268, 330);
        informador4.addPunto(268, 110);
        
        informador5= new Empleado();
        informador5.addPunto(164, 330);
        informador5.addPunto(164, 110);
        
        informador6= new Empleado();
        informador6.addPunto(60, 330);
        informador6.addPunto(60, 110);
        //
        contratador= new Empleado();
        contratador.addPunto(700, 290);
        contratador.addPunto(700, 370);
        contratador.addPunto(820, 430);//silla
        contratador.addPunto(750, 370);
        cobranza= new Empleado();
        cobranza.addPunto(700, 75);
        //cobranza.addPunto(posX, posY);
        /*********/
        cargarImagenes();
        hora=new Timer(VELOCIDAD, (java.awt.event.ActionEvent ae) -> {
            /*reloj*/
            int lim=pedirTimeRecarga();
            ++s; 
            if(s==60){
                s = 0;  ++m;
            }
            if(m==60){
                m = 0;  ++h;
            }
            if(h==25){
                h = 0;
            }
            nuevo++;
            if(nuevo>=lim&&lol<LIMITE){
                lol++;
                short tipoClienteGenerado=this.main.getDatosVariables().generarTipoCliente();
                addTipoCliente(tipoClienteGenerado);
                /*short tipoPaqueteComprado=this.main.getDatosVariables().generarTipoPaquete();
                addTipoPaquete(tipoPaqueteComprado);*/
                Cliente c=new Cliente(this, tipoClienteGenerado);
                listaClientes.add(c);
                c.start();
                nuevo=0;
                /*int num=informador1.atendidos+informador2.atendidos+informador3.atendidos
                        +informador4.atendidos+informador5.atendidos+informador6.atendidos;
                System.out.println("atendidos Totales:"+num);*/
            }
            actualizarHora();
        });
        
        /*reloj*/
        CustomFont cf = new CustomFont();
        tiempo = new JLabel();
        tiempo.setForeground(Color.green);
        tiempo.setFont(cf.MyFont(1, 30));
        tiempo.setBounds(410, 50, 150, 26);
        actualizarHora();
        add(tiempo);
    }
    /***** Para los reportes***/
    private void addTipoCliente(short n){
        if(n==1){        numParticulares++;}
        else if(n==2){   numEstudiantes++;}
        else if(n==3){   numEsquiadores++;}
        else{            numEsqExp++;}
    }
    private void addTipoPaquete(short n){
        if(n==1){        paqNormal++;}
        else if(n==2){   paqTuristico++;}
        else if(n==3){   paqPremium++;}
        else{            paqVip++;}
    }
    public void addQueTipoCliente(short n){
        if(n==1){        nuQueParticulares++;}
        else if(n==2){   nuQueEstudiantes++;}
        else if(n==3){   nuQueEsquiadores++;}
        else{            nuQueEsqExp++;}
    }
    /***************************/
    public void setVelocidad(int vel){VELOCIDAD=vel;}
    public void iniciar(){hora.start();
        for (Cliente cli : listaClientes) {
            cli.t.start();
        }
    }
    public void parar(){hora.stop();
        for (Cliente cli : listaClientes) {
            cli.t.stop();
        }
    }
    public void detener(){hora.stop();}
    public void restart(){hora.restart();}
    private void actualizarHora(){
        String time = (h<=9?"0":"")+h+":"+(m<=9?"0":"")+m;
        tiempo.setText(time);
    }
    private int pedirTimeRecarga(){
        return ditribTriangular.generar(main.getDatosVariables().getDatoTriangular(4).getValor()
                ,main.getDatosVariables().getDatoTriangular(4).getMaxMin() );
    }
    
    private void cargarImagenes(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        fondo=tk.getImage(getClass().getResource("/Images/fondo/fondoMain.png"));
        
        informesH=tk.getImage(getClass().getResource("/Images/administradores/estaticos/informesH.png"));
        informesM=tk.getImage(getClass().getResource("/Images/administradores/estaticos/informesM.png"));
        informesMEsp=tk.getImage(getClass().getResource("/Images/administradores/estaticos/informesMEsp.png"));
        
        ordenaza=tk.getImage(getClass().getResource("/Images/administradores/estaticos/ordenanza.png"));
        
        contratosH=tk.getImage(getClass().getResource("/Images/administradores/estaticos/contratosH.png"));
        contratosM=tk.getImage(getClass().getResource("/Images/administradores/estaticos/contratosM.png"));
        
        cajero=tk.getImage(getClass().getResource("/Images/administradores/estaticos/cajero.png"));
        cajera=tk.getImage(getClass().getResource("/Images/administradores/estaticos/cajera.png"));
        
        
        particular=new ClienteImagen(tk, "particular");
        estudiante=new ClienteImagen(tk, "estudiante");
        esquiador=new ClienteImagen(tk, "esquiador");
        esqExperto=new ClienteImagen(tk, "esq-experto");
        
    }
    public void paintComponent(Graphics grafico){
        super.paintComponents(grafico);
        Graphics2D g=(Graphics2D)grafico;
        //dibujo el fondo
        g.drawImage(fondo, 0, 0, fondo.getWidth(this), fondo.getHeight(this), this);
        //dibujo los trabajadores de infores varones
        g.drawImage(informesH, 88, 470, 45, 45, this);
        g.drawImage(informesMEsp, 196, 470, 45, 45, this);
        g.drawImage(informesH, 304, 470, 45, 45, this);
        //dibujo los trabajadores de informes mujeres
        /*g.drawImage(informesM, 60, 39, 45, 45, this);
        g.drawImage(informesM, 164, 39, 45, 45, this);
        g.drawImage(informesM, 268, 39, 45, 45, this);*/
        //dibujo a ordenanza
        g.drawImage(ordenaza, 504, 390, 45, 45, this);
        //dibujo a los de contratos
        //g.drawImage(contratosH, 900, 304, 45, 45, this);//400 en y
        g.drawImage(contratosM, 900, 433, 45, 45, this);
        //dibujo a los 2 cajeros
        g.drawImage(cajero, 827, 75, 45, 45, this);
        //g.drawImage(cajera, 827, 200, 45, 45, this);
        
        //g.setColor(new Color(174,188,160));
        g.fillRect(405, 50, 75, 30);
        g.setColor(Color.black);
        g.drawRect(405, 50, 75, 30);
        
        //prueba
        //g.drawImage(informesM, 700, 75, 45, 45, this);
        pintarClientes(g);
    }
    private void pintarClientes(Graphics2D g){
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteImagen im;
            if(listaClientes.get(i).tipo==1){im=particular;}
            else if(listaClientes.get(i).tipo==2){im=estudiante;}
            else if(listaClientes.get(i).tipo==3){im=esquiador;}
            else{im=esqExperto;}
            Image imAux;
            if(listaClientes.get(i).direccion==1){imAux=im.getAbajo();}
            else if(listaClientes.get(i).direccion==2){imAux=im.getDerecha();}
            else if(listaClientes.get(i).direccion==3){imAux=im.getArriba();}
            else{imAux=im.getIzquierda();}
            g.drawImage(imAux, (int)listaClientes.get(i).posX,(int)listaClientes.get(i).posY, 45, 45, this);
        }
        //g.drawImage(particular.getAbajo(), (int)punto3Fin.getX(),posY, 45, 45, this);
    }
    public class Cliente{
        short IN=5;//variable estatica
        boolean esAbajo=false;
        PanelDibujo panel;
        Timer t;
        int posX, posY;
        
        private Empleado informador;
        short tipo;// 1=particular, 2=estudiante, 3=esquiador, 4=esq. exp
        short direccion;//1=abajo, 2=dercha, 3=arriba, 4=izquierda
        boolean atendidoInformes=false;
        boolean atendidoContratos=false;
        boolean atendidoCobranzas=false;
        boolean concluyo=false;
        boolean deboSalir=false;
        //boolean meFui=false;
        
        int esperar=0;
        int esperarFilaContratos=0;
        int esperarFirmarContrato=0;
        int esperarCaja=0;
        public Cliente(PanelDibujo panel, short tipo){
            posX=395; posY=510;//posicion inicial de cualquier cliente
            this.tipo=tipo;
            direccion=3;//
            this.panel=panel;
            buscarInformadorVacio();
            //informador.setOcupado();
            int demora=ditribTriangular.generar(main.getDatosVariables().getDatoTriangular(5).getValor(), 
                        main.getDatosVariables().getDatoTriangular(5).getMaxMin());//tiempo de espera en informes
            int demoraFilaContrato=ditribTriangular.generar(main.getDatosVariables().getDatoTriangular(10).getValor(),
                        main.getDatosVariables().getDatoTriangular(10).getMaxMin());
            int demoraFirmar=ditribTriangular.generar(main.getDatosVariables().getDatoTriangular(tipo-1).getValor(),
                        main.getDatosVariables().getDatoTriangular(tipo-1).getMaxMin());
            int demoraCaja=ditribTriangular.generar(main.getDatosVariables().getDatoTriangular(9).getValor(),
                        main.getDatosVariables().getDatoTriangular(9).getMaxMin());
            boolean meRetirare=main.getDatosVariables().clienteSeRetira();//genero la prob de que si me retiro o no
            /**/
            if(!meRetirare){
                short tipoPaqueteComprado=main.getDatosVariables().generarTipoPaquete();
                this.panel.addQueTipoCliente(tipo);
                addTipoPaquete(tipoPaqueteComprado);
            }
            //boolean meRetirare=false;
            t=new Timer(VELOCIDAD, (java.awt.event.ActionEvent ae) -> {
                if(!atendidoInformes && esAbajo){//si no fui atendido en iformes y elegi abajo
                    if(posY<=330){
                        posY=330;posX-=IN;direccion=4;
                    }
                    if(posX<=informador.getPuntos().get(0).getX()){
                        posX=(int)informador.getPuntos().get(0).getX();direccion=1;posY+=(IN*2);
                    }
                    if(posX==informador.getPuntos().get(1).getX() && posY>=informador.getPuntos().get(1).getY()){
                        esperar++;posY-=IN;
                        if(esperar>=demora){
                            posY+=(IN*2);atendidoInformes=true;posY=(int)informador.getPuntos().get(1).getY();
                        }                    
                    }
                    posY-=IN;
                }//hasta aqui llegue a informes
                if(atendidoInformes && meRetirare && esAbajo && !atendidoContratos){//si decido retirarme y elegi abajo
                    posY-=IN;direccion=3;
                    if(posY<informador.getPuntos().get(0).getY()-40){
                        posY=(int)informador.getPuntos().get(0).getY()-40;posX+=IN;direccion=2;
                    }
                    if(posX>395+40){//salida
                        posY+=(IN*2);direccion=1;
                    }
                    if(posY>510+60){t.stop();informador.decrementarCola();}
                }
                if(atendidoInformes && !meRetirare && esAbajo && !atendidoContratos && !atendidoCobranzas){//si NO decido retirarme y elegi abajo
                    posY-=IN;//voy arriba
                    direccion=3;
                    if(posY<=informador.getPuntos().get(0).getY()-40){//voy derecha
                        posY=(int)informador.getPuntos().get(0).getY()-40;
                        posX+=IN;
                        direccion=2;
                    }
                    if(posX>contratador.getPuntos().get(0).getX() && posY>=290){//salida
                        posY+=(IN*2);
                        direccion=1;
                    }
                    if(posY>=contratador.getPuntos().get(1).getY()){
                        posY=(int)contratador.getPuntos().get(1).getY();
                        direccion=2;
                        esperarFilaContratos++;
                        if(esperarFilaContratos>=demoraFilaContrato){
                            posX=(int)contratador.getPuntos().get(2).getX();
                            posY=(int)contratador.getPuntos().get(2).getY();
                            esperarFirmarContrato++;
                            if(esperarFirmarContrato>=demoraFirmar){
                                atendidoContratos=true;
                                posX=(int)contratador.getPuntos().get(3).getX();
                                posY=(int)contratador.getPuntos().get(3).getY();
                                direccion=3;
                            }
                        }
                    }
                }
                if(atendidoContratos){
                    posY-=IN;//arriba
                    if(posY<=70 && !concluyo){
                        direccion=2;
                        posY+=IN;
                        esperarCaja++;
                        if(esperarCaja>=demoraCaja){
                            posX=(int)contratador.getPuntos().get(3).getX()-40;
                            concluyo=true;
                            direccion=1;
                        }
                    }
                    if(concluyo){
                        posY+=IN*2;//abajo
                    }
                    if(posY>=contratador.getPuntos().get(0).getY()+40 && concluyo){
                        posY-=IN;
                        posX-=IN;
                        direccion=4;//
                    }
                    if(posX<440 && concluyo){
                        posX+=IN;
                        direccion=1;
                        deboSalir=true;
                    }
                    if(deboSalir){
                        posY+=IN;
                        if(posY>510+60){t.stop();informador.decrementarCola();}
                    }
                }
                /**la otra alternativa*/
                /**********************/
                /**********************/
                /*if(!atendidoInformes && !esAbajo){//si no fui atendido en iformes y NO elegi abajo
                    posY-=IN;direccion=3;
                    if(posY<=330){
                        posY+=IN;posX-=IN;direccion=4;
                    }
                    if(posX<=informador.getPuntos().get(0).getX()){
                        posX=(int)informador.getPuntos().get(0).getX();direccion=3;posY-=IN;
                    }
                    if(posX==informador.getPuntos().get(1).getX() && posY<=informador.getPuntos().get(1).getY()){
                        esperar++;posY+=IN;
                        if(esperar>=demora){
                            atendidoInformes=true;posY=(int)informador.getPuntos().get(1).getY();
                        }                    
                    }
                }//hasta aqui llegue a informes
                else if(atendidoInformes && meRetirare && !esAbajo){//si decido retirarme y NO elegi abajo
                    posY+=IN;direccion=1;System.out.println("ERROR");
                    if(posY>=informador.getPuntos().get(0).getY()-40&&posX<395+40){
                        posY=(int)informador.getPuntos().get(0).getY()-40;posX+=IN;direccion=2;
                    }
                    if(posX>=395+40){//salida
                        posX=395+40;posY+=IN;direccion=1;System.out.println("ERROR");
                    }
                    if(posY>510+60){t.stop();informador.decrementarCola();}
                }
                else if(atendidoInformes && !meRetirare && !esAbajo){//si decido NO retirarme y NO elegi abajo
                    posY+=IN;
                    direccion=1;
                    if(posY>=informador.getPuntos().get(0).getY()-40 && posX<395+40){
                        posY=(int)informador.getPuntos().get(0).getY()-40;
                        posX+=IN;
                        direccion=2;
                        System.out.println("REVISA");
                    }
                }*/
                this.panel.repaint();
            });
            
        }
        private void buscarInformadorVacio(){
            if(informador1.cola<informador2.cola){
                informador=informador1; esAbajo=true;   
            }else if(informador2.cola<informador3.cola){
                informador=informador2; esAbajo=true;
            }else if(informador3.cola<informador4.cola){
                informador=informador3; esAbajo=true;
            }
            /*else if(informador4.cola<informador5.cola){
                informador=informador4; esAbajo=false;
            }else if(informador5.cola<informador6.cola){
                informador=informador5; esAbajo=false;
            }else{
                informador=informador6; esAbajo=false;
            }*/
            else{ informador=informador3; esAbajo=true;}//probando
            informador.aumentarCola();
            informador.incrementarAtendidos();
        }
        public void start(){t.start();}
        public void sotp(){t.stop();}
        public void restart(){t.restart();}
        
        //public void finalizo(){concluyo=true;}
        public int getPX(){return posX;}
        public int getPY(){return posY;}
    }
    
    public class Empleado {
        private int cola=0;
        private int atendidos=0;
        private ArrayList<Point> puntos;
        int contador=0;
        public Empleado(){
            puntos=new ArrayList<>(); 
        }
        public void addPunto(int posX, int posY){
            puntos.add(new Point(posX, posY));
        }
        public ArrayList<Point> getPuntos(){return puntos;
        }
        
        public int getAtendidos(){return atendidos;}
        public void incrementarAtendidos(){atendidos++;}
        
        public void aumentarCola(){cola++;}
        public void decrementarCola(){cola--;}
        public int getCola(){return cola;}
    }
    
}
