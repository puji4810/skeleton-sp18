public class NBody {
	private static Planet[] my_planets = null;
	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int n = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[n];
		for (int i = 0; i < n; i++) {
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
					in.readString());
		}
		return planets;
	}

	private static void background(double radius) {
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.show();
	}

	private static void draw() {
		for (Planet planet : my_planets) {
			planet.draw();
		}
	}

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		long time = 0;
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		my_planets = readPlanets(filename);
		double xForces[] = new double[my_planets.length];
		double yForces[] = new double[my_planets.length];
		
		background(radius);
		draw();
		while (time < T) {
			for (int i = 0; i < my_planets.length; i++) {
				xForces[i] = my_planets[i].calcNetForceExertedByX(my_planets);
				yForces[i] = my_planets[i].calcNetForceExertedByY(my_planets);
			}
			for (int i = 0; i < my_planets.length; i++) {
				my_planets[i].update(dt, xForces[i], yForces[i]);
			}
			background(radius);
			draw();
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}
		System.out.println(my_planets.length);
		System.out.println(radius);
		for (int i = 0; i < my_planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					my_planets[i].xxPos, my_planets[i].yyPos, 
					my_planets[i].xxVel,
					my_planets[i].yyVel, my_planets[i].mass, my_planets[i].imgFileName);
		}
	}
}
