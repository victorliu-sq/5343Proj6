int f() {
  int x;
  int y;
  int _t1;
  int _t2;
  x = 0;
  y = 0;

  _l1: _t1 = x < 3;
  if (!_t1) goto _l2;
  x = x + 1;
  goto _l1;

  _l2: _t2 = y < 2;
  if (!_t2) goto _l3;
  y = y + 1;
  goto _l2;

  _l3: return x;
}