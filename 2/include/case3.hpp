using namespace std;

void createAdjList(unordered_map<long long int, unordered_map<long long int, long double>> &input, unordered_map<long long int, nodes> &nodesList, vector<ways> waysList);
void dijkstra(unordered_map<long long int, unordered_map<long long int, long double>> &adjList, unordered_map<long long int, nodes> &nodesList, nodes source, nodes dest);
void printPath(unordered_map<long long int, long long int> &parent, unordered_map<long long int, nodes> &nodesList, long long int dest, long long int source);