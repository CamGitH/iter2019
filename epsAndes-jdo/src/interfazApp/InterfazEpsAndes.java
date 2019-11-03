package interfazApp;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import negocio.CampañaPrevencion;
import negocio.Eps;
import negocio.EpsAndes;
import negocio.Servicio;




@SuppressWarnings("serial")
public class InterfazEpsAndes extends JFrame implements ActionListener{

	private static Logger log = Logger.getLogger(InterfazEpsAndes.class.getName());
	
	private static final String CONFIG_INTERFAZ = "./resources/config/interfaceConfigApp.json";
	
	private static final String CONFIG_INTERFAZ_Admin = "./resources/config/interfaceConfigAdmin.json";
	
	private static final String CONFIG_INTERFAZ_Medico = "./resources/config/interfaceConfigDemo.json";
	
	private static final String CONFIG_INTERFAZ_Recepcionista = "./resources/config/interfaceConfigRecepcionista.json";
	
	private static final String CONFIG_INTERFAZ_Gerente = "./resources/config/interfaceConfigGerente.json";
	
	private static final String CONFIG_INTERFAZ_Admin_Campaña = "./resources/config/interfaceConfigAdminCampaña.json";
	
	
	private static final String CONFIG_TABLAS = "./resources/config/TablasBD_A.json"; 
	
	private JsonObject tableConfig;
	
	private EpsAndes epsAndes;
	
	private JsonObject guiConfig;
	
	private PanelDatos panelDatos;
	
	private JMenuBar menuBar;
	
	
	public InterfazEpsAndes()
	{
		
		String [] usuarios= {"Afiliado", "Medico", "Recepcionista", "Administrador", "Gerente", "Organizador de campaña"};
		
		ImageIcon icono= new ImageIcon("./resources/config/icono.jpg");
		
		String resp = (String) JOptionPane.showInputDialog(this, "Seleccione su perfil de usuario", "Carrera", JOptionPane.DEFAULT_OPTION,icono, usuarios, usuarios[0]);
		
		if(resp.equals("Afiliado"))
		{
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
		}
		else if (resp.equals("Administrador")) {
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_Admin);
		}
		else if (resp.equals("Medico")) {
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_Medico);
		}
		else if (resp.equals("Recepcionista")) {
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_Recepcionista);
		}
		else if (resp.equals("Gerente")) {
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_Gerente);
		}
		else if (resp.equals("Organizador de campaña")) {
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_Admin_Campaña);
		}
		
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        epsAndes = new EpsAndes (tableConfig);
        
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );
        ImageIcon imagen=new ImageIcon (path);
        JLabel img=new JLabel(imagen);
        img.setPreferredSize(new Dimension(200,200));
        

        setLayout (new BorderLayout());
        add (img, BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER ); 
	}
	
	private JsonObject openConfig(String tipo, String archConfig) {
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
	}
	
	private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
	
	private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "EpsAndes APP ";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }
	
	
	public void adicionarEps()
	{
		try 
    	{
    		String nombreEps = JOptionPane.showInputDialog (this, "Nombre de la eps?", "Adicionar Eps", JOptionPane.QUESTION_MESSAGE);
    		String nombreGerente=JOptionPane.showInputDialog (this, "Nombre del Gerente?", "Adicionar Gerente", JOptionPane.QUESTION_MESSAGE);
    		if (nombreEps != null&& nombreGerente != null)
    		{
    			Eps tb = epsAndes.agregarEps(nombreEps, nombreGerente);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear una eps con este nombre: " + nombreEps);
        		}
        		String resultado = "En adicionar Eps\n\n";
        		resultado += "Eps adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	
	public void RegistrarCampaña()
	{
		try 
    	{
    		String afiliadosEsperados = JOptionPane.showInputDialog (this, "ingrese el numero de afiliados esperados en la campaña?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
    		String fechaInicial=JOptionPane.showInputDialog (this, "Nombre del Gerente?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
    		String fechaFinal=JOptionPane.showInputDialog (this, "Nombre del Gerente?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
    		String servicios=JOptionPane.showInputDialog (this, "Ingrese los servicios que desea para la campaña separados por comas", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
    		String capacidades=JOptionPane.showInputDialog (this, "Ingrese los servicios que desea para la campaña separados por comas", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
    		ArrayList<Servicio> serviciosRegistrados= new ArrayList<Servicio>();
    		ArrayList<Servicio> servciosNoRegistrados= new ArrayList<Servicio>();
    		if (afiliadosEsperados != null&& fechaFinal != null&& fechaInicial != null && servicios !=null && capacidades !=null)
    		{
    			String []listaServicios= servicios.split(",");
    			String [] listaCapacidades= capacidades.split(",");
    			

    			for (int i = 0; i < listaServicios.length; i++) {
					
    				Servicio buscado= epsAndes.darServiciosporNombre(listaServicios[i]);
    				if(buscado!=null)
    				{
    					if(buscado.getCapacidad()<=Integer.parseInt(listaCapacidades[i]))
    					{
    						serviciosRegistrados.add(buscado);
    					}
    					else
    					{
    						servciosNoRegistrados.add(buscado);
    					}
    				}
    				else
    				{
    					servciosNoRegistrados.add(buscado);
    				}
				}
    			if(!servciosNoRegistrados.isEmpty())
    			{
    				String resp="No se pudo crear esta campaña porque estos servicios no estan disponibles: ";
    				for (int i = 0; i < servciosNoRegistrados.size(); i++) {
						resp+=servciosNoRegistrados.get(i).getTipo();
					}
    				
    				throw new Exception(resp);
    			}
    			else
    			{
    				for (int i = 0; i < serviciosRegistrados.size(); i++) {
						epsAndes.reservarServicio(serviciosRegistrados.get(i).getCodigoServicio(), Integer.parseInt(listaCapacidades[i]));
					}
    			
    			CampañaPrevencion tb = epsAndes.agregarEps(nombreEps, nombreGerente);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear una eps con este nombre: " + nombreEps);
        		}
        		String resultado = "En adicionar Eps\n\n";
        		resultado += "Eps adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    			}
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	
	
	
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		return resultado;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String evento=  arg0.getActionCommand();
		
		if(evento.equals("registrarEps"))
		{
			
			epsAndes.agregarEps("Yuyeimi", "Ricardo Millos");
			panelDatos.actualizarInterfaz("Todo bien todo correcto");
		}
		
	}
	
	
	 public static void main( String[] args )
	    {
	        try
	        {
	        	
	            // Unifica la interfaz para Mac y para Windows.
	            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
	            InterfazEpsAndes interfaz = new InterfazEpsAndes( );
	            interfaz.setVisible( true );
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace( );
	        }
	    }
	

}
