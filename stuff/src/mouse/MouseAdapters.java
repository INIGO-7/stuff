package mouse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;

import javax.swing.SwingUtilities;

import swing.Window;

public class MouseAdapters extends MouseAdapter {

	public Cursor cursor;
	private Point posicion;
	
	public MouseAdapters(Canvas c) {
		posicion = new Point();
		actualizarPosicion(c);
		//establecerCursor(Configuracion.rutaCursorDefecto);
	}
	
	public void actualizar(Canvas c) {
		actualizarPosicion(c);
	}
	
	public void dibujar() {
		System.out.println("X : " + obtenerPosicion().getX() + ", Y : " + obtenerPosicion().getY());
	}
	
	private void actualizarPosicion(Canvas c) {
		Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
		
		SwingUtilities.convertPointFromScreen(posicionInicial, c);
		posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
	}
	
	public Point obtenerPosicion() {
		return posicion;
	}

//	public Point obtenerPosicion() {
//		final Point posicion = MouseInfo.getPointerInfo().getLocation();
//
//		SwingUtilities.convertPointFromScreen(posicion, null);
//
//		return posicion;
//	}
//
//	public Rectangle obtenerAreaDeteccion() {
//		final Point posicion = obtenerPosicion();
//
//		return new Rectangle(posicion.x, posicion.y, 1, 1);
//	}
//
//	public void establecerCursor(final String ruta) {
//		final Toolkit configuracion = Toolkit.getDefaultToolkit();
//
//		final BufferedImage iconoInicial = CargadorRecursos.cargarImagen(ruta);
//
//		Configuracion.ladoCursor = iconoInicial.getWidth();
//
//		final Point punta = new Point(0, 0);
//
//		cursor = configuracion.createCustomCursor(iconoInicial, punta, "Cursor");
//	}
	
	public static void main(String[] args) {
		Window w = new Window(500, 200, "mouseAdapters");
		Canvas c = w.getCanvas();
		MouseAdapters m = new MouseAdapters(c);
		while(true) {
			m.actualizar(c);
			m.dibujar();
		}
	}

}