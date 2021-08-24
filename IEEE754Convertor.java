public class IEEE754Convertor {
	private static String sign;
	private static String exponent;
	private static String mantissa;

	private static String outSign;
	private static double outMantissa;
	private static int outExponent;
	private static double resultNoSide;

	public static void addZero(){
		int len;

		len = exponent.length();
		if (len < 8 ) {
			for (int i = 0; i < 8 - len; i++){
				exponent = exponent + "0";
			}
		}

		len = mantissa.length();
		if (len < 23 ) {
			for (int i = 0; i < 23 - len; i++){
				mantissa = mantissa + "0";
			}
		}
	}
	
	public static boolean checkInteger(){
		if (!isInteger(sign)){
			return false;
		}
		if (!isInteger(exponent)){
			return false;
		}
		for (int i=0; i < mantissa.length(); i++){
			if (!isInteger(mantissa.substring(i, i+1))){
				return false;
			}
		}
		return true;
	}

	public static boolean isInteger(String text){
		try
		{
			Integer.parseInt(text);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean checkBinary(){
		if (!isBinary(Integer.parseInt(sign))){
			return false;
		}
		if (!isBinary(Integer.parseInt(exponent))){
			return false;
		}
		for (int i=0; i < mantissa.length(); i++){
			if (!isBinary(Integer.parseInt(mantissa.substring(i, i+1)))){
				return false;
			}
		}
		return true;
	}

	public static boolean isBinary(int binary){
		while(true){
			if (binary == 0) {
				return true;
			}else{
				if (binary%10 > 1) {
					return false;
				}
				binary = binary/10;
			}
		}
	}

	public static int getDecimal(String binaryStr){
		int decimal = 0;
		int n = binaryStr.length()-1;
		
		for (int i = 0; i < binaryStr.length() ; i++){	
			int temp = Integer.parseInt(binaryStr.substring(i, i+1));
			decimal += temp * Math.pow(2, n);
			n--;
		}
		return decimal;
	}

/*
	public static int getDecimal(int binary){
		int decimal = 0;
		int n = 0;
		while(true){
			if(binary == 0){
				break;
			} else {
				int temp = binary%10;
				decimal += temp*Math.pow(2, n);
				binary = binary/10;
				n++;
			}
		}
		return decimal;
	}
*/

	public static double getFloatingPoint (String binaryStr){
		double decimal = 0;
		int n = -1;
		
		for (int i = 0; i < binaryStr.length(); i++){
			int temp = Integer.parseInt(binaryStr.substring(i, i+1));
			decimal = decimal + temp * Math.pow(2, n);
			n--;
		}
		return decimal;
	}
	
	public static String getHex(String binaryInString){
		String hex = "";
		
		for (int i = 0 ; i <= binaryInString.length()-4 ; i+=4 ){
			hex = hex + Integer.toHexString(Integer.parseInt(binaryInString.substring(i,i+4),2));
		}
		return hex;
	}

	public static void main(String [] args) {

			if (args.length == 3) {
				sign = args[0];
				exponent = args[1];
				mantissa = args[2];

				//Auto add trailing zero
				addZero();

				//Sign:1 Exponent:8 Mantissa:23
				sign = sign.substring(0,1);
				exponent = exponent.substring(0,8);
				mantissa = mantissa.substring(0,23);

				if (checkInteger()){
					if (checkBinary()){
						System.out.println("---------------------------");
						System.out.println("Binary value is: " + sign + " " + exponent + " " + mantissa);
						System.out.println("---------------------------");
					
						if (sign.equals("0")) {
							outSign = "+";
						}else{
							outSign = "-";
						}
						System.out.println("Sign is : " + outSign);

						outExponent = getDecimal(exponent);
						System.out.println("Exponent is : " + outExponent + " (" + outExponent + " - 127 = " + (outExponent - 127) + " )");

						outMantissa = 1 + getFloatingPoint(mantissa);
						System.out.println("Mantissa is : " + outMantissa);

						System.out.println("---------------------------");
						resultNoSide = outMantissa * Math.pow(2, outExponent-127);
						System.out.println(outMantissa + " * 2^" + (outExponent-127) + " = " + resultNoSide);

						System.out.println("---------------------------");
						System.out.println("The Decimal value is : " + outSign + resultNoSide);

						System.out.println("Hex is : " + getHex(sign+exponent+mantissa));
					}else{
						System.out.println("You can enter 0 or 1 only");
					}
				}else{
					System.out.println("You must input integer only.");
				}
			}else{
				System.out.println("You must input 3 number");
			}
	}
};
