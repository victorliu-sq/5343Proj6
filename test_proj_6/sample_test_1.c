int f() {
    int x;
    int y;
    int _t1;
    int _t2;
    x = 1;
    y = 2;
    _t1 = x < y;
    if (!t1) goto _l2;
    _t2 = x;
    goto _l1;
    _l2:
    _t2 = y;
    _l1:
    return _t2;
}