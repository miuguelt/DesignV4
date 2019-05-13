
package Principal;

/**
 *
 * @author Jairo Castellar && Leonardo Romero
 */
public class operaciones {
    double C,H,alfaa,uc,Kr,Hu,Bc;
    double Oc = 0;
    double S = 0;
    double Au = 114000000;
    double rHD, rhfD, rdHD, rDTD, rhrD, rhfaldaD, rdDD, rdBD, rLBD, raD, rddh, rDpiedh, rhbminDpie, rhbhbmin, rbbhb, rLKTD, rCBD, rdmunbieD, rlmunbiedmunbie, rdmunbanD, rlmunbandmunban;
     //CALCULO DEL CICLO
    double DT,Tr,pr,wad,Tk,pk,BX,E,n1; // DT=Aumento de Temperatura, Tr=Temp. gases residuales, pr=Presión gases residuales, wad=Velocidad media de los gases de entrada, Tk=Temperatura local, pk=presión local,BX=coeficiente de amortiguación, E=relación de compresión, n1=coef. politrópico de compresión
    double ua = 28.97;
    double Ra = 287; // Constante universal de los gases
    double fk = 1; // densidad del aire
    double n2; // Coef. politrópico de expansión
    //Parametros generales del ciclo
    double A,B,vp,J,n,Ni; // A y B coef. de fricción, vp=velocidad media del pistón
    double f=1; // densidad del aire
    double fii = 0.95; // Coef. para ajuste de la presión indicada
    double i = 1; // Numero de cilindros
    double nm = 0.9; // eficiencia mecánica
    
    double Xz; // Coef. de aprovechamiento de calor
    double fiz = 0.85; // Coef. para ajuste de la presión máxima
    
    //Calculos de los esfuerzos en las piezas
    double L; //Relación biela-manivela
    double mpAA=0.5; // Masa del émbolo en la sección AA
    double Gcomp = 35000000; //Esfuerzo de compresión en el pistón
    double Ebulo=22000;  // Modulo de Young del Acero
    double Km = 0.82; // 
    
     
    public operaciones(double C, double H, double alfaa, double uc, double Kr, double Hu, double Bc, double rHD, double rhfD, double rdHD, double rDTD, double rhrD, double rhfaldaD, double rdDD, double rdBD, double rLBD, double raD, double rddh, double rDpiedh, double rhbminDpie, double rhbhbmin, double rbbhb, double rLKTD, double rCBD, double rdmunbieD, double rlmunbiedmunbie, double rdmunbanD, double rlmunbandmunban, double DT, double Tr, double pr, double wad, double Tk, double pk, double BX, double E, double n1, double n2, double vp, double J, double n, double Ni, double Xz, double L) {
        this.C = C; // % Carbono
        this.H = H; // % Hidrogeno
        this.alfaa = alfaa; // Exceso de aire
        this.uc = uc; 
        this.Kr = Kr;
        this.Hu = Hu; // Poder calorífico
        this.Bc = Bc;
        this.rHD = rHD; // relaciones de las recomendaciones
        this.rhfD = rhfD;
        this.rdHD = rdHD;
        this.rDTD = rDTD;
        this.rhrD = rhrD;
        this.rhfaldaD = rhfaldaD;
        this.rdDD = rdDD;
        this.rdBD = rdBD;
        this.rLBD = rLBD;
        this.raD = raD;
        this.rddh = rddh;
        this.rDpiedh = rDpiedh;
        this.rhbminDpie = rhbminDpie;
        this.rhbhbmin = rhbhbmin;
        this.rbbhb = rbbhb;
        this.rLKTD = rLKTD;
        this.rCBD = rCBD;
        this.rdmunbieD = rdmunbieD;
        this.rlmunbiedmunbie = rlmunbiedmunbie;
        this.rdmunbanD = rdmunbanD;
        this.rlmunbandmunban = rlmunbandmunban;
        this.DT = DT;
        this.Tr = Tr;
        this.pr = pr;
        this.wad = wad;
        this.Tk = Tk;
        this.pk = pk;
        this.BX = BX;
        this.E = E;
        this.n1 = n1;
        this.n2 = n2;
        this.vp = vp;
        this.J = J; // Relación carrera/diámetro
        this.n = n;
        this.Ni = Ni;
        this.Xz = Xz;
        this.L = L;
    }
    
