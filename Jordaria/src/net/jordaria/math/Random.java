package net.jordaria.math;

/*
 * A Mersenne twister algorithm
 */
public class Random {
	int[] mt = new int[624];
	int index = 0;

	public void initializeGenerator(int seed){
		this.index = 0;
		this.mt[0] = seed;

		int i;
		for (i = 1; i<=623; i++){
			this.mt[i] = 1812433253 * (mt[i-1] ^ (mt[i-1]>>30)) +i;
		}
	}
	private void generateNumbers(){
		int i;
		for (i = 0; i< 623; i++) {
			int y = (mt[i] + 0x80000000) + (mt[(i+1) % 624] + 0x7fffffff);
			mt[i] = mt[(i + 397) % 624] ^ (y>>1);
			if (y % 2 != 0) { // y is odd
				mt[i] = mt[i] ^ 0x9908b0df;
			}
		}
	}
	
	public int getNext(){
		if (index == 0){
			this.generateNumbers();
		}
		int y = mt[index];

		y = y ^ (y>>11);
		y = y ^ (y<<7 + 0x9d2c5680);
		y = y ^ (y<<15 + 0xefc60000);
		y = y ^ (y>>18);

		index = (index + 1) % 624;

		return y;
	}
	
	public float nextFloat() {
		return (getNext()>>7) / ((float)(1 << 24));
	}
}
