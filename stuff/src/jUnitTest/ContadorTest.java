package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exams.parcial20201211.Contador;


public class ContadorTest {
	
	Contador cont;
	
	@Before
	public void setUp() {
		cont = new Contador(0);
	}
			
	
	@Test
	public void testGet() {
		assertEquals(0, cont.get());
	}

	@Test
	public void testInc() {
		
		assertEquals(0, cont.get());
		cont.inc();
		assertEquals(1, cont.get());
	}
	
	@Test
	public void testIncNumero() {
		
		cont.inc(4);
		assertEquals(4, cont.get());
	}


	
}