    // Cálculo del ciclo termodinámico
    
    public double getYB()
    {
       return  rdBD/rdHD;       
    }
    public double getlo() // Cantidad teórica de aire necesaria en Kg
    {
        return (8*this.C/3+8*this.H-this.Oc)/0.23;
    }
    public double getLo() // Cantidad teórica de aire necesaria en Kmol
    {
        return (this.C/21+this.H/4+this.Oc/32)/0.21;
    }
    public double getM1() // Cantidad total de la mezcla carburante
    {
        return (this.alfaa*getLo()+1/this.uc);        
    }
    public double getG1() 
    {
        return (1+this.alfaa*getlo());        
    }
    public double getMco() // fracción molar de CO en los productos
    {
        if (this.alfaa < 1) return 0.42*getLo()*(1-this.alfaa)/(1+this.Kr);
        else return 0;
    }
    public double getMco2() // fracción molar de CO2 en los productos
    {
        if(this.alfaa < 1) return (this.C/2-getMco());     
        else return this.C/12;
    }    
    public double getMh2() // fracción molar de H2 en los productos
    {
        if (this.alfaa < 1) return (0.42*this.Kr*getMco());
        else return 0;
    }   
    public double getMh2o() // fracción molar de H2O en los productos
    {
       if(this.alfaa < 1) return (this.H/2-getMco());
       else return this.H/2;
    }
    public double getMn2()// fracción molar de N2 en los productos
    {
        if(this.alfaa < 1) return (0.79*this.alfaa*getLo());
        else return 0.79*this.alfaa*getLo();
    
    }
    public double getMo2() // fracción molar de O2 en los productos
    {
        if(this.alfaa < 1) return 0;
        else return (0.21*(this.alfaa-1)*getLo());         
    }
    public double getM2()// Cantidad de productos de la combustión
    {
        return (getMco()+getMco2()+getMh2()+getMh2o()+getMn2()+getMo2());         
    }
    public double getuo() // Relación M2/M1
    {
        return (getM2()/getM1());         
    }    
    public double getDHuquim() // Fracción de calor que no se perdió
    {
        return this.Au*(1-this.alfaa)*getLo();
    }
    // Cálculo de la fase Admisión-Compresión
  
    public double getPa() //Presión de Admisión
    {
      return (this.pk-(this.BX*this.wad*this.wad*this.fk/2));   
    }
    public double getYr() 
    {
      return (((this.Tk+this.DT)/(this.Tr))*((pr)/(this.E*getPa()-this.pr)));   
    }
    public double getTa() // Temperatura de Admisión
    {
      return ((this.Tk+this.DT+(getYr()*this.Tr))/(1+getYr()));   
    }
    public double getnv() // Eficiencia Volumétrica
    {
      return (this.E*getPa()*this.Tk/(this.pk*getTa()*(E-1)*(1+getYr())));   
    }
    public double getPc() //Presión de Compresión
    {
      return (getPa()*Math.pow(this.E,this.n1));    
    }
    public double getTc() // Temperatura de Compresión
    {
        return (getTa()*Math.pow(this.E,this.n1-1.0));
    }
    // Fase Compresión-Expulsión
    
