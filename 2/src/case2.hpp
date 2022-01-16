using namespace std;

long double toRadians(long double degree);
long double corwFlyDist(long double lat1, long double lon1, long double lat2, long double lon2);
void findKNearest(unordered_map<long long, nodes> &nodesList, long long int id);