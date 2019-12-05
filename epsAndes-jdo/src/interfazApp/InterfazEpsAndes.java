package interfazApp;

import java.awt.BorderLayout;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.annotations.Value;
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
import negocio.Ips;
import negocio.MedicoGeneral;
import negocio.Servicio;
import negocio.Usuario;




@SuppressWarnings("serial")
public class InterfazEpsAndes extends JFrame implements ActionListener{

	
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
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();		
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
			titulo = "EpsAndes APP ";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
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
	
	//R0
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
	
	//R2
	public void adicionarUsuario()
	{
		try 
    	{
			String [] tipos= {"CEDULA", "TARJETA DE IDENTIDAD", "CEDULA DE EXTRANJERIA"};
			
			ImageIcon icono= new ImageIcon("./resources/config/icono.jpg");
			
			
    		String nombre = JOptionPane.showInputDialog (this, "Nombre del Usuario?", "Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
    		String apellido=JOptionPane.showInputDialog (this, "Apellido del Usuario?", "Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
    		String correo = JOptionPane.showInputDialog (this, "Correo del usuario?", "Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);
    		String nombreEps = JOptionPane.showInputDialog (this, "Nombre de la eps?", "Adicionar Usuario", JOptionPane.QUESTION_MESSAGE);

			String resp = (String) JOptionPane.showInputDialog(this, "Seleccione su tipo de identificacion", "Seleccione", JOptionPane.DEFAULT_OPTION,icono, tipos, tipos[0]);
    		if (nombre != null&& apellido != null)
    		{
    			Usuario tb = epsAndes.agregarUsuario(correo, nombre, apellido, resp, nombreEps);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un usuario con este nombre: " + nombre);
        		}
        		String resultado = "En adicionar Usuario\n\n";
        		resultado += "Usuario adicionada exitosamente: " + tb;
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
	
	
	
	
	//R3
	public void adicionarIps()
	{
		try 
    	{
    		String nombreIps = JOptionPane.showInputDialog (this, "Nombre de la ips?", "Adicionar Ips", JOptionPane.QUESTION_MESSAGE);
    		String localizacion =JOptionPane.showInputDialog (this, "Localizacion de la ips?", "Adicionar Ips", JOptionPane.QUESTION_MESSAGE);
    		String recepcionista =JOptionPane.showInputDialog (this, "Localizacion de la ips?", "Adicionar Ips", JOptionPane.QUESTION_MESSAGE);
    		String nombreEps=JOptionPane.showInputDialog (this, "Localizacion de la ips?", "Adicionar Ips", JOptionPane.QUESTION_MESSAGE);
    		
    		if (nombreIps != null)
    		{
    			Ips tb = epsAndes.agregarIps(nombreIps, localizacion, recepcionista, nombreEps);
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
	
	//R4
	public void adicionarMedico()
	{
		try 
    	{
			
			
    		String nombre = JOptionPane.showInputDialog (this, "Nombre del Medico?", "Adicionar Medico", JOptionPane.QUESTION_MESSAGE);
    		String apellido=JOptionPane.showInputDialog (this, "Apellido del Medico?", "Adicionar Medico", JOptionPane.QUESTION_MESSAGE);
    		String registroMedico = JOptionPane.showInputDialog (this, "Correo del usuario?", "Adicionar Medico", JOptionPane.QUESTION_MESSAGE);
    		String nombreIps = JOptionPane.showInputDialog (this, "Nombre de la ips?", "Adicionar Medico", JOptionPane.QUESTION_MESSAGE);

			if (nombre != null&& apellido != null)
    		{
    			MedicoGeneral tb = epsAndes.agregarMedicoGeneral(nombre, apellido, registroMedico, nombreIps);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un medico con este nombre: " + nombre);
        		}
        		String resultado = "En adicionar Medico\n\n";
        		resultado += "Medico adicionada exitosamente: " + tb;
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
	
	//R7
	public void cancelarServiciosCampaña()
	{
		try 
    	{
    		String servicios = JOptionPane.showInputDialog (this, "Ingrese los id de los servicios que desea cancelar separados por comas", "Cancelar Servicios", JOptionPane.QUESTION_MESSAGE);
    		String codigoCampaña=JOptionPane.showInputDialog (this, "Ingrese el id de la campaña", "Id de campaña de la cual se van a eliminar servicios", JOptionPane.QUESTION_MESSAGE);
    		if (servicios != null&& codigoCampaña != null)
    		{
    			String [] listaServicios = servicios.split(",");
    			for (int i = 0; i < listaServicios.length; i++) {
					long servicioEliminado= epsAndes.cancelarServicioCampaña(Long.parseLong(listaServicios[i]));
					if (servicioEliminado == 0)
	        		{
	        			throw new Exception ("No se pudo cancelar el servicio con este id: " + listaServicios[i]);
	        		}
    			}
    			
        		String resultado = "En cancelar servicios\n\n";
        		resultado += "Se eliminaron los servicios";
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
	//R12
	public void deshabilitarServicios()
	{
		try 
    	{
    		String servicios = JOptionPane.showInputDialog (this, "Ingrese los id de los servicios que desea deshabilitar separados por comas", "Cancelar Servicios", JOptionPane.QUESTION_MESSAGE);
    		String FechaI=JOptionPane.showInputDialog (this, "Ingrese la fecha inicial desde la cual se va a deshabilitar el servicio siguiendo el formato yyyy-mm-dd. Ej: 2019-12-24", "Deshabilitar servicio", JOptionPane.QUESTION_MESSAGE);
    		String FechaF=JOptionPane.showInputDialog (this, "Ingrese la fecha hasta la cual se va a deshabilitar el servicio siguiendo el formato yyyy-mm-dd. Ej: 2019-12-24", "Deshabilitar servicio", JOptionPane.QUESTION_MESSAGE);
    		if (servicios != null&& FechaI != null&& FechaF != null)
    		{
    			String [] listaServicios = servicios.split(",");
    			for (int i = 0; i < listaServicios.length; i++) {
					long servicioEliminado= epsAndes.deshabilitarServicio(Long.parseLong(listaServicios[i]));
					if (servicioEliminado == 0)
	        		{
	        			throw new Exception ("No se pudo deshabilitar el servicio con este id: " + listaServicios[i]);
	        		}
    			}
    			
        		String resultado = "En cancelar servicios\n\n";
        		resultado += "Se eliminaron los servicios";
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
	//R13
	public void rehabilitarServicios()
	{
		try 
    	{
    		String servicios = JOptionPane.showInputDialog (this, "Ingrese los id de los servicios que desea rehabilitar separados por comas", "Cancelar Servicios", JOptionPane.QUESTION_MESSAGE);
    		if (servicios != null)
    		{
    			String [] listaServicios = servicios.split(",");
    			for (int i = 0; i < listaServicios.length; i++) {
					long servicioEliminado= epsAndes.rehabilitarServicio(Long.parseLong(listaServicios[i]));
					if (servicioEliminado == 0)
	        		{
	        			throw new Exception ("No se pudo rehabilitar el servicio con este id: " + listaServicios[i]);
	        		}
    			}
    			
        		String resultado = "En cancelar servicios\n\n";
        		resultado += "Se eliminaron los servicios";
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
	
	
	
	
	//R10
	public void registrarCampaña()
	{
		try 
    	{
    		String afiliadosEsperados = JOptionPane.showInputDialog (this, "ingrese el numero de afiliados esperados en la campaña?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
    		String fechaInicial=JOptionPane.showInputDialog (this, "Ingrese la fecha inicial de la campaña siguiendo el formato yyyy-mm-dd. Ej: 2019-12-24", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
    		String fechaFinal=JOptionPane.showInputDialog (this, "Ingrese la fecha final de la campaña siguiendo el formato yyyy-mm-dd. Ej: 2019-12-24", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
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
    				
    			
    			CampañaPrevencion tb = epsAndes.registrarCampañaPrevencion(Integer.parseInt(afiliadosEsperados), Date.valueOf(fechaInicial), Date.valueOf(fechaFinal));
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear esta campaña");
        		}
        		String resultado = "En registrar una campaña\n\n";
        		resultado += "Campaña adicionada exitosamente: " + tb+"con estos servicios:";
        		for (int j = 0; j < serviciosRegistrados.size(); j++) {
        			resultado+= serviciosRegistrados.get(j).getTipo()+"en este horario"+ serviciosRegistrados.get(j).getHorario()+"\n";
					
				}
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
	
	//RC9
	public void consultarLaPrestacionDeServiciosAdmin()
	{
		String Fecha = JOptionPane.showInputDialog (this, "Desea filtrar por fecha?Escriba Si en caso afirmativo, No en caso contrario", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
		String TipoServico=JOptionPane.showInputDialog (this, "Desea filtrar por Tipo de Servcio?Escriba Si en caso afirmativo, No en caso contrario", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		String Ips=JOptionPane.showInputDialog (this, "Desea filtrar por Ips?Escriba Si en caso afirmativo, No en caso contrario", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		
		boolean pFecha=false;
		boolean pIps=false;
		boolean pTipo=false;
		boolean pOrden=false;
		boolean pGroup=false;
		String Fecha1 ="";
		String Fecha2="";
		String tipoSer="";
		String ips="";
		String criterio1="";
		String criterio2="";
		
		if(TipoServico.equalsIgnoreCase("si"))
		{
			pTipo=true;
			tipoSer= JOptionPane.showInputDialog (this, "Ingrese el tipo de servicio", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
		}
		if(Fecha.equalsIgnoreCase("si"))
		{
			pFecha=true;
			Fecha1 = JOptionPane.showInputDialog (this, "Ingrese la fecha inicial", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			Fecha2 = JOptionPane.showInputDialog (this, "Ingrese la fecha inicial", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
		}
		if(Ips.equalsIgnoreCase("si"))
		{
			pIps=true;
			ips= JOptionPane.showInputDialog (this, "Ingrese la ips", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			
		}
		
		String orden=JOptionPane.showInputDialog (this, "deseas ordenar tus resultados?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		
		if(orden.equalsIgnoreCase("si"))
		{
			pOrden=true;
			criterio1= JOptionPane.showInputDialog (this, "Ingrese el criterio con el que vas a ordenar", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			
		}
		String agrupe=JOptionPane.showInputDialog (this, "deseas agrupar tus resultados?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		
		if(orden.equalsIgnoreCase("si"))
		{
			pGroup=true;
			criterio2= JOptionPane.showInputDialog (this, "Ingrese el criterio con el que vas a agrupar", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			
		}
		try {
			List<Usuario>lista=epsAndes.consultaReq9(pFecha, pIps, pTipo, Date.valueOf(Fecha1), Date.valueOf(Fecha2), ips, tipoSer, pOrden, criterio1, pGroup, criterio2);
			String resp="";
			for (int i = 0; i < lista.size(); i++) {
				resp+=lista.get(i).getNombre();
			}
			panelDatos.actualizarInterfaz(resp);
		} catch (Exception e) {
			// TODO: handle exception

			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
		
		
		
	}
	
	public void consultarLaPrestacionDeServiciosAdminReq10()
	{
		String Fecha = JOptionPane.showInputDialog (this, "Desea filtrar por fecha?Escriba Si en caso afirmativo, No en caso contrario", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
		String TipoServico=JOptionPane.showInputDialog (this, "Desea filtrar por Tipo de Servcio?Escriba Si en caso afirmativo, No en caso contrario", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		String Ips=JOptionPane.showInputDialog (this, "Desea filtrar por Ips?Escriba Si en caso afirmativo, No en caso contrario", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		
		boolean pFecha=false;
		boolean pIps=false;
		boolean pTipo=false;
		boolean pOrden=false;
		boolean pGroup=false;
		String Fecha1 ="";
		String Fecha2="";
		String tipoSer="";
		String ips="";
		String criterio1="";
		String criterio2="";
		
		if(TipoServico.equalsIgnoreCase("si"))
		{
			pTipo=true;
			tipoSer= JOptionPane.showInputDialog (this, "Ingrese el tipo de servicio", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
		}
		if(Fecha.equalsIgnoreCase("si"))
		{
			pFecha=true;
			Fecha1 = JOptionPane.showInputDialog (this, "Ingrese la fecha inicial", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			Fecha2 = JOptionPane.showInputDialog (this, "Ingrese la fecha inicial", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
		}
		if(Ips.equalsIgnoreCase("si"))
		{
			pIps=true;
			ips= JOptionPane.showInputDialog (this, "Ingrese la ips", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			
		}
		
		String orden=JOptionPane.showInputDialog (this, "deseas ordenar tus resultados?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		
		if(orden.equalsIgnoreCase("si"))
		{
			pOrden=true;
			criterio1= JOptionPane.showInputDialog (this, "Ingrese el criterio con el que vas a ordenar", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			
		}
		String agrupe=JOptionPane.showInputDialog (this, "deseas agrupar tus resultados?", "Registrar Campaña", JOptionPane.QUESTION_MESSAGE);
		
		if(agrupe.equalsIgnoreCase("si"))
		{
			pGroup=true;
			criterio2= JOptionPane.showInputDialog (this, "Ingrese el criterio con el que vas a agrupar", "Consultar Afiliados", JOptionPane.QUESTION_MESSAGE);
			
		}
		try {
			epsAndes.consultaReq10(pFecha, pIps, pTipo, Date.valueOf(Fecha1), Date.valueOf(Fecha2), ips, tipoSer, pOrden, criterio1, pGroup, criterio2);
			
		} catch (Exception e) {
			// TODO: handle exception

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
		String evento=  arg0.getActionCommand();
		
		if(evento.equals("registrarEps"))
		{
			this.adicionarEps();
		}
		if (evento.equals("registrarMedico")) {
			
			this.adicionarMedico();
		}
		if(evento.equals("registrarUsuario"))
		{
			this.adicionarUsuario();
		}
		if(evento.equals("reservarCampaña"))
		{
			this.registrarCampaña();
		}
		if(evento.equals("cancelarServiciosCampaña"))
		{
			this.cancelarServiciosCampaña();
		}
		if(evento.equals("rehabilitarServicios"))
		{
			this.rehabilitarServicios();;
		}
		if (evento.equals("req9")) {
			this.consultarLaPrestacionDeServiciosAdmin();
		}
		if (evento.equals("req10")) {
			this.consultarLaPrestacionDeServiciosAdminReq10();
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
