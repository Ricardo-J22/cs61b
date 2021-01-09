
/**
 * Planet
 */
public class Planet {
    public static double gravity = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }
    
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet rocinante) {
        return Math.sqrt(Math.pow(this.xxPos - rocinante.xxPos, 2) + Math.pow(this.yyPos - rocinante.yyPos, 2));
    }
    public double calcForceExertedBy(Planet rocinante) {
        return gravity * this.mass * rocinante.mass / (calcDistance(rocinante) * calcDistance(rocinante));
    }
    public double calcForceExertedByX(Planet rocinante) {
        return calcForceExertedBy(rocinante) * (-this.xxPos + rocinante.xxPos) / calcDistance(rocinante);
    }
    public double calcForceExertedByY(Planet rocinante) {
        return calcForceExertedBy(rocinante) * (-this.yyPos + rocinante.yyPos) / calcDistance(rocinante);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double Fnetx = 0.0;
        for (int i = 0; i < allPlanets.length; i++) {
            if(allPlanets[i].equals(this)){
                continue;
            }
            Fnetx += calcForceExertedByX(allPlanets[i]);
        }
        return Fnetx;
        
    } 
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double Fnety = 0.0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (allPlanets[i].equals(this)){
                continue;
            }
            Fnety += calcForceExertedByY(allPlanets[i]);
        }
        return Fnety;
        
        
    } 
    public void update(double time, double Fxx, double Fyy) {
        double ax = Fxx/this.mass;
        double ay = Fyy/this.mass;
        this.xxVel += time * ax;
        this.yyVel += time * ay;
        this.xxPos += time * xxVel;
        this.yyPos += time * yyVel;
    }

    public Planet() {
    }
    public void draw(){
        String filename = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos , filename);
    }
}