package thisPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class greatDistanceChecker {

	@Test
	public void test() {
		
		assertEquals((int)1134.8364, (int)new greatCircleDistance().getDistance(21, 31, 51, 53));
	}

}
