# Calculator

CIS 200

Cameron Pilchard<br/>
Alex Whipple<br/>
Eli Forssberg<br/>

## Console Commands
Complex numbers require the following format:
```
[real]:[imag] = real + (i * imag)
```
where 
```
i = √(-1)
```
example:
```diff
0.707:0.707	= 0.707 + i0.707
0:-1		= 0 - i
```
The ':' is a delimiter to separate the imaginary number from the real number.

Mathematical operators are represented as functions.
Operators take on the following format:
```
FUNC(ARG1,ARG2)
```
Functions may be layered to produce desired results.
Here is an example:
```
FUNC(FUNC(ARG1),ARG2)
ADD(1,2,3,4,5,EXP(1))
HYPOT(1,2)

```
If and only if the last character of the expression string is the letter "P", case insensitive, then the result will be displayed in polar form.
Example:
```
EXP(0:1)P -> 1.0 * exp(i1.0)
```
Some functions require as little as one argument, where as some operators (w/ communicative properties) can support infinitely many.
Non-communicative operators will **always** utilize operands left to right.

### Operators
```
|	Oper		|	Num of	|	Arg a#	|	Math	|
|	Tag		|	Args	|	Order	|	Equiv.	|
—————————————————————————————————————————————————————————————————————————
Elementary Operators
	ABS     		1				Absolute Value
	ADD			Inf				Addition
	SUB			2		a1-a2		Subtraction
	MULT			Inf				Multiplication
	DIV			2		a1/a2		Division
 	EXP     		1				Exponential Function (e^x)
	LN       		1				Natural Logarithm (base e)
	POW      		2		a1^a2		Exponentiation
	LOG      		2		base a1		Logarithm (base a1)
						(arg a2)
	FACT			1				Factorial

Trig functions
	SQRT     		1				Square Root Shortcut
	SQ      		1				Square Shortcut
	HYPOT			Inf				Hypotenuse Shortcut
	CIRC			2		a1 = r		sqrt(r^2 - x^2)
						a2 = x
	REAL			1				Real (abscissa)
	IMAG			1				Imaginary (ordinate)
	RAD			1				Convert to Radians
	DEG			1				Convert to Degrees
	
	SIN     		1				Sine
	COS     		1				Cosine
	TAN     		1				Tangent
	SEC     		1				Secant
	CSC     		1				Cosecant
	COT     		1				Cotangent

	ASIN     		1				Arcsine
	ACOS     		1				Arccosine
	ATAN     		1				Arctangent
	ASEC     		1				Arcsecant
	ACSC     		1				Arccosecant
	ACOT     		1				Arccotangent
```
* FACT takes only natural numbers, and RAD/DEG only take real numbers.

**WARNING:**
- It is imperative that make sure you properly close input functions with parentheses. These are used to parse equations.
- Please understand that these functions abide by all mathematical standards. This includes when:
	- Functions are undefined (N/0, log 0)
	- Non-communative operations apply the rightmost operand unto the leftmost operand
