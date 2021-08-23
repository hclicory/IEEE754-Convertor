# IEEE754-Convertor
IEEE754 Convertor

[2021-08-23](#2021-08-23) (Version 1.0)

-------------------------------------------------

## 2021-08-23
### Version 1.0
Feature  
>Input binary and convert to decimal number

Able to input binary with digit:  
Sign : 1 digit  
Exponent : 8 digits  
Mantissa : 23 digits  

Notes:
1. Over the digits limit is not allowed
2. Program will auto add trailing zero if digit is not enough

Example:  
Run:
```
javac IEEE754Convertor.java
java IEEE754Convertor  0 10000101 001011111
```

Result:
```
---------------------------
Binary value is: 0 10000101 00101111100000000000000
---------------------------
Sign is : +
Exponent is : 133 (133 - 127 = 6 )
Mantissa is : 1.185546875
---------------------------
1.185546875 * 2^6 = 75.875
---------------------------
The Decimal value is : +75.875
```
