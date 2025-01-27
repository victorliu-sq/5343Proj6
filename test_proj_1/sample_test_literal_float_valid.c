int f() {
    double d;

    /* valid decimal double literals */
    d = 0000.0000;
    d = 0000.;
    d = .0000;

    d = 0000.0000f;
    d = 0000.f;
    d = .0000F;

    d = 0e0;
    /* normalized to 123e125 */
    d = 123e+123;
    d = .0e0;
    d = .0e+0;
    d = .0e-0;

    d = 000123456789.987654321e123;
    d = 0.0000000000001E-123f;

    /* valid hexadecimal double literals */
    /* hexadecimal double literals must contain exponential part */
    d = 0x1.0p0;
    d = 0x1.0p10;
    d = 0x.8p-4;
    d = 0x1p+4f;
    d = 0x004P+00400F;

    d = 0x1.fffffffffffffp1023;
    d = 0x0.0000000000001p-1022;
    d = 0x1.0p-1075;
    d = 0x1p1024;
}
