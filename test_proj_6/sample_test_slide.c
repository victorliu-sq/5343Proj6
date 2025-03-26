int f() {
  int i;
  int j;
  int t1;
  int t2;
  int t3;
  int t4;
  int t5;
  int t6;
  double a[1000];

  i = 1;

L2:
  j = 1;

L3:
  t1 = 10 * i;
  t2 = t1 + j;
  t3 = 8 * t2;
  t4 = t3 - 88;
  a[t4] = 0.0;
  j = j + 1;

  if (j <= 10) goto L3;

  i = i + 1;
  if (i <= 10) goto L2;

  i = 1;

L13:
  t5 = i - 1;
  t6 = 88 * t5;
  a[t6] = 1.0;
  i = i + 1;
  if (i <= 10) goto L13;

  return 0;
}