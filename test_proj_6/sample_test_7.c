int f() {
  int x;
  int _t1;
  int _t2;
  x = 0;
  _t1 = x < 0;
  if (!_t1) goto _l1;
  x = 1;
  _t2 = x < 10;
  if (!_t2) goto _l2;
  x = 2;
  goto _l3;
  _l1: x = 3;
  goto _l3;
  _l2: x = 4;
  _l3: return x;
}