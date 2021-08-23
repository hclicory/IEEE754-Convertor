public class IEEE754Convertor {
	private static String sign;
	private static String exponent;
	private static String mantissa;
	private static String mantissa1;
	private static String mantissa2;
	private static String mantissa3;

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
		if (	isInteger(sign) &&
			isInteger(exponent) &&
			isInteger(mantissa1) &&
			isInteger(mantissa2) &&
			isInteger(mantissa3)
		){
			return true;
		}else{
			return false;
		}
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
		if (	isBinary(Integer.parseInt(sign)) &&
			isBinary(Integer.parseInt(exponent)) &&
			isBinary(Integer.parseInt(mantissa1)) &&
			isBinary(Integer.parseInt(mantissa2)) &&
			isBinary(Integer.parseInt(mantissa3))
		){
			return true;
		}else{
			return false;
		}
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

	public static int getDecimal(int binary){
		int decimal = 0;
		int n = 0;
		while(true){
			if(binary == 0){
				break;
			} else {
				int temp = binary%10;
				decimal = decimal + (int)(temp*Math.pow(2, n));
				binary = binary/10;
				n++;
			}
		}
		return decimal;
	}

	public static double getFloatingPoint (int binary, int lenStart, int lenEnd){
		double decimal = 0;
		int n = -lenStart;
		while(true){
			if(n == lenEnd){
				break;
			} else {
				int temp = binary%10;
				decimal = decimal + temp*Math.pow(2, n);
				binary = binary/10;
				n++;
			}
		}
		return decimal;
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

				//Split large number
				mantissa1 = mantissa.substring(0,8);
				mantissa2 = mantissa.substring(8,16);
				mantissa3 = mantissa.substring(16,23);

				if (checkBinary()){

					System.out.println("---------------------------");
					System.out.println("Binary value is: " + sign + " " + exponent + " " + mantissa);
					System.out.println("---------------------------");

					if (checkInteger()){
						if (sign.equals("0")) {
							outSign = "+";
						}else{
							outSign = "-";
						}
						System.out.println("Sign is : " + outSign);

						outExponent = getDecimal(Integer.parseInt(exponent));
						System.out.println("Exponent is : " + outExponent + " (" + outExponent + " - 127 = " + (outExponent - 127) + " )");

						outMantissa = getFloatingPoint(Integer.parseInt(mantissa1), 8, 0);
						outMantissa = outMantissa + getFloatingPoint(Integer.parseInt(mantissa2), 16, 8);
						outMantissa = outMantissa + getFloatingPoint(Integer.parseInt(mantissa3), 23, 16);
						outMantissa = 1 + outMantissa;
						System.out.println("Mantissa is : " + outMantissa);

						System.out.println("---------------------------");
						resultNoSide = outMantissa * Math.pow(2, outExponent-127);
						System.out.println(outMantissa + " * 2^" + (outExponent-127) + " = " + resultNoSide);

						System.out.println("---------------------------");
						System.out.println("The Decimal value is : " + outSign + resultNoSide);

					}else{
						System.out.println("You must input integer only.");
					}
				}else{
					System.out.println("You can enter 0 or 1 only");
				}
			}else{
				System.out.println("You must input 3 number");
			}
	}
};
