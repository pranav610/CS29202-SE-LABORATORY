using namespace std;

typedef long long int ll;
typedef long double ld;

// a class to store various attributes related to a node
class nodes
{
public:
    string name;
    ll id;
    ld lat;
    ld lon;
};

// this function will print all details related to the node input 
void printNode(nodes input);