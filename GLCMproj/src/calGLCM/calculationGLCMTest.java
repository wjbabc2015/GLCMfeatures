package calGLCM;

import static org.junit.Assert.*;

import org.junit.Test;

public class calculationGLCMTest {

	@Test
	public void test() {
		double[][] imageMatrix = {{1,0,0,0,0,0,2,0,0,1},
								{0,1,2,1,2,0,4,0,0,2},
								{0,0,2,2,2,0,2,0,3,0},
								{1,1,0,2,0,2,0,0,1,2},
								{0,1,2,0,3,2,1,1,1,1},
								{0,2,1,2,1,0,1,2,0,0},
								{0,0,3,2,2,2,0,2,1,0},
								{1,2,0,0,3,2,0,1,0,1},
								{0,1,1,0,0,1,1,0,0,0},
								{1,1,2,1,0,0,0,2,0,0}};
	}

}
