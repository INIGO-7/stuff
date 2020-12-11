package exams.parcial20201210;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.function.Predicate;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/** Tabla de datos bidimensional de cualquier tipo para análisis posterior
 * Composición: una serie de cabeceras (columnas) con una serie de filas de datos (objetos) que tienen un dato para cada columna
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Tabla {
	
	// =================================================
	// Atributos
	
	private ArrayList<String> headers; // Nombres de las cabeceras-columnas
	private ArrayList<ArrayList<String>> data; // Datos de la tabla (en el orden de las columnnas), implementados todos como strings
	private ArrayList<Class<?>> types; // Tipos de cada una de las columnas (inferidos de los datos strings)
	
	public static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
	public static SimpleDateFormat sdfDMY = new SimpleDateFormat( "dd/MM/yyyy" );
	public static SimpleDateFormat sdfDM = new SimpleDateFormat( "dd/MM" );

	private static boolean LOG_CONSOLE_CSV = false;  // Log en consola del csv	
	
	// =================================================
	// Métodos

	/** Crea una tabla de datos vacía (sin cabeceras ni datos)
	 */
	public Tabla() {
		headers = new ArrayList<>();
		data = new ArrayList<>();
	}
	
	/** Crea una tabla de datos vacía con cabeceras
	 * @param headers	Nombres de las cabeceras de datos
	 */
	public Tabla( ArrayList<String> headers ) {
		this.headers = headers;
		data = new ArrayList<>();
	}
	
	/** Crea una tabla copia exacta de otra
	 * @param tabla	Tabla origen que se copia
	 */
	public Tabla( Tabla tabla ) {
		headers = new ArrayList<>( tabla.headers );
		types = new ArrayList<>( tabla.types );
		if (tabla.data!=null) {
			data = new ArrayList<>();
			for (ArrayList<String> l : tabla.data) {
				data.add( new ArrayList<String>( l ) );
			}
		}
	}
	
	/** Filtra los datos de una tabla quitando los que no cumplan una condición
	 * @param col	Columna de consulta de la tabla
	 * @param condMantener	Expresión lógica que debe cumplir el valor del campo para que se MANTENGA
	 * @return	Número de filas quitadas
	 */
	public int filtraTablaString( int col, Predicate<String> condMantener ) {
		int borrados = 0;
		for (int fila=size()-1; fila>=0; fila--) {
			String val = get( fila, col );
			if (!condMantener.test( val )) {
				removeRow( fila );
				borrados++;
			}
		}
		return borrados;
	}
	
	/** Ordena los datos de una tabla de acuerdo al valor alfabético de una columna
	 * @param col	Columna de ordenación de la tabla
	 */
	public void ordenaTablaString( int col ) {
		if (data!=null) { // Ordena data
			data.sort( new Comparator<ArrayList<String>>() {
				@Override
				public int compare(ArrayList<String> o1, ArrayList<String> o2) {
					String s1 = o1.get(col);
					String s2 = o2.get(col);
					if (s1==null && s2==null) return 0;
					if (s1==null) return -1;
					if (s2==null) return +1;
					return s1.compareTo(s2);
				}
			});
		}
	}
	
	/** Devuelve la fila donde se encuentra el primer código buscado 
	 * @param codigo	Código que se busca
	 * @param numCols	Columnas que componen el código (concatenadas en ese orden)
	 * @return	Fila del código, -1 si no se encuentra
	 */
	public int searchFila( String codigo, int... numCols ) {
		if (codigo==null || codigo.isEmpty()) return -1;
		for (int fila=0; fila<size(); fila++) {
			String codigoFila = "";
			for (int col : numCols) {
				codigoFila += get( fila, col );
			}
			if (codigo.equals( codigoFila )) {
				return fila;
			}
		}
		return -1;
	}
	
	/** Cambia las cabeceras
	 * @param headers	Nombres de las cabeceras de datos
	 */
	public void setHeaders( ArrayList<String> headers ) {
		this.headers = headers;
	}
	
	/** Cambia una cabecera
	 * @param numCol	Número de columna a modificar
	 * @param header	Nombres de la cabecera de datos de esa columna
	 */
	public void setHeader( int numCol, String header ) {
		headers.set( numCol, header );
	}
	
	/** Añade una columna al final de las existentes
	 * @param header	Nuevo nombre de cabecera para la columna
	 * @param defaultValue	Valor por defecto para asignar a cada fila existente en esa nueva columna
	 */
	public void addColumn( String header, String defaultValue ) {
		headers.add( header );
		if (data!=null)
			for (ArrayList<String> line : data) {
				line.add( defaultValue );
			}
		if (types!=null) {
			types.add( calcDataType( defaultValue ) );
		}
	}
	
	/** Añade una columna en la posición indicada
	 * @param header	Nuevo nombre de cabecera para la columna
	 * @param defaultValue	Valor por defecto para asignar a cada fila existente en esa nueva columna
	 */
	public void addColumn( int posCol, String header, String defaultValue ) {
		headers.add( posCol, header );
		if (data!=null)
			for (ArrayList<String> line : data) {
				line.add( posCol, defaultValue );
			}
		if (types!=null) {
			types.add( posCol, calcDataType( defaultValue ) );
		}
	}
	
	/** Añade una línea de datos al final de la tabla (no hace nada si la tabla es una tabla enlazada a lista)
	 * @param line	New data line
	 */
	public void addDataLine( ArrayList<String> line ) {
		if (data!=null) data.add( line );
	}
	
	/** Chequea si una cabecera particular existe, y devuelve su número de columna
	 * @param header	Nombre de cabecera a encontrar
	 * @param exact	true si el match debe ser exacto, false si vale con que sea parcial
	 * @return	número de la columna de primera cabecera que encaja con el nombre pedido, -1 si no existe ninguna
	 */
	public int getColumnWithHeader( String header, boolean exact ) {
		String headerUp = header.toUpperCase();
		int ret = -1;
		for (int col=0; col<headers.size();col++) {
			if (exact && headers.get(col).equals( header ) || !exact && headers.get(col).toUpperCase().contains( headerUp )) {
				ret = col;
				break;
			}
		}
		return ret;
	}
	
	public ArrayList<String> getHeaders() {
		return headers;
	}
	
	public String getHeader( int col ) {
		return headers.get(col);
	}
	
	/** Devuelve una fila completa de headers separada por tabs
	 * @param prefijo	Prefijo a añadir a las cabeceras
	 * @return	Serie de cabeceras precedidas del prefijo indicado y separadas por tabs y acabadas en tab
	 */
	public String getHeadersTabs( String prefijo ) {
		String ret = "";
		for (int c=0; c<getWidth(); c++) {
			ret += (prefijo + getHeader(c) + "\t");
		}
		return ret;
	}
	
	public ArrayList<Class<?>> getTypes() {
		return types;
	}
	
	public Class<?> getType( int col ) {
		if (types==null || types.size()<=col) return String.class;
		return types.get(col);
	}
	
	/** Devuelve tamaño de la tabla (número de filas de datos)
	 * @return	Número de filas de datos, 0 si no hay ninguno
	 */
	public int size() {
		if (data==null) return 0;
		return data.size();
	}
	
	/**  Devuelve el número de columnas de la tabla
	 * @return	Número de columnas
	 */
	public int getWidth() {
		return headers.size();
	}
	
	/** Elimina una fila de la tabla
	 * @param row	Número de fila a eliminar
	 */
	public void removeRow( int row ) {
		if (data!=null) {
			data.remove( row );
		}
	}

	/** Crea una nueva tabla solo con las columnas indicadas
	 * @param nomCol	Nombres de columnas a mantener de la tabla
	 * @return	Nueva tabla solo con las columnas indicadas, con las mismas filas y valores que la tabla original
	 */
	public Tabla crearTablaConCols( String... nomCol ) {
		ArrayList<Integer> colsADejar = new ArrayList<>();
		for (int i=0;i<getWidth();i++) {
			if (Arrays.asList(nomCol).contains( getHeader(i) )) {  // Añade las columnas cuyo nombre se ha indicado
				colsADejar.add( i );
			}
		}
		Tabla ret = new Tabla();
		for (int i : colsADejar) {  // Recorre solo las columnas cuyo nombre se ha indicado
			ret.addColumn( getHeader(i), "" );
		}
		for (int j=0; j<size(); j++) {
			ArrayList<String> linea = new ArrayList<String>();
			for (int i : colsADejar) {
				linea.add( get( j, getHeader(i) ) );
			}
			ret.addDataLine( linea );
		}
		return ret;
	}
	
	/** Devuelve un valor de dato de la tabla
	 * @param row	Número de fila
	 * @param col	Número de columna
	 * @return	Dato de ese valor
	 */
	public String get( int row, int col ) {
		return data.get( row ).get( col );
	}
	
	/** Devuelve un valor de dato de la tabla
	 * @param row	Número de fila
	 * @param ncol	Nombre exacto de columna
	 * @return	Dato de ese valor
	 */
	public String get( int row, String ncol ) {
		int col = getColumnWithHeader( ncol, true );
		return get( row, col );
	}
	
	/** Devuelve un valor de dato de la tabla en forma de entero
	 * @param row	Número de fila
	 * @param col	Número de columna
	 * @return	Dato de ese valor, -1 si es un entero incorrecto
	 */
	public int getInt( int row, int col ) {
		try {
			return Integer.parseInt( data.get( row ).get( col ) );
		} catch (Exception e) {
			return -1;
		}
	}
	
	/** Devuelve un valor de dato de la tabla en forma de entero
	 * @param row	Número de fila
	 * @param ncol	Nombre exacto de columna
	 * @return	Dato de ese valor, -1 si es un entero incorrecto
	 */
	public int getInt( int row, String ncol ) {
		int col = getColumnWithHeader( ncol, true );
		return getInt( row, col );
	}
	
	/** Devuelve un valor de dato de la tabla en forma de doble
	 * @param row	Número de fila
	 * @param col	Número de columna
	 * @return	Dato de ese valor, NaN si es un doble incorrecto
	 */
	public double getDouble( int row, int col ) {
		try {
			return Double.parseDouble( data.get( row ).get( col ) );
		} catch (Exception e) {
			return Double.NaN;
		}
	}
	
	/** Devuelve un valor de dato de la tabla en forma de doble
	 * @param row	Número de fila
	 * @param ncol	Nombre exacto de columna
	 * @return	Dato de ese valor, NaN si es un doble incorrecto
	 */
	public double getDouble( int row, String ncol ) {
		int col = getColumnWithHeader( ncol, true );
		return getDouble( row, col );
	}
	
	/** Devuelve un valor de dato de la tabla en forma de fecha dd/mm/aaaa
	 * @param row	Número de fila
	 * @param col	Número de columna
	 * @return	Dato de ese valor, null si es una fecha incorrecta
	 */
	public Date getDate( int row, int col ) {
		try {
			try {
				return sdf.parse( data.get( row ).get( col ) );
			} catch (Exception e) {}
			return sdfDMY.parse( data.get( row ).get( col ) );
		} catch (Exception e) {
			return null;
		}
	}
	
	/** Devuelve un valor de dato de la tabla en forma de fecha dd/mm/aaaa
	 * @param row	Número de fila
	 * @param ncol	Nombre exacto de columna
	 * @return	Dato de ese valor, null si es una fecha incorrecta
	 */
	public Date getDate( int row, String ncol ) {
		int col = getColumnWithHeader( ncol, true );
		return getDate( row, col );
	}
	
	/** Modifica un valor de dato de la tabla
	 * @param row	Número de fila
	 * @param col	Número de columna
	 * @param value	Valor a modificar en esa posición
	 */
	public void set( int row, int col, String value ) {
		data.get( row ).set( col, value );
		cambioEnTabla( row, col, row, col );
	}
	
	/** Modifica un valor de dato de la tabla
	 * @param row	Número de fila
	 * @param ncol	Nombre exacto de columna
	 * @param value	Valor a modificar en esa posición
	 */
	public void set( int row, String ncol, String value ) {
		int col = getColumnWithHeader( ncol, true );
		set( row, col, value );
	}
	
	/** Devuelve una fila completa de la tabla
	 * @param row	Número de fila
	 * @return	Lista de valores de esa fila
	 */
	public ArrayList<String> getFila( int row ) {
		return data.get(row);
	}
	
	/** Devuelve una fila completa de la tabla separada por tabs y quitando saltos de línea
	 * @param row	Número de fila
	 * @return	Serie de valores de esa fila separados por tabs y acabados en tab
	 */
	public String getFilaTabs( int row ) {
		String ret = "";
		for (int c=0; c<getWidth(); c++) {
			ret += (get(row,c).replaceAll("\n", "") + "\t");
		}
		return ret;
	}
	
	/** Procesa tabla de datos con los datos ya existentes. Calcula los tipos de datos (inferidos de los valores)
	 * @return	0 si el proceso es correcto, otro valor si se detecta algún error (número de líneas de datos erróneas - no hay el mismo número de datos que número de cabeceras)
	 */
	public int calcTypes() {
		// 1.- calcs errors
		int ok = 0;
		int lin = 1;
		if (data!=null) {
			for (ArrayList<String> line : data) if (line.size()!=headers.size()) {
				ok++;
				if (LOG_CONSOLE_CSV) {
					System.out.println( "Error en línea " + lin + ": " + line.size() + " valores en vez de " + headers.size() );
				}
				lin++;
			}
		}
		// 2.- calcs data types (if not error)
		if (ok>0) return ok;
		types = new ArrayList<>();
		for (int col=0; col<headers.size(); col++) {
			Class<?> type = String.class;
			if (size()>0) {
				type = calcDataType( data.get(0).get(col) );
				if (type!=String.class) { // String is the most general - not more info needed
					for (int row=1; row<data.size(); row++) {
						Class<?> nextType = null;
						nextType = calcDataType( data.get(row).get(col) );
						if (type==null) {
							type = nextType;
						} else {
							if (nextType==String.class) { // String is the most general - not more info needed
								type = String.class;
								break;
							} else {
								if (nextType!=null) {
									if (type!=nextType) {
										if (type==Integer.class && nextType==Double.class) {
											type = Double.class;
										} else if (type==Double.class && nextType==Integer.class) {
											// nothing (type = Double)
										} else { // Dif type: must be consdered as string
											type = String.class;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			types.add( type );
		}
		return ok;
	}
		private Class<?> calcDataType( String value ) {
			if (value==null || value.isEmpty()) return null;
			Class<?> ret = String.class;
			try {
				Integer.parseInt( value );
				ret = Integer.class;
			} catch (NumberFormatException e) {
				try {
					Double.parseDouble( value.replaceAll(",", ".") );
					ret = Double.class;
				} catch (NumberFormatException e2) {
					try {
						sdf.parse( value );
						ret = Date.class;
					} catch (Exception e3) {
					}
				}
			}
			return ret;
		}

		
	/** Informa a la tabla que se ha producido un cambio en los datos internos<br/>
	 * No hace falta hacerlo si se hace directamente con el set de la tabla, pero es necesario si se cambia el modelo enlazado a la tabla ya que de esa forma la tabla no puede saberlo de otra forma
	 * @param fila1	Fila inicial del cambio
	 * @param col1	Columna inicial del cambio
	 * @param fila2	Fila final del cambio (incluida)
	 * @param col2	Columna final del cambio (incluida)
	 */
	public void cambioEnTabla( int fila1, int col1, int fila2, int col2 ) {
		for (int col=col1; col<=col2; col++) {
			if (getTableModel().lListeners==null) {
				System.err.println( "ERROR EN ESCUCHADORES DE TABLA INEXISTENTES!!!" );
				return;
			}
			for (TableModelListener l : getTableModel().lListeners) {
				l.tableChanged( new TableModelEvent( getTableModel(), fila1, fila2, col, TableModelEvent.UPDATE ) );
			}
		}
	}
	
	/** Informa a la tabla que se ha producido un cambio en los datos internos<br/>
	 * No hace falta hacerlo si se hace directamente con el set de la tabla, pero es necesario si se cambia el modelo enlazado a la tabla ya que de esa forma la tabla no puede saberlo de otra forma
	 * @param fila1	Fila del cambio
	 */
	public void cambioEnTabla( int fila ) {
		cambioEnTabla( fila, 0, fila, getWidth() );
	}
	
	/** Informa a la tabla que se ha producido un remove en los datos internos<br/>
	 * @param fila	fila quitada
	 */
	public void filaQuitadaEnTabla( int fila ) {
		for (int col=0; col<getWidth(); col++)
			for (TableModelListener l : getTableModel().lListeners) {
				l.tableChanged( new TableModelEvent( getTableModel(), fila, fila, col, TableModelEvent.DELETE ) );
			}
		cambioEnTabla( fila, 0, fila, getWidth() );
	}
	
	/** Devuelve un listado de las frecuencias de valores en la columna col
	 * @param col	Número de columna de la tabla
	 * @return	Listado de valores, ordenado por frecuencias, con un valor por línea en formato valor <tab> frecuencia. "" si hay cualquier error
	 */
	public String getListadoFrecuencias( int col ) {
		String ret = "";
		if (col<0 || col>=getWidth()) return ret;
		HashMap<String,Contador> mapaFrecs = new HashMap<>();
		for (int row=0; row<size(); row++) {
			if (mapaFrecs.containsKey( get( row, col ) )) {
				mapaFrecs.get( get( row, col ) ).inc();
			} else {
				mapaFrecs.put( get( row, col ), new Contador(1) );
			}
		}
		ArrayList<String> valores = new ArrayList<>();
		for (String valor : mapaFrecs.keySet()) valores.add( valor );
		valores.sort( new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				Contador c1 = mapaFrecs.get( o1 );
				Contador c2 = mapaFrecs.get( o2 );
				int comp = c2.get()-c1.get();
				if (comp==0) comp = o1.compareTo(o2);
				return comp;
			}
		});
		for (String valor : valores) {
			ret += (valor + "\t" + mapaFrecs.get(valor) + "\n");
		}
		return ret;
	}
		
		
	// =================================================
	// toString
		
	@Override
	public String toString() {
		String ret = "";
		boolean ini = true;
		for (String header : headers) {
			if (!ini) ret += "\t";
			ret += header;
			ini = false;
		}
		ret += "\n";
		for (ArrayList<String> lin : data) {
			ini = true;
			for (String val : lin) {
				if (!ini) ret += "\t";
				ret += val;
				ini = false;
			}
			ret += "\n";
		}
		return ret;
	}
	
	// =================================================
	// Métodos estáticos
	
	// Método de carga de tabla desde CSV
	/** Procesa un fichero csv (codificado UTF-8) y lo carga devolviéndolo en una nueva tabla
	 * @param file	Fichero del csv
	 * @return	Nuevo objeto tabla con los contenidos de ese csv
	 * @throws IOException
	 */
	public static Tabla processCSV( File file ) 
	throws IOException // Error de I/O
	{
		Tabla tabla = processCSV( file.toURI().toURL() );
		return tabla;
	}

		private static Character SEPARADOR = null;
	/** Procesa un fichero csv (codificado UTF-8) y lo carga devolviéndolo en una nueva tabla
	 * @param urlCompleta	URL del csv
	 * @param lineaCabs	Por defecto es 1 (número de línea que incluye las cabeceras) pero si es otra se puede indicar (>= 1)
	 * @return	Nuevo objeto tabla con los contenidos de ese csv
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws FileNotFoundException
	 * @throws ConnectException
	 * @return
	 */
	public static Tabla processCSV( URL url, int... lineaCabs ) 
	throws MalformedURLException,  // URL incorrecta 
	 IOException, // Error al abrir conexión
	 UnknownHostException, // servidor web no existente
	 FileNotFoundException, // En algunos servidores, acceso a página inexistente
	 ConnectException // Error de timeout
	{
		SEPARADOR = null;
		int linCab = 1;
		if (lineaCabs.length>0) linCab = lineaCabs[0];
		Tabla table = new Tabla();
		BufferedReader input = null;
		InputStream inStream = null;
		try {
		    URLConnection connection = url.openConnection();
		    connection.setDoInput(true);
		    inStream = connection.getInputStream();
		    input = new BufferedReader(new InputStreamReader( inStream, "UTF-8" ));  // Supone utf-8 en la codificación de texto
		    String line = "";
		    int numLine = 0;
		    while ((line = input.readLine()) != null) {
		    	if (SEPARADOR==null) infiereSeparador( line );
		    	numLine++;
		    	if (LOG_CONSOLE_CSV) System.out.println( numLine + "\t" + line );
		    	try {
			    	ArrayList<String> l = processCSVLine( input, line, numLine );
			    	if (LOG_CONSOLE_CSV) System.out.println( "\t" + l.size() + "\t" + l );
			    	if (numLine==linCab) {
			    		table.setHeaders( l );
			    	} else {
			    		if (!l.isEmpty())
			    			table.addDataLine( l );
			    	}
		    	} catch (StringIndexOutOfBoundsException e) {
		    		/* if (LOG_CONSOLE_CSV) */ System.err.println( "\tError: " + e.getMessage() );
		    	}
		    }
		} finally {
			try {
				inStream.close();
				input.close();
			} catch (Exception e2) {
			}
		}
		table.calcTypes();
		for (int fila=table.size()-1; fila>=0; fila--) {  // Iteramos de arriba abajo porque vamos a ir quitando filas
			if (table.getFila(fila).size()!=table.headers.size()) {
				table.data.remove( fila );
			}
		}
	    return table;
	}
	
		private static void infiereSeparador( String line ) {
			SEPARADOR = Character.MAX_VALUE;
			int numComas = line.length() - line.replaceAll(",", "").length();
			int numPuntoComas = line.length() - line.replaceAll(";", "").length();
			int numDolares = line.length() - line.replaceAll("\\$", "").length();
			if (numComas>numPuntoComas && numComas>numDolares && numComas>5) SEPARADOR = ',';
			else if (numPuntoComas>numComas && numPuntoComas>numDolares && numPuntoComas>5) SEPARADOR = ';';
			else if (numDolares>numComas && numDolares>numPuntoComas && numDolares>5) SEPARADOR = '$';
		}
	
	
	/** Genera un fichero csv (codificado UTF-8) partiendo de los datos actuales de la tabla
	 * @param file	Fichero de salida
	 * @param cabeceras	Cabeceras a generar para el csv
	 * @throws IOException
	 */
	public void generarCSV( File file, String[] cabeceras ) 
	throws IOException // Error de E/S
	{
		if (cabeceras==null) {
			cabeceras = new String[ headers.size() ];
			for (int i=0; i<headers.size(); i++) cabeceras[i] = headers.get(i);
		}
		PrintStream ps = new PrintStream( file, "UTF-8" );
		for (int i=0; i<cabeceras.length-1; i++) {
			ps.print( cabeceras[i] + ";" );
		}
		ps.println( cabeceras[cabeceras.length-1] );
		for (int lin=0; lin<size(); lin++) {
			for (int col=0; col<getWidth()-1; col++) {
				ps.print( quitaPC(get( lin, col )) + ";" );
			}
			ps.println( quitaPC(get( lin, getWidth()-1 )) );
		}
		ps.close();
	}
	
		private String quitaPC( String st ) {
			if (st.contains(";")) {
				return "\"" + st.replaceAll( "\"", "\'" ) + "\"";
			} else {
				return st.replaceAll( "\"", "\'" );
			}
		}
	
		/** Procesa una línea de entrada de csv	
		 * @param input	Stream de entrada ya abierto
		 * @param line	La línea YA LE�?DA desde input
		 * @param numLine	Número de línea ya leída
		 * @return	Lista de strings procesados en el csv. Si hay algún string sin acabar en la línea actual, lee más líneas del input hasta que se acaben los strings o el input
		 * @throws StringIndexOutOfBoundsException
		 */
		public static ArrayList<String> processCSVLine( BufferedReader input, String line, int numLine ) throws StringIndexOutOfBoundsException {
			ArrayList<String> ret = new ArrayList<>();
			int posCar = 0;
			boolean inString = false;
			boolean finString = false;
			String stringActual = "";
			char separador = 0;
			while (line!=null && (posCar<line.length() || line.isEmpty() && posCar==0)) {
				if (line.isEmpty() && posCar==0) {
					if (!inString) return ret;  // Línea vacía
				} else {
					char car = line.charAt( posCar );
					if (car=='"') {
						if (inString) {
							if (nextCar(line,posCar)=='"') {  // Doble "" es un "
								posCar++;
								stringActual += "\"";
							} else {  // " de cierre
								inString = false;
								finString = true;
							}
						} else {  // !inString
							if (stringActual.isEmpty()) {  // " de apertura
								inString = true;
							} else {  // " después de valor - error
								throw new StringIndexOutOfBoundsException( "\" after data in char " + posCar + " of line [" + line + "]" );
							}
						}
					} else if ((SEPARADOR!=Character.MAX_VALUE && car==SEPARADOR) || (SEPARADOR==Character.MAX_VALUE && car==',' || car==';')) {
						if (inString) {  // separador dentro de string
							stringActual += car;
						} else {  // separador que separa valores
							if (separador==0) { // Si no se había encontrado separador hasta ahora
								separador = car;
								ret.add( stringActual );
								stringActual = "";
								finString = false;
							} else { // Si se había encontrado, solo vale el mismo (, o ;)
								if (separador==car) {  // Es un separador
									ret.add( stringActual );
									stringActual = "";
									finString = false;
								} else {  // Es un carácter normal
									if (finString) throw new StringIndexOutOfBoundsException( "Data after string in char " + posCar + " of line [" + line + "]");  // valor después de string - error
									stringActual += car;
								}
							}
						}
					} else {  // Carácter dentro de valor
						if (finString) throw new StringIndexOutOfBoundsException( "Data after string in char " + posCar + " of line [" + line + "]");  // valor después de string - error
						stringActual += car;
					}
					posCar++;
				}
				if (posCar>=line.length() && inString) {  // Se ha acabado la línea sin acabarse el string. Eso es porque algún string incluye salto de línea. Se sigue con la siguiente línea
					line = null;
				    try {
						line = input.readLine();
				    	if (LOG_CONSOLE_CSV) System.out.println( "  " + numLine + " (add)\t" + line );
						posCar = 0;
						stringActual += "\n";
					} catch (IOException e) {}  // Si la línea es null es que el fichero se ha acabado ya o hay un error de I/O
				}
			}
			if (inString) throw new StringIndexOutOfBoundsException( "String not closed in line " + numLine + ": [" + line + "]");
			ret.add( stringActual );
			return ret;
		}

			// Devuelve el siguiente carácter (car 0 si no existe el siguiente carácter)
			private static char nextCar( String line, int posCar ) {
				if (posCar+1<line.length()) return line.charAt( posCar + 1 );
				else return Character.MIN_VALUE;
			}

	
	// =================================================
	// Métodos relacionados con el modelo de tabla (cuando se quiere utilizar esta tabla en una JTable)
	
	private TablaTableModel miModelo = null;
	/** Devuelve un modelo de tabla de este objeto tabla para poderse utilizar como modelo de datos en una JTable
	 * @return	modelo de datos de la tabla
	 */
	public TablaTableModel getTableModel() {
		if (miModelo==null) {
			miModelo = new TablaTableModel();
		}
		return miModelo;
	}
	
	public class TablaTableModel implements TableModel {
		@Override
		public int getRowCount() {
			return size();
		}
		@Override
		public int getColumnCount() {
			return headers.size();
		}
		@Override
		public String getColumnName(int columnIndex) {
			return headers.get(columnIndex);
		}
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class; //  types.get(columnIndex);  si se quisiera tipos específicos
		}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) { // Estas tablas de datos no son editables por defecto
			return false;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return get(rowIndex,columnIndex);
		}
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			for (TableModelListener l : lListeners) {
				l.tableChanged( new TableModelEvent( this, rowIndex, rowIndex, columnIndex, TableModelEvent.UPDATE ) );
			}
		}
		
		private ArrayList<TableModelListener> lListeners = new ArrayList<>();
		@Override
		public void addTableModelListener(TableModelListener l) {
			lListeners.add( l );
		}
		@Override
		public void removeTableModelListener(TableModelListener l) {
			lListeners.remove( l );
		}
		
	}

	
	// =================================================
	// Clases/Métodos relacionados con las operaciones sobre tablas
	
	/** Clase cuyo método run, al ser ejecutado, copia el contenido de la tabla asociada al portapapeles
	 */
	public static class CopyToClipboard implements Runnable {
		private Tabla tabla;
		private VentanaDatos vd;
		public CopyToClipboard( Tabla tabla, VentanaDatos vd ) {
			this.tabla = tabla;
			this.vd = vd;
		}
		@Override
		public void run() {
			String texto = "";
			for (int col=0; col<tabla.getWidth(); col++) texto = texto + tabla.getHeader( col ) + "\t";
			for (int fila=0; fila<tabla.size(); fila++) {
				String mens = "Copiando contenido a portapapeles... " + (fila+1) + " de " + tabla.size();
				if (vd==null) System.out.println( mens ); else vd.setMensaje( mens );
				texto += "\n";
				for (int col=0; col<tabla.getWidth(); col++) {
					if (tabla.getType(col) == Double.class) {
						texto = texto + tabla.get( fila, col ).replaceAll("\\.",",") + "\t";
					} else {
						texto = texto + tabla.get( fila, col ) + "\t";
					}
				}
			}
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents( new StringSelection(texto),null);				
			String mens = "Contenido de tabla copiado al portapapeles.";
			if (vd==null) System.out.println( mens ); else vd.setMensaje( mens );
		}
	}

	
}
