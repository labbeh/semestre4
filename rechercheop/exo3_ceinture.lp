max : 4*x1+3*x2;

contrainte_cuir: x1 + x2 <= 40;
contrainte_travail: 2*x1 + x2 <= 60;
contrainte_signe: x1>=0; x2>=0;

int x1,x2;
