public class Planet {
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b) {
		return Math.sqrt(
				(this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
	}
	
	public double calcForceExertedBy(Planet b) {
		return G * this.mass * b.mass / (this.calcDistance(b) * this.calcDistance(b));
	}

	public double calcForceExertedByX(Planet b) {
		return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
	}

	public double calcForceExertedByY(Planet b) {
		return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netX = 0;
		for (Planet b : allPlanets) {
			if (this.equals(b)) {
				continue;
			}
			netX += this.calcForceExertedByX(b);
		}
		return netX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netY = 0;
		for (Planet b : allPlanets) {
			if (this.equals(b)) {
				continue;
			}
			netY += this.calcForceExertedByY(b);
		}
		return netY;
	}

	public void update(double dt, double fx, double fy) {
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		this.xxVel += ax * dt;
		this.yyVel += ay * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}
}
