int f()
{
  int res;
  int i;
  int _t2;
  int _t1;
  int n;
  int _t3;
  n = 10;
  i = 1;
  res = 1;
  L1:
  _t1 = i <= n;
  if (!_t1) goto L2;
  _t2=res*i;
  res = _t2;
  _t3=i+1;
  i = _t3;
  goto L1;
  L2:
  return res;
  ;
}