    public double getur()
    {
        return (getuo()+getYr())/(1+getYr());
    }
    public double getUc() //Energía interna de compresión
    {
        double Tc = getTc();      
        double Uc=0;
        double cEspecifico[][] = new double[26][2]; // Creación del vector para la tabla
         cEspecifico[0][0]=0;
        for(int k = 1; k<26; k++)
        {
            cEspecifico[k][0] = cEspecifico[k-1][0] + 100;            
        }
        //Tabla 25 de calores específicos del Anexo B
        cEspecifico[0][1]=20.758;
        cEspecifico[1][1]=20.838;
        cEspecifico[2][1]=20.984;
        cEspecifico[3][1]=21.206;
        cEspecifico[4][1]=21.474;
        cEspecifico[5][1]=21.78;
        cEspecifico[6][1]=22.09;
        cEspecifico[7][1]=22.408;
        cEspecifico[8][1]=22.713;
        cEspecifico[9][1]=23.006;
        cEspecifico[10][1]=23.283;
        cEspecifico[11][1]=23.547;
        cEspecifico[12][1]=23.794;
        cEspecifico[13][1]=24.018;
        cEspecifico[14][1]=24.25;
        cEspecifico[15][1]=24.459;
        cEspecifico[16][1]=24.652;
        cEspecifico[17][1]=24.863;
        cEspecifico[18][1]=25.003;
        cEspecifico[19][1]=25.167;
        cEspecifico[20][1]=25.326;
        cEspecifico[21][1]=25.474;
        cEspecifico[22][1]=25.611;
        cEspecifico[23][1]=25.745;
        cEspecifico[24][1]=25.87;
        cEspecifico[25][1]=25.992;
        for(int j = 0; j<26; j++)
        {
            if(Tc < cEspecifico[j][0]) 
                 if ((Tc+50) > cEspecifico[j][0]) 
                 {
                     Uc = cEspecifico[j][1]*getTc()*1000;
                     return Uc;
                 }
                 else
                 {
                     Uc = cEspecifico[j-1][1]*getTc()*1000; 
                     return Uc;
                 }                     
        }
        return Uc;
    }
    
    
    public double getUz() // Energía interna de combustión
    {
        double ope= (Xz*(Hu-getDHuquim())/(getM1()*(1+getYr()))) + getUc();
        return ((ope)/(getur()*1000000));
    }
    
     public double getTz() // Temperatura de combustión
    {
        double Uz=getUz();
        double Tz=0;
        double eInterna[][] = new double[26][2]; // Creación del vector para la tabla
        eInterna[0][0]=0;
        for(int k = 1; k<26; k++)
        {
            eInterna[k][0] = eInterna[k-1][0] + 100; 
        }  
        // Tabla 26 de Energías internas Anexo B
        eInterna[0][1]=0;
        eInterna[1][1]=2.256;
        eInterna[2][1]=4.5766;
        eInterna[3][1]=6.3969;
        eInterna[4][1]=9.4881;
        eInterna[5][1]=12.074;
        eInterna[6][1]=14.75;
        eInterna[7][1]=17.513;
        eInterna[8][1]=20.309;
        eInterna[9][1]=23.26;
        eInterna[10][1]=26.2262;
        eInterna[11][1]=29.249;
        eInterna[12][1]=32.325;
        eInterna[13][1]=35.443;
        eInterna[14][1]=38.601;
        eInterna[15][1]=41.676;
        eInterna[16][1]=45.014;
        eInterna[17][1]=48.268;
        eInterna[18][1]=51.537;
        eInterna[19][1]=54.835;
        eInterna[20][1]=58.15;
        eInterna[21][1]=61.491;
        eInterna[22][1]=64.846;
        eInterna[23][1]=68.208;
        eInterna[24][1]=71.588;
        eInterna[25][1]=74.976;        
        for(int j = 0; j<26; j++)
        {
         
            
                 if(Uz < eInterna[j][1]) 
                 if ((Uz+0.3) > eInterna[j][1]) 
                 {
                     Tz = eInterna[j][0];
                     return Tz;
                 }
                 else
                 {
                     if(eInterna[j][1]==0)
                     {
                         Tz = eInterna[j][0];
                     return Tz;
                     }
                     else
                     {                         
                         Tz = eInterna[j-1][0]; 
                         return Tz; 
                     }                     
                 }                  
        }
        return Tz;
        
    }    
    public double getLp()
    {        
        return getur()*getTz()/getTc();
    }    
    public double getPz() // Presión de combustión
    {
        return getLp()*getPc();
    }
    public double getPzredo()//Presión máxima de combustión
    {
        return this.fiz*getPz();
    }
    public double getPcc()
    {
        return getPz();
    }
    public double getPb() // Presión de expulsión
    {
        return getPz()/(Math.pow(this.E, this.n2));
    }
    public double getTb() // Temperatura de expulsión
    {
        return getTz()/(Math.pow(this.E, this.n2-1.0));
    }
    
