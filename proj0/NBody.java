
public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        in.readInt();
        double uRadius = in.readDouble();
        return uRadius;
    }
    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int number = in.readInt();
        Planet [] array = new Planet[number];
        for (int i = 0; i < array.length; i++) {
           array[i] = new Planet();
        }
        in.readDouble();
        for (int i = 0; i < number; i++) {
            array[i].xxPos = in.readDouble();
            array[i].yyPos = in.readDouble();
            array[i].xxVel = in.readDouble();
            array[i].yyVel = in.readDouble();
            array[i].mass = in.readDouble();
            array[i].imgFileName = in.readString();
        }
        return array;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet [] all = readPlanets(filename);
        double uRadius = readRadius(filename);
        StdDraw.setScale(-uRadius, uRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (int i = 0; i < all.length; i++) {
            all[i].draw();
        }
        
        StdDraw.enableDoubleBuffering();
        
        //Draw animation
        double t = 0;
        while ( t <= T) { 
            double [] xForces = new double [all.length];
            double [] yForces = new double [all.length];
            for (int i = 0; i < all.length; i ++) {
                xForces[i] = all[i].calcNetForceExertedByX(all);
                yForces[i] = all[i].calcNetForceExertedByY(all);
            }
            for (int i = 0; i < all.length; i++) {
                all[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (int i = 0; i < all.length; i++) {
                all[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        //print universe
        StdOut.printf("%d\n", all.length);
        StdOut.printf("%.2e\n", uRadius);
        for (int i = 0; i < all.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  all[i].xxPos, all[i].yyPos, all[i].xxVel,
                  all[i].yyVel, all[i].mass, all[i].imgFileName);   
        }
    }
}
