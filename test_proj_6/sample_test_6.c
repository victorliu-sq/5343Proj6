int f() {
  int x;
  int _t1;
  x = 0;
  _l1: _t1 = x < 5;
  if (!_t1) goto _l2;
  x = x + 1;
  goto _l1;
  _l2: return x;
}