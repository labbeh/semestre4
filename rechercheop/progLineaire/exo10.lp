max: 900x1 + 700x2 + 500x3;

// voir les pourentages 10%,46%, 100%
0.1x1 + 0.46x2 + x3 >= 0.25;
0.15x1 - 0.21x2 - 0.75x3 <= 0;
5x1 - 7x2 - 25x3 <= 0;


x1 + x2 + x3 <= 18;
// x1 <= 25; car contrainte du dessus plus forte
x2 <= 6;
x3 <= 4;

x1 >= 0;
x2 >= 0;
x3 >= 0;

int x1, x2, x3;
