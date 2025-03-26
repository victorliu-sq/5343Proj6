int f() {
  int x;
  int _t1;
  int _t2;
  int _t3;
  int _t4;
  int _t5;
  int _t6;
  int _t7;
  int _t8;
  int _t9;
  x = 0;

  L1:
  _t1 = x < 100;
  if (!_t1) goto L2;

  _t2 = x + 1;
  x = _t2;

  _t3 = x * 2;
  _t4 = _t3 - 5;
  _t5 = _t4 + x;
  _t6 = _t5 / 3;

  _t7 = _t6 * 2;
  _t8 = _t7 + 42;
  _t9 = _t8 - x;

  goto L1;

  L2:
  return x;
}