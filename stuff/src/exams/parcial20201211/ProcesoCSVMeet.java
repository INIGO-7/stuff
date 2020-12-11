package exams.parcial20201210;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

	
/** Proceso de ficheros CSV de meet
 */
public class ProcesoCSVMeet {

	public static void main( String[] s ) {
		preparaVentana();
		// Carga de ficheros (quitar si no se quiere cargar los ficheros de inicio)
		cargaCSVMeet( "src/examen/parc202012/meet2020-10-15.csv" );
		cargaCSVMeet( "src/examen/parc202012/meet2020-10-22.csv" );
		cargaCSVMeet( "src/examen/parc202012/meet2020-10-29.csv" );
		cargaCSVMeet( "src/examen/parc202012/meet2020-11-05.csv" );
	}
	
	// Parte de Drag&Drop
	
    @SuppressWarnings("serial")
	private static TransferHandler handler = new TransferHandler() {
        public boolean canImport(TransferHandler.TransferSupport support) {
            if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                return false;
            }
            return true;
        }
        
        @SuppressWarnings("unchecked")
		public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }
            Transferable t = support.getTransferable();
            try {
                java.util.List<File> l = (java.util.List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
                for (File f : l) {
                	if (f.isFile()) {
                		cargaCSVMeet( f.getAbsolutePath() );
                	}
                }
            } catch (UnsupportedFlavorException e) {
                return false;
            } catch (IOException e) {
                return false;
            }
            return true;
        }
    };


	// PARTE DE LÓGICA DE CSV Y DATOS
	
		private static VentanaDatos newVentanaTabla( VentanaGeneral vg, Tabla tabla, String codTabla, int posX, int posY ) {
			try {
				String tit = codTabla + " (" + tabla.size() + ")";
				VentanaDatos vd = new VentanaDatos( vg, codTabla, tit ); 
				vd.setSize( 600, 450 );
				vd.setTabla( tabla ); 
				vg.addVentanaInterna( vd, codTabla );
				vd.setLocation( posX, posY );
				vd.addBoton( "-> clipboard", new Tabla.CopyToClipboard( tabla, vd ), true );
				vd.addBoton( "guardar", new Guardar( tabla, codTabla ), true );
				vd.setVisible( true );
				try { Thread.sleep( 100 ); } catch (InterruptedException e) {}
				return vd;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
			public static class Guardar implements Runnable {
				private Tabla tabla;
				@SuppressWarnings("unused")
				private String codTabla;
				public Guardar( Tabla tabla, String codTabla ) {
					this.tabla = tabla;
					this.codTabla = codTabla;
				}
				@Override
				public void run() {
					String nomFic = sdf.format( new Date() );
					nomFic = JOptionPane.showInputDialog( ventana, "Introduce nombre de fichero csv para guardar", nomFic ); 
					if (nomFic!=null && !nomFic.isEmpty()) {
						if (!nomFic.toUpperCase().endsWith( ".CSV" )) nomFic += ".csv";
						if (new File(nomFic).exists()) {
							int resp = JOptionPane.showConfirmDialog( ventana, "El fichero indicado ya existe. ¿Sobreescribirlo? ", "Atención", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE );
							if (resp==JOptionPane.CANCEL_OPTION) return; // Cortar
						}
						try {
							tabla.generarCSV( new File( nomFic ), tabla.getHeaders().toArray( new String[0] ) );
						} catch (IOException e) {
							JOptionPane.showMessageDialog( ventana, "Error al generar csv", "Error", JOptionPane.ERROR_MESSAGE );
						}
					}
				}
			}


	private static ArrayList<Tabla> lTablas = new ArrayList<Tabla>();
	private static ArrayList<String> lFechas = new ArrayList<String>();
	private static VentanaGeneral ventana;
	private static final SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd" );

	protected static void preparaVentana() {
		ventana = new VentanaGeneral();
		ventana.setSize( 800, 600 );
		ventana.setEnCierre( new Runnable() { public void run() {  } } );
		ventana.setTitle( "Lectura de datos de meet" );
		ventana.setVisible( true );
		ventana.getJDesktopPane().setTransferHandler( handler );
		ventana.setMensaje( "Arrastra ficheros csv de asistencia al panel", Color.yellow );
	}

	// Utilidades
	
	/** Devuelve la duración en formato de horas - minutos
	 * @param minutos	Minutos de duración
	 * @return	Esa duración en formato <num> h <num> min
	 */
	public static String duracionAHHMM( int minutos ) {
		return (minutos/60) + " h " + (minutos%60) + " min";
	}
	
	/** Calcula los minutos partiendo del string en formato <num> h <num> min
	 * @param duracion	Duración en formato <num> h <num> min
	 * @return	Minutos correspondientes a esa duración
	 */
	public static int calcMinutos( String duracion ) {
		int ret = 0;
		int posiH = duracion.toLowerCase().indexOf( "h" );
		int posiM = duracion.toLowerCase().indexOf( "min" );
		try {
			if (posiH>0) {
				ret = Integer.parseInt( duracion.substring( 0, posiH ).trim()) * 60;
			}
			if (posiM>0) {
				ret = ret + Integer.parseInt( duracion.substring( posiH+1, posiM ).trim() );
			}
		} catch (Exception e) { e.printStackTrace(); }
		return ret;
	}

	
	
		private static int xIni = 0;
		private static int yIni = 0;
		
	protected static void cargaCSVMeet( String fic ) {
		ventana.setMensaje( "Arrastra ficheros csv de asistencia al panel" );
		try {
			// Saca fecha
			Date fecha = null;
			try {
				Pattern patFecha = Pattern.compile( ".*\\d\\d\\d\\d-\\d\\d-\\d\\d" );  // Fecha dddd-dd-dd  (aaaa-mm-dd)
				Matcher m = patFecha.matcher( fic );
				if (m.find()) {
					int fin = m.end();
					String fechaS = fic.substring( fin-10, fin );
					fecha = sdf2.parse( fechaS );
				}
			} catch (Exception e) {}
			
			// Crea la tabla con todos los datos
			Tabla tablaCSV = Tabla.processCSV( new File( fic ).toURI().toURL() );
			VentanaDatos vd = newVentanaTabla( ventana, tablaCSV, "CSV cargado", xIni, yIni );
			lTablas.add( tablaCSV );
			if (fecha==null) {
				vd.setTitle( "Datos de fecha indefinida - se supone hoy: " + sdf.format( new Date() ) );
				lFechas.add( sdf.format( new Date() ) );
			} else {
				vd.setTitle( "Datos de " + sdf.format( fecha ));
				lFechas.add( sdf.format( fecha ) );
			}
			xIni += 10; yIni += 10;
			// System.out.println( tablaCSV.getHeadersTabs( "" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}

