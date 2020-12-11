package jUnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exams.parcial20201211.Contador;

public class ContadorTest {
	
	@Test
	public void testGet() {
		Contador cont = new Contador(0);
		assertEquals(0, cont.get());
	}

	@Test
	public void testInc() {
		Contador cont = new Contador(0);
		assertEquals(0, cont.get());
		cont.inc();
		assertEquals(1, cont.get());
	}
	
	@Test
	public void testIncNumero() {
		Contador cont = new Contador(3);
		cont.inc(4);
		assertEquals(7, cont.get());
	}



}