    //Parametros generales del ciclo
    
    
    public double getPit() // Presión indicada
    {
        double in=getLp()*(this.f-1)+getLp()*(1-(1/Math.pow(E,this.n2-1.0 )))/(this.n2-1)-(1-1/1/Math.pow(this.E,this.n1-1 ))/(this.n1-1);
        return getPa()*Math.pow(this.E,this.n1)*in/(E-1);
    }
    public double getPi() // Presión indicada real
    {        
        return this.fii*getPit();
    }
    public double getVh() // Cilindrada
    {
        return ((60*Ni)/(getPi()*n))*1000000*42.78;
    }
    public double getD() //Diámetro
    {   
        return (Math.pow(4*getVh()/(Math.PI*J), 1.0/3.0));
    }
    public double getS() //Carrera
    { 
        return (J*getD());
    }
    public void aplicarAB() // Coef. de fricción
    {
        
        double cociente = getS()/getD();
        if(cociente<1)
        {
            this.A = 0.04;
            this.B = 0.0135;
        }
        else
        {
            this.A = 0.05;
            this.B = 0.0155;
        }
    }
    public double getPm() // Presión de fricción
    {
        aplicarAB();
        return (this.A+this.B*this.vp)*1000000;
    }
    public double getPe() // Presión de frenado
    {
        return (getPi()-getPm());
    }
    public double getNe() // Potencia al freno 
    {
        return (nm*Ni);
    }
    
    //Calculos de los esfuerzos en las piezas
    // Pistón
    
    public double getmp() // Masa del pistón
    {
        return 0.01*Math.PI*getD()*getD()/4;
    }
    public double getmb() // Masa de la biela
    {
        return 0.015*Math.PI*getD()*getD()/4;
    }
    
    public double getGflexion() // Esfuerzo de flexión del pistón
    {
        return (0.25*getPzredo()*Math.pow(rdBD*getD()/rdDD*getD(), 2));
    }
    public double getR() // Longitud de la manivela
    {
        return 15.0*vp/(2.0*Math.PI*n);
    }
    public double getAp() // Área del pistón
    {
        return (Math.PI*getD()*getD()/4);
    }
    public double getFzmax() // Fuerza máxima de combustión
    {
        return getPzredo()/getAp();
    }
    public double getAAA() // Área de la sección AA del pistón
    {
        return getFzmax()/Gcomp;
    }
     public double getGflexp() // Esfuerzo de flexión en la sección AA
    {
        return (0.0045*getPzredo()*Math.pow(getD()/rhrD*getD(), 2.0));
    }
    public double gettflexp()// Esfuerzo de torsión en la sección AA
    {
        return (0.0314*getPzredo()*getD()/rhrD*getD());
    }
    
 

//Fuerzas en el émbolo
    

    public double getw() // Vel. angular [rad/seg]
    {
        return (n*Math.PI/30.0);
    }
    public double getFip() // Fuerza sobre el pistón 
    {
        return -(getmp()*getw()*getw()*getR()*(1-L));
    }
    public double getKR()
    {
        return (1.5-15*Math.pow(getYB()-0.4,3.0));
    }
    public double getPembolo() // Presión sobre el émbolo
    {
        return (getPzredo()*getAp()+Km*getFip())/(rdHD*getD()*(rLBD*getD()-rDTD*getD()));
    }
    
