#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

string login, logout;

const int N = 1010;

char a[N];

int main(int args, char** argv) {
  cout << "Reading from: " << argv[1] << '\n';
  cout << "Writing to: " << argv[2] << '\n';

  ifstream F(argv[1]);
  ofstream G(argv[2]);

  while (F.getline(a, N, '\n')) {
    string s = "";
    for (int i = 0; i < strlen(a); ++i) {
      s += a[i];
    }

    if (s.find("g.c.MainApplication")!=std::string::npos) {
      for (int i = 0; i < strlen(a); ++i) {
        G << a[i];
      }
      G << '\n';
    }
  }

  F.close();
  G.close();
}
