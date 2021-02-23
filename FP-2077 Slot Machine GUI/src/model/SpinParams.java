package model;

public enum SpinParams {

		FAST(50, 5),
		DEFAULT(100, 10),
		SLOW(250, 20),
		VERY_SLOW(1000, 50);
		
		private int delay;
		private int turns;
		
		SpinParams(int delay, int turns) {
			this.delay = delay;
			this.turns = turns;
		}
		
		public int getDelay() {
			return delay;
		}
		
		public int getTurns() {
			return turns;
		}
}