    public double getPbiela() // Presión sobre la biela
    {
        return (getPzredo()*getAp()+Km*getFip())/(rdHD*getD()*(raD*getD()));
    }
    public double getF()
    {
        return (getPzredo()*getAp()+Km*getFip())/1000000;
    }
    public double getGflexbulo() // Esfuerzo de flexión sobre el bulón
    {
        return (getF()*(rLBD*getD()+2*rDTD*getD()-1.5*raD*getD()))/(1.2*Math.pow(rdHD*getD(), 3.0)*(1-Math.pow(getYB(), 4.0)));
    }
     public double gettbulon() // Esfuerzo de torsión sobre el bulón
    {
        return (0.85*getF()*(1+getYB()+getYB()*getYB()))/((1-Math.pow(getYB(), 4))*Math.pow(rdHD*getD(), 2.0));
    }
    public double getDdmax() // Holgura máxima
    {
        return ((0.09*getF()*getKR())/(Ebulo*rLBD*getD())*Math.pow((1+getYB())/(1-getYB()), 3.0));
    }
    public double getGa1() // Esfuerzo en el punto a1
    {
        double ope = 0.19*(2+getYB())*(1+getYB())/Math.pow(1-getYB(), 2.0)-1/(1-getYB());
        return (getF()*ope*getKR()/(rLBD*getD()*rdHD*getD()));
    }
    public double getGi2() // Esfuerzo en el punto i2
    {
        double ope = 0.19*(1+2*getYB())*(1+getYB())/(Math.pow(1-getYB(), 2.0)*getYB())-1/(1-getYB());
        return -(getF()*ope*getKR()/(rLBD*getD()*rdHD*getD()));
    }
    public double getGa3() // Esfuerzo en el punto a3
    {
        double ope = 0.174*(2+getYB())*(1+getYB())/Math.pow(1-getYB(), 2.0)-0.636/(1-getYB());
        return -(getF()*ope*getKR()/(rLBD*getD()*rdHD*getD()));
    }
    public double getGi4() // Esfuerzo en el punto i4
    {
        double ope = 0.174*(1+2*getYB())*(1+getYB())/(Math.pow(1-getYB(), 2.0)*getYB())-0.636/(1-getYB());
        return (getF()*getKR()*ope/(rLBD*getD()*rdHD*getD()));
    }
   

//Pagina 7
    
    public double getrm() //Hacer una tabla
    {          
        return (rDpiedh*rdHD*getD()+rddh*rdHD*getD());        
    }
    public double getNo(double ya) //Hacer una tabla
    {
          ya=ya*Math.PI/180;
        return (getFip()*(0.572-0.0008*ya));        
    }
    public double getMo(double ya)
    {
          ya=ya*Math.PI/180;
        return (getFip()*(0.00033* ya -0.00297));        
    }
 
    // Bloque
    
    public double getespesor()
    {
        double ope = (0.5*getD()*(Math.pow((550000000+0.4*getPzredo())/(550000000-1.3*getPzredo()), 1.0/2.0)-0.7));
        return ope;        
    }
    
    public double getGco()
    {
        return (0.5*getPzredo()*getD()/getespesor());        
    }
    public double getGt()
    {
        return (110000*10400000*getPzredo()/(2*(1-0.25)));        
    }
    public double getPosicion(double x)
    {
        x=x*Math.PI/180;
        return (getR()*(1-Math.cos(x))+getR()*L*(1-Math.cos(2*x))/4)*100;
    }
    public double getVelocidad(double v)
    {
          v=v*Math.PI/180;
        return getR()*getw()*(Math.sin(v))*100;
    }
    public double getAceleracion(double a)
    {
          a=a*Math.PI/180;
        return getR()*getw()*getw()*(Math.cos(a))/10;
    }
    public double getmL()
    {
        return getmp()+0.3*getmb();
    }
    public double getFiner(double x1)
    {
          x1=x1*Math.PI/180;
        return getmL()*getR()*getw()*getw()*(Math.cos(x1)+L*Math.cos(2*x1));
    }
    public double getN(double x2)
    {
        x2=x2*Math.PI/180;
        return getPz()*L*Math.sin(x2)/1000000;
    }
   
}

