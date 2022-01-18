using namespace std;

typedef pair<ld, nodes> Pair; // Pair to make priority queue

class compareDist // custom comparator for comparing pair such that min heap is created
{
public:
    bool operator()(Pair n1, Pair n2)
    {
        return n1.first > n2.first;
    }
};

ld crowFlyDist(ld lat1, ld lon1, ld lat2, ld lon2);                   // direct distance between two points using latitudes and longitudes
void findKNearest(unordered_map<ll, nodes> &nodesList, ll id, int k); // will return K nearest nodes to the input node