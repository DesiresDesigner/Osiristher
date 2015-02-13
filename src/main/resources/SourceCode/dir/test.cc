#include <iostream>
#include <string>
#include <cstdio>
#include <algorithm>
#include <limits.h>
#include <deque>

#define forn(n) for (int i = 0; i < n; i ++)

using namespace std;

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

    int n;

    cin >> n;

    string *names = new string[n];
    int *left = new int[n];
    int *right = new int[n];
    
    forn(n){
    	cin >> names[i];
    	left[i] = (i + n - 1) % n;
        right[i] = (i + 1) % n;
    }

    forn(n - 3){
        int p;
        cin >> p;
        --p;
        cout << names[left[p]] << " " << names[right[p]] << endl;
        right[left[p]] = right[p];
        left[right[p]] = left[p];
    }

	return 0;
}