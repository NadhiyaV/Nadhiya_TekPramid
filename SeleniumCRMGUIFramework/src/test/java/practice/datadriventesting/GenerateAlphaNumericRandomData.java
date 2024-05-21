package practice.datadriventesting;

public class GenerateAlphaNumericRandomData {

	public static void main(String[] args) {
		int n=20;
		String alphanumericstring="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb= new StringBuilder(n);
		for(int i=0;i<n;i++) {
			int index=(int)(alphanumericstring.length()*Math.random());
			sb.append(alphanumericstring.charAt(index));
		}
		System.out.println(sb);
	}

}
