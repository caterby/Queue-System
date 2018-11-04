/*Jie Liu Netid:jxl164830  */
public class Compute {
	public static void main(String[] args){
		//double lambda=6;
		double mu = 3;
		double L = 10;
		double m = 2;
		int K = 4;
		//double EN = 0;
		for(int i = 1;i <= 10;i++){
			double EN = 0;
			double lambda = 0.1*m*mu*i;
			double rho = 0.1*i;
		double s = lambda/mu;
		
		double[] p=new double[K+1];
		p[0] = 1/(1+L*s+(L*(L-1)/2)*Math.pow(s, 2)+(L*(L-1)*(L-2)/4)*Math.pow(s, 3)+(L*(L-1)*(L-2)*(L-3)/8)*Math.pow(s, 4));
		p[1] = L*s*p[0];
		p[2] = (L*(L-1)/2)*Math.pow(s, 2)*p[0];
		p[3] = (L*(L-1)*(L-2)/4)*Math.pow(s, 3)*p[0];
		p[4] = (L*(L-1)*(L-2)*(L-3)/8)*Math.pow(s, 4)*p[0];
		//System.out.println(p[0]+p[1]+p[2]+p[3]+p[4]);
		for(int j = 0;j <= 4;j++){
			EN += j*p[j];}
		double lambdaavg = L*lambda*p[0]+(L-1)*lambda*p[1]+(L-2)*lambda*p[2]+(L-3)*lambda*p[3];
		double ET = EN/lambdaavg;
		double U = 0.5*(p[1])+p[2]+p[3]+p[4];
		double Pblock = (L-K)*lambda*p[4]/(lambdaavg+(L-K)*lambda*p[4]);
		System.out.println("Situation for rho = "+ rho+" "+"lambda = "+lambda+" "+"mu = "+mu+" "+"K = "+K);
		System.out.println("E[T]: "+ET);
		System.out.println("E[N]: "+EN);
		System.out.println("Utilization: "+U);
		System.out.println("Blocking probability: "+Pblock);
		System.out.println(" ");
	}}

}
