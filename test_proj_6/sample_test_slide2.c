int f() {
  int i;
  int j;

/* Block 1 */
L1:
  i = 1;
  if (i <= 10) goto L2;

/* Block 2 */
  i = i + 1;

/* Block 3 */
L2:
  j = 1;

/* Block 4 */
L3:
  if (j > 10) goto L2;

/* Block 5 */
  i = i + 2;
  goto L5;

/* Block 6 */
L4:
  j += 1;

/* Block 7 */
L5:
 if (!i) goto L3;

/* Block 8 */
 if (!i) goto L2;

/* Block 9 */
L6:
 goto L6;

 return 0;
}