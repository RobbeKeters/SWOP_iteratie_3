package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.Handler.EditLabelHandler;

public class RegexTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(EditLabelHandler.isCorrectInvocationMessageLabel("a()"));
		assertTrue(EditLabelHandler.isCorrectInvocationMessageLabel("abc(12,34,56)"));
		assertFalse(EditLabelHandler.isCorrectInvocationMessageLabel("a"));
		assertFalse(EditLabelHandler.isCorrectInvocationMessageLabel("a("));
		assertFalse(EditLabelHandler.isCorrectInvocationMessageLabel("a)"));
		assertFalse(EditLabelHandler.isCorrectInvocationMessageLabel("Abc(12,34,56)"));
		assertTrue(EditLabelHandler.isCorrectInvocationMessageLabel(""));
		assertFalse(EditLabelHandler.isCorrectInvocationMessageLabel("a(,)"));
		assertFalse(EditLabelHandler.isCorrectInvocationMessageLabel("a((,))"));
		assertTrue(EditLabelHandler.isCorrectInvocationMessageLabel("ajKjI_0129(jklJKL,uioUIO)"));
	}

}
