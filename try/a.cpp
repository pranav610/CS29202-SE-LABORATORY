#include<bits/stdc++.h>

using namespace std;

typedef pair<int, int> Pair;

class CompareDist
{
public:
    bool operator()(Pair n1,Pair n2) {
        return n1.first > n2.first;
    }
};

int main(){

    priority_queue <Pair, vector<Pair>,CompareDist > p;
    p.push(make_pair(10,8));
    p.push(make_pair(10,6));
    p.push(make_pair(15,4));

    while(!p.empty()){
        cout << p.top().first << " " << p.top().second << endl;
        p.pop();
    }
}