using namespace std;

typedef pair<long double, nodes> Pair;

class CompareDist
{
public:
    bool operator()(Pair n1,Pair n2) {
        return n1.first > n2.first;
    }
};

long double toRadians(long double degree);
long double crowFlyDist(long double lat1, long double lon1, long double lat2, long double lon2);
void findKNearest(unordered_map<long long int, nodes> &nodesList, long long int id, int k);