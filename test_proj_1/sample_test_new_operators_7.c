double f() {
   double a;
   double b;
   a = 5.0;
   b = 3.0;
   a = a * b + a / b - a % b;
   a += b -= a *= b /= a % 2;
}
